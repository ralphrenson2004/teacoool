package com.teacool.teacool

import android.content.Intent  // ADD THIS LINE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    private lateinit var emptyCartState: LinearLayout
    private lateinit var cartMainLayout: LinearLayout
    private lateinit var cartItemsContainer: LinearLayout
    private lateinit var tvCartTotal: TextView
    private lateinit var btnCheckout: Button
    private lateinit var btnBackToMenu: Button
    private lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Initialize views
        initializeViews()

        // Setup listeners
        setupListeners()

        // Update UI
        updateCartDisplay()
    }

    override fun onResume() {
        super.onResume()
        // Refresh cart display when returning to this screen
        updateCartDisplay()
    }

    private fun initializeViews() {
        emptyCartState = findViewById(R.id.emptyCartState)
        cartMainLayout = findViewById(R.id.cartMainLayout)
        cartItemsContainer = findViewById(R.id.cartItemsContainer)
        tvCartTotal = findViewById(R.id.tvCartTotal)
        btnCheckout = findViewById(R.id.btnCheckout)
        btnBackToMenu = findViewById(R.id.btnBackToMenu)
        logo = findViewById(R.id.logo)
    }

    private fun setupListeners() {
        // Logo click - go back
        logo.setOnClickListener {
            finish()
        }

        // Back to menu button
        btnBackToMenu.setOnClickListener {
            finish()
        }

        // Checkout button
        btnCheckout.setOnClickListener {
            if (!CartManager.isEmpty()) {
                // Navigate to CheckoutActivity
                val intent = Intent(this, CheckoutActivity::class.java)
                startActivity(intent)
                finish() // Close cart activity
            }
        }
    }

    private fun updateCartDisplay() {
        if (CartManager.isEmpty()) {
            // Show empty state
            emptyCartState.visibility = View.VISIBLE
            cartMainLayout.visibility = View.GONE
        } else {
            // Show cart with items
            emptyCartState.visibility = View.GONE
            cartMainLayout.visibility = View.VISIBLE

            // Clear existing views
            cartItemsContainer.removeAllViews()

            // Add "Your Cart" header
            val headerText = TextView(this).apply {
                text = "Your Cart"
                textSize = 24f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setTextColor(getColor(android.R.color.black))
                setPadding(0, 0, 0, 48)
            }
            cartItemsContainer.addView(headerText)

            // Add cart items dynamically
            for (item in CartManager.getItems()) {
                addCartItemView(item)
            }

            // Update total
            updateTotal()
        }
    }

    private fun addCartItemView(item: CartManager.CartItem) {
        val itemView = LayoutInflater.from(this).inflate(R.layout.cart_item_layout, cartItemsContainer, false)

        // Find views in the item layout
        val tvItemName = itemView.findViewById<TextView>(R.id.tvCartItemName)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvCartPrice)
        val tvQty = itemView.findViewById<TextView>(R.id.tvCartQty)
        val btnMinus = itemView.findViewById<ImageButton>(R.id.btnMinus)
        val btnPlus = itemView.findViewById<ImageButton>(R.id.btnPlus)
        val btnRemove = itemView.findViewById<ImageButton>(R.id.btnRemoveItem)
        val imgItem = itemView.findViewById<ImageView>(R.id.imgCartItem)

        // Set item data
        tvItemName.text = "${item.name} (${item.size}) - ${item.sugarLevel}% sugar"
        tvPrice.text = "₱${item.price}"
        tvQty.text = item.quantity.toString()
        imgItem.setImageResource(item.imageResource)

        // Quantity buttons
        btnPlus.setOnClickListener {
            item.quantity++
            tvQty.text = item.quantity.toString()
            updateTotal()
        }

        btnMinus.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                tvQty.text = item.quantity.toString()
                updateTotal()
            }
        }

        // Remove button
        btnRemove.setOnClickListener {
            CartManager.removeItem(item)
            updateCartDisplay()
            Toast.makeText(this, "Item removed from cart", Toast.LENGTH_SHORT).show()
        }

        // Add to container
        cartItemsContainer.addView(itemView)
    }

    private fun updateTotal() {
        val total = CartManager.getTotalPrice()
        tvCartTotal.text = "₱$total"
    }
}