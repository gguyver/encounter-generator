package com.example.encounter_generator.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MultipliersDAO {
    // need to implement party-size multiplier here as well, using lead/lag SQL function

    // Use for parties 3-5 members
    @Query("SELECT encounter_multiplier FROM encounter_multipliers WHERE :encounterSize >= minimum_encounter_size AND :encounterSize <= maximum_encounter_size")
    fun getNormalMultiplier(encounterSize : Int) : Int

    // Use for parties <3 members
    @Query("SELECT LAG(encounter_multiplier) FROM encounter_multipliers WHERE :encounterSize >= minimum_encounter_size AND :encounterSize <= maximum_encounter_size")
    fun getSmallPartyMultiplier(encounterSize : Int) : Int

    // Use for parties >5 members
    @Query("SELECT LEAD(encounter_multiplier) FROM encounter_multipliers WHERE :encounterSize >= minimum_encounter_size AND :encounterSize <= maximum_encounter_size")
    fun getLargePartyMultiplier(encounterSize : Int) : Int
}