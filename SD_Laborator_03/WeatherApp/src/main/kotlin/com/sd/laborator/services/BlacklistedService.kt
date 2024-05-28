package com.sd.laborator.services

import com.sd.laborator.interfaces.BlacklistedServiceInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.TimeServiceInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import org.springframework.stereotype.Service
import java.net.URL
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class BlacklistedService : BlacklistedServiceInterface {
    @Autowired
    override lateinit var nextService: LocationSearchInterface
    override fun isBlacklisted(locationName: String): String {
        // codificare parametru URL (deoarece poate conţine caractere speciale)
        val cities = mutableListOf("Berlin", "New York")
        if(cities.contains(locationName))
            return "ZONA INTERZISA"
        return nextService.getLocationId(locationName).toString()
    }
}