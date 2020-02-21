package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MultipliersDAO {
    // need to implement party-size multiplier here as well, using lead/lag SQL function

    // Use for parties 3-5 members
    @Query("SELECT encounter_multiplier FROM encounter_multipliers WHERE :encounterSize >= minimum_encounter_size AND :encounterSize <= maximum_encounter_size")
    fun getNormalMultiplier(encounterSize : Int) : Double

    // Given multiplier, get the next one up
    @Query("SELECT encounter_multiplier FROM encounter_multipliers WHERE  encounter_multiplier > :multiplier ORDER BY encounter_multiplier LIMIT 1")
    fun getNextMultiplier(multiplier : Double) : Double

    @Query("SELECT encounter_multiplier FROM encounter_multipliers WHERE  encounter_multiplier < :multiplier ORDER BY encounter_multiplier DESC LIMIT 1")
    fun getPrevMultiplier(multiplier : Double) : Double

//    // Use for parties <3 members
//    @Query("SELECT LAG(encounter_multiplier, 1, 1) FROM encounter_multipliers WHERE :encounterSize >= minimum_encounter_size AND :encounterSize <= maximum_encounter_size")
//    fun getSmallPartyMultiplier(encounterSize : Int) : Double
//
//    // Use for parties >5 members
//    @Query("SELECT LEAD(encounter_multiplier, 1, 1) FROM encounter_multipliers WHERE :encounterSize >= minimum_encounter_size AND :encounterSize <= maximum_encounter_size")
//    fun getLargePartyMultiplier(encounterSize : Int) : Double
}