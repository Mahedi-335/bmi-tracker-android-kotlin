package com.example.bodybalance

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mikhaellopez.circularprogressbar.CircularProgressBar


class ResultActivity : AppCompatActivity() {

    private lateinit var progressbar: CircularProgressBar
    private lateinit var progressbarText: TextView
    private lateinit var weightResultText: TextView
    private lateinit var userAgeText: TextView
    private lateinit var userWeightText: TextView
    private lateinit var userHeightText: TextView

    private lateinit var layoutOne: RelativeLayout
    private lateinit var layoutTwo: RelativeLayout
    private lateinit var layoutThree: RelativeLayout
    private lateinit var layoutFour: RelativeLayout
    private lateinit var layoutFive: RelativeLayout
    private lateinit var layoutSix: RelativeLayout
    private lateinit var layoutSeven: RelativeLayout
    private lateinit var layoutEight: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Finding Views by ID
        progressbar = findViewById(R.id.circleProgressbar)
        progressbarText = findViewById(R.id.circleProgressbarText)

        weightResultText = findViewById(R.id.weightResultText)

        userAgeText = findViewById(R.id.ageTextShow)
        userWeightText = findViewById(R.id.weightTextShow)
        userHeightText = findViewById(R.id.heightTextShow)

        layoutOne = findViewById(R.id.resultOne)
        layoutTwo = findViewById(R.id.resultTwo)
        layoutThree = findViewById(R.id.resultThree)
        layoutFour = findViewById(R.id.resultFour)
        layoutFive = findViewById(R.id.resultFive)
        layoutSix = findViewById(R.id.resultSix)
        layoutSeven = findViewById(R.id.resultSeven)
        layoutEight = findViewById(R.id.resultEight)

        // Get Data from MainActivity
        val age = intent.getStringExtra("USER_AGE")
        val weight = intent.getStringExtra("USER_WEIGHT")
        val height = intent.getStringExtra("USER_HEIGHT")
        val weightUnit = intent.getStringExtra("WEIGHT_UNIT")
        val heightUnit = intent.getStringExtra("HEIGHT_UNIT")

        if (age != null && weight != null && height != null && weightUnit != null && heightUnit != null) {
            // Show Data
            userAgeText.text = age
            userWeightText.text = "$weight $weightUnit"
            userHeightText.text = "$height $heightUnit"

            // Convert Pound to Kg
            val weightInKg: Double = if (weightUnit.equals("Ibs", ignoreCase = true)) {
                weight.toDouble() * 0.453592
            } else {
                weight.toDouble()
            }

            // Convert Feet to Meter and CM to Meter
            val heightInMeter: Double = if (heightUnit.equals("Cm", ignoreCase = true)) {
                height.toDouble() / 100.0
            } else {
                val parts = height.split(".")
                val feet = parts[0].toDouble()
                val inch = parts[1].toDouble()
                val totalInches = (feet * 12) + inch
                totalInches * 0.0254
            }

            // BMI Formula
            val bmi = weightInKg / (heightInMeter * heightInMeter)
            // updateUI fun call
            updateUI(bmi, heightInMeter)

        } else {
            Log.e("ResultActivity", "Data Missing!")
            weightResultText.text = "Need Data!"
        }


    }

    private fun updateUI(bmi: Double, heightInMeter: Double) {
        progressbar.progress = bmi.toFloat()
        progressbarText.text = String.format("%.1f", bmi)

        when {

            // Very severely underweight Layout
            bmi < 16.0 -> {
                weightResultText.text = "You have underweight body weight!"
                layoutOne.setBackgroundColor(ContextCompat.getColor(this, R.color.one))
            }
            // Severely underweight Layout
            bmi in 16.1..16.9 -> {
                weightResultText.text = "You have underweight body weight!"
                layoutTwo.setBackgroundColor(ContextCompat.getColor(this, R.color.two))
            }
            // Underweight Layout
            bmi in 17.0..18.4 -> {
                weightResultText.text = "You have underweight body weight!"
                layoutThree.setBackgroundColor(ContextCompat.getColor(this, R.color.three))
            }
            // Normal Layout
            bmi in 18.5..24.9 -> {
                weightResultText.text = "You have Normal body weight!"
                layoutFour.setBackgroundColor(ContextCompat.getColor(this, R.color.four))
            }
            // Overweight Layout
            bmi in 25.00..29.9 -> {
                weightResultText.text = "You have overweight body weight!"
                layoutFive.setBackgroundColor(ContextCompat.getColor(this, R.color.five))
            }
            // Obese Class | Layout
            bmi in 30.0..34.9 -> {
                weightResultText.text = "You have overweight body weight!"
                layoutSix.setBackgroundColor(ContextCompat.getColor(this, R.color.six))
            }
            // Obese Class || Layout
            bmi in 35.0..39.9 -> {
                weightResultText.text = "You have overweight body weight!"
                layoutSix.setBackgroundColor(ContextCompat.getColor(this, R.color.seven))
            }
            // Obese Class ||| Layout
            bmi > 40.0 -> {
                weightResultText.text = "You have overweight body weight!"
                layoutSix.setBackgroundColor(ContextCompat.getColor(this, R.color.eight))
            }
        }
    }
}