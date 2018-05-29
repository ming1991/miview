package com.itheima31.view19;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SlidingMenuView mSlidingMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlidingMenuView = (SlidingMenuView) findViewById(R.id.sliding_menu);
    }
    
    // 4. 点击菜单列表，弹出选项对应的文本，同时关闭侧滑菜单
    public void onTabClick(View v) {
    	//关闭侧滑菜单
    	mSlidingMenuView.toggle();
    	TextView tv = (TextView)v;
    	Toast.makeText(this, tv.getText(), Toast.LENGTH_SHORT).show();
    }

}
