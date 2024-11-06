package com.example.assignment2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteLocationActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText addressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_location);

        // Back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DatabaseHelper(this);
        addressInput = findViewById(R.id.addressInput);

        // Set up the Back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());

        Button deleteLocationButton = findViewById(R.id.deleteLocationButton);
        deleteLocationButton.setOnClickListener(v -> deleteLocation());
    }

    private void deleteLocation() {
        String address = addressInput.getText().toString();

        boolean isDeleted = dbHelper.deleteLocation(address);
        if (isDeleted) {
            Toast.makeText(this, "Location deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
        }
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

