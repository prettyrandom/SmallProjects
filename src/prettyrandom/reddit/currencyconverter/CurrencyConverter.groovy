package prettyrandom.reddit.currencyconverter

/**
 * Created by prettyrandom on 21.08.2015.
 */
class CurrencyConverter {
    def currencies = [:]

    public CurrencyConverter(){
        currencies = CurrencySlurper.getRates()
        currencies."EUR" = BigDecimal.ONE
    }

    def convertToEuro( String currency, BigDecimal value ){
        if( ! currencies."$currency" ){
            System.out.println("Currency $currency not in System")
            return BigDecimal.ZERO
        }
        return value.divide( currencies."$currency" ?: BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_UP )
    }

    def convertToCurrency( String sourceCurrency, String targetCurrency, BigDecimal value ){
        if( ! currencies."$targetCurrency" ){
            System.out.println("Currency $targetCurrency not in System")
            return BigDecimal.ZERO
        }
        if( sourceCurrency == null )
            sourceCurrency = "EUR"
        BigDecimal result = convertToEuro( sourceCurrency, value ).multiply( currencies."$targetCurrency" ?: BigDecimal.ONE )
        return result.setScale( 2, BigDecimal.ROUND_HALF_UP )
    }

    def printAllRates(){
        currencies.sort().each{ print "${it.key} " }
    }
}
