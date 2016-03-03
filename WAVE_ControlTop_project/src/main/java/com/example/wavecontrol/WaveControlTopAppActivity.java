package com.example.wavecontrol;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ComponentName;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class WaveControlTopAppActivity extends Activity
{
		private static final String TAG = "HeadsetActivity";
		private HeadsetActionReceiver mHeadsetActionReceiver;
		private AudioManager mAudioManager;
    private ComponentName mRemoteControlResponder;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mHeadsetActionReceiver = new HeadsetActionReceiver();
        
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        mRemoteControlResponder = new ComponentName(getPackageName(), mHeadsetActionReceiver.getClass().getName());
    }
    
    @Override 
    public void onResume() 
    {
    		super.onResume();
    		registerReceiver(mHeadsetActionReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
    		mAudioManager.registerMediaButtonEventReceiver(mRemoteControlResponder);
    		
		}

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mAudioManager.unregisterMediaButtonEventReceiver(mRemoteControlResponder);
        unregisterReceiver(mHeadsetActionReceiver);
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
    		int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) 
        {
        		case KeyEvent.KEYCODE_VOLUME_DOWN:
        				if(KeyEvent.ACTION_DOWN == action)
								{
        					Log.d(TAG, "dispatchKeyEvent: Volume down is pressed");
		            	Toast.makeText(getApplicationContext(), "dispatchKeyEvent: Volume down", Toast.LENGTH_SHORT).show();
		            }
            		return false;
        		case KeyEvent.KEYCODE_VOLUME_UP:
        				if(KeyEvent.ACTION_DOWN == action)
								{        		
        					Log.d(TAG, "dispatchKeyEvent: Volume up is pressed");
		            	Toast.makeText(getApplicationContext(), "dispatchKeyEvent: Volume up", Toast.LENGTH_SHORT).show();
		            }
		            return false;
        		default:
            		return super.dispatchKeyEvent(event);
        }
    }		
}
