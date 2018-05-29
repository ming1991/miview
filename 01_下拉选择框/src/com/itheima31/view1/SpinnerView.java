package com.itheima31.view1;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SpinnerView extends RelativeLayout {

	private EditText mEdit;

	private ImageView mArrow;

	private PopupWindow mPopupWindow;

	private List<String> mDataList;

	/**
	 * 系统解析xml使用到构造函数
	 * 
	 * @param context
	 * @param attrs
	 */
	public SpinnerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		// 将view_spinner.xml解析出来挂载到SpinnerView内部
		//inflate返回值
		//当提供了root, 返回root
		//当没有提供root, 返回的xml中的根布局
		LayoutInflater.from(getContext()).inflate(R.layout.view_spinner, this);
		
		mockList();
        initView();
        initEvent();
	}

	/**
	 * 通过代码new一个对象 new TextView(context);
	 * 
	 * @param context
	 */
	public SpinnerView(Context context) {
		this(context, null);
	}
	
	private void mockList() {
		mDataList = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			mDataList.add(String.valueOf(i));
		}
		
	}

	private void initEvent() {
		mArrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showPopupWindow();
			}
		});
	}

	protected void showPopupWindow() {
		//popupwindow就创建一次
		if (mPopupWindow == null) {
			int width = mEdit.getWidth();
			int height = 300;
			mPopupWindow = new PopupWindow(width, height);
//			TextView tv = new TextView(this);
//			tv.setBackgroundColor(Color.YELLOW);
			ListView listView = new ListView(getContext());
			//设置listView的背景
			listView.setBackgroundResource(R.drawable.listview_background);
			listView.setAdapter(mBaseAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String data = mDataList.get(position);
					//2. 点击列表选项，在编辑框内显示id
					mEdit.setText(data);
					//设置光标位置
					mEdit.setSelection(data.length());
					//消失popupwindow
					mPopupWindow.dismiss();
				}
			});
			mPopupWindow.setContentView(listView);
			
			mPopupWindow.setOutsideTouchable(true);
			//设置背景之后，Popupwindow内部才会去处理touch事件 
			mPopupWindow.setBackgroundDrawable(new ColorDrawable());
			//让popupwindow可获取焦点
			mPopupWindow.setFocusable(true);
		}
		
		//弹出popupwindow
		mPopupWindow.showAsDropDown(mEdit);
		
	}

	private void initView() {
		mEdit = (EditText) findViewById(R.id.edit);
		mArrow = (ImageView) findViewById(R.id.arrow);
	}
	
	private BaseAdapter mBaseAdapter = new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh = null;
			if (convertView == null) {
//				View.inflate(context, resource, root);
				convertView = LayoutInflater.from(getContext())
						.inflate(R.layout.list_item_view, null);
				
				vh = new ViewHolder(convertView);
				
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			final String data = mDataList.get(position);
			vh.mUserId.setText(data);
			//3. 点击删除按钮，删除列表选项
			vh.mDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//移除对应位置的数据
					mDataList.remove(data);
					//刷新列表
					notifyDataSetChanged();
				}
			});
			
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getCount() {
			return mDataList.size();
		}
	};
	
	private class ViewHolder {
		TextView mUserId;
		ImageView mDelete;
		
		public ViewHolder(View root) {
			mUserId = (TextView) root.findViewById(R.id.user_id);
			mDelete = (ImageView) root.findViewById(R.id.delete);
		}
	}

}
