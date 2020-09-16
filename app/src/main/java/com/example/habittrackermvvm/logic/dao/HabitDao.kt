package com.example.habittrackermvvm.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.habittrackermvvm.data.models.Habit


@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHabit (habit:Habit)


    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("SELECT * FROM habits_table ORDER BY id DESC")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("DELETE FROM habits_table")
    suspend fun deleteAllHabits()


}