package hr.ferit.lv3_zad2.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hr.ferit.lv3_zad2.model.InspiringPerson

@Dao
interface InspiringPersonDao {

    @Insert
    fun insert(inspiringPerson: InspiringPerson)

    @Delete
    fun delete(inspiringPerson: InspiringPerson)

    @Query("SELECT * FROM inspiringPersons")
    fun getAll(): List<InspiringPerson>

    @Query("SELECT * FROM inspiringPersons WHERE id = :id")
    fun getById(id: Long): InspiringPerson
}