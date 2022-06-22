package com.ilhamnurilmi.todonoted.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: TodoEntity)

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getDataTodo(): LiveData<List<TodoEntity?>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getOneDataTodo(id: Long): LiveData<TodoEntity?>

    @Query("DELETE FROM todo")
    fun clearData()
}