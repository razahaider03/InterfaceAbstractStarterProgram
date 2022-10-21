package InterfaceAbstractExercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int nKey;
    private HashMap <String, ArrayList<String>> answer;

    public EfficientMarkovModel(int nKey) {
        myRandom = new Random();
        this.nKey = nKey;
        answer = new HashMap<>();
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
        HashMap<String, ArrayList<String>> buid = buildMap();
        for(int k=0; k < numChars-nKey; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }

    public HashMap<String,ArrayList<String>> buildMap() {
        int pos = 0;
        String key = myText.substring(pos, nKey);
//        int index = myText.indexOf(key, pos);
        for (int i = nKey; i < myText.length()-nKey; i++) {
            String next = myText.substring(i, i + 1);
            if (!answer.containsKey(key)) {
                ArrayList<String> list = new ArrayList<>();
                list.add(next);
                answer.put(key, list);
                key = key.substring(1) + next;
            }
            else key = key.substring(1) + next;
        }

        return answer;
    }

    public ArrayList<String> getFollows(String key) {

        ArrayList<String> follows = answer.get(key);
//        System.out.println(follows);
        return follows;
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel";
    }

    public void printHashMapInfo() {
        HashMap<String, ArrayList<String>> buid = buildMap();
        System.out.println(answer.keySet());
        System.out.println(answer.size());
        int largest = 0;
        ArrayList<String> largestList = new ArrayList<>();
        for (ArrayList<String> curr : answer.values()) {
            if (curr.size() > largest) {
                largest = curr.size();
                largestList = curr;
            }
        }
        System.out.println(largest);
        System.out.println(largestList);
    }

    public static void main(String[] args) {

    }
}
