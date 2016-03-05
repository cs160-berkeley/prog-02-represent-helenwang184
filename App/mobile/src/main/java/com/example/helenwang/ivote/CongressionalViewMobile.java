package com.example.helenwang.ivote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class CongressionalViewMobile extends AppCompatActivity implements View.OnClickListener {

    ImageButton button1;
    Button button2;
    String zip_display;
    EditText zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional_view_mobile);
        Intent i = getIntent();
        zip = (EditText)findViewById(R.id.text_calories);
        zip_display = i.getStringExtra("zip_display");
        zip.setText(zip_display);
        button1 = (ImageButton)findViewById(R.id.imageButton);
        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button_zipco234de);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                Intent detailedViewMobile = new Intent(getApplicationContext(), com.example.helenwang.ivote.DetailedViewMobile.class);
                startActivity(detailedViewMobile);
                break;
            case R.id.button_zipco234de:
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                startService(sendIntent);
                break;
        }
    }
}
