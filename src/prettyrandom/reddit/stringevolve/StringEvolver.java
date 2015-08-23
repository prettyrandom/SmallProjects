package prettyrandom.reddit.stringevolve;

import org.apache.commons.collections.buffer.CircularFifoBuffer;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author prettyrandom
 * @version 1.0
 */
public class StringEvolver implements Runnable {
    private long generation = 0;

    private String targetWord;
    private String evolvingWord = "";

    public static Random random = new Random();
    private ArrayList<Thread> threads;

    private CircularFifoBuffer generations = new CircularFifoBuffer(1000);

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";

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

    public static char getRandomCharacter(){
        return ALPHABET.charAt(random.nextInt(ALPHABET.length()));
    }

    private void evolve(){
        generation++;
        ArrayList<EvolvedString> strings = new ArrayList<>();
        for(int i=0; i<50; i++)
            strings.add( new EvolvedString(evolvingWord) );
        double maxSimilarity = 0;
        String evolvedWord = evolvingWord;
        for( EvolvedString s : strings ){
            double similarity = s.similarity( targetWord );
            if( maxSimilarity < similarity ) {
                maxSimilarity = similarity;
                evolvedWord = s.getEvolvedWord();
            }
        }
        evolvingWord = evolvedWord;
        generations.add(evolvedWord);
    }

    private boolean finalForm(){
        if( ! evolvingWord.equals( targetWord ) )
            return false;
        System.out.println("Evolver " + Thread.currentThread().getId() + " evolved the phrase \"" + evolvingWord + "\" after " + generation + " generations!");
        System.out.println( "Last "+generations.size()+" generations: "+generations);
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
