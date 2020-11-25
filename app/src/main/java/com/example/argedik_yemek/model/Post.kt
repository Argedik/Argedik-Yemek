package com.example.argedik_yemek.model

import com.google.gson.annotations.SerializedName


data class Yemek(
    @SerializedName("aisim")
    val aisim : String?,
    @SerializedName("amalzeme")
    val amalzeme : String?,
    @SerializedName("asos")
    val asos : String?,
    @SerializedName("gorsel")
    val gorsel : String?,
    @SerializedName("Tarif")
    val Tarif : String?,
){

}

class Post (var kullaniciEmail: String, var kullaniciYorum:String, var gorselUrl:String)