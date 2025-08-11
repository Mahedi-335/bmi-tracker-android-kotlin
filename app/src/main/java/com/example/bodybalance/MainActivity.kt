package com.example.bodybalance

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
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

        val name = intent.getStringExtra("USER_NAME") ?: ""
        if (name.isNotEmpty()){
            nameText.text = "Hi $name!"
        }else{
            nameText.text = "Hi!"
        }

    }
}