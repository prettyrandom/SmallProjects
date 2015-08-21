package prettyrandom.reddit.currencyconverter;

import java.math.BigDecimal;

/**
 * Created by prettyrandom on 20.08.2015.
 */
public class ConverterMain {
    public static void main(String[] args){
        CurrencyConverter currs = new CurrencyConverter();
        String fromCurrency = null;
        String toCurrency  = null;
        BigDecimal value = null;
        if( args.length == 2 ){
            toCurrency = args[0];
            value = new BigDecimal( args[1] );
        } else if( args.length != 3 ) {
            System.out.println("Please use: SourceCurrency Amount TargetCurrency");
            System.out.print("Possible rates: ");
            currs.printAllRates();
            return;
        } else {
            fromCurrency = args[0];
            toCurrency = args[1];
            value = new BigDecimal( args[2] );
        }

        System.out.println();
//        System.out.println(currs.convertToEuro("EUR", BigDecimal.TEN));
        System.out.println(currs.convertToCurrency( fromCurrency, toCurrency, value ));
    }
}
