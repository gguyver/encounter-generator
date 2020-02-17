package com.example.encounter_generator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "threshold_by_level")
data class Threshold (@ColumnInfo(name = "level") val level : Int,
                      @ColumnInfo(name = "encounter_difficulty") val difficulty : String,
                      @ColumnInfo(name = "experience_threshold") val XP : Int) {
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0

}