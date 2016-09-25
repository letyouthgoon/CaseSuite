 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;

import java.io.IOException;
import java.util.*;

import net.asiasofti.android.R;
import net.asiasofti.android.common.*;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.*;
import net.asiasofti.android.ui.custom.ContactItemLayout;
import net.asiasofti.android.ui.custom.FlowLayout;
import net.asiasofti.android.ui.search.SearchActivity;
import net.asiasofti.android.ui.type.GoodsTabActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends Activity
{
	public class ViewPagerAdapter extends PagerAdapter
	{

	
		private List views;

		public void destroyItem(View view, int j, Object obj)
		{
			((ViewPager)view).removeView((View)views.get(j));
		}

		public int getCount()
		{
			if (views != null)
				return views.size();
			else
				return 0;
		}

		public Object instantiateItem(View view, int j)
		{
			((ViewPager)view).addView((View)views.get(j));
			return views.get(j);
		}

		public boolean isViewFromObject(View view, Object obj)
		{
			return view == obj;
		}

		public ViewPagerAdapter(List list)
		{
			
			super();
			views = list;
		}
	}


	private LayoutInflater FootlayoutInflater;
	private LayoutInflater HeadlayoutInflater;
	private ArrayList arrayList;
	private int count;
	private int i;
	private ArrayList imageViews;
	private LinearLayout ll_point;
	private TextView textHomeSearch;
	private Timer timer;
	private ViewPager viewPager;

	public HomeActivity()
	{
	}

	public void draw_Point(int j)
	{
		int k = 0;
		do
		{
			if (k >= imageViews.size())
			{
				((ImageView)imageViews.get(j)).setImageResource(R.drawable.point_red);
				return;
			}
			((ImageView)imageViews.get(k)).setImageResource(R.drawable.point_gray);
			k++;
		} while (true);
	}

	public Bitmap getBitmap(Bitmap bitmap, int j)
	{
		int k = bitmap.getWidth();
		int l = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float f = (float)j / (float)k;
		matrix.postScale(f, f);
		return Bitmap.createBitmap(bitmap, 0, 0, k, l, matrix, true);
	}

	public void initFootView(ArrayList arraylist)
	{
		
		final Home2List bean0 = (Home2List)arraylist.get(0);
		ImageView imageview = (ImageView)findViewById(R.id.imageBean1Pic);
		MyBackAsynaTask mybackasynatask = new MyBackAsynaTask(bean0.getImage(), imageview);
		mybackasynatask.execute(new String[0]);
		((TextView)findViewById(R.id.textTitleBean1Name)).setText(bean0.getTitle());
		TextView textDesc1Bean1 = (TextView)findViewById(R.id.textDesc1Bean1);
		TextView textDesc2Bean1 = (TextView)findViewById(R.id.textDesc2Bean1);
		String[] as = bean0.getDesc().split(",");
		int j = 0;

		while (j < as.length)
		{
			if (j == 0)
				textDesc1Bean1.setText(as[j]);
			if (j == 1)
				textDesc2Bean1.setText(as[j]);
			j++; 
		}
		

		final Home2List bean1;
		TextView textview2;
		String as1[];
		LinearLayout linearlayout = (LinearLayout)findViewById(R.id.linearlayoutBean1);
		android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

			
			public void onClick(View view)
			{
				String s = bean0.getKeyword();
				if (s.equals("") || s.equals("") || s == null)
				{
					Toast.makeText(HomeActivity.this, "请输入搜索内容", 0).show();
					return;
				} else
				{
					Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
					intent.putExtra("keyword", s);
					intent.putExtra("gc_name", s);
					startActivity(intent);
					return;
				}
			}
			
		};
		linearlayout.setOnClickListener(onclicklistener);
		bean1 = (Home2List)arraylist.get(1);
		ImageView imageview1 = (ImageView)findViewById(R.id.imageBean2Pic);
		MyBackAsynaTask mybackasynatask1 = new MyBackAsynaTask(bean1.getImage(), imageview1);
		mybackasynatask1.execute(new String[0]);
		((TextView)findViewById(R.id.textTitleBean2Name)).setText(bean1.getTitle());
		textview2 = (TextView)findViewById(R.id.textDesc1Bean2);
//		(TextView)findViewById(0x7f09006b);
		as1 = bean1.getDesc().split(",");
		for(int k=0;k<=as1.length-1;k++)
		{
			if (k == 0)
				textview2.setText(as1[k]);
			if (k == 1)
				textview2.setText(as1[k]);
		}
		final Home2List bean2;
		TextView textview3;
		TextView textview4;
		String as2[];
		LinearLayout linearlayout1 = (LinearLayout)findViewById(R.id.linearlayoutBean2);
		android.view.View.OnClickListener onclicklistener1 = new android.view.View.OnClickListener() {

		
			public void onClick(View view)
			{
				String s = bean1.getKeyword();
				if (s.equals("") || s.equals("") || s == null)
				{
					Toast.makeText(HomeActivity.this, "请输入搜索内容", 0).show();
					return;
				} else
				{
					Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
					intent.putExtra("keyword", s);
					intent.putExtra("gc_name", s);
					startActivity(intent);
					return;
				}
			}

			
		};
		linearlayout1.setOnClickListener(onclicklistener1);
		bean2 = (Home2List)arraylist.get(2);
		ImageView imageview2 = (ImageView)findViewById(R.id.imageBean3Pic);
		MyBackAsynaTask mybackasynatask2 = new MyBackAsynaTask(bean2.getImage(), imageview2);
		mybackasynatask2.execute(new String[0]);
		((TextView)findViewById(R.id.textTitleBean3Name)).setText(bean2.getTitle());
		textview3 = (TextView)findViewById(R.id.textDesc1Bean3);
		textview4 = (TextView)findViewById(R.id.textDesc2Bean3);
		as2 = bean2.getDesc().split(",");
		for(int l=0;l<=as2.length-1;l++)
		{
			if (l == 0)
				textview3.setText(as2[l]);
			if (l == 1)
				textview4.setText(as2[l]);
		}
		final Home2List bean3;
		TextView textview5;
		TextView textview6;
		String as3[];
		LinearLayout linearlayout2 = (LinearLayout)findViewById(R.id.linearlayoutBean3);
		android.view.View.OnClickListener onclicklistener2 = new android.view.View.OnClickListener() {



			public void onClick(View view)
			{
				String s = bean2.getKeyword();
				if (s.equals("") || s.equals("") || s == null)
				{
					Toast.makeText(HomeActivity.this, "请输入搜索内容", 0).show();
					return;
				} else
				{
					Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
					intent.putExtra("keyword", s);
					intent.putExtra("gc_name", s);
					startActivity(intent);
					return;
				}
			}

			
		
		};
		linearlayout2.setOnClickListener(onclicklistener2);
		bean3 = (Home2List)arraylist.get(3);
		ImageView imageview3 = (ImageView)findViewById(R.id.imageBean4Pic);
		MyBackAsynaTask mybackasynatask3 = new MyBackAsynaTask(bean3.getImage(), imageview3);
		mybackasynatask3.execute(new String[0]);
		((TextView)findViewById(R.id.textTitleBean4Name)).setText(bean3.getTitle());
		textview5 = (TextView)findViewById(R.id.textDesc1Bean4);
		textview6 = (TextView)findViewById(R.id.textDesc2Bean4);
		as3 = bean3.getDesc().split(",");
        for(int i1=0;i1<=as3.length-1;i1++)
        {
        	if (i1 == 0)
    			textview5.setText(as3[i1]);
    		if (i1 == 1)
    			textview6.setText(as3[i1]);
        }
	
			LinearLayout linearlayout3 = (LinearLayout)findViewById(0x7f090071);
			android.view.View.OnClickListener onclicklistener3 = new android.view.View.OnClickListener() {

				public void onClick(View view)
				{
					String s = bean3.getKeyword();
					if (s.equals("") || s.equals("") || s == null)
					{
						Toast.makeText(HomeActivity.this, "请输入搜索内容", 0).show();
						return;
					} else
					{
						Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
						intent.putExtra("keyword", s);
						intent.putExtra("gc_name", s);
						startActivity(intent);
						return;
					}
				}

			
			
			};
			linearlayout3.setOnClickListener(onclicklistener3);
			return;
		
	}

	public int initGetH(String s)
	{
		int j;
		try
		{
			j = HttpHelper.downloadBitmap(s).getHeight();
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
			return 250;
		}
		return j;
	}

	public int initGetW(String s)
	{
		int j;
		try
		{
			j = HttpHelper.downloadBitmap(s).getWidth();
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
			return 250;
		}
		return j;
	}

	public void initHeadImage(ArrayList arraylist)
	{
		viewPager = (ViewPager)findViewById(R.id.viewpager);
		ll_point = (LinearLayout)findViewById(R.id.ll_point);
		initPagerChild(arraylist);
		int j = SystemHelper.getScreenInfo(this).widthPixels;
		int k = SystemHelper.getScreenInfo(this).heightPixels;
		if (arraylist.size() != 0)
		{
			int temp = (initGetH(((AdvertList)arraylist.get(0)).getImage()) * (j / k));
		}
		android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, 250);
		viewPager.setLayoutParams(layoutparams);
		viewPager.setAdapter(new ViewPagerAdapter(arrayList));
		draw_Point(0);
		timer = new Timer(true);
		timer.schedule(new TimerTask() {

	

			public void run()
			{
				runOnUiThread(new Runnable() {

				

					public void run()
					{
						int j = viewPager.getCurrentItem();
						int k;
						if (j == -1 + arrayList.size())
							k = 0;
						else
							k = j + 1;
						viewPager.setCurrentItem(k);
					}

			
			
				});
			}


		
		}, 5000L, 5000L);
		viewPager.setOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

		

			public void onPageScrollStateChanged(int l)
			{
			}

			public void onPageScrolled(int l, float f, int i1)
			{
			}

			public void onPageSelected(int l)
			{
				draw_Point(l);
			}

			
			
		});
	}

	public void initMiddle(ArrayList arraylist)
	{
		final Home1List bean1;
		FlowLayout flowlayout;
		ImageView imageview;
		String as[];
		bean1 = (Home1List)arraylist.get(0);
		TextView textview = (TextView)findViewById(R.id.textTitleName1);
		flowlayout = (FlowLayout)findViewById(R.id.checkBoxLayout1);
		imageview = (ImageView)findViewById(R.id.imagePic1);
		textview.setText(bean1.getTitle());
		(new MyBackAsynaTask(bean1.getImage(), imageview)).execute(new String[0]);
		as = bean1.getKeyword1().split(",");
		for(int j =0;j<=as.length-1;j++)
		{
			ContactItemLayout contactitemlayout2 = new ContactItemLayout(this, as[j]);
			flowlayout.addView(contactitemlayout2);
		}

		final Home1List bean2;
		FlowLayout flowlayout1;
		ImageView imageview1;
		String as1[];
		android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {


			public void onClick(View view)
			{
				String s = bean1.getKeyword();
				if (s.equals("") || s.equals("") || s == null)
				{
					Toast.makeText(HomeActivity.this, "请输入搜索内容", 0).show();
					return;
				} else
				{
					Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
					intent.putExtra("keyword", s);
					intent.putExtra("gc_name", s);
					startActivity(intent);
					return;
				}
			}

			
		
		};
		imageview.setOnClickListener(onclicklistener);
		bean2 = (Home1List)arraylist.get(1);
		TextView textview1 = (TextView)findViewById(R.id.textTitleName2);
		flowlayout1 = (FlowLayout)findViewById(R.id.checkBoxLayout2);
		imageview1 = (ImageView)findViewById(R.id.imagePic2);
		textview1.setText(bean2.getTitle());
		(new MyBackAsynaTask(bean2.getImage(), imageview1)).execute(new String[0]);
		as1 = bean2.getKeyword1().split(",");
	    for(int k=0;k<= as1.length-1;k++)
		{
			ContactItemLayout contactitemlayout1 = new ContactItemLayout(this, as1[k]);
			flowlayout1.addView(contactitemlayout1);
		
		}
	
		final Home1List bean3;
		FlowLayout flowlayout2;
		ImageView imageview2;
		String as2[];
		android.view.View.OnClickListener onclicklistener1 = new android.view.View.OnClickListener() {

			public void onClick(View view)
			{
				String s = bean2.getKeyword();
				if (s.equals("") || s.equals("") || s == null)
				{
					Toast.makeText(HomeActivity.this, "请输入搜索内容", 0).show();
					return;
				} else
				{
					Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
					intent.putExtra("keyword", s);
					intent.putExtra("gc_name", s);
					startActivity(intent);
					return;
				}
			}

			
		};
		imageview1.setOnClickListener(onclicklistener1);
		bean3 = (Home1List)arraylist.get(2);
		TextView textview2 = (TextView)findViewById(R.id.textTitleName3);
		flowlayout2 = (FlowLayout)findViewById(R.id.checkBoxLayout3);
		imageview2 = (ImageView)findViewById(R.id.imagePic3);
		textview2.setText(bean3.getTitle());
		(new MyBackAsynaTask(bean3.getImage(), imageview2)).execute(new String[0]);
		as2 = bean3.getKeyword1().split(",");
		for(int l=0; l<=as2.length-1;l++)
		{
			ContactItemLayout contactitemlayout = new ContactItemLayout(this, as2[l]);
			flowlayout2.addView(contactitemlayout);
		}

			android.view.View.OnClickListener onclicklistener2 = new android.view.View.OnClickListener() {

				

				public void onClick(View view)
				{
					String s = bean3.getKeyword();
					if (s.equals("") || s.equals("") || s == null)
					{
						Toast.makeText(HomeActivity.this, "请输入搜索内容", 0).show();
						return;
					} else
					{
						Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
						intent.putExtra("keyword", s);
						intent.putExtra("gc_name", s);
						startActivity(intent);
						return;
					}
				}

			
			
			};
			imageview2.setOnClickListener(onclicklistener2);
			return;
		
	}

	public void initPagerChild(ArrayList arraylist)
	{
		arrayList = new ArrayList();
		int j = 0;
		do
		{
			if (j >= arraylist.size())
			{
				initPoint(arraylist);
				return;
			}
			final AdvertList advert = (AdvertList)arraylist.get(j);
			ImageView imageview = new ImageView(this);
			imageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
			(new MyBackAsynaTask(advert.getImage(), imageview)).execute(new String[0]);
			imageview.setOnClickListener(new android.view.View.OnClickListener() {

			

				public void onClick(View view)
				{
					String s = advert.getKeyword();
					if (!s.equals("") && !s.equals("") && s != null)
					{
						Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
						intent.putExtra("keyword", s);
						intent.putExtra("gc_name", s);
						startActivity(intent);
					}
				}

			
			});
			arrayList.add(imageview);
			j++;
		} while (true);
	}

	public void initPoint(ArrayList arraylist)
	{
		imageViews = new ArrayList();
		i = 0;
		do
		{
			if (i >= arraylist.size())
				return;
			ImageView imageview = new ImageView(this);
			android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
			layoutparams.leftMargin = 10;
			layoutparams.rightMargin = 10;
			ll_point.addView(imageview, layoutparams);
			imageViews.add(imageview);
			i = 1 + i;
		} while (true);
	}

	public void loadingHomeData()
	{
		RemoteDataHandler.asyncStringGet(Constants.URL_HOME, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

	
			public void dataLoaded(ResponseData responsedata)
			{
				if (responsedata.getCode() != 200)
				{
					Toast.makeText(HomeActivity.this, "数据加载失败，请稍后重试", 0).show();
					return;
				}
				String s = responsedata.getJson();
				ArrayList arraylist = null;
				ArrayList arraylist1 = null;
				ArrayList arraylist2 = null;
				try
				{
					JSONObject jsonobject = new JSONObject(s);
					String s1 = jsonobject.getString("adv_list");
					String s2 = jsonobject.getString("home1");
					String s3 = jsonobject.getString("home2");
					arraylist = AdvertList.newInstanceList(s1);
					arraylist1 = Home1List.newInstanceList(s2);
					arraylist2 = Home2List.newInstanceList(s3);
				}
				catch (JSONException jsonexception)
				{
					jsonexception.printStackTrace();
					return;
				}
				if (arraylist != null)
				{
					if (arraylist.size() > 0)
					{
						initHeadImage(arraylist);
					}
				}
				
				if (arraylist1 != null)
				{
				if (arraylist1.size() > 0)
					{initMiddle(arraylist1);}
				}
				if (arraylist2 != null)
				{
					if (arraylist2.size() > 0)
						{initFootView(arraylist2);}
				}
				return;
			
			}

			
		});
	}

	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.tab_home);
		textHomeSearch = (TextView)findViewById(R.id.textHomeSearch);
		loadingHomeData();
		textHomeSearch.setOnClickListener(new android.view.View.OnClickListener() {


			public void onClick(View view)
			{
				Intent intent = new Intent(HomeActivity.this, net.asiasofti.android.ui.search.SearchActivity.class);
				startActivity(intent);
			}

			
			
		});
	}

	public boolean onKeyDown(int j, KeyEvent keyevent)
	{
		if (j == 4)
		{
			count = 1 + count;
			if (count >= 3)
			{
				finish();
				return true;
			} else
			{
				Toast.makeText(this, "再点击一次退出程序", 0).show();
				return true;
			}
		} else
		{
			return super.onKeyDown(j, keyevent);
		}
	}

	protected void onResume()
	{
		super.onResume();
		count = 1;
	}


}
