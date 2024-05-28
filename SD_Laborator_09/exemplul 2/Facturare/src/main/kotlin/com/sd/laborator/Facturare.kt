package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.integration.annotation.Transformer
import java.io.File
import kotlin.math.abs
import kotlin.random.Random
import kotlin.random.nextUInt

@EnableBinding(Processor::class)
@SpringBootApplication
open class FacturareMicroservice {
    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    fun emitereFactura(comandaID: String?): String {
        val dateComanda = File("/home/e/Downloads/labsd09/FIsiere/ComenziClient.txt").readLines()[0].split("|")
        println("Emit factura pentru comanda $comandaID...")
        val nrFactura = abs(dateComanda[0].substring(1,dateComanda[0].length-1).toInt()*2)
        println("S-a emis factura cu nr $nrFactura..")

        File("/home/e/Downloads/labsd09/FIsiere/FacturaComenzi.txt").writeText(dateComanda.toString() + " | "+nrFactura )


        return "$comandaID"
    }
}

fun main(args: Array<String>) {
    runApplication<FacturareMicroservice>(*args)
}