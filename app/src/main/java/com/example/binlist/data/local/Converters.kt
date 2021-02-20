package com.example.binlist.data.local

import androidx.room.TypeConverter
import com.example.binlist.data.entities.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringToResponseResource(value: String): ResponseResource {
        val type = object : TypeToken<ResponseResource>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromResponseResourceToString(worker: ResponseResource): String {
        val type = object : TypeToken<ResponseResource>() {}.type
        return Gson().toJson(worker, type)
    }

    @TypeConverter
    fun fromStringToNumberObject(value: String): NumberObject {
        val type = object : TypeToken<NumberObject>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromNumberObjectToString(worker: NumberObject): String {
        val type = object : TypeToken<NumberObject>() {}.type
        return Gson().toJson(worker, type)
    }

    @TypeConverter
    fun fromStringToBankObject(value: String): BankObject {
        val type = object : TypeToken<BankObject>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromBankObjectToString(worker: BankObject): String {
        val type = object : TypeToken<BankObject>() {}.type
        return Gson().toJson(worker, type)
    }

    @TypeConverter
    fun fromStringToCountryObject(value: String): CountryObject {
        val type = object : TypeToken<CountryObject>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromCountryObjectToString(worker: CountryObject): String {
        val type = object : TypeToken<CountryObject>() {}.type
        return Gson().toJson(worker, type)
    }

    @TypeConverter
    fun fromStringToBinModel(value: String): BinModel {
        val type = object : TypeToken<BinModel>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromBinModelString(worker: BinModel): String {
        val type = object : TypeToken<BinModel>() {}.type
        return Gson().toJson(worker, type)
    }
}