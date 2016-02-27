package com.example.mycoolweather.widget;

import com.example.mycoolweather.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class FirstActivity extends Activity{
	
	private ImageView imageLogo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start_activity);
		imageLogo = (ImageView)findViewById(R.id.img_log);
		AlphaAnimation animation = new AlphaAnimation(0.5f, 1.0f);
		animation.setDuration(2500);
		imageLogo.setAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FirstActivity.this, ChooseAreaActivity.class);
				startActivity(intent);
				finish();
				onDestroy();
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
