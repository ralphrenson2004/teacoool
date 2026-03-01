package com.teacool.teacool

object CartManager {
    data class CartItem(
        val name: String,
        val size: String,
        val price: Int,
        var quantity: Int,
        val sugarLevel: Int,
        val imageResource: Int
    )

    private val cartItems = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        // Check if item already exists (same name, size, sugar level)
        val existingItem = cartItems.find {
            it.name == item.name &&
                    it.size == item.size &&
                    it.sugarLevel == item.sugarLevel
        }

        if (existingItem != null) {
            // Item exists, increase quantity
            existingItem.quantity += item.quantity
        } else {
            // New item, add to cart
            cartItems.add(item)
        }
    }

    fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }

    fun getItems(): List<CartItem> {
        return cartItems.toList()
    }

    fun getItemCount(): Int {
        return cartItems.sumOf { it.quantity }
    }

    fun getTotalPrice(): Int {
        return cartItems.sumOf { it.price * it.quantity }
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun isEmpty(): Boolean {
        return cartItems.isEmpty()
    }
}