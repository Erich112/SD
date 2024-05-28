package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
@SpringBootApplication
open class SpringDataFlowTimeSinkApplication {
    @StreamListener(Sink.INPUT)
    fun casetaPrint(date: String) {
        print(" ")
        for (i:Int  in 0..date.length)
        {
            print("_")
        }
        println(" ")
        println("< $date >")
        print(" ")
        for (i:Int  in 0..date.length)
        {
            print("_")
        }
        println(" ")
    }
}

fun main(args: Array<String>) {
    runApplication<SpringDataFlowTimeSinkApplication>(*args)
}