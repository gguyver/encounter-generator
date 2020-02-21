package com.example.encounter_generator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// annotates class to Room database with several tables (Entities)
// Tables:
// - threshold-by-level (single party member XP threshold by level, difficulty)
// - encounter-size-multipliers table (xp multiplier of encounter by size of encounter)
// - monster-xp-table (monsters by XP)
// - xp-by-cr-table

@Database(entities = arrayOf(Threshold::class, Multipliers::class, MonstersXP::class, XPbyCR::class), version = 1, exportSchema = true)
abstract class MonsterRoomDatabase : RoomDatabase() {

    abstract fun ThresholdDAO() : ThresholdDAO
    abstract fun MultipliersDAO() : MultipliersDAO
    abstract fun MonstersXPDAO() : MonstersXPDAO
    abstract fun XPbyCRDAO() : XPbyCRDAO

    companion object {
        // Singleton pattern
        private var INSTANCE: MonsterRoomDatabase? = null

        fun getDatabase(context : Context): MonsterRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, MonsterRoomDatabase::class.java, "monster_database").createFromAsset("app-encounter-data.db").allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}