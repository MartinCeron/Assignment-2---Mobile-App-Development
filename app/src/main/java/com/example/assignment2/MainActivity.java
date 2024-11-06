package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button findLocationButton = findViewById(R.id.findLocationButton);
        Button addLocationButton = findViewById(R.id.addLocationButton);
        Button updateLocationButton = findViewById(R.id.updateLocationButton);
        Button deleteLocationButton = findViewById(R.id.deleteLocationButton);

        findLocationButton.setOnClickListener(v -> openActivity(FindLocationActivity.class));
        addLocationButton.setOnClickListener(v -> openActivity(AddLocationActivity.class));
        updateLocationButton.setOnClickListener(v -> openActivity(UpdateLocationActivity.class));
        deleteLocationButton.setOnClickListener(v -> openActivity(DeleteLocationActivity.class));
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}

