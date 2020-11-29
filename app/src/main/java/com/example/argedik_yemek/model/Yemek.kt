package com.example.argedik_yemek.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
//şu anda entity tablo adı: Yemek
data class Yemek(
    @ColumnInfo(name="aisim")
    @SerializedName("aisim")
    val aisim : String?,
    @ColumnInfo(name="bsos")
    @SerializedName("bsos")
    val bsos : String?,
    @ColumnInfo(name="amalzeme")
    @SerializedName("amalzeme")
    val amalzeme : String?,
    @ColumnInfo(name="asos")
    @SerializedName("asos")
    val asos : String?,
    @ColumnInfo(name="gorsel")
    @SerializedName("gorsel")
    val gorsel : String?,
    @ColumnInfo(name="Tarif")
    @SerializedName("Tarif")
    val Tarif : String?,
    ){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}