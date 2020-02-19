package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MonstersXPDAO {
    @Query("SELECT monster_name FROM monster_xp WHERE experience <= :XP")
    fun getMonstersUnderXPAmount(XP : Int) : List<String>
}