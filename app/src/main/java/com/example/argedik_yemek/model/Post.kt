package com.example.argedik_yemek.model

import com.google.gson.annotations.SerializedName

data class Besin(
    @SerializedName("isim")
    val besin : Besin
){

}

class Post (var kullaniciEmail: String, var kullaniciYorum:String, var gorselUrl:String)