package com.itheima31.view7;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	private MyView mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (MyView) findViewById(R.id.my_view);
    }
    
    public void onStartUpdateProgress(View v) {
    	//更新进度条
//    	mProgressBar.updateProgress(80);
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i =1; i <= 100; i++) {
					mProgressBar.updateProgress(i);
					SystemClock.sleep(50);
				}
			}
		}).start();
    }

    
}
