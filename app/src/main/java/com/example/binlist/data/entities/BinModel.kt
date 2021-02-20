package com.example.binlist.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bins_table")
data class BinModel(
    val responseResource: ResponseResource

) {
    @PrimaryKey(autoGenerate = true)
    var localId: Int = 0
}


class ResponseResource {
    @SerializedName("number")
    var number: NumberObject? = null

    @SerializedName("scheme")
    var scheme: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("brand")
    var brand: String? = null

    @SerializedName("prepaid")
    var prepaid: Boolean? = null

    @SerializedName("country")
    var country: CountryObject? = null

    @SerializedName("bank")
    var bank: BankObject? = null
}

class NumberObject {
    @SerializedName("length")
    var length: Int? = null

    @SerializedName("luhn")
    var luhn: Boolean? = false
}

class CountryObject {

    @SerializedName("numeric")
    var numeric: String? = null

    @SerializedName("alpha2")
    var alpha2: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("emoji")
    var emoji: String? = null

    @SerializedName("currency")
    var currency: String? = null

    @SerializedName("latitude")
    var latitude: Int? = null

    @SerializedName("longitude")
    var longitude: Int? = null
}

class BankObject {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("city")
    var city: String? = null
}