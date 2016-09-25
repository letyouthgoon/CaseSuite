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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.SubmenuListViewAdapter;
import net.asiasofti.android.common.*;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.*;
import net.asiasofti.android.ui.custom.MyListView;
import net.asiasofti.android.ui.more.AboutActivity;

import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package net.asiasofti.android.ui.mystore:
//			LoginActivity, OrderListActivity, FavoritesListActivity, AddressListActivity, 
//			HistoryBrowseListActivity, HelpActivity, RegisteredActivity

public class MyStoreActivity extends Activity
{

	private ScrollView SView;
	private SubmenuListViewAdapter adapter;
	private Button buttonLoginSubmit;
	private Button buttonLoingOut;
	private Button buttonRegistered;
	private ImageView imageMyAvator;
	private LinearLayout linearLayoutNOLogin;
	private LinearLayout linearLayoutYesLogin;
	private BroadcastReceiver mBroadcastReceiver;
	private MyApp myApp;
	private MyListView mystoreListView;
	private TextView textMyPoint;
	private TextView textMyPredepoit;
	private TextView textMyUserName;

	public MyStoreActivity()
	{
		mBroadcastReceiver = new BroadcastReceiver() {

			

			public void onReceive(Context context, Intent intent)
			{
				if (intent.getAction().equals(Constants.APP_BORADCASTRECEIVER))
					loadingMyStoreData();
			}

			
			
		};
	}

	public void delAllFile(String s)
	{
		File file;
		file = new File(s);
		if (file.exists() && file.isDirectory())
		{
			String as[] = file.list();
			int i = 0;
			while (i < as.length) 
			{
				File file1;
				if (s.endsWith(File.separator))
					file1 = new File((new StringBuilder(String.valueOf(s))).append(as[i]).toString());
				else
					file1 = new File((new StringBuilder(String.valueOf(s))).append(File.separator).append(as[i]).toString());
				if (file1.isFile())
					file1.delete();
				if (file1.isDirectory())
					delAllFile((new StringBuilder(String.valueOf(s))).append("/").append(as[i]).toString());
				i++;
			}
		}
		return;
	}

	public void loadingMyStoreData()
	{
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		RemoteDataHandler.asyncPost2(Constants.URL_MYSTOIRE, hashmap, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {


			public void dataLoaded(ResponseData responsedata)
			{
				String s;
				if (responsedata.getCode() == 200)
				{
				s = responsedata.getJson();
				if (responsedata.getLogin() == 0)
				{
					myApp.setLoginKey("");
					linearLayoutNOLogin.setVisibility(0);
					linearLayoutYesLogin.setVisibility(8);
					buttonLoingOut.setVisibility(8);
				}
				
	
					try
					{String s1 = (new JSONObject(s)).getString("error");
					if (s1 != null)
					{
						Toast.makeText(MyStoreActivity.this, s1, 0).show();
					}
						return;
					}
					catch (JSONException jsonexception)
					{
						jsonexception.printStackTrace();
					}
				try
				{
					MyStore mystore = MyStore.newInstanceList((new JSONObject(s)).getString("member_info"));
					textMyUserName.setText(mystore.getUsername());
					textMyPoint.setText(mystore.getPoint());
					textMyPredepoit.setText((new StringBuilder("￥")).append(mystore.getPredepoit()).toString());
					(new MySrcAsynaTask(mystore.getAvator(), imageMyAvator)).execute(new String[0]);
					return;
				}
				catch (JSONException jsonexception1)
				{
					jsonexception1.printStackTrace();
				}
				return;
				}
				Toast.makeText(MyStoreActivity.this, "数据加载失败，请稍后重试", 0).show();

			}

			
			
		});
	}

	public void loginOut()
	{
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("username", myApp.getLoginName());
		hashmap.put("client", "android");
		myApp.setLoginKey("");
		myApp.setLoginName("");
		HistoryBrowse.deleteAll();
		linearLayoutNOLogin.setVisibility(0);
		linearLayoutYesLogin.setVisibility(8);
		buttonLoingOut.setVisibility(8);
		RemoteDataHandler.asyncPost2(Constants.URL_LOGIN_OUT, hashmap, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

		

			public void dataLoaded(ResponseData responsedata)
			{
				String s;
				if (responsedata.getCode() == 200)
				{
					s = responsedata.getJson();
					try
					{
					if(s!=null)
					{
						if(s.equals("1"))
						{
							
						}
						String error = (new JSONObject(s)).getString("error");
					}
					}
					catch(JSONException e)
					{
						e.printStackTrace();
					}
									
					return;
				}
				return;
			}

		});
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.tab_mystore);
		myApp = (MyApp)getApplication();
		mystoreListView = (MyListView)findViewById(R.id.mystoreListView);
		imageMyAvator = (ImageView)findViewById(R.id.imageMyAvator);
		textMyUserName = (TextView)findViewById(R.id.textMyUserName);
		textMyPoint = (TextView)findViewById(R.id.textMyPoint);
		textMyPredepoit = (TextView)findViewById(R.id.textMyPredepoit);
		buttonLoingOut = (Button)findViewById(R.id.buttonLoingOut);
		linearLayoutYesLogin = (LinearLayout)findViewById(R.id.linearLayoutYesLogin);
		linearLayoutNOLogin = (LinearLayout)findViewById(R.id.linearLayoutNOLogin);
		buttonLoginSubmit = (Button)findViewById(R.id.buttonLoginSubmit);
		buttonRegistered = (Button)findViewById(R.id.buttonRegistered);
		SView = (ScrollView)findViewById(R.id.SView);
		loadingMyStoreData();
		mystoreListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

		

			public void onItemClick(AdapterView adapterview, View view, int i, long l)
			{
				int j = (int)l;
				Intent intent = null;
				switch(j)
				{
					case R.drawable.icon_about:
						intent = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.more.AboutActivity.class);
						break;
					case R.drawable.icon_hancun:
						HistoryBrowse.deleteAll();
						Toast.makeText(MyStoreActivity.this, "删除浏览历史成功", 0).show();
						intent = null;
						break;
					case R.drawable.icon_help:
						intent = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.HelpActivity.class);
						break; /* Loop/switch isn't completed */
					case R.drawable.icon_pic_hancun:
						delAllFile(Constants.CACHE_DIR_IMAGE);
						Toast.makeText(MyStoreActivity.this, "删除缓存图片成功", 0).show();
						intent = null;
						break; /* Loop/switch isn't completed */
					case R.drawable.mystore_address:
						if (myApp.getLoginKey() == null || myApp.getLoginKey().equals("") || myApp.getLoginKey().equals("null"))
						{
							Intent intent2 = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.LoginActivity.class);
							startActivity(intent2);
							return;
						}
						intent = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.AddressListActivity.class);
						break; /* Loop/switch isn't completed */
					case R.drawable.mystore_collect:
						if (myApp.getLoginKey() == null || myApp.getLoginKey().equals("") || myApp.getLoginKey().equals("null"))
						{
							Intent intent3 = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.LoginActivity.class);
							startActivity(intent3);
							return;
						}
						intent = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.FavoritesListActivity.class);
						break; /* Loop/switch isn't completed */
					case R.drawable.mystore_history:
						if (myApp.getLoginKey() == null || myApp.getLoginKey().equals("") || myApp.getLoginKey().equals("null"))
						{
							Intent intent1 = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.LoginActivity.class);
							startActivity(intent1);
							return;
						}
						intent = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.HistoryBrowseListActivity.class);
						break; /* Loop/switch isn't completed */
					case R.drawable.mystore_order:
						if (myApp.getLoginKey() == null || myApp.getLoginKey().equals("") || myApp.getLoginKey().equals("null"))
						{
							Intent intent4 = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.LoginActivity.class);
							startActivity(intent4);
							return;
						}
						intent = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.OrderListActivity.class);
						break; /* Loop/switch isn't completed */
												
					
				}

				if (intent != null)
					startActivity(intent);
				return;


			}
			
		});
		buttonLoingOut.setOnClickListener(new android.view.View.OnClickListener() {


			public void onClick(View view)
			{
				loginOut();
			}

			
			
		});
		buttonLoginSubmit.setOnClickListener(new android.view.View.OnClickListener() {

		

			public void onClick(View view)
			{
				Intent intent = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.LoginActivity.class);
				startActivity(intent);
			}

		
		});
		buttonRegistered.setOnClickListener(new android.view.View.OnClickListener() {

			

			public void onClick(View view)
			{
				Intent intent = new Intent(MyStoreActivity.this, net.asiasofti.android.ui.mystore.RegisteredActivity.class);
				startActivity(intent);
			}

		});
	}

	protected void onDestroy()
	{
		super.onDestroy();
		unregisterReceiver(mBroadcastReceiver);
	}

	public boolean onKeyDown(int i, KeyEvent keyevent)
	{
		if (i == 4)
		{
			myApp.getTabHost().setCurrentTab(0);
			myApp.getHomeButton().setChecked(true);
			return true;
		} else
		{
			return super.onKeyDown(i, keyevent);
		}
	}

	protected void onResume()
	{
		super.onResume();
		SView.smoothScrollTo(0, 20);
		ArrayList arraylist;
		HashMap hashmap;
		HashMap hashmap1;
		HashMap hashmap2;
		HashMap hashmap3;
		HashMap hashmap4;
		HashMap hashmap5;
		HashMap hashmap6;
		HashMap hashmap7;
		if (myApp.getLoginKey() == null || myApp.getLoginKey().equals("") || myApp.getLoginKey().equals("null"))
		{
			linearLayoutNOLogin.setVisibility(0);
			linearLayoutYesLogin.setVisibility(8);
			buttonLoingOut.setVisibility(8);
		} else
		{
			linearLayoutYesLogin.setVisibility(0);
			linearLayoutNOLogin.setVisibility(8);
			buttonLoingOut.setVisibility(0);
		}
		arraylist = new ArrayList();
		hashmap = new HashMap();
		hashmap.put("text", getString(R.string.mystore_order_text));
		hashmap.put("icon", Integer.valueOf(R.drawable.mystore_order));
		arraylist.add(hashmap);
		hashmap1 = new HashMap();
		hashmap1.put("text", getString(R.string.mystore_collection_text));
		hashmap1.put("icon", Integer.valueOf(R.drawable.mystore_collect));
		arraylist.add(hashmap1);
		hashmap2 = new HashMap();
		hashmap2.put("text", getString(R.string.mystore_address_text));
		hashmap2.put("icon", Integer.valueOf(R.drawable.mystore_address));
		arraylist.add(hashmap2);
		hashmap3 = new HashMap();
		hashmap3.put("text", getString(R.string.mystore_history_text));
		hashmap3.put("icon", Integer.valueOf(R.drawable.mystore_history));
		arraylist.add(hashmap3);
		hashmap4 = new HashMap();
		hashmap4.put("text", getString(R.string.mystore_huancun_text));
		hashmap4.put("icon", Integer.valueOf(R.drawable.icon_hancun));
		arraylist.add(hashmap4);
		hashmap5 = new HashMap();
		hashmap5.put("text", getString(R.string.mystore_pic_huancan_text));
		hashmap5.put("icon", Integer.valueOf(R.drawable.icon_pic_hancun));
		arraylist.add(hashmap5);
		hashmap6 = new HashMap();
		hashmap6.put("text", getString(R.string.mystore_help_text));
		hashmap6.put("icon", Integer.valueOf(R.drawable.icon_help));
		arraylist.add(hashmap6);
		hashmap7 = new HashMap();
		hashmap7.put("text", getString(R.string.mystore_about_text));
		hashmap7.put("icon", Integer.valueOf(R.drawable.icon_about));
		arraylist.add(hashmap7);
		adapter = new SubmenuListViewAdapter(this, arraylist);
		mystoreListView.setAdapter(adapter);
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
