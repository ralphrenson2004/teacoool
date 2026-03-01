package com.teacool.teacool

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView

class MenuActivity2 : BaseActivity() {  // Changed from AppCompatActivity to BaseActivity

    // Card references
    private lateinit var cardHotCoffee: CardView
    private lateinit var cardColdCoffee: CardView
    private lateinit var cardHotTea: CardView
    private lateinit var cardIcedEnergy: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu2)

        // Logo Click Listener - goes back to MenuActivity (welcome screen)
        val logo = findViewById<ImageView>(R.id.logo)
        logo.setOnClickListener {
            finish()
        }

        // Initialize views
        initializeViews()

        // Set up card click listeners
        setupCardListeners()

        // Setup footer - "home" indicates this is the home page
        setupFooter("home")
    }

    private fun initializeViews() {
        // Initialize cards
        cardHotCoffee = findViewById(R.id.cardHotCoffee)
        cardColdCoffee = findViewById(R.id.cardColdCoffee)
        cardHotTea = findViewById(R.id.cardHotTea)
        cardIcedEnergy = findViewById(R.id.cardIcedEnergy)
    }

    private fun setupCardListeners() {
        cardHotCoffee.setOnClickListener {
            // Navigate to HotCoffee activity
            val intent = Intent(this, HotCoffee::class.java)
            startActivity(intent)
        }

        cardColdCoffee.setOnClickListener {
            Toast.makeText(this, "Cold Coffee - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        cardHotTea.setOnClickListener {
            Toast.makeText(this, "Hot Tea - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        cardIcedEnergy.setOnClickListener {
            Toast.makeText(this, "Iced Energy - Coming Soon!", Toast.LENGTH_SHORT).show()
        }
    }
}