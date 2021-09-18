package com.example.myroomapplication

import com.example.myroomapplication.database.Animals

interface AnimalsListener {
    fun delete(id:Int)

    fun update(animals:Animals)
}