package com.example.androidfeed.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
  @SerializedName("id")
  var id:Int,
  @SerializedName("name")
  var name:String,
  @SerializedName("username")
  var userName:String,
  @SerializedName("email")
  var email:String,
  @SerializedName("address")
  var address:Address,
  @SerializedName("phone")
  var phone:String,
  @SerializedName("website")
  var website:String,
  @SerializedName("company")
  var company:Company

):Parcelable
