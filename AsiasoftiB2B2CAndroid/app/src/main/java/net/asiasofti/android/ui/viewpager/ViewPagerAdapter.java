package net.asiasofti.android.ui.viewpager;


import java.util.Arrays;
import java.util.HashMap;

import net.asiasofti.android.StartActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author frankiewei
 * 相册的适配器.
 */
public class ViewPagerAdapter extends PagerAdapter {

	private Activity activity;
	private int ALBUM_RES[];
	
	/**
	 * 上下文
	 */
	private Context mContext;
	
	/**
	 * 数据源,这里是JSONARRAY
	 */
	private JSONArray mJsonArray;
	
	/**
	 * Hashmap保存相片的位置以及ItemView.
	 */
	private HashMap<Integer, ViewPagerItemView> mHashMap;
	
	public ViewPagerAdapter(Context context,JSONArray arrays, Activity activity,int ALBUM_RES[]) {
		this.mContext = context;
		this.mJsonArray = arrays;
		this.activity=activity;
		this.ALBUM_RES=ALBUM_RES;
		mHashMap = new HashMap<Integer, ViewPagerItemView>();
	}
	
	//这里进行回收，当我们左右滑动的时候，会把早期的图片回收掉.
	@Override
	public void destroyItem(View container, int position, Object object) {
		ViewPagerItemView itemView = (ViewPagerItemView)object;
		itemView.recycle();
	}
	
	@Override
	public void finishUpdate(View view) {

	}

	//这里返回相册有多少条,和BaseAdapter一样.
	@Override
	public int getCount() {
		return mJsonArray.length();
	}

	//这里就是初始化ViewPagerItemView.如果ViewPagerItemView已经存在,
	//重新reload，不存在new一个并且填充数据.
	@Override
	public Object instantiateItem(View container, final int position) {	
		ViewPagerItemView itemView;
		if(mHashMap.containsKey(position)){
			itemView = mHashMap.get(position);
			itemView.reload();
		}else{
			itemView = new ViewPagerItemView(mContext);
			try {
				JSONObject dataObj = (JSONObject) mJsonArray.get(position);
				itemView.setData(dataObj,ALBUM_RES,position);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			mHashMap.put(position, itemView);
			((ViewPager) container).addView(itemView);
		}
		
      itemView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int i=position;
				if (i==mJsonArray.length()-1) {
					Intent intent=new Intent();
					intent.setClass(mContext, StartActivity.class);
					mContext.startActivity(intent);
					activity.finish();
					
				}
			}
			
		});
		
		return itemView;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View view) {

	}
}
