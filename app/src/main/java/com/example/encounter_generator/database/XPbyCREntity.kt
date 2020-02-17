package com.example.encounter_generator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "xp_by_cr")
data class XPbyCR(@ColumnInfo(name = "experience") val XP : Int,
                  @ColumnInfo(name = "challenge_rating") val CR : String) {
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0
}