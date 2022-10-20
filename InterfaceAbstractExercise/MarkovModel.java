package InterfaceAbstractExercise;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {


    private int nKey;

    public MarkovModel(int nKey) {
        myRandom = new Random();
        this.nKey = nKey;
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

    @Override
    public String toString() {
        return "EfficientMarkovModel{}";
    }
}
