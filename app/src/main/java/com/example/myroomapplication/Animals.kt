package com.example.myroomapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="animals_table")
data class Animals(
    @PrimaryKey @ColumnInfo (name="id") val id: Int,
    @ColumnInfo (name="name") val name:String,
    @ColumnInfo (name="age") val age:String,
    @ColumnInfo (name="breed") val breed: String
)