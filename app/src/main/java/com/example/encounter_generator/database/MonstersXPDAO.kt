package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MonstersXPDAO {
    @Query("SELECT monster_name FROM monster_xp WHERE experience <= :XP")
    fun getMonstersUnderXPAmount(XP : Int) : List<String>

    @Query("SELECT monster_name FROM monster_xp WHERE experience >= :minXP AND experience <= :maxXP")
    fun getMonstersBetweenXPAmounts(minXP : Int, maxXP : Int) : List<String>

    @Query("SELECT experience FROM monster_xp WHERE monster_name == :name")
    fun getXPforMonster(name : String) : Int
}