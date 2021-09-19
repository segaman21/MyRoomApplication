package com.example.myroomapplication

import com.example.myroomapplication.database.Animal

interface AnimalsListener {
    fun delete(id:Int)

    fun update(animal:Animal)
}