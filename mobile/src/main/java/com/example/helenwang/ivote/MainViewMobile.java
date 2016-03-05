package com.example.helenwang.ivote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainViewMobile extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2;
    EditText zipcode;
    String zipcode_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_mobile);

        button1 = (Button)findViewById(R.id.button_zipcode);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button_current_location);
        button2.setOnClickListener(this);
        zipcode = (EditText)findViewById(R.id.edit_zip);

    }

    @Override
    public void onClick(View view) {
        Intent congressionalViewMobile = new Intent(getApplicationContext(), com.example.helenwang.ivote.CongressionalViewMobile.class);
        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
        if (zipcode.getText().length() == 6) {
            zipcode_display = zipcode.getText().toString();
        } else {
            zipcode_display = "32003";
        }
        congressionalViewMobile.putExtra("zip_display", zipcode_display);
        sendIntent.putExtra("zip_display", zipcode_display);

        switch (view.getId()) {
            case R.id.button_zipcode:
                if (zipcode.getText().length() == 6) {
                    startActivity(congressionalViewMobile);
                    startService(sendIntent);
                } else {
                    Toast.makeText(this, "You did not enter a valid zipcode", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button_current_location:
                startActivity(congressionalViewMobile);
                startService(sendIntent);
                break;
        }
    }

}
