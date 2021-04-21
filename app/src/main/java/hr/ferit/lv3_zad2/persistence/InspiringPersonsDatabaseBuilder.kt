package hr.ferit.lv3_zad2.persistence

import androidx.room.Room
import hr.ferit.lv3_zad2.InspiringPersonsApp

object InspiringPersonsDatabaseBuilder {

    private var instance: InspiringPersonDatabase? = null

    fun getInstance(): InspiringPersonDatabase {
        synchronized(InspiringPersonDatabase::class) {
            if (instance == null) {
                instance = buildDatabase()
            }
            return instance!!
        }
    }

    private fun buildDatabase(): InspiringPersonDatabase {
        return Room.databaseBuilder(
            InspiringPersonsApp.ApplicationContext, InspiringPersonDatabase::class.java, InspiringPersonDatabase.NAME
        )
            .allowMainThreadQueries()
            .build()
    }
}