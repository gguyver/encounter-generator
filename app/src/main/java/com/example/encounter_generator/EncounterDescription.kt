package com.example.encounter_generator

data class EncounterDescription (
    val numMonsters : Int,
    val Monsters : List<String>,
    val difficulty : String,
    val XP : Int
)