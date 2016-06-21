package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int priceOfCoffee = calculatePrice(quantity);
        CheckBox whippedCream = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        boolean whippedCreamYN = whippedCream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.checkbox_chocolate);
        boolean chocolateYN = chocolate.isChecked();
        EditText userName = (EditText) findViewById(R.id.user_name);
        String userNameText = userName.getText().toString();
        String priceMessage = "Name: " + userNameText;
        priceMessage += "\nPrice: $" + priceOfCoffee;
        priceMessage += "\nWhipped Cream: " + whippedCreamYN;
        priceMessage += "\nChocolate: " + chocolateYN;
        priceMessage += "\nThank You!";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "Vinnyv283@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     *
     * @param cups is the number of cups of coffee ordered
     */
    private int calculatePrice(int cups) {
        int price = cups * 5;
        return price;
    }

    public void Increment(View view) {
        if (quantity < 100)
            quantity += 1;
        display(quantity);
    }

    public void Decrement(View view) {
        if (quantity > 1) {
            quantity -= 1;
        } else {
//            Context context = getApplicationContext();
//            CharSequence text = "Hello toast!";
//            int duration = Toast.LENGTH_SHORT;
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
            Toast.makeText(this, "Can't go below 1", Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}