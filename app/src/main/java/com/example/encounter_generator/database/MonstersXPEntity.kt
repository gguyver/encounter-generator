package com.example.encounter_generator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monsters")
data class MonstersXP(@PrimaryKey @ColumnInfo(name = "id") val id : Int,
                      @ColumnInfo(name = "name") val name : String,
                      @ColumnInfo(name = "health") val HP : String,
                      @ColumnInfo(name = "challenge") val CR : String,
                      @ColumnInfo(name = "XP") val XP : Int)