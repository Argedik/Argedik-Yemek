package com.example.argedik_yemek.servis

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.argedik_yemek.model.Yemek

//Database ile ilgili bir değişiklik yaptığımızda versiyonu değiştirmemiz gerekiyor
@Database(entities = arrayOf(Yemek::class),version=4)
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


        @JvmField
        @VisibleForTesting
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Room uses an own database hash to uniquely identify the database
                // Since version 1 does not use Room, it doesn't have the database hash associated.
                // By implementing a Migration class, we're telling Room that it should use the data
                // from version 1 to version 2.
                // If no migration is provided, then the tables will be dropped and recreated.
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }

        @JvmStatic
        fun getInstance(context: Context): YemekDataBase? {
            synchronized(lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        YemekDataBase::class.java, "yemekdatabase")
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
                return instance
            }
        }
    }



}