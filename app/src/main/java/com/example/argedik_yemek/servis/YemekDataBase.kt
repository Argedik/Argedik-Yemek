package com.example.argedik_yemek.servis

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.argedik_yemek.model.Yemek

@Database(entities = arrayOf(Yemek::class),version=4)
abstract class YemekDataBase : RoomDatabase() {
    abstract fun yemekDao():YemekDAO

    companion object{
        @Volatile private var instance: YemekDataBase? = null
        private val lock =Any()

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
        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //database.execSQL("Yemek bsos")
            }
        }

        @JvmStatic
        fun getInstance(context: Context): YemekDataBase? {
            synchronized(lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        YemekDataBase::class.java, "yemekdatabase")
                        .addMigrations(MIGRATION_3_4)
                        .build()
                }
                return instance
            }
        }
    }
}