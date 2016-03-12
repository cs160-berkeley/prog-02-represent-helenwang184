package com.example.helenwang.ivote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class MainViewWatch extends Activity implements WearableListView.ClickListener {

    private WearableListView listView;
    private ArrayList<Phone_legis> phone_legises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Watch", "6");
        setContentView(R.layout.activity_main_view_watch);
        phone_legises = new ArrayList<>();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
             @Override
             public void onLayoutInflated(WatchViewStub stub) {
                 listView = (WearableListView) findViewById(R.id.listView1);
                 listView.setAdapter(new MyAdapter(MainViewWatch.this));
                 listView.setClickListener(MainViewWatch.this);
             }
        });
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {

    }

    @Override
    public void onTopEmptyRegionClick() {}

    private class MyAdapter extends WearableListView.Adapter {

        private final LayoutInflater inflater;

        private MyAdapter(Context c) {
            inflater = LayoutInflater.from(c);
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Button mFeedBtn = (Button) findViewById(R.id.buttonWOWonly);

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            if (extras != null) {
                Log.d("Watch", "7");
                String[] ppl = extras.getString("msg").split("|");
                for (int index = 0; index < ppl.length; index++) {
                    String[] person = ppl[index].split(",");
                    phone_legises.add(new Phone_legis(person[0], person[1], person[2]));
                }
            }

            mFeedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent(getBaseContext(), ElectionViewWatch.class);
                    startActivity(sendIntent);
                }
            });

            return new WearableListView.ViewHolder(inflater.inflate(R.layout.row_simple_item_layout, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int i) {

//            TextView view = (TextView) viewHolder.itemView.findViewById(R.id.item_textView);
//            view.setText(listItems.get(i).getPosition());

            Button view1 = (Button) viewHolder.itemView.findViewById(R.id.buttonWOWindividual);
            view1.setText(phone_legises.get(i).getName());

            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                    startService(sendIntent);
                }
            });

//            TextView view2 = (TextView) viewHolder.itemView.findViewById(R.id.item_textView2);
//            view2.setText(listItems.get(i).getParty());

        }

        @Override
        public int getItemCount() {
            return phone_legises.size();
        }
    }
}
