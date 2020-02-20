package com.example.encounter_generator

data class EncounterParameters (
    var partySize : Int = 1,
    var partyLevel : Int = 1,
    var difficulty : String = "Medium",
    var encounterSize : Int = 1
) {
    fun setPartySize(newSize : String) {
        partySize = newSize.toInt()
    }

    fun setPartyLevel(newLevel : String) {
        partyLevel = newLevel.toInt()
    }

    fun setEncounterSize(newSize : String) {
        encounterSize = newSize.toInt()
    }
}