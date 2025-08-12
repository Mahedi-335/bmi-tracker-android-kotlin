package com.example.bodybalance

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

        val nameText = findViewById<TextView>(R.id.nameTextView)
        val btnMale = findViewById<MaterialButton>(R.id.btnMale)
        val btnFemale = findViewById<MaterialButton>(R.id.btnFemale)
        val ageEditText = findViewById<EditText>(R.id.ageInput)
        val weightEditText = findViewById<EditText>(R.id.weightInput)
        val heightEditText = findViewById<EditText>(R.id.heightInput)
        val calculateButton = findViewById<AppCompatButton>(R.id.calculateButton)
        val weightSpinner = findViewById<Spinner>(R.id.weightSpinner)

        // Name Show
        val name = intent.getStringExtra("USER_NAME") ?: ""
        if (name.isNotEmpty()){
            nameText.text = "Hi $name!"
        }else{
            nameText.text = "Hi!"
        }

        // Weight Spinner Setup
        val units = resources.getStringArray(R.array.weight_units)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weightSpinner.adapter = adapter

        // Listener
        weightSpinner.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long){
        val selectedUnit = parent.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}