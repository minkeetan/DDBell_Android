package com.example.wavecontrol;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.view.KeyEvent;
import android.util.Log;

public class HeadsetActionReceiver extends BroadcastReceiver
{
		private static final String TAG = "HeadsetActivity";
		
		@Override 
		public void onReceive(Context context, Intent intent) 
		{
				String intentAction = intent.getAction();
			
    		if (intentAction.equals(Intent.ACTION_HEADSET_PLUG)) 
    		{
        		int state = intent.getIntExtra("state", 0xFF);
        		switch (state) 
        		{
		            case 0:
		                Log.d(TAG, "Headset is unplugged");
		                Toast.makeText(context, context.getString(R.string.headset_unplugged), Toast.LENGTH_SHORT).show();
		                break;
		            case 1:
		                Log.d(TAG, "Headset is plugged");
		                Toast.makeText(context, context.getString(R.string.headset_plugged), Toast.LENGTH_SHORT).show();
		                break;
		            default:
		                Log.d(TAG, "I have no idea what the headset state is");
		                break;
            }
    		}
    		else if(intentAction.equals(Intent.ACTION_MEDIA_BUTTON))
    		{
    				KeyEvent extraEvent = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        		
        		int extraAction = extraEvent.getAction();
        		int extraKeyCode = extraEvent.getKeyCode();
						
        		if(KeyEvent.ACTION_UP == extraAction) 
        		{
        				switch(extraKeyCode)
								{
										case KeyEvent.KEYCODE_VOLUME_DOWN:
												Log.d(TAG, "Volume down is pressed");
		                		Toast.makeText(context, context.getString(R.string.volume_down), Toast.LENGTH_SHORT).show();
												break;
								
										case KeyEvent.KEYCODE_VOLUME_UP:
												Log.d(TAG, "Volume up is pressed");
		                		Toast.makeText(context, context.getString(R.string.volume_up), Toast.LENGTH_SHORT).show();
												break;
										
										case KeyEvent.KEYCODE_HEADSETHOOK:
												Log.d(TAG, "Play/pause/hook is pressed");
		                		Toast.makeText(context, context.getString(R.string.media_play_pause), Toast.LENGTH_SHORT).show();
												break;
										
										default:
												Log.d(TAG, "Unknown Keycode");
												Toast.makeText(context, "Unknown Keycode:" + extraKeyCode, Toast.LENGTH_SHORT).show();
												break;		
								}
        		}
    		}
		}
}