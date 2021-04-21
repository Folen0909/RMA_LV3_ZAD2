package hr.ferit.lv3_zad2.utilities

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun mutableListToString(value: MutableList<String>): String {
        var quotes = ""
        for(quote in value) quotes += quote + "\n"
        quotes = quotes.trimEnd()
        return quotes
    }

    @TypeConverter
    fun stringToMutableList(value: String): MutableList<String> {
        return value.toString().split("\n").toMutableList()
    }
}