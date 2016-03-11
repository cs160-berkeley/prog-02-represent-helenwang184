package com.example.helenwang.ivote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedViewMobile extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Detail", "5");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view_mobile);

        Intent i = getIntent();
        int picture = Integer.parseInt(i.getStringExtra("picture"));
        String position = i.getStringExtra("position");
        String name = i.getStringExtra("name");
        String party = i.getStringExtra("party");
        String term_ends = i.getStringExtra("term_ends");
        String committees = i.getStringExtra("committees");
        String bills = i.getStringExtra("bills");

        ImageView detailed_picture = (ImageView)findViewById(R.id.detailed_picture);
        TextView detailed_position = (TextView)findViewById(R.id.detailed_position);
        TextView detailed_name = (TextView)findViewById(R.id.detailed_name);
        TextView detailed_party = (TextView)findViewById(R.id.detailed_party);
        TextView detailed_term_ends = (TextView)findViewById(R.id.detailed_term_ends);
        TextView detailed_committees = (TextView)findViewById(R.id.detailed_committees);
        TextView detailed_bills = (TextView)findViewById(R.id.detailed_bills);

        detailed_picture.setImageResource(picture);
        detailed_position.setText(position);
        detailed_name.setText(name);
        detailed_party.setText(party);
        detailed_term_ends.setText(term_ends);
        detailed_committees.setText(committees);
        detailed_bills.setText(bills);

    }
}
