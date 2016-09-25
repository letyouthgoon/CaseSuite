/**
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
import android.view.View;
import android.widget.*;

import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.AddressListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.AddressList;
import net.asiasofti.android.model.ResponseData;

import org.json.JSONException;
import org.json.JSONObject;

public class AddressListActivity extends Activity {

	private AddressListViewAdapter adapter;
	private ImageView imageBack;
	private ListView listViewAddress;
	private BroadcastReceiver mBroadcastReceiver;
	private MyApp myApp;
	private TextView textAddressAddButton;

	public AddressListActivity() {
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
		RemoteDataHandler.asyncPost2(Constants.URL_ADDRESS_LIST, hashmap,
				new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

					public void dataLoaded(ResponseData responsedata) {
						if (responsedata.getCode() != 200) {
							Toast.makeText(AddressListActivity.this,
									"数据加载失败,请稍候再试", 0).show();
							return;
						}
						String s = responsedata.getJson();
						if (responsedata.getLogin() == 0)
							myApp.setLoginKey("");
						String s1;
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
							return;
						}
						if (s1 != null) {
							Toast.makeText(AddressListActivity.this, s1, 0)
									.show();
						}
						return;

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
								AddressListActivity.this,
								net.asiasofti.android.ui.mystore.AddressEditActivity.class);
						intent.putExtra("address_id",
								addresslist.getAddress_id());
						startActivity(intent);
					}

				});
		textAddressAddButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						Intent intent = new Intent(
								AddressListActivity.this,
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
