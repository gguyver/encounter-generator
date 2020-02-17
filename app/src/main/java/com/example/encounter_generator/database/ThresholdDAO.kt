package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ThresholdDAO {
    @Query("SELECT experience_threshold from threshold_by_level WHERE level = :InputLevel AND encounter_difficulty = :InputDifficulty")
    fun getThresholdXP(InputLevel: Int, InputDifficulty: String) : Int
}