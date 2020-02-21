package com.example.encounter_generator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge_ratings")
data class XPbyCR(@PrimaryKey @ColumnInfo(name = "id") val id : Int, @ColumnInfo(name = "challenge") val CR: String,
                  @ColumnInfo(name = "XP") val XP : Double)