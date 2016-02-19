package com.example.hpq368.test;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class HelloWorld extends AppCompatActivity {
    private EditText output;
    private HeadsetAction remote_intent;
    private IntentFilter filter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        remote_intent = new HeadsetAction();

        /* This is to add headset detection plug in or plug out using the broadcast intent */
        filter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        //filter.addAction(Intent.ACTION_MEDIA_BUTTON);
        registerReceiver(remote_intent, filter);

        /* This section is to add listener for button press on the headset. */

        /* This section is test some function */
        output = (EditText) findViewById(R.id.editText);

        output.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                    Log.d("gina", "Button press " + keyCode +" " +event);
                return true;
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void button_pressed(View v) {
        Log.d("hello", "Button1 pressed");
        output.setText("Hello Button 1 Press", TextView.BufferType.NORMAL);
    }

    public void button2_pressed(View v) {
        output.setText("Hello Button 2 Press", TextView.BufferType.NORMAL);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "HelloWorld Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.hpq368.test/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "HelloWorld Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.hpq368.test/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private static class HeadsetAction extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("hdset", "Intent Receive " + intent.getAction());
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                switch (intent.getIntExtra("state", 0xFF)) {
                    case 0:
                        Log.d("hdset", "Headset disconnected");
                        //output.setText("Headset Disconnected", TextView.BufferType.NORMAL);
                        break;

                    case 1:
                        Log.d("hdset", "Headset connected");
                        //output.setText("Headset connected", TextView.BufferType.NORMAL);
                        if (intent.getIntExtra("microphone", 0xFF) == 1) {
                            Log.d("hdset", "Mic connected");
                        }
                        break;

                    default:
                        Log.d("hdset", "Wrong Parameter");
                        //output.setText("Error, Wrong Parameter", TextView.BufferType.NORMAL);
                        break;
                }
            } else if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON)) {
                KeyEvent event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

                Log.d("hdset", "Media Button Pressed " + event.getKeyCode());
            }
        }
    }
}

