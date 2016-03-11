package com.example.helenwang.ivote;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainViewMobile extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    Button button1, button2;
    EditText zipcode;
    String zipcode_display;
    private GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    List<Address> addresses;
    String postalCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_mobile);


        button1 = (Button)findViewById(R.id.button_home_current_location);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button_home_search);
        button2.setOnClickListener(this);
        zipcode = (EditText)findViewById(R.id.edit_zip);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_home_current_location:
                Log.d("current location", "5");
                zipcode.setText(postalCode);
                break;

            case R.id.button_home_search:
                Log.d("current location", "6");
                if (zipcode.getText().length() == 5) {
                    zipcode_display = zipcode.getText().toString();
                    Intent congressionalViewMobile = new Intent(getApplicationContext(), com.example.helenwang.ivote.CongressionalViewMobile.class);

//                    Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    congressionalViewMobile.putExtra("zip_display", zipcode_display);
//                    sendIntent.putExtra("zip_display", zipcode_display);
                    startActivity(congressionalViewMobile);
//                    startService(sendIntent);
                } else {
                    Toast.makeText(this, "You did not enter a valid zipcode", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {

        new Thread( new Runnable() {
            @Override
            public void run() {
                Log.d("current location", "1");
                try {
                    Log.d("current location", "2");
                    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                } catch (SecurityException e) {
                    Toast.makeText(getBaseContext(), "The app does not have permission to access current location.", Toast.LENGTH_SHORT).show();
                }

                if (mLastLocation != null) {
                    Log.d("current location", "3");
                    Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                    try {
                        addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
                        postalCode = addresses.get(0).getPostalCode();
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "You did not enter a valid zipcode", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("current location", "4");
                    postalCode = "0";
                }
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}

}
