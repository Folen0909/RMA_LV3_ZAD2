package hr.ferit.lv3_zad2.persistence

import hr.ferit.lv3_zad2.model.InspiringPerson

object InspiringPersonRepository {
    private val inspiringPersons = mutableListOf(
        InspiringPerson(0,
            "Dennis Ritchie",
            "9.9.1941.",
            "12.10.2011.",
            "Created the C programming language and developed the UNIX OS. He was awarded with the Turning Award in 1983.",
            "https://upload.wikimedia.org/wikipedia/commons/2/23/Dennis_Ritchie_2011.jpg",
            mutableListOf("UNIX is basically a simple operating system, but you have to be a genius to understand the simplicity",
                "I've done a reasonable amount of travelling, which I enjoyed, but not for too long at a time.")
        )
    )

    fun getAll(): List<InspiringPerson> =
        inspiringPersons
    fun getById(id: Long): InspiringPerson = inspiringPersons.first { it -> it.id == id }
    fun insert(inspiringPerson: InspiringPerson) = inspiringPersons.add(inspiringPerson)
    fun delete(inspiringPerson: InspiringPerson) = inspiringPersons.remove(inspiringPerson)
}