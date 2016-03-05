package com.example.helenwang.ivote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.widget.TextView;

public class ElectionViewWatch extends Activity implements WearableListView.ClickListener {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.election_view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
//
        tv = (TextView) findViewById(R.id.item_textView1r);
//        Button mFeedBtn = (Button) findViewById(R.id.button);
//
//        Log.d("switch mobile", "5");
//
        if (extras != null) {
            Log.d("nihaoooo", extras.getString("zip_display"));
            tv.setText("37%               63%");
        }
    }


    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {

    }

    @Override
    public void onTopEmptyRegionClick() {
    }
}