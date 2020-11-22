package com.example.argedik_yemek.model

import com.google.gson.annotations.SerializedName

data class Yemek(
    @SerializedName("isim")
    val besin : String?,

){

}

class Post (var kullaniciEmail: String, var kullaniciYorum:String, var gorselUrl:String)