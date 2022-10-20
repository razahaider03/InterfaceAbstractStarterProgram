package InterfaceAbstractExercise;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }

//    protected void setRandom(int seed){
//        myRandom = new Random(seed);
//    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
        int pos = 0;
        ArrayList<String> follows = new ArrayList<>();
        while (pos < myText.length() ) {
            int index = myText.indexOf(key,pos);
            if (index == -1 || index >= (myText.length() - key.length()) ) {
                break;
            }
            String text = myText.substring(index+key.length(),index+key.length()+1);
            follows.add(text);
            pos=index+key.length();
        }
        return follows;
    }

}
