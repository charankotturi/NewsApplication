package com.example.newsapplication2.database

import androidx.room.TypeConverter
import com.example.newsapplication2.models.Source


class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(string: String): Source {
        return Source(string, string)
    }

}