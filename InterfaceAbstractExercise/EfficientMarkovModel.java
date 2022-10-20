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

        if (myText == null){
            return null;
        }
        int index = myRandom.nextInt((myText.length()-nKey));
        String key = myText.substring(index, index + nKey);
        for (int i = key.length(); i < myText.length(); i++) {
            String k = myText.substring(i-1, i);
            if (answer.containsKey(k)) {
                ArrayList<String> f = answer.get(k);
                answer.put(key, f);
            }
            else {
                ArrayList<String> f = new ArrayList<>();
                if ((i + 1) < myText.length()) {
                    f.add(myText.substring(i, i + 1));
                    answer.put(key, f);
                }
            }
        }
        return answer;
    }

    @Override
    public String toString() {
        return "EfficientMarkovModel{}";
    }

    public static void main(String[] args) {

    }
}
