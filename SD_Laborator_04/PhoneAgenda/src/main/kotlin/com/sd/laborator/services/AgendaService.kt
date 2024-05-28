package com.sd.laborator.services;

import com.sd.laborator.interfaces.IAgendaService
import com.sd.laborator.interfaces.IEncryptingService
import com.sd.laborator.pojo.Cheltuieli
import com.sd.laborator.pojo.Person
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class AgendaService : IAgendaService {
    companion object {
        val initialAgenda = arrayOf(
            Person(1, "Hello", "Kotlin", "1234","1234".hashCode().toString(), Cheltuieli(10,30,20,40)),
            Person(2, "Hello", "Spring", "5678","5678".hashCode().toString(), Cheltuieli(10,30,220,40)),
            Person(3, "Hello", "Microservice", "9101112","9101112".hashCode().toString(), Cheltuieli(0,0,40,0))
        )
    }

    private val agenda = ConcurrentHashMap<Int, Person>(
        initialAgenda.associateBy { person: Person -> person.id }
    )

    override fun getPerson(id: Int): Person? {
        return agenda[id]
    }

    override fun createPerson(person: Person) {
        agenda[person.id] = person
    }

    override fun deletePerson(id: Int) {
        agenda.remove(id)
    }

    override fun updatePerson(id: Int, person: Person) {
        deletePerson(id)
        createPerson(person)
    }

    override fun searchAgenda(userNameFilter: String): List<Person> {
        return agenda.filter {
                    it.value.userName.lowercase(Locale.getDefault()).contains(userNameFilter, ignoreCase = true)
        }.map {
            it.value
        }.toList()
    }
    override fun searchAgendaAdmin(lastNameFilter: String, firstNameFilter: String, userNameFilter: String): List<Person> {
        return agenda.filter {
            it.value.lastName.lowercase(Locale.getDefault()).contains(lastNameFilter, ignoreCase = true) &&
                    it.value.firstName.lowercase(Locale.getDefault()).contains(firstNameFilter, ignoreCase = true) &&
                    it.value.userName.lowercase(Locale.getDefault()).contains(firstNameFilter, ignoreCase = true)
        }.map {
            it.value
        }.toList()
    }
}
