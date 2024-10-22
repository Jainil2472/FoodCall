package com.example.foodcall;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {

    private static final int SMS_PERMISSION_REQUEST_CODE = 123;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView cartItemsTextView = view.findViewById(R.id.cart_items_text);
        TextView totalAmountTextView = view.findViewById(R.id.total_amount_text);
        Button payButton = view.findViewById(R.id.pay);

        // Get cart items
        CartManager cartManager = CartManager.getInstance();
        List<CartItem> cartItems = cartManager.getCartItems();

        // Display cart items
        StringBuilder itemsText = new StringBuilder();
        for (CartItem item : cartItems) {
            itemsText.append(item.getItemName()).append(" x ")
                    .append(item.getQuantity()).append(" = ")
                    .append(item.getTotalPrice()).append(" Rs\n");
        }
        cartItemsTextView.setText(itemsText.toString());

        // Display total amount
        totalAmountTextView.setText("Total: " + cartManager.getTotalAmount() + " Rs");

        // Set click listener for Pay button
        payButton.setOnClickListener(v -> {
            // Check SMS permission
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
            } else {
                sendSmsAndSaveOrder(cartItems, cartManager.getTotalAmount());
            }
        });
    }

    // Method to send SMS and save order data
    private void sendSmsAndSaveOrder(List<CartItem> cartItems, double totalAmount) {
        // Sending SMS
        String phoneNumber = "9712220477";
        String message = "Your order has been placed. Total amount: " + totalAmount + " Rs";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getContext(), "SMS sent successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "SMS sending failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        // Saving order in Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> order = new HashMap<>();
        order.put("items", cartItems);
        order.put("totalAmount", totalAmount);

        db.collection("orders")
                .add(order)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Order saved to Firebase!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Failed to save order: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Re-trigger the Pay button functionality
                CartManager cartManager = CartManager.getInstance();
                sendSmsAndSaveOrder(cartManager.getCartItems(), cartManager.getTotalAmount());
            } else {
                Toast.makeText(getContext(), "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
