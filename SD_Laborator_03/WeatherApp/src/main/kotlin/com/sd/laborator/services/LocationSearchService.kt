package com.sd.laborator.services

import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import org.springframework.stereotype.Service
import java.net.URL
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class LocationSearchService : LocationSearchInterface {
    @Autowired
    override lateinit var nextService: WeatherForecastInterface
    override fun getLocationId(locationName: String): String {
       /*

        // construire obiect de tip URL
        val locationSearchURL = URL("https://wttr.in/$encodedLocationName?format=j1")
        // preluare raspuns HTTP (se face cerere GET şi se preia conţinutul răspunsului sub formă de text)
        val rawResponse: String = locationSearchURL.readText() // parsare obiect JSON
        val responseRootObject = JSONObject("{\"data\": ${rawResponse}}")
        val responseContentObject = responseRootObject.getJSONObject("data").takeUnless { it.isEmpty }
            ?.getJSONArray("request")
        return locationName ?: "NOT_FOUND"*/
        // codificare parametru URL (deoarece poate conţine caractere speciale)
        val encodedLocationName = URLEncoder.encode(locationName, StandardCharsets.UTF_8.toString())
        return nextService.getForecastData(encodedLocationName).toString() ?: "EROARE LOCATION NUJ"
    }
}