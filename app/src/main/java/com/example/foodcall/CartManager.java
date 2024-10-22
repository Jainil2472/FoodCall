package com.example.foodcall;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems = new ArrayList<>();

    private CartManager() {
        // Private constructor to prevent instantiation
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        // Check if the item already exists in the cart
        for (CartItem cartItem : cartItems) {
            if (cartItem.getItemName().equals(item.getItemName())) {
                // If the item is already in the cart, increase the quantity
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        // Otherwise, add a new item to the cart
        cartItems.add(item);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getTotalAmount() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
