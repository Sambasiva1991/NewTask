package com.app.task.Database

import androidx.room.TypeConverter
import com.app.task.Model.Attachments
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList


class Converters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromAttacmentToString(value: String): ArrayList<Attachments> {
        val listType = object : TypeToken<ArrayList<Attachments>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromAttacmentStringToArrayList(list: ArrayList<Attachments>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}