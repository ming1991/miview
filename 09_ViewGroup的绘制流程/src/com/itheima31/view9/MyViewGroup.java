package com.itheima31.view9;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyViewGroup extends ViewGroup {

	private static final String TAG = "MyViewGroup";
	private View mChild;


	public MyViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//检查孩子的大小
		mChild = getChildAt(0);
//		int childMeasuredWidth = mChild.getMeasuredWidth();
//		int childMeasuredHeight = mChild.getMeasuredHeight();
//		Log.d(TAG, "child " + childMeasuredWidth + " " + childMeasuredHeight);
		
		//获取孩子向我申请的宽高
		LayoutParams layoutParams = mChild.getLayoutParams();//布局文件中带layout属性的值
		int childWidth = layoutParams.width;//-->layout_width;
		int childHeight = layoutParams.height;//-->layout_height;
		
		//构造对孩子宽高的期望
		int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
		int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
		
		//测量孩子
		mChild.measure(childWidthMeasureSpec, childHeightMeasureSpec);
		
		int childMeasuredWidth = mChild.getMeasuredWidth();
		int childMeasuredHeight = mChild.getMeasuredHeight();
		Log.d(TAG, "child " + childMeasuredWidth + " " + childMeasuredHeight);
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	

	/**
	 * 作为一个ViewGroup，要实现onLayout方法 去布局孩子，指定孩子上下左右的位置,mChild.layout(left, top, right, bottom);布局孩子
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		//布局TextView
		int left = 0;
		int top = 0;
		int right = left + mChild.getMeasuredWidth();//获取测量好的宽度
		int bottom = top + mChild.getMeasuredHeight();
		
//		mChild.getWidth();//mRight - mLeft;
		Log.d(TAG, "child before layout " + mChild.getWidth());
		mChild.layout(left, top, right, bottom);
		//布局之后才会对mRight和mLeft赋值，mChild.getWidth()才会有值
		Log.d(TAG, "child after layout " + mChild.getWidth());
		
	}

}
