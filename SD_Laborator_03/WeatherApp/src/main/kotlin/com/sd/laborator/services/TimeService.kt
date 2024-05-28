package com.sd.laborator.services

import com.sd.laborator.interfaces.TimeServiceInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class TimeService : TimeServiceInterface {
    override fun getCurrentTime():String {
        val formatter =  SimpleDateFormat("dd-MM-yyyy HH:mm")
        return formatter.format(Date())
    }
}