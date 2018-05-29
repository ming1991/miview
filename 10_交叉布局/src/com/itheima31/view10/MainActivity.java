package com.itheima31.view10;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	private MyViewGroup mMyViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyViewGroup = (MyViewGroup) findViewById(R.id.my_view_group);
    }

    public void onRevert(View v) {
    	//交叉布局
    	mMyViewGroup.revert();
    }
    
}
