package com.teacool.teacool

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CheckoutActivity : AppCompatActivity() {

    private lateinit var logo: ImageView
    private lateinit var orderItemsContainer: LinearLayout
    private lateinit var tvOrderTime: TextView
    private lateinit var tvThankYouMessage: TextView
    private lateinit var btnTrackOrder: Button

    // Tabs
    private lateinit var tabMenu: TextView
    private lateinit var tabFeatured: TextView
    private lateinit var tabPrevious: TextView
    private lateinit var tabFavorites: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Initialize views
        initializeViews()

        // Setup listeners
        setupListeners()

        // Display order items
        displayOrderItems()

        // Set order time
        setOrderTime()

        // Start countdown timer (optional)
        startOrderTimer()
    }

    private fun initializeViews() {
        logo = findViewById(R.id.logo)
        orderItemsContainer = findViewById(R.id.orderItemsContainer)
        tvOrderTime = findViewById(R.id.tvOrderTime)
        tvThankYouMessage = findViewById(R.id.tvThankYouMessage)
        btnTrackOrder = findViewById(R.id.btnTrackOrder)

        // Tabs
        tabMenu = findViewById(R.id.tabMenu)
        tabFeatured = findViewById(R.id.tabFeatured)
        tabPrevious = findViewById(R.id.tabPrevious)
        tabFavorites = findViewById(R.id.tabFavorites)
    }

    private fun setupListeners() {
        // Logo click - go back to main menu
        logo.setOnClickListener {
            // Clear cart after order
            CartManager.clearCart()
            finish()
        }

        // Track order button
        btnTrackOrder.setOnClickListener {
            Toast.makeText(this, "Tracking your order... 🚚", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to order tracking screen
        }

        // Tab listeners
        tabMenu.setOnClickListener {
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
        }

        tabFeatured.setOnClickListener {
            Toast.makeText(this, "Featured", Toast.LENGTH_SHORT).show()
        }

        tabPrevious.setOnClickListener {
            Toast.makeText(this, "Previous Orders", Toast.LENGTH_SHORT).show()
        }

        tabFavorites.setOnClickListener {
            Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayOrderItems() {
        // Clear container first
        orderItemsContainer.removeAllViews()

        // Get items from cart
        val items = CartManager.getItems()

        for (item in items) {
            // Create order item view
            val itemView = layoutInflater.inflate(R.layout.order_item_layout, orderItemsContainer, false)

            val imgItem = itemView.findViewById<ImageView>(R.id.imgOrderItem)
            val tvItemName = itemView.findViewById<TextView>(R.id.tvOrderItemName)
            val tvItemSize = itemView.findViewById<TextView>(R.id.tvOrderItemSize)
            val tvItemQty = itemView.findViewById<TextView>(R.id.tvOrderItemQty)

            // Set data
            imgItem.setImageResource(item.imageResource)
            tvItemName.text = item.name
            tvItemSize.text = item.size
            tvItemQty.text = "x${item.quantity}"

            // Add to container
            orderItemsContainer.addView(itemView)
        }
    }

    private fun setOrderTime() {
        // Get current time and add 30 minutes for estimated delivery
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, 30)

        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val estimatedTime = timeFormat.format(calendar.time)

        tvOrderTime.text = estimatedTime
    }

    private fun startOrderTimer() {
        // 30 minutes countdown (1800000 milliseconds)
        object : CountDownTimer(1800000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                tvOrderTime.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                tvOrderTime.text = "Ready!"
                Toast.makeText(this@CheckoutActivity, "Your order is ready! 🎉", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    override fun onBackPressed() {
        // Clear cart when going back
        CartManager.clearCart()
        super.onBackPressed()
    }
}