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
import android.widget.ImageView;

import java.util.ArrayList;

public class MainViewWatch extends Activity implements WearableListView.ClickListener {

    private WearableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_watch);




//        mFeedBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                startService(sendIntent);
//            }
//        });
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

    private static ArrayList<Candidate> listItems;
    static {
        listItems = new ArrayList<Candidate>();
        listItems.add(new Candidate("Bill Nelson", "Democrat", "Senator", "123@gmail.com", "bill.com", R.drawable.bill, "tweet"));
        listItems.add(new Candidate("Marco Rubio", "Republican", "Senator", "123@gmail.com", "bill.com", R.drawable.marco, "tweet"));
        listItems.add(new Candidate("Corrine Brown", "Democrat", "Representative", "123@gmail.com", "bill.com", R.drawable.corrine, "tweet"));
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

//            if (extras != null) {
//                Log.d("nihaoooo", extras.getString("zip_display"));
//                String catName = extras.getString("zip_display");
//                if (mFeedBtn==null) {
//                    Log.d("WHY IS IT NULL", "WHAT THE");
//                } else {
//                    mFeedBtn.setText("Zipcode " + catName);
//                }
//            }

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

            ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.item_imageView);
            imageView.setImageResource(listItems.get(i).getPicture());

//            TextView view = (TextView) viewHolder.itemView.findViewById(R.id.item_textView);
//            view.setText(listItems.get(i).getPosition());

            Button view1 = (Button) viewHolder.itemView.findViewById(R.id.buttonWOWindividual);
            view1.setText( listItems.get(i).getName());

            Log.d("Detail", "1");
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Detail", "2");
                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                    startService(sendIntent);
                }
            });

//            TextView view2 = (TextView) viewHolder.itemView.findViewById(R.id.item_textView2);
//            view2.setText(listItems.get(i).getParty());

        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }
    }
}
