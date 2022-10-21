package InterfaceAbstractExercise;

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size,int seed) {

        markov.setTraining(text);
		markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){

			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		String test = "This is a test yes this is test";
		int size = 200;
		int seed = 15;

//        MarkovZero mz = new MarkovZero();
//        runModel(mz, st, size,seed);
//
//        MarkovOne mOne = new MarkovOne();
//        runModel(mOne, st, size,seed);
//
//        MarkovModel mThree = new MarkovModel(3);
//        runModel(mThree, st, size,seed);
//
//        MarkovFour mFour = new MarkovFour();
//        runModel(mFour, st, size,seed);

		EfficientMarkovModel ef = new EfficientMarkovModel(3);
		runModel(ef,test,size,seed);
		ef.printHashMapInfo();

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for (String word : words) {
			System.out.print(word + " ");
			psize += word.length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public static void main(String[] args) {
		MarkovRunnerWithInterface runner = new MarkovRunnerWithInterface();
		runner.runMarkov();
	}
	
}
