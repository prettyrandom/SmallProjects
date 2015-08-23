package prettyrandom.reddit.calculateday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author philla
 * @version 1.0
 */
public class CalculateDayMain {

    public static final int[] DAYS_OF_MONTH   = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    public static final String[] DAYS_OF_WEEK = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    public static final String PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})$";

    public static void main( String[] args ){
        Scanner scanner = new Scanner( System.in );
        Pattern p = Pattern.compile( PATTERN );

        String input = scanner.next();
        while( ! input.equals("q") ) {
            Matcher matcher = p.matcher(input);
            if ( ! matcher.matches() ) {
                System.out.println("Please input the date in the format yyyy-MM-dd");
                input = scanner.next();
                continue;
            }

            int year  = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day   = Integer.parseInt(matcher.group(3));

            int result = ((year - 1900) * 365) + (year - 1900) / 4;
            if (isLeapYear(year) && month < 3)
                result--;
            for (int i = 0; i < month - 1; i++)
                result += DAYS_OF_MONTH[i];
            result += day;
            result = result % 7;


            System.out.println( input + " was a " + DAYS_OF_WEEK[result] );
            input = scanner.next();
        }
    }

    public static boolean isLeapYear( int year ){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
