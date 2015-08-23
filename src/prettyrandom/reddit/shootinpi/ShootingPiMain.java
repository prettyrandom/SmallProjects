package prettyrandom.reddit.shootinpi;

import java.util.Random;
import java.util.Scanner;

/**
 * @author prettyrandom
 * @version 1.0
 */
public class ShootingPiMain {

    public static void main(String[] args){
        Scanner scanner = new Scanner( System.in );
        System.out.println("Please input the square size");
        int length = scanner.nextInt();
        System.out.println("Please input the number of shots");
        int numberOfShots = scanner.nextInt();
        int hitCounter = 0;

        for( int i=0; i<numberOfShots; i++ ){
            Shot s = new Shot( length );
            if( s.hitCircle() )
                hitCounter++;
        }
        System.out.println( ((double)hitCounter/numberOfShots*4) );
    }
}

class Shot{
    double x;
    double y;
    int length;
    Random random = new Random();

    public Shot( int length ){
        this.x = random.nextDouble()*length;
        this.y = random.nextDouble()*length;
        this.length = length;
    }

    public boolean hitCircle(){
        return ( Math.sqrt( (x*x + y*y))) <= length;
    }
}
