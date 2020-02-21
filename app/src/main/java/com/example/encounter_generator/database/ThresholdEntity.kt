package com.example.encounter_generator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thresholds")
data class Threshold (@PrimaryKey @ColumnInfo(name = "id") val id : Int,
                      @ColumnInfo(name = "level") val level : Int,
                      @ColumnInfo(name = "difficulty") val difficulty : String,
                      @ColumnInfo(name = "XP") val XP : Int)