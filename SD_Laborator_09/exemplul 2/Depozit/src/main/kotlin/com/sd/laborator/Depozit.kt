package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.integration.annotation.Transformer
import org.springframework.messaging.support.MessageBuilder
import java.io.File
import kotlin.random.Random

@EnableBinding(Processor::class)
@SpringBootApplication
open class DepozitMicroservice {
    companion object {
        var stocProduse: MutableList<Pair<String, Int>> = mutableListOf(
            "Masca protectie" to 100,
            "Vaccin anti-COVID-19" to 20,
            "Combinezon" to 30,
            "Manusa chirurgicala" to 40
        )
    }

    private fun acceptareComanda(identificator: Int): String {
        println("Comanda cu identificatorul $identificator a fost acceptata!")
        val dateComanda = File("/home/e/Downloads/labsd09/FIsiere/ComenziClient.txt").readLines()[0].split("|")
        val produsDeExpediat = dateComanda[2]
        val cantitate = dateComanda[3].substring(1,dateComanda[3].length-1)

        return pregatireColet(produsDeExpediat, cantitate.toInt())
    }

    private fun respingereComanda(identificator: Int): String {
        println("Comanda cu identificatorul $identificator a fost respinsa! Stoc insuficient.")
        return "RESPINSA"
    }

    private fun verificareStoc(produs: String, cantitate: Int): Boolean {
        for (prod in stocProduse)
            if (prod.first in produs && prod.second >= cantitate)
                return true
        return false
    }

    private fun pregatireColet(produs: String, cantitate: Int): String {
        for (prod in stocProduse)
            if (prod.first == produs) {
                val cantitateNoua = prod.second - cantitate
                stocProduse.remove(prod)
                stocProduse.add(Pair(produs,cantitateNoua))

            }
        println("Produsul $produs in cantitate de $cantitate buc. este pregatit de livrare.")
        return "$produs|$cantitate"
    }

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    fun procesareComanda(comandaID: String?): String {
        val dateComanda = File("/home/e/Downloads/labsd09/FIsiere/ComenziClient.txt").readLines()[0].split("|")
        if(comandaID == dateComanda[0])
            println("Procesez comanda cu identificatorul $comandaID...")
        val rezultatProcesareComanda: String = if (verificareStoc(dateComanda[2], dateComanda[3].substring(1,dateComanda[3].length-1).toInt())) {
            acceptareComanda(dateComanda[0].substring(1,dateComanda[0].length-1).toInt())
        } else {
            respingereComanda(dateComanda[0].substring(1,dateComanda[0].length-1).toInt())
        }
        println(rezultatProcesareComanda)
        return "$comandaID"
    }
}

fun main(args: Array<String>) {
    runApplication<DepozitMicroservice>(*args)
}