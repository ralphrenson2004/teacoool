package com.teacool.teacool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val buttonSignIn = findViewById<Button>(R.id.buttonSignIn)
        val buttonJoin = findViewById<Button>(R.id.buttonJoin)

        // Sign In Click Listener
        buttonSignIn.setOnClickListener {
            val user = etUsername.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            Log.d("MainActivity", "Sign-in button clicked. User: $user")

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                try {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    // finish() removed so MainActivity stays in the back stack
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error starting MenuActivity: ${e.message}")
                    Toast.makeText(this, "Menu screen not found!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Join Now Click Listener
        buttonJoin.setOnClickListener {
            try {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                // finish() removed so MainActivity stays in the back stack
            } catch (e: Exception) {
                Log.e("MainActivity", "Error starting MenuActivity: ${e.message}")
                Toast.makeText(this, "Menu screen not found!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
