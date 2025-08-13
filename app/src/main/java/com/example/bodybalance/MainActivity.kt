package com.example.bodybalance

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize with ID
        val nameText = findViewById<TextView>(R.id.nameTextView)
        val btnMale = findViewById<MaterialButton>(R.id.btnMale)
        val btnFemale = findViewById<MaterialButton>(R.id.btnFemale)
        val ageEditText = findViewById<EditText>(R.id.ageInput)
        val weightEditText = findViewById<EditText>(R.id.weightInput)
        val heightEditText = findViewById<EditText>(R.id.heightInput)
        val calculateButton = findViewById<AppCompatButton>(R.id.calculateButton)
        val weightSpinner = findViewById<Spinner>(R.id.weightSpinner)
        val heightSpinner = findViewById<Spinner>(R.id.heightSpinner)

        // Name Show
        val name = intent.getStringExtra("USER_NAME") ?: ""
        if (name.isNotEmpty()){
            nameText.text = "Hi $name!"
        }else{
            nameText.text = "Hi!"
        }

        // Weight Spinner Setup
        val weightUnits = resources.getStringArray(R.array.weight_units)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,weightUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weightSpinner.adapter = adapter
        weightSpinner.onItemSelectedListener = this

        // Height Spinner Setup
        val heightUtils = resources.getStringArray(R.array.height_units)
        val heightAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,heightUtils)
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        heightSpinner.adapter = heightAdapter
        heightSpinner.onItemSelectedListener = this

        // Calculate Button
        calculateButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long){
        val selectedUnit = parent.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}