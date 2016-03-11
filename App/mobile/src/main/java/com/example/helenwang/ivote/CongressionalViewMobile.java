package com.example.helenwang.ivote;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CongressionalViewMobile extends AppCompatActivity implements View.OnClickListener {

    ImageButton button1;
    Button button2;
    String zip_display;
    EditText zip;
    public List<Legislator> myLegislators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional_view_mobile);

        Intent i = getIntent();
        zip = (EditText)findViewById(R.id.text_calories);
        zip_display = i.getStringExtra("zip_display");
        zip.setText(zip_display);

//        populateLegislators();
        Log.d("thread", "-1");
        String baseURL = "https://congress.api.sunlightfoundation.com";
        String apikey = "628b2d7f5645463cb6046121e6d0f5f4";
        String zipAddition = "/legislators/locate?zip=" + zip_display + "&apikey=" + apikey;
        Log.d("thread", zip_display + "zip_display");
        String url = baseURL + zipAddition;
        new ProcessJSON().execute(url);

//        Log.d("adapter", myLegislators.get(0) + "first legislator");
        Log.d("adapter", " 1");
        Log.d("adapter", myLegislators.size() + "after json size");

        registerClickCallback();


//        button1 = (ImageButton)findViewById(R.id.imageButton);
//        button1.setOnClickListener(this);
        button2 = (Button)findViewById(R.id.button_zipco234de);
        button2.setOnClickListener(this);

    }


    private class ProcessJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... strings){
            Log.d("thread", "0");
            String stream;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url

            return stream;
        }

        protected void onPostExecute(String stream){
            //tv.setText(stream);

            /*
                Important in JSON DATA
                -------------------------
                * Square bracket ([) represents a JSON array
                * Curly bracket ({) represents a JSON object
                * JSON object contains key/value pairs
                * Each key is a String and value may be different data types
             */

            //..........Process JSON DATA................
            Log.d("thread", "PARSING");
            if(stream !=null){
                Log.d("thread", "STREAM IS NOT NULL");
                try{
                    Log.d("thread", "trying");
                    JSONObject reader= new JSONObject(stream);
                    for (int i = 0; i < reader.getJSONArray("results").length(); i++) {
                        Log.d("thread", "5");
                        JSONObject array = reader.getJSONArray("results").getJSONObject(i);


                        String name = array.getString("first_name") + " " + array.getString("last_name");
                        Log.d("thread", name);
                        String party = array.getString("party");
                        Log.d("thread", party);
                        String position;

                        if (array.getString("chamber").equals("house")) {
                            position = "Representative";
                        } else {
                            Log.d("position", array.getString("chamber"));
                            position = "Senator";
                        }
                        String email = array.getString("oc_email");
                        String website = array.getString("website");
                        int picture = R.drawable.big_bill;
                        String tweet = "NTSB just announced it will search again for El Faro’s missing data recorder. http://bit.ly/1PPOodK ";
                        String termEnds = array.getString("term_end");
                        String committees = "Aging, Armed Services, Commerce, Science, Transportation, Finance";
                        String bills = "Lithium Battery Safety Act of 2016 Feb 10, 2016";
                        myLegislators.add(new Legislator(name, party, position, email, website, picture, tweet, termEnds, committees, bills));
                        Log.d("adapter", myLegislators.size() + "size");
                    }
                    populateListView();
                }catch(JSONException e){
                    Log.d("thread", "ERRORORR");
                    e.printStackTrace();
                }
            } else {
                Log.d("thread", "STREAM IS NULL");
            }// if statement end
        } // onPostExecute() end
    } // ProcessJSON class end

    private void populateListView() {
        Log.d("adapter",  " 2");
        ArrayAdapter<Legislator> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView);
        Log.d("adapter",  " set");
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailedViewMobile = new Intent(getApplicationContext(), com.example.helenwang.ivote.DetailedViewMobile.class);
                Legislator l = myLegislators.get(position);
                detailedViewMobile.putExtra("picture", l.getPicture() + "");
                detailedViewMobile.putExtra("position", l.getPosition() + "");
                detailedViewMobile.putExtra("name", l.getName() + "");
                detailedViewMobile.putExtra("party", l.getParty() + "");
                detailedViewMobile.putExtra("term_ends", l.getTermEnds() + "");
                detailedViewMobile.putExtra("committees", l.getCommittees() + "");
                detailedViewMobile.putExtra("name", l.getName() + "");
                detailedViewMobile.putExtra("bills", l.getBills() + "");
                startActivity(detailedViewMobile);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Legislator> {
        public MyListAdapter() {
            super(CongressionalViewMobile.this, R.layout.item_view_mobile, myLegislators);
            Log.d("adapter", " 3");
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d("adapter", "WHY");
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view_mobile, parent, false);
            }

            Legislator currentLegislator = myLegislators.get(position);

            TextView makeText = (TextView) itemView.findViewById(R.id.item_name);
            makeText.setText(currentLegislator.getName());

            Log.d("adapter", position + " posititon");
            TextView makeText1 = (TextView) itemView.findViewById(R.id.item_position);
            makeText1.setText(currentLegislator.getPosition());

            TextView makeText2 = (TextView) itemView.findViewById(R.id.item_party);
            makeText2.setText(currentLegislator.getParty());

            TextView makeText3 = (TextView) itemView.findViewById(R.id.item_email);
            makeText3.setText(currentLegislator.getEmail());

            TextView makeText4 = (TextView) itemView.findViewById(R.id.item_website);
            makeText4.setText(currentLegislator.getWebsite());

            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_picture);
            imageView.setImageResource(currentLegislator.getPicture());

            TextView makeText5 = (TextView) itemView.findViewById(R.id.item_tweet);
            makeText5.setText(currentLegislator.getTweet());

            return itemView;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_zipco234de:
                if (zip.getText().length() == 5) {
                    String zipcode_display = zip.getText().toString();
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
}
