package prettyrandom.reddit.stringevolve;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author prettyrandom
 * @version 1.0
 *
 * Better only use short phrases!
 * Only characters from a to z!
 */
public class StringEvolveMain {
    public static ArrayList<Thread> threads = new ArrayList<>();

    public static void main(String[] args){
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Please input a phrase: ");
        String text = scanner.nextLine();
//        text = text.replaceAll("\\s", "");
        System.out.println();

        for( int i=0; i<10; i++ ){
            Thread t = new Thread( new StringEvolver( text, threads ));
            threads.add( t );
            t.start();
        }
    }
}
