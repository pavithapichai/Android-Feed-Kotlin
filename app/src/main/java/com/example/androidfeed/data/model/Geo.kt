package com.example.androidfeed.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geo(
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("lng")
    var longitude:Double
    ):Parcelable
