package com.example.ming;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout.LayoutParams;

public class CustomSurfaceView extends SurfaceView {

	public static final String TAG = "MyGesture";


	private int screenHeight = 0;

	private int screenWidth = 0;

	private static float touchSlop = 0;

	private int start_Top = -1, start_Right = -1, start_Left = -1, start_Bottom = -1;


	View view;


	private int initViewWidth = 0;

	private int initViewHeight = 0;

	private int fatherView_W;

	private int fatherView_H;

	private int fatherTop;

	private int fatherBottom;


	public CustomSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CustomSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomSurfaceView(Context context) {
		super(context);
		init();
	}


	private void init() {
		touchSlop = ViewConfiguration.getTouchSlop();
		this.setFocusable(true);
		this.setClickable(true);
		this.setLongClickable(true);
		view = CustomSurfaceView.this;
		screenHeight = ScreenUtils.getScreenHeight(getContext());
		screenWidth = ScreenUtils.getScreenWidth(getContext());

	}


	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (start_Top == -1) {
			start_Top = top;
			start_Left = left;
			start_Right = right;
			start_Bottom = bottom;
			initViewWidth = view.getWidth();
			initViewHeight = view.getHeight();
			Log.d(TAG, "onLayout: initViewWidth = " + initViewWidth + "initViewHeight = " + initViewHeight);
		}
	}


	/**
	 * 实现拖动的处理
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	private void setPosition(int left, int top, int right, int bottom) {
		this.layout(left, top, right, bottom);

	}

	/**
	 * surfaceView父控件的宽高
	 * @param fatherView_Width
	 * @param fatherView_Height
	 */
	public void setFatherW_H(int fatherView_Width, int fatherView_Height) {
		this.fatherView_W = fatherView_Width;
		this.fatherView_H = fatherView_Height;
	}

	public void setLayoutParam(float scale) {
		LayoutParams layoutParams = (LayoutParams) getLayoutParams();
		layoutParams.gravity = Gravity.CENTER;
		layoutParams.width = (int) (scale * (layoutParams.width));
		layoutParams.height = (int) (scale * (layoutParams.height));
		setLayoutParams(layoutParams);
	}


	public void setFatherTopAndBottom(int fatherTop, int fatherBottom) {
		this.fatherTop = fatherTop;
		this.fatherBottom = fatherBottom;
	}

	public void setExtend(int ratio) {
		int left = 0, top = 0, right = 0, bottom = 0 ,length;
		left = getLeft();
		top = getTop();
		bottom = getBottom();
		right = getRight();

		length = (int)(getHeight() * (ratio - 1));
		left = (int) (left - length / 2);
		right = (int) (right + length / 2);
		bottom = (int) (bottom + length / 2);
		top = (int) (top - length / 2);

		setPosition(left, top, right, bottom);


	}

	public void setOriginal() {

		setPosition(0, 0, fatherView_W, fatherView_H);
	}



	
}
