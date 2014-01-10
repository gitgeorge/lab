package com.msingiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));

		setContentView(R.layout.splashactivity);

		startAnimating();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	/**
	 * Helper method to start the animation on the splash screen
	 */
	private void startAnimating() {	
		// Fade in bottom title after a built-in delay.
		TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
		Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
		logo2.startAnimation(fade2);
		// Transition to Main Menu when bottom title finishes animating
		fade2.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				// The animation has ended, transition to the Main Menu screen
				startActivity(new Intent(SplashActivity.this, MainMenu.class));
				SplashActivity.this.finish();
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationStart(Animation animation) {
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Stop the animation	
		TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
		logo2.clearAnimation();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Start animating at the beginning so we get the full splash screen
		// experience
		startAnimating();
	}

}
