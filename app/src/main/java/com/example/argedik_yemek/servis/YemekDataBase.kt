package com.example.argedik_yemek.servis

import android.content.Context
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.argedik_yemek.model.Yemek
import com.example.argedik_yemek.servis.YemekDataBase.Companion.DB_VERSION

@Database(entities = arrayOf(Yemek::class), version = DB_VERSION)
abstract class YemekDataBase : RoomDatabase() {
    abstract fun yemekDao(): YemekDAO

    // Şu haliyle 1. versiyonla kurması lazım uygulamayı silip tekrar kuralım
    companion object {
        const val DB_VERSION = 2
        const val DB_NAME = "yemekdatabase"

        @Volatile
        private var instance: YemekDataBase? = null

        @Synchronized
        fun getInstance(context: Context): YemekDataBase {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    YemekDataBase::class.java,
                    DB_NAME
                )
                    .addMigrations(MIGRATION_1_TO_2)
                    .build()


            }



            return instance as YemekDataBase
        }

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE Yemek ADD COLUMN adeneme TEXT")
            }

        }

        fun destroyInstance() {
            instance = null
        }
    }


}