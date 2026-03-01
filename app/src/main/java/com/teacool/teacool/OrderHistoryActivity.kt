package com.teacool.teacool

import android.os.Bundle

class OrderHistoryActivity : BaseActivity() {  // Changed from AppCompatActivity to BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        // Setup footer - "orders" indicates this is the order history page
        setupFooter("orders")
    }
}