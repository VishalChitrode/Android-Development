package com.example.mvvmandroomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Notes")
data class DataEntity(
   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "Id")
    var id: Int,
   @ColumnInfo(name = "Date")
    var date: Date,
   @ColumnInfo(name = "Notes")
    var Notes: String) {

}
