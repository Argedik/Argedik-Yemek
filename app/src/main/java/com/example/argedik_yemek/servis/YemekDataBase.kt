package com.example.argedik_yemek.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.argedik_yemek.model.Yemek

//Database ile ilgili bir değişiklik yaptığımızda versiyonu değiştirmemiz gerekiyor
@Database(entities = arrayOf(Yemek::class),version=2)
abstract class YemekDataBase : RoomDatabase() {
    abstract fun yemekDao():YemekDAO

    //Singleton
    companion object{
        @Volatile private var instance: YemekDataBase? = null
        private val lock =Any()

        //instance null değilse instance döndür. Eğer oluşturulmadıysa synchronized başlat ve dayabase oluştur
        //also: bunu yap üstüne şunuda yap
        operator fun invoke(context:Context) = instance?: synchronized(lock){
            instance?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) =Room.databaseBuilder(
            context.applicationContext,
            YemekDataBase::class.java,"yemekdatabase").build()
    }

}