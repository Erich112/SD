package com.sd.laborator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.integration.annotation.Transformer
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement
import kotlin.math.absoluteValue
import kotlin.random.Random

@EnableBinding(Processor::class)
@SpringBootApplication
open class ComandaMicroservice {

    private fun pregatireComanda(produs: String, cantitate: Int): Int {
        /*
        try {
            val connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            val statement = connection.createStatement();
            statement.queryTimeout = 30 // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists person")
            statement.executeUpdate("create table person (id integer, name string)")
            statement.executeUpdate("insert into person values(1, 'leo')")
            statement.executeUpdate("insert into person values(2, 'yui')")
            val rs = statement.executeQuery("select * from person")
            while (rs.next()) {
                // read the result set
                println("name = " + rs.getString("name"))
                println("id = " + rs.getInt("id"))
            }
        }
        catch (e: SQLException) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err)
        }
        */
        println("Se pregateste comanda $cantitate x \"$produs\"...")


        return Random.nextInt().absoluteValue
    }

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    fun preluareComanda(comanda: String?): String {
        val (identitateClient, produsComandat, cantitate, adresaLivrare) = comanda!!.split("|")
        println("Am primit comanda urmatoare: ")
        println("$identitateClient | $produsComandat | $cantitate | $adresaLivrare")

        val idComanda = pregatireComanda(produsComandat, cantitate.toInt())
        File("/home/e/Downloads/labsd09/FIsiere/ComenziClient.txt").writeText("$idComanda | $identitateClient | $produsComandat | $cantitate | $adresaLivrare")
        return "$idComanda"
    }
}

fun main(args: Array<String>) {
    runApplication<ComandaMicroservice>(*args)
}