package com.example.bodybalance

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mikhaellopez.circularprogressbar.CircularProgressBar


class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val progressbar = findViewById<CircularProgressBar>(R.id.circleProgressbar)
        val progressbarText = findViewById<TextView>(R.id.circleProgressbarText)

        val weightResultText = findViewById<TextView>(R.id.weightResultText)

        val userAgeText = findViewById<TextView>(R.id.ageTextShow)
        val userWeightText = findViewById<TextView>(R.id.weightTextShow)
        val userHeightText = findViewById<TextView>(R.id.heightTextShow)

        val layoutOne = findViewById<RelativeLayout>(R.id.resultOne)
        val layoutTwo = findViewById<RelativeLayout>(R.id.resultTwo)
        val layoutThree = findViewById<RelativeLayout>(R.id.resultThree)
        val layoutFour = findViewById<RelativeLayout>(R.id.resultFour)
        val layoutFive = findViewById<RelativeLayout>(R.id.resultFive)
        val layoutSix = findViewById<RelativeLayout>(R.id.resultSix)
        val layoutSeven = findViewById<RelativeLayout>(R.id.resultSeven)
        val layoutEight = findViewById<RelativeLayout>(R.id.resultEight)

        // Get Data from MainActivity
        val age = intent.getStringExtra("USER_AGE")
        val weight = intent.getStringExtra("USER_WEIGHT")
        val height = intent.getStringExtra("USER_HEIGHT")
        val weightUnit = intent.getStringExtra("WEIGHT_UNIT")
        val heightUnit = intent.getStringExtra("HEIGHT_UNIT")

        if (age != null && weight != null && height != null && weightUnit != null && heightUnit != null)
        {
            // Show Data
            userAgeText.text = age
            userWeightText.text = "$weight $weightUnit"
            userHeightText.text = "$height $heightUnit"
        }






    }
}