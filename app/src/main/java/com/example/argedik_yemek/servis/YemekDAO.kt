package com.example.argedik_yemek.servis

import android.content.Context
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.argedik_yemek.model.Yemek

@Dao
interface YemekDAO {
    //Data Access Object
    @Insert
    suspend fun insertAll(vararg yemek:Yemek) : List<Long>

    @Query("SELECT * FROM yemek")
    suspend fun getAllYemek(): List<Yemek>

    @Query("SELECT * FROM yemek WHERE uuid =:yemekId ")
    suspend fun getYemek(yemekId:Int) :Yemek

    @Query("DELETE FROM yemek")
    suspend fun deleteAllYemek()


}