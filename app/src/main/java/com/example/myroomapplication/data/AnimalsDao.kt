package com.example.myroomapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalsDao {

    @Query("SELECT * FROM animals_table")
    fun getAllAnimals(): Flow<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(animal: Animal)

    @Query("DELETE FROM animals_table WHERE id=:id")
    suspend fun deleteChosen(id:Int)

    @Query("UPDATE animals_table SET name=:name,age=:age,breed=:breed WHERE id=:id")
    suspend fun update(id:Int,name:String,age:String,breed:String)

}