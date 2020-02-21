package com.example.encounter_generator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_encounter_parameters.*

const val PARAMETER_NAME = "com.example.encounter_generator.PARAMETERS"
class EncounterParametersActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var currentParameters : EncounterParameters = EncounterParameters(1, 1, "Medium", 1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encounter_parameters)
        setSupportActionBar(ParameterToolbar)
        supportActionBar?.title = "Encounter Parameters"

        val partySizeSpinner : Spinner = findViewById(R.id.PartySizeSpinner)
        ArrayAdapter.createFromResource(this, R.array.average_level_array, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            partySizeSpinner.adapter = adapter
        }

        val partyLevelSpinner : Spinner = findViewById(R.id.PartyLevelSpinner)
        ArrayAdapter.createFromResource(this, R.array.average_level_array, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            partyLevelSpinner.adapter = adapter
        }

        val difficultySpinner : Spinner = findViewById(R.id.DifficultySpinner)
        ArrayAdapter.createFromResource(this, R.array.encounter_challenge_array, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            difficultySpinner.adapter = adapter
        }

        val encounterSizeSpinner : Spinner = findViewById(R.id.EncounterSizeSpinner)
        ArrayAdapter.createFromResource(this, R.array.average_level_array, android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            encounterSizeSpinner.adapter = adapter
        }

        partySizeSpinner.onItemSelectedListener = this
        partyLevelSpinner.onItemSelectedListener = this
        difficultySpinner.onItemSelectedListener = this
        encounterSizeSpinner.onItemSelectedListener = this

    }

    // override to set what happens when option selected for spinners
    override fun onItemSelected(parent : AdapterView<*>, view : View, pos : Int, id : Long) {
        when (parent.id) {
            R.id.PartySizeSpinner -> currentParameters.setPartySize(parent.getItemAtPosition(pos).toString())
            R.id.PartyLevelSpinner -> currentParameters.setPartyLevel(parent.getItemAtPosition(pos).toString())
            R.id.DifficultySpinner -> currentParameters.difficulty = (parent.getItemAtPosition(pos).toString())
            R.id.EncounterSizeSpinner -> currentParameters.setEncounterSize(parent.getItemAtPosition(pos).toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    // Called when use taps "Generate Encounter"
    fun buildEncounterWithParameters(view : View) {
        val intent = Intent(this, EncounterDisplayActivity::class.java).apply {
            putExtra(PARAMETER_NAME, currentParameters)
        }
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
