package com.itheima31.view8;

import com.itheima31.view8.SwitchToggleView.OnSwitchChangeListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SwitchToggleView mSwitchToggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitchToggleView = (SwitchToggleView) findViewById(R.id.switch_toggle);
        mSwitchToggleView.setOnSwitchChangeListener(mOnSwitchChangeListener);
    }
    
    private OnSwitchChangeListener mOnSwitchChangeListener = new OnSwitchChangeListener() {
		
		@Override
		public void onSwitch(boolean isClose) {
			String text = isClose ? "关闭" : "打开";
			Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
		}
	};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
