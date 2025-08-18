package com.example.bodybalance

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Finding Views by ID
        val inputEditText = findViewById<EditText>(R.id.inputEditText)
        val nextButton = findViewById<MaterialButton>(R.id.nextButton)

        // Set Length in EditText
        inputEditText.filters = arrayOf(InputFilter.LengthFilter(12))

        // Next Button
        nextButton.setOnClickListener {

            // Get User Input
            val name = inputEditText.text.toString()

            // Check that EditText != null
            if (name.isEmpty()) {
                Toast.makeText(this, "Enter your name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Send user name in MainActivity
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("USER_NAME", name)
            }
            startActivity(intent)
            finish()

        }

    }
}