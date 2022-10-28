package com.murillo.helpdeaftoy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Serie::class], version = 1, exportSchema = false)
abstract class SerieDatabase : RoomDatabase() {

    abstract fun serieDao(): SerieDao

    companion object{
        @Volatile
        private var INSTANCE: SerieDatabase? = null

        fun getDatabase(context: Context): SerieDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SerieDatabase::class.java,
                    "serie_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}