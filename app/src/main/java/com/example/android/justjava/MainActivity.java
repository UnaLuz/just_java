package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    protected int cupsCount = 1;
    protected final int cupPrice = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (cupsCount < 100) {
            cupsCount += 1;
            display(cupsCount);
        } else {
            Toast.makeText(this, R.string.increment_toast, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (cupsCount > 1) {
            cupsCount -= 1;
            display(cupsCount);
        } else {
            Toast.makeText(this, R.string.decrement_toast, Toast.LENGTH_SHORT).show();
        }
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
        // Getting the name that the user entered
        EditText nameView = findViewById(R.id.name_edittext_view);
        String nameText = nameView.getText().toString().trim();

        if (nameText.isEmpty()) {
            Toast.makeText(this, R.string.blank_name_toast, Toast.LENGTH_SHORT).show();
        } else {
            // Getting the state of the whipped cream check box
            CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
            boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

            // Getting the state of the chocolate check box
            CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
            boolean hasChocolate = chocolateCheckBox.isChecked();

            // Calculate the final price
            int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);

            // Create the full order summary
            String orderSummary = createOderSummary(nameText, hasWhippedCream, hasChocolate, totalPrice);

            composeEmail(new String[]{"cucarella_luz@justjava.com"}, orderSummary, getString(R.string.name_subject, nameText));
        }
    }

    /**
     * Knowing the price of a cup of coffee, how many coffees the user wants,
     * and getting what toppings the user wants to add it calculates the total price
     *
     * @param addWhippedCream is whether or not the user wants whipped cream
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @return the total price
     */
    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int totalPerCup = cupPrice;

        // Whipped cream costs 20 percent of the price of a cup of coffee
        if (addWhippedCream) {
            totalPerCup += cupPrice * 0.2;
        }

        // Chocolate costs 40 percent of the price of a cup of coffee
        if (addChocolate) {
            totalPerCup += cupPrice * 0.4;
        }

        return cupsCount * totalPerCup;
    }

    /**
     * @param nameText        is the text entered by the user, luckily their name
     * @param hasWhippedCream tells if the user wants whipped cream or not
     * @param hasChocolate    tells if the user wants chocolate topping
     * @param totalPrice      total price of the order, including the toppings
     * @return the order summary with the final price and a nice message
     */
    public String createOderSummary(String nameText, boolean hasWhippedCream, boolean hasChocolate, int totalPrice) {
        String summary = getString(R.string.name_summary, nameText);
        summary += "\n" + getString(R.string.quantity_summary, cupsCount);
        summary += "\n" + getString(R.string.whipped_cream_summary, hasWhippedCream);
        summary += "\n" + getString(R.string.chocolate_summary, hasChocolate);
        summary += "\n" + getString(R.string.total_price_summary, totalPrice);
        summary += "\n" + getString(R.string.thank_you_summary);
        return summary;
    }

    public void composeEmail(String[] addresses, String message, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
