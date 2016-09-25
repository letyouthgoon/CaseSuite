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
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;

import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.MyListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.*;

import org.json.JSONException;
import org.json.JSONObject;

public class AddressEditActivity extends Activity {

	private String a_id;
	private MyListViewAdapter adapter;
	private String addressID;
	private String addressInFoString;
	private String area_id;
	private String area_id1;
	private String area_id2;
	private String area_id3;
	private Button buttonDelete;
	private String city_id;
	private TextView editAddressInfo;
	private EditText editAddressMobPhone;
	private EditText editAddressName;
	private EditText editAddressTelPhone;
	private EditText editJieDaoAddress;
	private boolean flag;
	private ImageView imageBack;
	private LinearLayout linearLayoutSelectCity;
	private MyApp myApp;
	private TextView textAddressID;
	private TextView textCityID;
	private TextView textEditAddressInfo;
	private TextView textRegionID;
	private TextView textSendCityButton;

	public AddressEditActivity() {
		flag = true;
		area_id1 = "";
		area_id2 = "";
		area_id3 = "";
		city_id = "";
		area_id = "";
		a_id = "";
	}

	public void DeteleAddressData(String s) {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("address_id", s);
		RemoteDataHandler.asyncPost2(Constants.URL_ADDRESS_DETELE, hashmap,
				new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

					public void dataLoaded(ResponseData responsedata) {
						if (responsedata.getCode() != 200) {
							Toast.makeText(AddressEditActivity.this,
									"数据加载失败，请稍后重试", 0).show();
							return;
						}
						String s1 = responsedata.getJson();
						if (s1.equals("1")) {
							Toast.makeText(AddressEditActivity.this, "删除成功", 0)
									.show();
							Intent intent = new Intent(
									Constants.APP_BORADCASTRECEIVER);
							sendBroadcast(intent);
							finish();
						}
						String s2;
						try {
							s2 = (new JSONObject(s1)).getString("error");
						} catch (JSONException jsonexception) {
							jsonexception.printStackTrace();
							return;
						}
						if (s2 != null) {
							Toast.makeText(AddressEditActivity.this, s2, 0)
									.show();
						}
						return;

					}
				});
	}

	public void SendCityData() {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		String s;
		String s1;
		if (area_id3.equals(""))
			s = a_id;
		else
			s = area_id3;
		hashmap.put("address_id", s);
		hashmap.put("true_name", editAddressName.getText().toString());
		hashmap.put("area_id", city_id);
		if (area_id2.equals(""))
			s1 = area_id;
		else
			s1 = area_id2;
		hashmap.put("city_id", s1);
		hashmap.put("area_info", editAddressInfo.getText().toString());
		hashmap.put("address", editJieDaoAddress.getText().toString());
		hashmap.put("tel_phone", editAddressTelPhone.getText().toString());
		hashmap.put("mob_phone", editAddressMobPhone.getText().toString());
		if (editAddressInfo.getText().toString().equals("")
				|| editAddressInfo.getText().toString().equals("null")
				|| editAddressInfo.getText().toString() == null) {
			Toast.makeText(this, "地址信息不能为空", 0).show();
			return;
		}
		if (editJieDaoAddress.getText().toString().equals("")
				|| editJieDaoAddress.getText().toString().equals("null")
				|| editJieDaoAddress.getText().toString() == null) {
			Toast.makeText(this, "街道地址不能为空", 0).show();
			return;
		}
		if (editAddressName.getText().toString().equals("")
				|| editAddressName.getText().toString().equals("null")
				|| editAddressName.getText().toString() == null) {
			Toast.makeText(this, "姓名不能为空", 0).show();
			return;
		}
		if (editAddressMobPhone.getText().toString().equals("")
				|| editAddressMobPhone.getText().toString().equals("null")
				|| editAddressMobPhone.getText().toString() == null) {
			Toast.makeText(this, "手机号不能为空", 0).show();
			return;
		}
		if (editAddressTelPhone.getText().toString().equals("")
				|| editAddressTelPhone.getText().toString().equals("null")
				|| editAddressTelPhone.getText().toString() == null) {
			Toast.makeText(this, "固定电话不能为空", 0).show();
			return;
		} else {
			RemoteDataHandler
					.asyncPost2(
							Constants.URL_ADDRESS_EDIT,
							hashmap,
							new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

								public void dataLoaded(ResponseData responsedata) {
									if (responsedata.getCode() != 200) {
										Toast.makeText(
												AddressEditActivity.this,
												"数据加载失败，请稍后重试", 0).show();
										return;
									}
									String s2 = responsedata.getJson();
									if (s2.equals("1")) {
										Toast.makeText(
												AddressEditActivity.this,
												"保存成功", 0).show();
										Intent intent = new Intent(
												Constants.APP_BORADCASTRECEIVER);
										sendBroadcast(intent);
										finish();
									}
									String s3;
									try {
										s3 = (new JSONObject(s2))
												.getString("error");
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
										return;
									}
									if (s3 != null) {
										Toast.makeText(
												AddressEditActivity.this, s3, 0)
												.show();
									}
									return;

								}

							});
			return;
		}
	}

	public void loadingAddressDetailsData(String s) {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("address_id", s);
		RemoteDataHandler.asyncPost2(Constants.URL_ADDRESS_DETAILS, hashmap,
				new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

					public void dataLoaded(ResponseData responsedata) {
						if (responsedata.getCode() != 200) {
							Toast.makeText(AddressEditActivity.this,
									"数据加载失败，请稍后重试", 0).show();
							return;
						}
						String s1 = responsedata.getJson();
						String s2;
						try {
							AddressDetails addressdetails = AddressDetails
									.newInstanceList((new JSONObject(s1))
											.getString("address_info"));
							a_id = addressdetails.getAddress_id();
							city_id = addressdetails.getCity_id();
							area_id = addressdetails.getArea_id();
							addressInFoString = addressdetails.getArea_info();
							editAddressName.setText(addressdetails
									.getTrue_name());
							editAddressInfo.setText(addressdetails
									.getArea_info());
							editAddressMobPhone.setText(addressdetails
									.getMob_phone());
							editAddressTelPhone.setText(addressdetails
									.getTel_phone());
							editJieDaoAddress.setText(addressdetails
									.getAddress());
						} catch (JSONException jsonexception) {
							jsonexception.printStackTrace();
						}
						try {
							s2 = (new JSONObject(s1)).getString("error");
						} catch (JSONException jsonexception1) {
							jsonexception1.printStackTrace();
							return;
						}
						if (s2 != null) {
							Toast.makeText(AddressEditActivity.this, s2, 0)
									.show();
						}
						return;

					}
				});
	}

	public void loadingCityData(String s) {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("area_id", s);
		RemoteDataHandler.asyncPost2(Constants.URL_GET_CITY, hashmap,
				new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

					public void dataLoaded(ResponseData responsedata) {
						if (responsedata.getCode() != 200) {
							Toast.makeText(AddressEditActivity.this,
									"数据加载失败，请稍后重试", 0).show();
							return;
						}
						String s1 = responsedata.getJson();
						String s2;
						try {
							java.util.ArrayList arraylist = CityList
									.newInstanceList((new JSONObject(s1))
											.getString("area_list"));
							adapter.setDatas(arraylist);
							adapter.notifyDataSetChanged();
						} catch (JSONException jsonexception) {
							jsonexception.printStackTrace();
						}
						try {
							s2 = (new JSONObject(s1)).getString("error");
						} catch (JSONException jsonexception1) {
							jsonexception1.printStackTrace();
							return;
						}
						if (s2 != null) {
							Toast.makeText(AddressEditActivity.this, s2, 0)
									.show();
						}
						return;

					}

				});
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.address_edit_view);
		myApp = (MyApp) getApplication();
		addressID = getIntent().getStringExtra("address_id");
		editAddressName = (EditText) findViewById(R.id.editAddressName);
		editAddressInfo = (TextView) findViewById(R.id.editAddressInfo);
		editAddressMobPhone = (EditText) findViewById(R.id.editAddressMobPhone);
		editAddressTelPhone = (EditText) findViewById(R.id.editAddressTelPhone);
		textEditAddressInfo = (TextView) findViewById(R.id.textEditAddressInfo);
		editJieDaoAddress = (EditText) findViewById(R.id.editJieDaoAddress);
		textSendCityButton = (TextView) findViewById(R.id.textSendCityButton);
		textCityID = (TextView) findViewById(R.id.textCityID);
		textRegionID = (TextView) findViewById(R.id.textRegionID);
		textAddressID = (TextView) findViewById(R.id.textAddressID);
		buttonDelete = (Button) findViewById(R.id.buttonDelete);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		linearLayoutSelectCity = (LinearLayout) findViewById(R.id.linearLayoutSelectCity);
		loadingAddressDetailsData(addressID);
		adapter = new MyListViewAdapter(this);
		textEditAddressInfo
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (flag) {
							flag = false;
							textEditAddressInfo.setText("完成");
							linearLayoutSelectCity.setVisibility(0);
							return;
						}
						flag = true;
						textEditAddressInfo.setText("编辑");
						linearLayoutSelectCity.setVisibility(8);
						String s = (new StringBuilder(String.valueOf(textCityID
								.getText().toString())))
								.append(textRegionID.getText().toString())
								.append(textAddressID.getText().toString())
								.toString();
						TextView textview = editAddressInfo;
						if (s.equals(""))
							s = addressInFoString;
						textview.setText(s);
					}

				});
		textCityID.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
						AddressEditActivity.this);
				final ListView listview = new ListView(AddressEditActivity.this);
				listview.setScrollbarFadingEnabled(false);
				listview.setFastScrollEnabled(false);
				listview.setAdapter(adapter);
				builder.setView(listview);
				final AlertDialog dialog = builder.create();
				listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView adapterview, View view,
							int i, long l) {
						CityList citylist = (CityList) listview
								.getItemAtPosition(i);
						area_id1 = citylist.getArea_id();
						textCityID.setText(citylist.getArea_name());
						textRegionID.setText("");
						textAddressID.setText("");
						dialog.dismiss();
					}

				});
				loadingCityData("");
			}

		});
		textRegionID
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (textCityID.getText().toString().equals("")
								|| textCityID.getText().toString()
										.equals("null")
								|| textCityID.getText().toString() == null) {
							Toast.makeText(AddressEditActivity.this, "城市不能为空",
									0).show();
							return;
						} else {
							android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
									AddressEditActivity.this);
							final ListView listview = new ListView(
									AddressEditActivity.this);
							listview.setScrollbarFadingEnabled(false);
							listview.setFastScrollEnabled(false);
							listview.setAdapter(adapter);
							builder.setView(listview);
							final AlertDialog dialog = builder.create();
							dialog.show();
							listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

								public void onItemClick(
										AdapterView adapterview, View view,
										int i, long l) {
									CityList citylist = (CityList) listview
											.getItemAtPosition(i);
									area_id2 = citylist.getArea_id();
									textRegionID.setText(citylist
											.getArea_name());
									textAddressID.setText("");
									dialog.dismiss();
								}

							});
							loadingCityData(area_id1);
							return;
						}
					}

				});
		textAddressID
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (textRegionID.getText().toString().equals("")
								|| textRegionID.getText().toString()
										.equals("null")
								|| textRegionID.getText().toString() == null) {
							Toast.makeText(AddressEditActivity.this, "地区不能为空",
									0).show();
							return;
						} else {
							android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
									AddressEditActivity.this);
							final ListView listview = new ListView(
									AddressEditActivity.this);
							listview.setScrollbarFadingEnabled(false);
							listview.setFastScrollEnabled(false);
							listview.setAdapter(adapter);
							builder.setView(listview);
							final AlertDialog dialog = builder.create();
							listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
								public void onItemClick(
										AdapterView adapterview, View view,
										int i, long l) {
									CityList citylist = (CityList) listview
											.getItemAtPosition(i);
									city_id = citylist.getArea_id();
									textAddressID.setText(citylist
											.getArea_name());
									dialog.dismiss();
								}
							});
							loadingCityData(area_id2);
							return;
						}
					}

				});
		textSendCityButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						SendCityData();
					}

				});
		buttonDelete
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						DeteleAddressData(addressID);
					}

				});
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				finish();
			}

		});
	}

}
