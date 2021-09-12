package com.example.myroomapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="animals_table")
data class Animals(
    @PrimaryKey (autoGenerate = true) val id: Int=0,
    @ColumnInfo (name="name") val name:String,
    @ColumnInfo (name="age") val age:String,
    @ColumnInfo (name="breed") val breed: String
)