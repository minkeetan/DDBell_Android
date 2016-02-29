package com.example.wavecontrol;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ComponentName;
import android.media.AudioManager;

public class WaveControlTopAppActivity extends Activity
{
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
        mRemoteControlResponder = new ComponentName(getPackageName(), HeadsetActionReceiver.class.getName());
    }
    
    @Override 
    public void onResume() 
    {
    		super.onResume();
    		//IntentFilter filter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
    		//filter.setPriority(1000); //this line sets receiver priority
    		registerReceiver(mHeadsetActionReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
    		//registerReceiver(myHeadsetActionReceiver, filter);
    		mAudioManager.registerMediaButtonEventReceiver(mRemoteControlResponder);
    		
		}

		@Override 
		public void onPause() 
		{
    		//unregisterReceiver(mHeadsetActionReceiver);
    		super.onPause();
		}
		
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mAudioManager.unregisterMediaButtonEventReceiver(mRemoteControlResponder);
        unregisterReceiver(mHeadsetActionReceiver);
    }		
}
