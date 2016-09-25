package net.asiasofti.android.ui.mystore;

import java.util.ArrayList;
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.MyListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.CityList;
import net.asiasofti.android.model.ResponseData;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddressAddActivity extends Activity {
	private MyListViewAdapter adapter;
	private String addressInFoString;
	private String area_id1 = "";
	private String area_id2 = "";
	private Button buttonSend;
	private String city_id = "";
	private TextView editAddressInfo;
	private EditText editAddressMobPhone;
	private EditText editAddressName;
	private EditText editAddressTelPhone;
	private EditText editJieDaoAddress;
	private boolean flag = true;
	private ImageView imageBack;
	private LinearLayout linearLayoutSelectCity;
	private MyApp myApp;
	private TextView textAddressID;
	private TextView textCityID;
	private TextView textEditAddressInfo;
	private TextView textRegionID;
	private TextView textSendCityButton;

	public void SendCityData() {
		HashMap localHashMap = new HashMap();
		localHashMap.put("key", this.myApp.getLoginKey());
		localHashMap
				.put("true_name", this.editAddressName.getText().toString());
		localHashMap.put("area_id", this.city_id);
		localHashMap.put("city_id", this.area_id2);
		localHashMap
				.put("area_info", this.editAddressInfo.getText().toString());
		localHashMap
				.put("address", this.editJieDaoAddress.getText().toString());
		localHashMap.put("tel_phone", this.editAddressTelPhone.getText()
				.toString());
		localHashMap.put("mob_phone", this.editAddressMobPhone.getText()
				.toString());
		if ((this.editAddressInfo.getText().toString().equals(""))
				|| (this.editAddressInfo.getText().toString().equals("null"))
				|| (this.editAddressInfo.getText().toString() == null)) {
			Toast.makeText(this, "地址信息不能为空", 0).show();
			return;
		}
		if ((this.editJieDaoAddress.getText().toString().equals(""))
				|| (this.editJieDaoAddress.getText().toString().equals("null"))
				|| (this.editJieDaoAddress.getText().toString() == null)) {
			Toast.makeText(this, "街道地址不能为空", 0).show();
			return;
		}
		if ((this.editAddressName.getText().toString().equals(""))
				|| (this.editAddressName.getText().toString().equals("null"))
				|| (this.editAddressName.getText().toString() == null)) {
			Toast.makeText(this, "姓名不能为空", 0).show();
			return;
		}
		if ((this.editAddressMobPhone.getText().toString().equals(""))
				|| (this.editAddressMobPhone.getText().toString()
						.equals("null"))
				|| (this.editAddressMobPhone.getText().toString() == null)) {
			Toast.makeText(this, "手机号不能为空", 0).show();
			return;
		}
		if ((this.editAddressTelPhone.getText().toString().equals(""))
				|| (this.editAddressTelPhone.getText().toString()
						.equals("null"))
				|| (this.editAddressTelPhone.getText().toString() == null)) {
			Toast.makeText(this, "固定电话不能为空", 0).show();
			return;
		}
		RemoteDataHandler.asyncPost2(Constants.URL_ADDRESS_ADD, localHashMap,
				new RemoteDataHandler.Callback() {
					public void dataLoaded(
							ResponseData paramAnonymousResponseData) {
						if (paramAnonymousResponseData.getCode() == 200) {
							String str1 = paramAnonymousResponseData.getJson();
							if (str1.contains("address_id")) {
								Toast.makeText(AddressAddActivity.this, "保存成功",
										1000).show();
								Intent localIntent = new Intent(
										Constants.APP_BORADCASTRECEIVER);
								AddressAddActivity.this
										.sendBroadcast(localIntent);
								AddressAddActivity.this.finish();
							}
							try {
								String str2 = new JSONObject(str1)
										.getString("error");
								if (str2 != null)
									Toast.makeText(AddressAddActivity.this,
											str2, 1000).show();
								return;
							} catch (JSONException localJSONException) {
								localJSONException.printStackTrace();
								return;
							}
						}
						Toast.makeText(AddressAddActivity.this, "数据加载失败，请稍后重试",
								0).show();
					}
				});
	}

	public void loadingCityData(String paramString) {
		HashMap localHashMap = new HashMap();
		localHashMap.put("key", this.myApp.getLoginKey());
		localHashMap.put("area_id", paramString);
		RemoteDataHandler.asyncPost2(Constants.URL_GET_CITY, localHashMap,
				new RemoteDataHandler.Callback() {
					public void dataLoaded(
							ResponseData paramAnonymousResponseData) {
						if (paramAnonymousResponseData.getCode() == 200) {
							String str1 = paramAnonymousResponseData.getJson();
							try {
								ArrayList localArrayList = CityList
										.newInstanceList(new JSONObject(str1)
												.getString("area_list"));
								AddressAddActivity.this.adapter
										.setDatas(localArrayList);
								AddressAddActivity.this.adapter
										.notifyDataSetChanged();
								return;
							} catch (JSONException localJSONException1) {
								try {
									String str2 = new JSONObject(str1)
											.getString("error");
									if (str2 != null)
										Toast.makeText(AddressAddActivity.this,
												str2, 0).show();
									return;

								} catch (JSONException localJSONException2) {
									localJSONException2.printStackTrace();
									// return;
								}
							}
						} else {
							Toast.makeText(AddressAddActivity.this,
									"数据加载失败，请稍后重试", 0).show();
						}
					}
				});
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.address_add_view);
		this.myApp = ((MyApp) getApplication());
		this.editAddressName = ((EditText) findViewById(R.id.editAddressName));
		this.editAddressInfo = ((TextView) findViewById(R.id.editAddressInfo));
		this.editAddressMobPhone = ((EditText) findViewById(R.id.editAddressMobPhone));
		this.editAddressTelPhone = ((EditText) findViewById(R.id.editAddressTelPhone));
		this.textEditAddressInfo = ((TextView) findViewById(R.id.textEditAddressInfo));
		this.editJieDaoAddress = ((EditText) findViewById(R.id.editJieDaoAddress));
		this.textSendCityButton = ((TextView) findViewById(R.id.textSendCityButton));
		this.textCityID = ((TextView) findViewById(R.id.textCityID));
		this.textRegionID = ((TextView) findViewById(R.id.textRegionID));
		this.textAddressID = ((TextView) findViewById(R.id.textAddressID));
		this.buttonSend = ((Button) findViewById(R.id.buttonSend));
		this.imageBack = ((ImageView) findViewById(R.id.imageBack));
		this.linearLayoutSelectCity = ((LinearLayout) findViewById(R.id.linearLayoutSelectCity));
		this.adapter = new MyListViewAdapter(this);

		this.textEditAddressInfo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				if (AddressAddActivity.this.flag) {
					AddressAddActivity.this.flag = false;
					AddressAddActivity.this.textEditAddressInfo.setText("完成");
					AddressAddActivity.this.linearLayoutSelectCity
							.setVisibility(0);
					return;
				}
				AddressAddActivity.this.flag = true;
				AddressAddActivity.this.textEditAddressInfo.setText("编辑");
				AddressAddActivity.this.linearLayoutSelectCity.setVisibility(8);
				String str = AddressAddActivity.this.textCityID.getText()
						.toString()
						+ AddressAddActivity.this.textRegionID.getText()
								.toString()
						+ AddressAddActivity.this.textAddressID.getText()
								.toString();
				TextView localTextView = AddressAddActivity.this.editAddressInfo;
				if (str.equals(""))
					str = AddressAddActivity.this.addressInFoString;
				localTextView.setText(str);
			}
		});
		this.textCityID.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AlertDialog.Builder localBuilder = new AlertDialog.Builder(
						AddressAddActivity.this);
				final ListView localListView = new ListView(
						AddressAddActivity.this);
				localListView.setScrollbarFadingEnabled(false);
				localListView.setFastScrollEnabled(false);
				localListView.setAdapter(AddressAddActivity.this.adapter);
				localBuilder.setView(localListView);
				final AlertDialog alt = localBuilder.create();
				alt.show();
				localListView
						.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							public void onItemClick(
									AdapterView<?> paramAnonymous2AdapterView,
									View paramAnonymous2View,
									int paramAnonymous2Int,
									long paramAnonymous2Long) {
								CityList localCityList = (CityList) localListView
										.getItemAtPosition(paramAnonymous2Int);
								AddressAddActivity.this.area_id1 = localCityList
										.getArea_id();
								AddressAddActivity.this.textCityID
										.setText(localCityList.getArea_name());
								AddressAddActivity.this.textRegionID
										.setText("");
								AddressAddActivity.this.textAddressID
										.setText("");
								alt.dismiss();
								// this.val$dialog.dismiss();
								// localBuilder.
							}
						});
				AddressAddActivity.this.loadingCityData("");
			}
		});
		this.textRegionID.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				if ((AddressAddActivity.this.textCityID.getText().toString()
						.equals(""))
						|| (AddressAddActivity.this.textCityID.getText()
								.toString().equals("null"))
						|| (AddressAddActivity.this.textCityID.getText()
								.toString() == null)) {
					Toast.makeText(AddressAddActivity.this, "城市不能为空", 0).show();
					return;
				}
				AlertDialog.Builder localBuilder = new AlertDialog.Builder(
						AddressAddActivity.this);
				final ListView localListView = new ListView(
						AddressAddActivity.this);
				localListView.setScrollbarFadingEnabled(false);
				localListView.setFastScrollEnabled(false);
				localListView.setAdapter(AddressAddActivity.this.adapter);
				localBuilder.setView(localListView);
				final AlertDialog alt = localBuilder.create();
				alt.show();
				localListView
						.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							public void onItemClick(
									AdapterView<?> paramAnonymous2AdapterView,
									View paramAnonymous2View,
									int paramAnonymous2Int,
									long paramAnonymous2Long) {
								CityList localCityList = (CityList) localListView
										.getItemAtPosition(paramAnonymous2Int);
								AddressAddActivity.this.area_id2 = localCityList
										.getArea_id();
								AddressAddActivity.this.textRegionID
										.setText(localCityList.getArea_name());
								AddressAddActivity.this.textAddressID
										.setText("");
								alt.dismiss();
							}
						});
				AddressAddActivity.this
						.loadingCityData(AddressAddActivity.this.area_id1);
			}
		});
		this.textAddressID.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				if ((AddressAddActivity.this.textRegionID.getText().toString()
						.equals(""))
						|| (AddressAddActivity.this.textRegionID.getText()
								.toString().equals("null"))
						|| (AddressAddActivity.this.textRegionID.getText()
								.toString() == null)) {
					Toast.makeText(AddressAddActivity.this, "地区不能为空", 0).show();
					return;
				}
				AlertDialog.Builder localBuilder = new AlertDialog.Builder(
						AddressAddActivity.this);
				final ListView localListView = new ListView(
						AddressAddActivity.this);
				localListView.setScrollbarFadingEnabled(false);
				localListView.setFastScrollEnabled(false);
				localListView.setAdapter(AddressAddActivity.this.adapter);
				localBuilder.setView(localListView);
				final AlertDialog dialog = localBuilder.create();
				dialog.show();
				localListView
						.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							public void onItemClick(
									AdapterView<?> paramAnonymous2AdapterView,
									View paramAnonymous2View,
									int paramAnonymous2Int,
									long paramAnonymous2Long) {
								CityList localCityList = (CityList) localListView
										.getItemAtPosition(paramAnonymous2Int);
								AddressAddActivity.this.city_id = localCityList
										.getArea_id();
								AddressAddActivity.this.textAddressID
										.setText(localCityList.getArea_name());
								dialog.dismiss();

							}
						});
				AddressAddActivity.this
						.loadingCityData(AddressAddActivity.this.area_id2);
			}
		});
		this.textSendCityButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddressAddActivity.this.SendCityData();
			}
		});
		this.buttonSend.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddressAddActivity.this.SendCityData();
			}
		});
		this.imageBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddressAddActivity.this.finish();
			}
		});
	}
}
