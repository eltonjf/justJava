package com.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int calculePrice(boolean hasWhippedCream, boolean chocolateCream){

        int basePrice = 5;

        if(hasWhippedCream){
            basePrice += 1;
        }
        if(chocolateCream){
            basePrice += 2;
        }

        return quantity * basePrice;
    }

    public String createOrderSummary(int price, boolean hasWhippedCream, boolean chocolateCream, String editTextName){

        String message = "";

        message = "Name: "+editTextName+ "\n";
        message += "Add whipped cream? " +hasWhippedCream+"\n";
        message += "Chocolate cream? " +chocolateCream+"\n";
        message += "Quantity: "+ quantity +"\n";
        message += "Total $"+price+",00"+"\n";
        message += "Thank you!";

        return message;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_meat);
        CheckBox chocolateCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        EditText editText = (EditText) findViewById(R.id.editText);

        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean chocolateCream = chocolateCreamCheckBox.isChecked();
        String editTextName = editText.getText().toString();

        int price = calculePrice(hasWhippedCream, chocolateCream);

        Log.v("Main Activity","The Price is "+price);

        String priceMessage = createOrderSummary(price, hasWhippedCream, chocolateCream, editTextName);

        //displayMessage(priceMessage);

        String subject = "Just Java, Order for "+editTextName;

        composeEmail(subject,priceMessage);
    }


    public void composeEmail(String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

   /* private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.totally_text_view);
        orderSummaryTextView.setText(message);
    }*/


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(this, "You cannot have more than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity +1;
        display(quantity);

    }

    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }
/*
    private void displayPrice(int number){
        TextView totallyTextView = (TextView) findViewById(R.id.totally_text_view);
        totallyTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    */
}
