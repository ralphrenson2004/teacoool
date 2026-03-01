package com.teacool.teacool

import android.content.Intent
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected fun setupFooter(currentPage: String = "") {
        val btnHome = findViewById<LinearLayout>(R.id.btnHome)
        val btnProfile = findViewById<LinearLayout>(R.id.btnProfile)
        val btnOrderHistory = findViewById<LinearLayout>(R.id.btnOrderHistory)

        // Home button
        btnHome.setOnClickListener {
            if (currentPage == "home") {
                Toast.makeText(this, "Already on Home", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MenuActivity2::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        }

        // Profile button
        btnProfile.setOnClickListener {
            if (currentPage == "profile") {
                Toast.makeText(this, "Already on Profile", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }

        // Order History button
        btnOrderHistory.setOnClickListener {
            if (currentPage == "orders") {
                Toast.makeText(this, "Already on Order History", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, OrderHistoryActivity::class.java)
                startActivity(intent)
            }
        }
    }
}