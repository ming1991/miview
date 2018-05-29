package com.itheima31.view8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SwitchToggleView extends View {

	private Bitmap mSwitchBackground;
	private Paint mPaint;
	private Bitmap mSwitchButton;
	private boolean isClose = false;
	
	private OnSwitchChangeListener mOnSwitchChangeListener;

	public SwitchToggleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mSwitchBackground = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
		mSwitchButton = BitmapFactory.decodeResource(getResources(), R.drawable.switch_button);
		setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//改变开关的状态
				isClose = !isClose;
				//刷新ui
				invalidate();//--》onDraw
				
				if (mOnSwitchChangeListener != null) {
					mOnSwitchChangeListener.onSwitch(isClose);
				}
			}
		});
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//设置成背景图片的宽高
		int measuredWidth = mSwitchBackground.getWidth();
		int measuredHeight = mSwitchBackground.getHeight();
		setMeasuredDimension(measuredWidth, measuredHeight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		//画背景图片
		canvas.drawBitmap(mSwitchBackground, 0, 0, mPaint);
		//画滑块
		if (isClose) {
			canvas.drawBitmap(mSwitchButton, 0, 0, mPaint);
		} else {
			//滑块开
			int left = mSwitchBackground.getWidth() - mSwitchButton.getWidth();
			canvas.drawBitmap(mSwitchButton, left, 0, mPaint);
		}
	}
	
	public interface OnSwitchChangeListener {
		//什么事件声明什么方法
		void onSwitch(boolean isClose);
	}
	
	public void setOnSwitchChangeListener(OnSwitchChangeListener l) {
		mOnSwitchChangeListener = l;
	}

}
