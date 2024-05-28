package com.sd.laborator.interfaces

interface LocationSearchInterface {
    var nextService: WeatherForecastInterface
    fun getLocationId(locationName: String): String
}