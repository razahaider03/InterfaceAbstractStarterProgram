package InterfaceAbstractExercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int nKey;
    private HashMap <String, ArrayList<String>> map;

    public EfficientMarkovModel(int nKey) {
        myRandom = new Random();
        this.nKey = nKey;
        map = new HashMap<>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt((myText.length()-nKey));
        String key = myText.substring(index, index + nKey);
        sb.append(key);
        map.clear();
        buildMap();
        for(int k=0; k < numChars-nKey; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows == null) {
                break;
            }
            if (follows.size() == 0) {
                break;
            }
            int newIndex = myRandom.nextInt(follows.size());
            String next = follows.get(newIndex);
            sb.append(next);
            key = key.substring(1)+ next;
        }
        return sb.toString();
    }

    public HashMap<String,ArrayList<String>> buildMap() {
        int pos = 0;
        String key = myText.substring(pos, nKey);
            while (pos < myText.length()-nKey) {
            int index = myText.indexOf(key,pos);
            if (index == -1 || index >= (myText.length() - nKey) ) {
                break;
            }
            String next = myText.substring(index +nKey, index + 1 + nKey);
            if (!map.containsKey(key)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(next);
                map.put(key, list);
            }
            else {
                map.get(key).add(next);
            }
            key = key.substring(1)+next;

            pos +=1;
        }

        return map;
    }

    public ArrayList<String> getFollows(String key) {

        ArrayList<String> follows = map.get(key);
        return follows;
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel";
    }

    public void printHashMapInfo() {
//        System.out.println(map);
        System.out.println(map.size());
        int largest = 0;
        for (ArrayList<String> curr : map.values()) {
            if (curr.size() > largest) {
                largest = curr.size();
            }
        }
        System.out.println(largest);
        for (Map.Entry<String,ArrayList<String>> curr: map.entrySet()) {
            if (curr.getValue().size() == largest) {
                System.out.print(curr.getKey() + " ,");
            }
        }
    }

}
