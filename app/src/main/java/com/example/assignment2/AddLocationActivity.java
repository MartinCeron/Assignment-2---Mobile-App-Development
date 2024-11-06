package com.example.assignment2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddLocationActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText addressInput, latitudeInput, longitudeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        // Back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DatabaseHelper(this);
        addressInput = findViewById(R.id.addressInput);
        latitudeInput = findViewById(R.id.latitudeInput);
        longitudeInput = findViewById(R.id.longitudeInput);

        // Set up the Back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());

        Button addLocationButton = findViewById(R.id.addLocationButton);
        addLocationButton.setOnClickListener(v -> addLocation());
    }

    private void addLocation() {
        String address = addressInput.getText().toString();
        double latitude = Double.parseDouble(latitudeInput.getText().toString());
        double longitude = Double.parseDouble(longitudeInput.getText().toString());

        boolean isInserted = dbHelper.addLocation(address, latitude, longitude);
        Toast.makeText(this, isInserted ? "Location added" : "Error adding location", Toast.LENGTH_SHORT).show();
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

