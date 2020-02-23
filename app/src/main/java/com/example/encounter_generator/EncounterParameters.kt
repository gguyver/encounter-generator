package com.example.encounter_generator

import android.os.Parcel
import android.os.Parcelable
import com.example.encounter_generator.database.MonsterRoomDatabase
import com.example.encounter_generator.database.MonstersXPDAO
import com.example.encounter_generator.database.MultipliersDAO
import com.example.encounter_generator.database.ThresholdDAO
import kotlin.random.Random

data class EncounterParameters(
    var partySize: Int = 1,
    var partyLevel: Int = 1,
    var difficulty: String? = "Medium",
    var encounterSize: Int = 1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    )

    fun setPartySize(newSize : String) {
        partySize = newSize.toInt()
    }

    fun setPartyLevel(newLevel : String) {
        partyLevel = newLevel.toInt()
    }

    fun setEncounterSize(newSize : String) {
        encounterSize = newSize.toInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(partySize)
        parcel.writeInt(partyLevel)
        parcel.writeString(difficulty)
        parcel.writeInt(encounterSize)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EncounterParameters> {
        override fun createFromParcel(parcel: Parcel): EncounterParameters {
            return EncounterParameters(parcel)
        }

        override fun newArray(size: Int): Array<EncounterParameters?> {
            return arrayOfNulls(size)
        }
    }

    fun generateEncounter(database : MonsterRoomDatabase) : EncounterDescription {
        // The meat of the encounter generation

        // Determine XP Budget based off party size and levels
        val thresholds = database.ThresholdDAO()
        val experienceBudgets = generateXPBudget(thresholds)

        // Calculate multiplier
        val multiplierData : MultipliersDAO = database.MultipliersDAO()
        val multiplier = getCorrectMultiplier(multiplierData)

        // Calculate minimum and maximum XP-per-monster to get required number of monsters for encounter size and difficulty
        // ASSUMPTION: there is at least one monster in data that can fulfill set requirements
        // TODO: make function where this is not assumed
        var minXP : Int = when(difficulty) {
            "Easy" -> 0
            "Medium" -> (experienceBudgets[0] / multiplier).toInt()
            "Hard" -> (experienceBudgets[1] / multiplier).toInt()
            "Deadly" -> (experienceBudgets[2] / multiplier).toInt()
            else -> 0
        }

        var maxXP : Int = when(difficulty) {
            "Easy" -> (experienceBudgets[0] / multiplier).toInt()
            "Medium" -> (experienceBudgets[1] / multiplier).toInt()
            "Hard" -> (experienceBudgets[2] / multiplier).toInt()
            "Deadly" -> (experienceBudgets[3] / multiplier).toInt()
            else -> 100_000
        }

        // Generate the actual EncounterDescription
        val monsterData : MonstersXPDAO = database.MonstersXPDAO()
        return generateEncounterDescription(monsterData, minXP, maxXP)
    }

    private fun generateEncounterDescription(monsterData: MonstersXPDAO, minXP: Int, maxXP: Int): EncounterDescription {
        var trueXP : Int = 0
        var monsters : ArrayList<String> = ArrayList()
        for (m in 1..encounterSize) {
            // generate a list of monsters that fit the criteria, and randomly decide between
            val possibleMonsters : List<String> = monsterData.getMonstersBetweenXPAmounts(minXP, maxXP)
            if(possibleMonsters.isNotEmpty()) {
                val selectedMonsterPos = Random.nextInt(0, possibleMonsters.size)
                monsters.add(possibleMonsters[selectedMonsterPos])
                trueXP += monsterData.getXPforMonster(possibleMonsters[selectedMonsterPos])
            }
        }
        return EncounterDescription(monsters.size, monsters, difficulty!!, trueXP)
    }

    private fun generateXPBudget(data : ThresholdDAO) : Array<Int> {
        // not actual XP of encounter, as will be multiplied by encounter-multiplier
        val difficulties : Array<String> = arrayOf("Easy", "Medium", "Hard", "Deadly")
        val thresholds : Array<Int> = arrayOf(0, 0, 0, 0)
        for((i, d) in difficulties.withIndex()) {
            var oneXP : Int = data.getThresholdXP(partyLevel, d)
            thresholds[i] = (oneXP * partySize)
        }
        return thresholds
    }

    private fun getCorrectMultiplier(data : MultipliersDAO) : Double {
        var baseMultiplier : Double = data.getNormalMultiplier(encounterSize)
        if(partySize < 3) baseMultiplier = data.getPrevMultiplier(baseMultiplier)
        if(partySize > 6) baseMultiplier = data.getNextMultiplier(baseMultiplier)
        return baseMultiplier
    }

    fun randomize() {
        // Randomises the parameters for use with "I'm Feeling Lucky" button on Main Activity
        partySize = Random.nextInt(1, 10) // more realistic values
        partyLevel = Random.nextInt(1, 20)
        encounterSize = Random.nextInt(1, 10) // more realistic
        val diffs : List<String> = listOf("Easy", "Medium", "Hard", "Deadly")
        difficulty = diffs[Random.nextInt(0, 3)]
    }
}