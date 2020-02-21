package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ThresholdDAO {
    @Query("SELECT XP from thresholds WHERE level = :InputLevel AND difficulty = :InputDifficulty")
    fun getThresholdXP(InputLevel: Int, InputDifficulty: String) : Int
}