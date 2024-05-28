package com.sd.laborator.interfaces

import com.sd.laborator.pojo.Person

interface IAgendaService {
    fun getPerson(id: Int) : Person?
    fun createPerson(person: Person)
    fun deletePerson(id: Int)
    fun updatePerson(id: Int, person: Person)
    fun searchAgenda(userNameFilter: String): List<Person>
    fun searchAgendaAdmin(lastNameFilter: String, firstNameFilter: String, userNameFilter: String): List<Person>
}