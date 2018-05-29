package com.itheima31.view18;

import com.itheima31.view18.LockView.OnUnlockListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private LockView mLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLockView = (LockView) findViewById(R.id.lock_view);
        mLockView.setOnUnlockListener(new OnUnlockListener() {
			
			@Override
			public void onUnlock() {
				finish();
			}
		});
    }


    
}
