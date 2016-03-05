package com.example.helenwang.ivote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DetailedViewMobile extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Detail", "5");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view_mobile);
    }
}
