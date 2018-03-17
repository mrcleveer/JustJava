/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 0;

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
        }
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
        }
        display(quantity);
    }

    public void submitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        String txtname = name.getText().toString();
        CheckBox checked = (CheckBox) findViewById(R.id.checkbox);
        boolean HaswhipedCream = checked.isChecked();
        CheckBox checked2 = (CheckBox) findViewById(R.id.checkbox2);
        boolean HasChocolate = checked2.isChecked();

        int price = calcutalePrice(HaswhipedCream, HasChocolate);
        String priceMessage = createOrderSummary(price, HaswhipedCream, HasChocolate, txtname);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order summary");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        /** displayMessage(priceMessage);(we do not need it anymore because we are emailing the order)*/

    }

    private int calcutalePrice(boolean addwhipedcream, boolean addchocolate) {
        int baseprice = 5;
        if (addwhipedcream) {
            baseprice = baseprice + 1;
        }

        if (addchocolate) {
            baseprice = baseprice + 2;
        }

        return quantity * baseprice;
    }

    private String createOrderSummary(int price, boolean addwhipedcream, boolean addchocolate, String txtname) {
        String pricemessage = txtname + "\n" + "Quantity: " + quantity + "\n" + "Add Whipped Cream ? = " + addwhipedcream + "\n" + "Add Chocolate ? = " + addchocolate + "\n" + "Total: " + price + "\n" + "Thanks.";
        return pricemessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.

     private void displayMessage(String message) {
     TextView OrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
     OrderSummaryTextView.setText(message);
     } (we do not need it anymore because we are emailing the order)*/
}

/**
 * this .xml code i deleted because i am emailing the oreder summery and i do not need this in my program anymore
 * <p>
 * <TextView
 * android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * android:layout_marginBottom="16dp"
 * android:text="Order Summary"
 * android:textAllCaps="true"
 * android:textSize="16sp" />
 * <p>
 * <TextView
 * android:id="@+id/order_summary_text_view"
 * android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * android:text="$0" />
 */