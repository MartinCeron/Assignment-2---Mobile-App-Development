package com.example.assignment2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FindLocationActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText addressInput;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_location);

        // Back button (action bar)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DatabaseHelper(this);
        addressInput = findViewById(R.id.addressInput);
        resultTextView = findViewById(R.id.resultTextView);

        // Set up the Back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());

        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> findLocation());
    }

    private void findLocation() {
        String address = addressInput.getText().toString();
        Cursor cursor = dbHelper.getLocationByAddress(address);

        if (cursor.moveToFirst()) {
            double latitude = cursor.getDouble(0);
            double longitude = cursor.getDouble(1);
            resultTextView.setText("Latitude: " + latitude + "\nLongitude: " + longitude);
        } else {
            Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click
            finish(); // Closes the current activity and returns to the previous one
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // This closes the current activity
    }
}

