package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MonstersXPDAO {
    @Query("SELECT name FROM monsters WHERE XP <= :XP")
    fun getMonstersUnderXPAmount(XP : Int) : List<String>

    @Query("SELECT name FROM monsters WHERE XP >= :minXP AND XP <= :maxXP")
    fun getMonstersBetweenXPAmounts(minXP : Int, maxXP : Int) : List<String>

    @Query("SELECT XP FROM monsters WHERE name == :name")
    fun getXPforMonster(name : String) : Int
}