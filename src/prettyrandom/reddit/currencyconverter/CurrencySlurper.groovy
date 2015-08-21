package prettyrandom.reddit.currencyconverter

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

/**
 * Created by prettyrandom on 21.08.2015.
 */
public class CurrencySlurper {
    static def getRates(){
        HTTPBuilder http = new HTTPBuilder("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml")

        http.request( Method.GET, ContentType.TEXT ) { req ->
            response.success = { resp, reader ->
                def response = new XmlSlurper().parseText( reader.text )
                println "Using rates rom ${response.Cube.Cube.@time}"
                return response.Cube.Cube.Cube.collectEntries{ [it.@currency as String, new BigDecimal( it.@rate as String ) ] } // yeah cube times 3...
            }
        }
    }
}