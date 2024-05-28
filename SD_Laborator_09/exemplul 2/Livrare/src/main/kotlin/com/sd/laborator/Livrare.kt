package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
@SpringBootApplication
open class LivrareMicroservice {
    @StreamListener(Sink.INPUT)
    fun expediereComanda(comandaID: String) {
        println("S-a expediat urmatoarea comanda: $comandaID")
    }
}

fun main(args: Array<String>) {
    runApplication<LivrareMicroservice>(*args)
}