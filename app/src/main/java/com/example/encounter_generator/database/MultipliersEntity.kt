package com.example.encounter_generator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "encounter_multipliers")
data class Multipliers (@ColumnInfo(name = "minimum_encounter_size") val minSize: Int,
                        @ColumnInfo(name = "maximum_encounter_size") val maxSize: Int,
                        @ColumnInfo(name = "encounter_multiplier") val multiplier: Double) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}