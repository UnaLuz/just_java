package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    protected int coffeeCount = 0;
    protected int coffeePrice = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //displayPrice(coffeeCount * coffeePrice);
        String message = "That'll be $" + (coffeeCount * coffeePrice) + "\nThank you!";
        displayMessage(message);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        coffeeCount += 1;
        display(coffeeCount);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (coffeeCount > 0) {
            coffeeCount -= 1;
        }
        display(coffeeCount);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(NumberFormat.getInstance().format(number));
    }

    /**
     * This method displays the price value on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String m) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(m);
    }
}