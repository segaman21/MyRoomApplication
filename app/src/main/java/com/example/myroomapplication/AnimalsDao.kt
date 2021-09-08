package com.example.myroomapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalsDao {

    @Query("SELECT * FROM animals_table")
    fun getAllWords(): Flow<List<Animals>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(animals: Animals)

    @Query("DELETE FROM animals_table")
    suspend fun deleteAll()
}