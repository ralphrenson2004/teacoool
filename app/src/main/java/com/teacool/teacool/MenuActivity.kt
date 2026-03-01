package com.teacool.teacool

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu) // This is your welcome screen layout

        // Logo click listener - goes back to login
        val logo = findViewById<ImageView>(R.id.logo)
        logo.setOnClickListener {
            finish() // Goes back to MainActivity (login screen)
        }

        // Go to Menu button click listener
        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)
        btnGetStarted.setOnClickListener {
            // Navigate to MenuActivity2 (the drinks menu)
            val intent = Intent(this, MenuActivity2::class.java)
            startActivity(intent)
        }
    }

    // Required if using android:onClick in XML
    fun goBack(view: View) {
        finish()
    }
}