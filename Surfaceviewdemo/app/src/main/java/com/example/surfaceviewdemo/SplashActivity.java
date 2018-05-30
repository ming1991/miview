package com.example.surfaceviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {
	Button btnstartPortActivity;
	Button btnstartLandActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);

		btnstartLandActivity = (Button) findViewById(R.id.start_landscape_activity);
		btnstartPortActivity = (Button) findViewById(R.id.start_portable_activity);

		/** 竖屏播放*/
		btnstartPortActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SplashActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});

		/** 横屏播放*/
		btnstartLandActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SplashActivity.this,DemoActivty.class);
				startActivity(intent);
			}
		});
		

	}
}
