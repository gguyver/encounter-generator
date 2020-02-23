package com.example.encounter_generator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "multipliers")
data class Multipliers (@ColumnInfo(name = "multiplier") val multiplier: Double,
                        @PrimaryKey @ColumnInfo(name = "id") val id : Int,
                        @ColumnInfo(name = "maxMonsters") val maxSize: Int,
                        @ColumnInfo(name = "minMonsters") val minSize: Int)
