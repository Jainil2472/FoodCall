package com.example.foodcall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.imageview.ShapeableImageView;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the views
        ShapeableImageView pizzaAddButton = view.findViewById(R.id.pizza_add);
        ShapeableImageView momoAddButton = view.findViewById(R.id.momo_add);
        ShapeableImageView choleBhatureAddButton = view.findViewById(R.id.cholebhature_add);
        ShapeableImageView burgerButton = view.findViewById(R.id.burger_add);
        ShapeableImageView wrapButton = view.findViewById(R.id.wrap_add);
        ShapeableImageView sandwichButton = view.findViewById(R.id.sandwich_add);
        ShapeableImageView samosaButton = view.findViewById(R.id.samosa_add);
        ShapeableImageView doshaButton = view.findViewById(R.id.dosha_add);
        ShapeableImageView cakeButton = view.findViewById(R.id.cake_add);

        // Get CartManager instance
        CartManager cartManager = CartManager.getInstance();

        // Set click listeners for each button
        pizzaAddButton.setOnClickListener(v -> {
            CartItem pizza = new CartItem("Pizza", 220, 1);
            cartManager.addItem(pizza);
            Toast.makeText(getContext(), "Pizza added to cart!", Toast.LENGTH_SHORT).show();
        });

        momoAddButton.setOnClickListener(v -> {
            CartItem momo = new CartItem("Momo", 80, 1);
            cartManager.addItem(momo);
            Toast.makeText(getContext(), "Momo added to cart!", Toast.LENGTH_SHORT).show();
        });

        choleBhatureAddButton.setOnClickListener(v -> {
            CartItem choleBhature = new CartItem("Chole Bhature", 130, 1);
            cartManager.addItem(choleBhature);
            Toast.makeText(getContext(), "Chole Bhature added to cart!", Toast.LENGTH_SHORT).show();
        });


        sandwichButton.setOnClickListener(v -> {
            CartItem sandwich = new CartItem("Sandwich", 100, 1);
            cartManager.addItem(sandwich);
            Toast.makeText(getContext(), "Sandwich added to cart!", Toast.LENGTH_SHORT).show();
        });

        burgerButton.setOnClickListener(v -> {
            CartItem burger = new CartItem("Burger", 120, 1);
            cartManager.addItem(burger);
            Toast.makeText(getContext(), "Burger added to cart!", Toast.LENGTH_SHORT).show();
        });

        wrapButton.setOnClickListener(v -> {
            CartItem Wrap = new CartItem("Wrap", 170, 1);
            cartManager.addItem(Wrap);
            Toast.makeText(getContext(), "Wrap added to cart!", Toast.LENGTH_SHORT).show();
        });

        samosaButton.setOnClickListener(v -> {
            CartItem samosa = new CartItem("Samosa", 30, 1);
            cartManager.addItem(samosa);
            Toast.makeText(getContext(), "Samosa added to cart!", Toast.LENGTH_SHORT).show();
        });

        doshaButton.setOnClickListener(v -> {
            CartItem dosha = new CartItem("Dosha", 90, 1);
            cartManager.addItem(dosha);
            Toast.makeText(getContext(), "Dosha added to cart!", Toast.LENGTH_SHORT).show();
        });

        cakeButton.setOnClickListener(v -> {
            CartItem cake = new CartItem("Cake", 300, 1);
            cartManager.addItem(cake);
            Toast.makeText(getContext(), "Cake added to cart!", Toast.LENGTH_SHORT).show();
        });



    }
}
