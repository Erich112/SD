package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.context.annotation.Bean
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import java.io.File
import kotlin.random.Random

@EnableBinding(Source::class)
@SpringBootApplication
open class ClientMicroservice {
    companion object {
        /*
        val listaProduse: List<String> = arrayListOf(
            "Masca protectie",
            "Vaccin anti-COVID-19",
            "Combinezon",
            "Manusa chirurgicala"
        )
        */

        var listaProduse = File("/home/e/Downloads/labsd09/FIsiere/ListaProduse.txt").readLines()
    }

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = [Poller(fixedDelay = "10000", maxMessagesPerPoll = "1")])
    open fun comandaProdus(): () -> Message<String> {
        return {
            /*
            val produsComandat = listaProduse[(0 until listaProduse.size).shuffled()[0]]
            val cantitate: Int = Random.nextInt(1, 100)
            val identitateClient = "Popescu Ion"
            val adresaLivrare = "Codrii Vlasiei nr 14"
            */
            var listaComnezi = File("/home/e/Downloads/labsd09/FIsiere/DateClienti.txt").readLines()
            var comandaCurenta = listaComnezi[0]
            val date = comandaCurenta.split(",")

            val produsComandat = date[2]
            val cantitate = date[3]
            val identitateClient = date[0]
            val adresaLivrare = date[1]

            val mesaj = "$identitateClient|$produsComandat|$cantitate|$adresaLivrare"
            MessageBuilder.withPayload(mesaj).build()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ClientMicroservice>(*args)
}