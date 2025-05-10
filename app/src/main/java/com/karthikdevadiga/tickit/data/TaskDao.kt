package com.karthikdevadiga.tickit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TaskDao {

    @Insert
    fun createTask(task:Task)

    @Query("SELECT * FROM Task" )
    fun getAllTasks():List<Task>

    @Update
    fun editask(task:Task)

    @Delete
    fun deletetask(task:Task)

}