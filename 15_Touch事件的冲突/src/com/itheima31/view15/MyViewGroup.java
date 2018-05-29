package com.itheima31.view15;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyViewGroup extends RelativeLayout {

	private static final String TAG = "MyViewGroup";
	private float mDownX;
	private float mDownY;

	public MyViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 有条件的去拦截事件
	 * 只拦截左右滑动的事件
	 * 判断x轴的变化量与y轴上的变化量 dx > dy，表示左右滑动
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = ev.getX();
			mDownY = ev.getY();
			
			break;
		case MotionEvent.ACTION_MOVE:
			float moveX = ev.getX();
			float moveY = ev.getY();
			float dx = Math.abs(moveX - mDownX);
			float dy = Math.abs(moveY - mDownY);
			if (dx > dy) {
				return true;
			}
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "onTouchEvent 左右滑动" );
		return true;
	}

}
