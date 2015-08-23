package prettyrandom.reddit.stringevolve;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author philla
 * @version 1.0
 */
public class StringEvolver implements Runnable {
    private long generation = 0;

    private String targetWord;
    private String evolvingWord = "";

    private Random random = new Random();
    private ArrayList<Thread> threads;

    public StringEvolver( String target, ArrayList<Thread> threads ){
        targetWord = target;
        generateRandomString();
        this.threads = threads;
    }

    private void generateRandomString(){
        for(int i=0; i<targetWord.length(); i++){
            evolvingWord += getRandomCharacter();
        }
    }

    private char getRandomCharacter(){
        int min = 'a';
        int max = 'z';

        return (char) ( random.nextInt(max - min + 1) + min );
    }

    private void evolve(){
        int chosenChar = random.nextInt( evolvingWord.length() );
        evolvingWord = evolvingWord.substring(0, chosenChar) + getRandomCharacter() + evolvingWord.substring( chosenChar+1 );
        generation++;
    }

    private boolean finalForm(){
        if( ! evolvingWord.equals( targetWord ) )
            return false;
        System.out.println("Evolver " + Thread.currentThread().getId() + " evolved the word " + evolvingWord + " after " + generation + " generations!");
        return true;
    }

    private void killAllOthers(){
        for (Thread thread : threads) {
            if( thread.getId() != Thread.currentThread().getId() )
                thread.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Started evolving for Evolver " + Thread.currentThread().getId());
        while( ! Thread.currentThread().isInterrupted() ){
            evolve();
            if( finalForm() ) {
                killAllOthers();
                return;
            }
        }
    }
}
