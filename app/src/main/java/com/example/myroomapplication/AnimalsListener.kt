package com.example.myroomapplication

import com.example.myroomapplication.data.Animal

interface AnimalsListener {
    fun delete(id:Int)

    fun update(animal:Animal)
}