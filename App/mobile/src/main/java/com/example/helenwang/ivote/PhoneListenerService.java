package com.example.helenwang.ivote;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

//   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
private static final String TOAST = "/send_toast";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
//        if( messageEvent.getPath().equalsIgnoreCase(TOAST) ) {

            // Value contains the String we sent over in WatchToPhoneService, "good job"
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//
//            // Make a toast with the String
//            Context context = getApplicationContext();
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, value, duration);
//            toast.show();

        Log.d("Detail", "4");
        Intent intent = new Intent(this, DetailedViewMobile.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //you need to add this flag since you're starting a new activity from a service
        Log.d("T", "about to start watch MainActivity with CAT_NAME: Fred");
        startActivity(intent);

            // so you may notice this crashes the phone because it's
            //''sending message to a Handler on a dead thread''... that's okay. but don't do this.
            // replace sending a toast with, like, starting a new activity or something.
            // who said skeleton code is untouchable? #breakCSconceptions

//        } else {
//            super.onMessageReceived( messageEvent );
//        }

    }
}
