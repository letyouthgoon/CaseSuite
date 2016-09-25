/**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */
package net.asiasofti.android.ui.type;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.PrintStream;
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.AddressListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.AddressList;
import net.asiasofti.android.model.ResponseData;
import net.asiasofti.android.ui.mystore.AddressAddActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class BuyAddressListActivity extends Activity {

	private AddressListViewAdapter adapter;
	private ImageView imageBack;
	private ListView listViewAddress;
	private BroadcastReceiver mBroadcastReceiver;
	private MyApp myApp;
	private TextView textAddressAddButton;

	public BuyAddressListActivity() {
		mBroadcastReceiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent) {
				if (intent.getAction().equals(Constants.APP_BORADCASTRECEIVER))
					loadingAddressListData();
			}

		};
	}

	public void loadingAddressListData() {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		System.out.println((new StringBuilder("key-->>")).append(
				myApp.getLoginKey()).toString());
		RemoteDataHandler.asyncPost2(Constants.URL_ADDRESS_LIST, hashmap,
				new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

					public void dataLoaded(ResponseData responsedata) {
						if (responsedata.getCode() != 200) {
							Toast.makeText(BuyAddressListActivity.this,
									"数据加载失败，请稍后重试", 0).show();
							return;
						}
						String s = responsedata.getJson();
						String s1 = null;
						try {
							java.util.ArrayList arraylist = AddressList
									.newInstanceList((new JSONObject(s))
											.getString("address_list"));
							adapter.setAddressLists(arraylist);
							adapter.notifyDataSetChanged();
						} catch (JSONException jsonexception) {
							jsonexception.printStackTrace();
						}
						try {
							s1 = (new JSONObject(s)).getString("error");
						} catch (JSONException jsonexception1) {
							jsonexception1.printStackTrace();
							// return;
						}
						if (s1 != null && !"".equals(s1)) {
							Toast.makeText(BuyAddressListActivity.this, s1, 0)
									.show();
							return;
						}

					}

				});
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.address_listview);
		myApp = (MyApp) getApplication();
		listViewAddress = (ListView) findViewById(R.id.listViewAddress);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		textAddressAddButton = (TextView) findViewById(R.id.textAddressAddButton);
		adapter = new AddressListViewAdapter(this);
		loadingAddressListData();
		listViewAddress.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				finish();
			}

		});
		listViewAddress
				.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView adapterview, View view,
							int i, long l) {
						AddressList addresslist = (AddressList) listViewAddress
								.getItemAtPosition(i);
						Intent intent = new Intent(
								Constants.APP_BORADCASTRECEIVER);
						intent.putExtra("cityId", addresslist.getCity_id());
						intent.putExtra("areaId", addresslist.getArea_id());
						intent.putExtra("tureName", addresslist.getTrue_name());
						intent.putExtra("addressInFo",
								addresslist.getArea_info());
						intent.putExtra("mobPhone", addresslist.getMob_phone());
						intent.putExtra("addressId",
								addresslist.getAddress_id());
						sendBroadcast(intent);
						finish();
					}

				});
		textAddressAddButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						Intent intent = new Intent(
								BuyAddressListActivity.this,
								net.asiasofti.android.ui.mystore.AddressAddActivity.class);
						startActivity(intent);
					}
				});
	}

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBroadcastReceiver);
	}

	protected void onStart() {
		super.onStart();
		registerBoradcastReceiver();
	}

	public void registerBoradcastReceiver() {
		IntentFilter intentfilter = new IntentFilter();
		intentfilter.addAction(Constants.APP_BORADCASTRECEIVER);
		registerReceiver(mBroadcastReceiver, intentfilter);
	}

}
