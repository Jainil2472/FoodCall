package com.example.foodcall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;

    // Constructor to initialize cart items list
    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the cart_item layout for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Get the cart item at the current position
        CartItem cartItem = cartItems.get(position);

        // Set item name, quantity, and total price in the corresponding views
        holder.itemName.setText(cartItem.getItemName());
        holder.itemQuantity.setText("x" + cartItem.getQuantity());
        holder.itemPrice.setText(cartItem.getTotalPrice() + " Rs");
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the cart
        return cartItems.size();
    }

    // Add item to the cart and notify the adapter
    public void addItem(CartItem cartItem) {
        cartItems.add(cartItem);  // Add the item to the list
        notifyDataSetChanged();   // Notify adapter that the data set has changed
    }

    // ViewHolder to manage the individual cart item views
    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity, itemPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views from the layout
            itemName = itemView.findViewById(R.id.cart_item_name);
            itemQuantity = itemView.findViewById(R.id.quantity);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
        }
    }
}
