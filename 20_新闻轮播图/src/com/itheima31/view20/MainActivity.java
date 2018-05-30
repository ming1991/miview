package com.itheima31.view20;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	protected static final String TAG = "MainActivity";

	private ViewPager mViewPager;
	
	private TextView mTitle;
	
	private LinearLayout mDotsContainer;
	
	private int mLastPosition = 0;
	
	private int[] mImages = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3,
			R.drawable.icon_4, R.drawable.icon_5};
	
	private String[] mTitles = {"为梦想坚持", "我相信我是黑马", "黑马公开课", "Google/IO", "轻松1w+"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDots();
        mTitle.setText(mTitles[0]);
        

        //ListView -> setAdapter -> 创建Adapter -> 数据集合
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        
        
        //设置viewpager初始化位置
        int initPosition = Integer.MAX_VALUE / 2;
        //跳转位置
        initPosition = initPosition - initPosition % mImages.length;
        mViewPager.setCurrentItem(initPosition);
        
//        mViewPager.setOffscreenPageLimit(3);//设置左右两边各缓存三页
//        mViewPager.addView(new TextView(MainActivity.this));
    }

	private void initDots() {
		for (int i = 0; i < mImages.length; i++) {
			View dot = new View(this);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(5, 5);
			//最后一个点不要margin
			if (i != mImages.length - 1) {
				layoutParams.rightMargin = 8;
			}
			
			dot.setLayoutParams(layoutParams);
			//第一点默认选中
			if (i == 0) {
				dot.setBackgroundResource(R.drawable.dot_selected);
			} else {
				dot.setBackgroundResource(R.drawable.dot_normal);
			}
			
			//添加到容器
			mDotsContainer.addView(dot);
		}
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.vp);
        mTitle = (TextView) findViewById(R.id.tv);
        mDotsContainer = (LinearLayout) findViewById(R.id.dots_container);
	}
    
    
    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
		
    	/**
    	 * 当页面被选中时回调
    	 * 
    	 * @param position 选中页面的位置
    	 */
		@Override
		public void onPageSelected(int position) {
			Log.d(TAG, "onPageSelected " + position);
			position = position % mImages.length;//取模
			Log.d(TAG, "position " + position);
			mTitle.setText(mTitles[position]);
			//如果选中点的位置跟上次选中点是同一个位置，不做处理
			if (position == mLastPosition) {
				return;
			}
			
			
			
			//选中点变红
			View dot = mDotsContainer.getChildAt(position);
			dot.setBackgroundResource(R.drawable.dot_selected);
			
			//把上次选中的点变白
			View lastDot = mDotsContainer.getChildAt(mLastPosition);
			lastDot.setBackgroundResource(R.drawable.dot_normal);
			
			//更新上次选中点
			mLastPosition = position;
			
		}
		
		/**
		 * 当页面滚动时的回调
		 * 
		 * @param position 滚动页面的下标
		 * @param positionOffset 滚动偏移量的像素个数 / ViewPager宽度
		 * @param positionOffsetPixels 滚动偏移量的像素个数
		 */
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
//			Log.d(TAG, "onPageScrolled " + position + " " + " " + positionOffset + " " + positionOffsetPixels);
		}
		
		/**
		 * 页面滚动状态变化时回调
		 * 
		 * @param state 
		 */
		@Override
		public void onPageScrollStateChanged(int state) {
			//public static final int SCROLL_STATE_IDLE = 0; 空闲状态
			//public static final int SCROLL_STATE_DRAGGING = 1; 拖拽状态
			//public static final int SCROLL_STATE_SETTLING = 2; 设置状态 拖拽状态到空闲状态中间状态
			
			Log.d(TAG, "onPageScrollStateChanged " + state);
			
		}
	};
    
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
		
    	/**
    	 * 判断view是否被标记
    	 * 
    	 * @param view ViewPager内部的孩子
    	 * @param object ViewPager内部页面的标记
    	 * 
    	 * @return true 孩子被标记过 是合法孩子，才去显示出来
    	 */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		/**
		 * 返回页面的个数
		 */
		@Override
		public int getCount() {
//			return mImages.length;
			//为循环切换，加大页面个数
			return Integer.MAX_VALUE;
		}
		
		/**
		 * 初始化页面
		 * 
		 * @param position 初始化页面的位置
		 * @param container 就是ViewPager
		 * 
		 * @return 返回页面的标记
		 */
		public Object instantiateItem(android.view.ViewGroup container, int position) {
//			Log.d(TAG, " instantiateItem " + position);
			//5--> 0
			//6-->1
			//7-->2
			// ----
			position = position % mImages.length;//取模
			//将对应position位置页面加载ViewPager内
			ImageView page = new ImageView(MainActivity.this);
			page.setImageResource(mImages[position]);
			container.addView(page);
			
			return page;
		};
		
		/**
		 * 销毁页面
		 * 
		 * @param container ViewPager
		 * @param position 销毁页面的下标
		 * @param object 销毁页面的标记
		 * 
		 */
		public void destroyItem(android.view.ViewGroup container, int position, Object object) {
//			Log.d(TAG, "destroyItem " + position);
			//将要销毁的页面从ViewPager里面移除
			container.removeView((View)object);
		};
	};
    
}
