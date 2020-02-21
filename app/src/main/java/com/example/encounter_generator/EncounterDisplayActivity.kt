package com.example.encounter_generator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.encounter_generator.database.MonsterRoomDatabase

import kotlinx.android.synthetic.main.activity_encounter_display.*

class EncounterDisplayActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encounter_display)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Generated Encounter"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val database = MonsterRoomDatabase.getDatabase(this)

        val encounterParameters : EncounterParameters = intent.getParcelableExtra(PARAMETER_NAME)!!

        var generatedEncounter : EncounterDescription = encounterParameters.generateEncounter(database)

        // Now can populate the fields in Activity
        val viewXP : TextView = findViewById(R.id.XpView)
        viewXP.text = generatedEncounter.XP.toString()

        val viewDiff : TextView = findViewById(R.id.DifficultyView)
        viewDiff.text = generatedEncounter.difficulty

        // Recycler View
        viewManager = LinearLayoutManager(this)
        viewAdapter = DisplayListAdapter(generatedEncounter.Monsters)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

}
