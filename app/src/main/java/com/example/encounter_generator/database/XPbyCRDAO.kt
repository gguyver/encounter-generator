package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface XPbyCRDAO {
    @Query("SELECT experience FROM xp_by_cr WHERE challenge_rating = :challengeRating")
    fun getXPfromCR(challengeRating : String) : Int
}