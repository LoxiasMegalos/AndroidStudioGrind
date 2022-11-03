package com.murillo.gradeufabc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Aluno::class, Materia::class], version = 1, exportSchema = false)
abstract class AulasDatabase : RoomDatabase() {

    abstract fun aulasDao(): AulasDao

    companion object{
        @Volatile
        private var INSTANCE: AulasDatabase? = null

        fun getDatabase(context: Context): AulasDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AulasDatabase::class.java,
                    "aulas_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}