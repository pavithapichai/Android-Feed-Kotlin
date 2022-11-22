package com.example.androidfeed.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    @SerializedName("street")
    var street:String,
    @SerializedName("suite")
    var suite:String,
    @SerializedName("city")
    var city:String,
    @SerializedName("zipcode")
    var zipCode:String,
    @SerializedName("geo")
    var geo:Geo

):Parcelable
