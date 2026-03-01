package com.teacool.teacool

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast

class ProfileActivity : BaseActivity() {  // Changed from AppCompatActivity to BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Setup footer - "profile" indicates this is the profile page
        setupFooter("profile")

        // Setup logout button
        setupLogoutButton()
    }

    private fun setupLogoutButton() {
        val btnLogout = findViewById<LinearLayout>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show()

            // Clear cart
            CartManager.clearCart()

            // Go back to login and clear all activities
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}