package com.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int calculePrice(){

        int pricePerCup = 5;

        return quantity * pricePerCup;
    }

    public String createOrderSummary(int price, boolean hasWhippedCream, boolean chocolateCream, String editTextName){

        String message = "";

        message = "Name: "+editTextName+ "\n";
        message += "Add whipped cream? " +hasWhippedCream+"\n";
        message += "Chocolate cream? " +chocolateCream+"\n";
        message += "Quantity: "+ quantity +"\n";
        message += "Total "+price+"\n";
        message += "Thank you!";

        return message;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //int price = quantity * 5 ;

        int price = calculePrice();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_meat);
        CheckBox chocolateCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        EditText editText = (EditText) findViewById(R.id.editText);

        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean chocolateCream = chocolateCreamCheckBox.isChecked();
        String editTextName = editText.getText().toString();

        Log.v("Main Activity","The Price is "+price);

        String priceMessage = createOrderSummary(price, hasWhippedCream, chocolateCream, editTextName);

        displayMessage(priceMessage);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.totally_text_view);
        orderSummaryTextView.setText(message);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {

        quantity = quantity +1;
        display(quantity);

    }

    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }

    private void displayPrice(int number){
        TextView totallyTextView = (TextView) findViewById(R.id.totally_text_view);
        totallyTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
