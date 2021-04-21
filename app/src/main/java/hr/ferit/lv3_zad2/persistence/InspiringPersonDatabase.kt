package hr.ferit.lv3_zad2.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.ferit.lv3_zad2.model.InspiringPerson

@Database(entities = [InspiringPerson::class], version = 1)
abstract class InspiringPersonDatabase : RoomDatabase() {

    abstract fun inspiringPersonDao(): InspiringPersonDao

    companion object {
        const val NAME = "inspiringPersonsDB"
    }
}