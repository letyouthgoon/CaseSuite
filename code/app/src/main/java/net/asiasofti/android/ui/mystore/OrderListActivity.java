 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.ui.mystore;

import android.app.Activity;
import android.content.*;
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.OrderGroupListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.OrderGroupList2;
import net.asiasofti.android.model.ResponseData;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderListActivity extends Activity
	implements android.widget.AbsListView.OnScrollListener
{

	private OrderGroupListViewAdapter adapter;
	private ImageView imageBack;
	private int lastItem;
	private ListView listViewOrder;
	private Boolean list_flag;
	private ArrayList lists;
	private BroadcastReceiver mBroadcastReceiver;
	private Handler mHandler;
	private View moreView;
	private MyApp myApp;
	public int pageno;

	public OrderListActivity()
	{
		list_flag = Boolean.valueOf(false);
		pageno = 1;
		mBroadcastReceiver = new BroadcastReceiver() {

		

			public void onReceive(Context context, Intent intent)
			{
				if (intent.getAction().equals(Constants.APP_BORADCASTRECEIVER))
					loadingfavoritesListData();
			}

			
		
		};
		mHandler = new Handler() {

			

			public void handleMessage(Message message)
			{
				OrderListActivity orderlistactivity;
				switch (message.what)
				{
				case 1: // '\001'
				default:
					return;

				case 0: // '\0'
					orderlistactivity = OrderListActivity.this;
					break;
				}
				orderlistactivity.pageno = 1 + orderlistactivity.pageno;
				loadingfavoritesListData();
			}

			
		
		};
	}

	public void loadingfavoritesListData()
	{
		String s = (new StringBuilder(Constants.URL_ORDER_LIST + "&curpage=")).append(pageno).append("&page=").append(10).toString();
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		RemoteDataHandler.asyncPost2(s, hashmap, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {


			public void dataLoaded(ResponseData responsedata)
			{
				if (responsedata.getCode() == 200)
				{
				String s1 = responsedata.getJson();
				if (responsedata.getLogin() == 0)
					myApp.setLoginKey("");
				String s2;
				if (!responsedata.isHasMore())
				{
					list_flag = Boolean.valueOf(true);
					listViewOrder.removeFooterView(moreView);
				} else
				{
					list_flag = Boolean.valueOf(false);
					moreView.setVisibility(0);
				}
				if (pageno == 1)
					lists.clear();
				try
				{
					String s3 = (new JSONObject(s1)).getString("order_group_list");
					System.out.println((new StringBuilder("order_group_list-->")).append(s3).toString());
					ArrayList arraylist = OrderGroupList2.newInstanceList(s3);
					lists.addAll(arraylist);
					adapter.setOrderLists(lists);
					adapter.notifyDataSetChanged();
				}
				catch (JSONException jsonexception)
				{
					jsonexception.printStackTrace();
				}
				try
				{
					s2 = (new JSONObject(s1)).getString("error");
				}
				catch (JSONException jsonexception1)
				{
					jsonexception1.printStackTrace();
					return;
				}
				if (s2 != null)
					
					Toast.makeText(OrderListActivity.this, s2, 0).show();
				return;
				}
				Toast.makeText(OrderListActivity.this, "数据加载失败，请稍后重试", 0).show();
				return;
			}

			
			
		});
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.order_listview);
		myApp = (MyApp)getApplication();
		listViewOrder = (ListView)findViewById(R.id.listViewOrder);
		imageBack = (ImageView)findViewById(R.id.imageBack);
		moreView = getLayoutInflater().inflate(R.layout.list_more_load, null);
		adapter = new OrderGroupListViewAdapter(this);
		lists = new ArrayList();
		listViewOrder.addFooterView(moreView);
		listViewOrder.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		loadingfavoritesListData();
		listViewOrder.setOnScrollListener(this);
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {

			

			public void onClick(View view)
			{
				finish();
			}

			
			
		});
	}

	protected void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(mBroadcastReceiver);
	}

	public void onScroll(AbsListView abslistview, int i, int j, int k)
	{
		lastItem = -1 + (i + j);
	}

	public void onScrollStateChanged(AbsListView abslistview, int i)
	{
		if (lastItem == -1 + listViewOrder.getCount() && i == 0 && !list_flag.booleanValue())
			mHandler.sendEmptyMessage(0);
	}

	protected void onStart()
	{
		super.onStart();
		registerBoradcastReceiver();
	}

	public void registerBoradcastReceiver()
	{
		IntentFilter intentfilter = new IntentFilter();
		intentfilter.addAction(Constants.APP_BORADCASTRECEIVER);
		registerReceiver(mBroadcastReceiver, intentfilter);
	}






}
