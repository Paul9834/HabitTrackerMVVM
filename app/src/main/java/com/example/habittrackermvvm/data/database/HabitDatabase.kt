package com.example.habittrackermvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittrackermvvm.data.models.Habit
import com.example.habittrackermvvm.logic.dao.HabitDao


@Database (entities = [Habit::class], version = 1, exportSchema = false)
abstract class HabitDatabase : RoomDatabase () {

    abstract fun habitDao() : HabitDao


    companion object {
        @Volatile
        private var INSTANCE: HabitDatabase? = null

        fun getDatabase(context:Context): HabitDatabase {
             val tempInstace = INSTANCE
            if (tempInstace != null) {
                return tempInstace
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }




}