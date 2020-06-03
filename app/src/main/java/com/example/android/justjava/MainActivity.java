package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    protected int cupsCount = 1;
    protected int cupPrice = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        cupsCount += 1;
        display(cupsCount);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (cupsCount > 0) {
            cupsCount -= 1;
        }
        display(cupsCount);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(NumberFormat.getInstance().format(number));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOderSummary());
    }

    /**
     * Given the amount of coffees and the price per coffee
     * it calculates the total value
     *
     * @param coffees     is the number of coffees
     * @param pricePerCup is the price per cup of coffee
     * @return the total price
     */
    public int calculatePrice(int coffees, int pricePerCup) {
        return coffees * pricePerCup;
    }

    /**
     * @return the order summary with the final price and a nice message
     */
    public String createOderSummary() {
        String summary = "Name: Maria Luz Cucarella";
        summary += "\nQuantity: " + cupsCount;
        summary += "\nTotal: $" + calculatePrice(cupsCount, cupPrice);
        summary += "\nThank you!";
        return summary;
    }

    /**
     * @param m is the summary message to display on the screen
     */
    public void displayMessage(String m) {
        TextView priceTextView = findViewById(R.id.summary_text_view);
        priceTextView.setText(m);
    }
}
