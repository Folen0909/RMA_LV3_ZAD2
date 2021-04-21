package hr.ferit.lv3_zad2.model

import androidx.room.*
import hr.ferit.lv3_zad2.utilities.Converters
import java.io.Serializable

@Entity(tableName = "inspiringPersons")
@TypeConverters(Converters::class)
data class InspiringPerson(
 @PrimaryKey(autoGenerate = true) val id: Long,
 @ColumnInfo(name = "name") val name: String,
 @ColumnInfo(name = "date of birth") val dateOfBirth: String,
 @ColumnInfo(name = "date of death") val dateOfDeath: String,
 @ColumnInfo(name = "description") val shortDescription: String,
 @ColumnInfo(name = "image link") val imageLink: String,
 @ColumnInfo(name = "quotes") val quotes: MutableList<String>
) : Serializable