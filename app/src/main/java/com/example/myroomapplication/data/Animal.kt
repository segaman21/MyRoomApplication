package com.example.myroomapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="animals_table")
data class Animal(
    @PrimaryKey (autoGenerate = true) val id: Int=0,
    @ColumnInfo (name="name") val name:String,
    @ColumnInfo (name="age") val age:String,
    @ColumnInfo (name="breed") val breed: String
)