package hilmanshini.speaqs.activity;

import hilmanshini.speaqs.R;
import speaqs.hilmanshini.tool.AndroidTool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

public class SplashActivity extends Activity {
	Handler h = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			//Intent i = new Intent(getApplicationContext(),WelcomeActivity.class);
			//finish();
			//startActivity(i);
			super.handleMessage(msg);
		}
	}; 
	View v1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		AndroidTool.makeActivityFullScreen(this);
		setContentView(R.layout.intro);
		Animation fadeIn = new AlphaAnimation(0, 1);
	    fadeIn.setDuration(1000);

	    
	    LayoutInflater v = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
	    v1 =v.inflate(R.layout.intro, null);
	    v1.setAnimation(fadeIn);
	    setContentView(v1);
	    v1.startAnimation(fadeIn);
		final Thread e = new Thread(){
			@Override
			public void run() {
				try {
					sleep(4000);
					h.dispatchMessage(h.obtainMessage());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		e.start();
	}
}
