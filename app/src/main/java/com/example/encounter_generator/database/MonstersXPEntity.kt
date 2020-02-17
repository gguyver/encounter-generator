package com.example.encounter_generator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monster_xp")
data class MonstersXP(@ColumnInfo(name = "monster_name") val name : String,
                      @ColumnInfo(name = "hit_points") val HP : String,
                      @ColumnInfo(name = "challenge_rating") val CR : String,
                      @ColumnInfo(name = "experience") val XP : Int) {
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0
}