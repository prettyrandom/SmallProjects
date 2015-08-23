package prettyrandom.reddit.stringevolve;

import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * @author prettyrandom
 * @version 1.0
 */
public class EvolvedString {
    private String s;
    private String evolvedWord;
    private Random random = StringEvolver.random;

    public EvolvedString( String text ){
        s = text;
        evolve();
    }

    public void evolve(){
        int chosenChar = random.nextInt( s.length() );
        evolvedWord = s.substring(0, chosenChar) + StringEvolver.getRandomCharacter() + s.substring( chosenChar+1 );
    }

    public double similarity(String s1) {
        String longer = s1, shorter = evolvedWord;
        if (s1.length() < evolvedWord.length()) { // longer should always have greater length
            longer = evolvedWord; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) / (double) longerLength;
    }

    public String getEvolvedWord() {
        return evolvedWord;
    }
}
