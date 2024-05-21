package com.example.mvvmandroomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotes(dataEntity: DataEntity)
    @Delete
    suspend fun deleteNotes(dataEntity: DataEntity)
    @Update
    suspend fun updateNotes(dataEntity: DataEntity)
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes():List<DataEntity>

}