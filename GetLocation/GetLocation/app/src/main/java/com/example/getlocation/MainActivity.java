package com.example.getlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);

        Button button = findViewById(R.id.getLocation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION)
                                                       != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                client.getLastLocation().addOnSuccessListener(MainActivity.this,
                                                               new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if(location != null) {
                                    TextView textView = findViewById(R.id.location);
                                    String result = "Longtitude: " + location.getLongitude() + "    Lattitude: " + location.getLatitude();
                                    textView.setText(result);
                                }
                            }
                        });
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[] {ACCESS_FINE_LOCATION}, 1);
    }
}
