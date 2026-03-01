package com.teacool.teacool

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HotCoffee : AppCompatActivity() {

    // Views from hot_coffee.xml
    private lateinit var tvPrice: TextView
    private lateinit var tvQuantity: TextView
    private lateinit var btnS: Button
    private lateinit var btnM: Button
    private lateinit var btnL: Button
    private lateinit var btnQtyUp: ImageButton
    private lateinit var btnQtyDown: ImageButton
    private lateinit var btnAddToCart: Button
    private lateinit var sugarSeekBar: SeekBar
    private lateinit var sugarValueText: TextView

    // Views from header_layout.xml
    private lateinit var cartBadge: TextView
    private lateinit var cartContainer: FrameLayout
    private lateinit var logo: ImageView
    private lateinit var tabMenu: TextView

    // Variables to track selections
    private var currentPrice = 80
    private var currentQuantity = 1
    private var currentSize = "S"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hot_coffee)

        // Initialize all views
        initializeViews()

        // Setup listeners
        setupSizeButtons()
        setupQuantityButtons()
        setupSugarSeekBar()
        setupAddToCartButton()
        setupHeaderButtons()

        // Set default selection
        updateSelection(selected = btnS, price = 80, sizeLabel = "S")

        // Update cart badge
        updateCartBadge()
    }

    override fun onResume() {
        super.onResume()
        // Update cart badge when returning to this screen
        updateCartBadge()
    }

    private fun initializeViews() {
        // Views from hot_coffee.xml
        tvPrice = findViewById(R.id.tvPrice)
        tvQuantity = findViewById(R.id.tvQuantity)
        btnS = findViewById(R.id.btnSizeS)
        btnM = findViewById(R.id.btnSizeM)
        btnL = findViewById(R.id.btnSizeL)
        btnQtyUp = findViewById(R.id.btnQtyUp)
        btnQtyDown = findViewById(R.id.btnQtyDown)
        btnAddToCart = findViewById(R.id.btnAddToCart)
        sugarSeekBar = findViewById(R.id.sugarSeekBar)
        sugarValueText = findViewById(R.id.sugarValueText)

        // Views from header_layout.xml
        cartBadge = findViewById(R.id.cartBadge)
        cartContainer = findViewById(R.id.cartContainer)
        logo = findViewById(R.id.logo)
        tabMenu = findViewById(R.id.tabMenu)
    }

    private fun setupSizeButtons() {
        btnS.setOnClickListener {
            updateSelection(selected = btnS, price = 80, sizeLabel = "S")
        }
        btnM.setOnClickListener {
            updateSelection(selected = btnM, price = 95, sizeLabel = "M")
        }
        btnL.setOnClickListener {
            updateSelection(selected = btnL, price = 105, sizeLabel = "L")
        }
    }

    private fun setupQuantityButtons() {
        btnQtyUp.setOnClickListener {
            if (currentQuantity < 99) {
                currentQuantity++
                tvQuantity.text = currentQuantity.toString()
            }
        }

        btnQtyDown.setOnClickListener {
            if (currentQuantity > 1) {
                currentQuantity--
                tvQuantity.text = currentQuantity.toString()
            }
        }
    }

    private fun setupSugarSeekBar() {
        sugarSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sugarValueText.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupAddToCartButton() {
        btnAddToCart.setOnClickListener {
            val sugarLevel = sugarSeekBar.progress

            // Create cart item
            val cartItem = CartManager.CartItem(
                name = "Morning Bliss",
                size = currentSize,
                price = currentPrice,
                quantity = currentQuantity,
                sugarLevel = sugarLevel,
                imageResource = R.drawable.hotcoffee
            )

            // Add to cart
            CartManager.addItem(cartItem)

            // Show success message
            Toast.makeText(
                this,
                "✓ Added to cart: Morning Bliss ($currentSize) x$currentQuantity",
                Toast.LENGTH_SHORT
            ).show()

            // Update cart badge
            updateCartBadge()

            // Reset to defaults
            currentQuantity = 1
            tvQuantity.text = "1"
            sugarSeekBar.progress = 50
        }
    }

    private fun setupHeaderButtons() {
        // Logo click - go back to MenuActivity2 (drinks menu)
        logo.setOnClickListener {
            finish()
        }

        // Cart container click - navigate to CartActivity
        cartContainer.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Menu tab click - go back to menu
        tabMenu.setOnClickListener {
            finish()
        }
    }

    private fun updateSelection(selected: Button, price: Int, sizeLabel: String) {
        // Update current selections
        currentPrice = price
        currentSize = sizeLabel

        // Update price display
        tvPrice.text = "Price: ₱$price"

        // Update button states
        val buttons = listOf(btnS, btnM, btnL)

        buttons.forEach { button ->
            if (button == selected) {
                button.setBackgroundColor(getColor(android.R.color.holo_green_light))
            } else {
                button.setBackgroundColor(getColor(android.R.color.darker_gray))
            }
        }
    }

    private fun updateCartBadge() {
        val itemCount = CartManager.getItemCount()
        if (itemCount > 0) {
            cartBadge.text = itemCount.toString()
            cartBadge.visibility = android.view.View.VISIBLE
        } else {
            cartBadge.visibility = android.view.View.GONE
        }
    }
}