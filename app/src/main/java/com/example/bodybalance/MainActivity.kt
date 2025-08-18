package com.example.bodybalance

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var heightEditText: EditText
    private lateinit var feetInchLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize with ID
        val nameText = findViewById<TextView>(R.id.nameTextView)

        val btnMale = findViewById<MaterialButton>(R.id.btnMale)
        val btnFemale = findViewById<MaterialButton>(R.id.btnFemale)

        val ageEditText = findViewById<EditText>(R.id.ageInput)
        val weightEditText = findViewById<EditText>(R.id.weightInput)

        heightEditText = findViewById(R.id.heightInput)
        feetInchLayout = findViewById(R.id.feetInchLayout)
        val heightInFeet = findViewById<EditText>(R.id.heightInputFeet)
        val heightInInch = findViewById<EditText>(R.id.heightInputInch)

        val weightSpinner = findViewById<Spinner>(R.id.weightSpinner)
        val heightSpinner = findViewById<Spinner>(R.id.heightSpinner)

        val calculateButton = findViewById<AppCompatButton>(R.id.calculateButton)


        // Name Show
        val name = intent.getStringExtra("USER_NAME") ?: ""
        if (name.isNotEmpty()) {
            nameText.text = "Hi $name!"
        } else {
            nameText.text = "Hi!"
        }

        // Select Gender
        val selectGender: String? = null

        btnMale.isSelected = false
        btnFemale.isSelected = false

        btnMale.setBackgroundColor(getColor(R.color.white))
        btnFemale.setBackgroundColor(getColor(R.color.white))

        btnMale.setOnClickListener {
            btnMale.isSelected = true
            btnFemale.isSelected = false

            btnMale.setBackgroundColor(getColor(R.color.button_color))
            btnFemale.setBackgroundColor(getColor(R.color.white))
        }

        btnFemale.setOnClickListener {
            btnFemale.isSelected = true
            btnMale.isSelected = false

            btnFemale.setBackgroundColor(getColor(R.color.button_color))
            btnMale.setBackgroundColor(getColor(R.color.white))
        }

        // Weight Spinner Setup
        val weightUnits = resources.getStringArray(R.array.weight_units)
        val weightAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, weightUnits)
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weightSpinner.adapter = weightAdapter
        weightSpinner.onItemSelectedListener = this

        // Height Spinner Setup
        val heightUtils = resources.getStringArray(R.array.height_units)
        val heightAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, heightUtils)
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        heightSpinner.adapter = heightAdapter
        heightSpinner.onItemSelectedListener = this

        // Calculate Button
        calculateButton.setOnClickListener {
            val age = ageEditText.text.toString()
            val weight = weightEditText.text.toString()

            val selectedWeightUnit = weightSpinner.selectedItem.toString()

            val selectedHeightUnit = heightSpinner.selectedItem.toString()
            val height: String


            if (selectedHeightUnit.equals("Cm", ignoreCase = true)) {
                height = heightEditText.text.toString()
            } else {
                // feet + inch = height
                val feet = heightInFeet.text.toString()
                val inch = heightInInch.text.toString()
                height = "$feet.$inch"
            }
            // Check if any EditText is empty show Toast
            if (age.isEmpty() || weight.isEmpty() || height.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("USER_AGE", age)
                    putExtra("USER_WEIGHT", weight)
                    putExtra("USER_HEIGHT", height)
                    putExtra("WEIGHT_UNIT", selectedWeightUnit)
                    putExtra("HEIGHT_UNIT", selectedHeightUnit)
                }
                startActivity(intent)
            }

        }

    }

    // Spinner Item Select
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        if (parent.id == R.id.heightSpinner) {
            val selectedUnit = parent.getItemAtPosition(position).toString()

            // Set visibility for Ft/Cm
            if (selectedUnit.equals("Ft", ignoreCase = true)) {
                heightEditText.visibility = View.GONE
                feetInchLayout.visibility = View.VISIBLE
            } else {
                heightEditText.visibility = View.VISIBLE
                feetInchLayout.visibility = View.GONE
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}