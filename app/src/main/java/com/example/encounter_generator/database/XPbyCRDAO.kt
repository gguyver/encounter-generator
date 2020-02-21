package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface XPbyCRDAO {
    @Query("SELECT XP FROM challenge_ratings WHERE challenge == :challengeRating")
    fun getXPfromCR(challengeRating : String) : Int
}