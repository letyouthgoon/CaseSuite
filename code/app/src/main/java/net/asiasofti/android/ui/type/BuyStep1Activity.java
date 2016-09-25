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
import android.app.AlertDialog;
import android.content.*;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;

import java.io.PrintStream;
import java.util.*;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.*;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.*;
import net.asiasofti.android.ui.custom.MyListView;
import net.asiasofti.android.ui.mystore.OrderListActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class BuyStep1Activity extends Activity {
	class MyRadioButtonClickListener implements
			android.view.View.OnClickListener {

		public void onClick(View view) {
			switch (((RadioButton) view).getId()) {
			default:
				return;

			case R.id.radioButtonZaiPay:
				pay_name = "online";
				return;

			case R.id.radioButtonISOffPay:
				pay_name = "offline";
				break;
			}
		}

		MyRadioButtonClickListener() {

			super();
		}
	}

	private CheckBox CheckBoxCheckAvailable;
	private String PasswordFlag;
	private StoreCartListViewAdapter aStoreCartListViewAdapter;
	private String addressId;
	private Button buttonAvailablePredepositPassword;
	private Button buttonEditInvoice;
	private Button buttonNoInvoice;
	private Button buttonSaveInvoice;
	private Button buttonSendBuyStep;
	private String cartFlag;
	private String cart_id;
	private EditText editUnitName;
	private EditText editviewAvailablePredeposit;
	private String freight_hash;
	private MyListView goodsListView;
	public double goods_freight;
	public double goods_total;
	private ImageView imageBack;
	private InvoiceListViewAdapter invoiceListViewAdapter;
	private String invoice_id;
	private LinearLayout linearLayoutAvailablePredeposit1;
	private LinearLayout linearLayoutAvailablePredeposit2;
	private LinearLayout linearLayoutUnit;
	private LinearLayout linearlayoutEditInvInfo;
	private LinearLayout linearlayoutNOAddress;
	public LinearLayout linearlayoutNewInvInfo;
	private LinearLayout linearlayoutYesAddress;
	private BroadcastReceiver mBroadcastReceiver;
	private MyApp myApp;
	private MyInvoiceContentListViewAdapter myListViewAdapter;
	private MyListView mylistviewInvoice;
	private String offpay_hash;
	private String pay_name;
	private RadioButton radioButtonISOffPay;
	private RadioButton radioButtonZaiPay;
	public CheckBox textAddInvoice;
	private TextView textAddressInfo;
	private TextView textAddressName;
	private TextView textAddressPhone;
	private TextView textCheckPasswordFlag;
	private TextView textInvInfo;
	private TextView textInvoiceContent;
	private TextView textInvoiceTitle;
	private TextView textViewGoodsFreight;
	private TextView textViewGoodsTotal;
	private TextView textVoucher;
	private TextView textviewAllPrice;
	private TextView textviewAvailablePredeposit;
	private String vat_hash;
	private String voucher;
	private double voucherPrice;

	public BuyStep1Activity() {
		goods_total = 0.0D;
		goods_freight = 0.0D;
		voucherPrice = 0.0D;
		pay_name = "online";
		PasswordFlag = "未验证";
		mBroadcastReceiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent) {
				String s = intent.getAction();
				if (s.equals(Constants.APP_BORADCASTRECEIVER)) {
					String s4 = intent.getStringExtra("cityId");
					String s5 = intent.getStringExtra("areaId");
					String s6 = intent.getStringExtra("tureName");
					String s7 = intent.getStringExtra("addressInFo");
					String s8 = intent.getStringExtra("mobPhone");
					addressId = intent.getStringExtra("addressId");
					textAddressName.setText(s6);
					textAddressInfo.setText(s7);
					textAddressPhone.setText(s8);
					linearlayoutYesAddress.setVisibility(0);
					linearlayoutNOAddress.setVisibility(8);
					updataAddress(s4, s5);
				} else if (s.equals(Constants.APP_BORADCASTRECEIVER2)) {
					String s1 = intent.getStringExtra("voucher_price");
					String s2 = intent.getStringExtra("voucher_t_id");
					String s3 = intent.getStringExtra("store_id");
					if (s1 != null && !s1.equals("null") && !s1.equals("")) {
						voucherPrice = Double.parseDouble(s1);
						voucher = (new StringBuilder(String.valueOf(s2)))
								.append("|").append(s3).append("|").append(s1)
								.toString();
						updateVoucher();
						return;
					}
				}
			}

		};
	}

	public void CheackPassword(String s) {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("password", s);
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_CHECK_PASSWORD,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								if (responsedata.getCode() != 200) {
									PasswordFlag = "未验证";
									Toast.makeText(BuyStep1Activity.this,
											"数据加载失败，请稍后重试", 0).show();
								}
								String s1 = responsedata.getJson();
								if (!s1.equals("1")) {

									try {
										String s2 = (new JSONObject(s1))
												.getString("error");
										if (s2 != null)
											Toast.makeText(
													BuyStep1Activity.this, s2,
													0).show();
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
									}
									PasswordFlag = "未验证";
									/* Loop/switch isn't completed */
								} else {

									Toast.makeText(BuyStep1Activity.this,
											"验证成功", 0).show();
									PasswordFlag = "通过验证";
									linearLayoutAvailablePredeposit2
											.setVisibility(8);
								}

								textCheckPasswordFlag
										.setText((new StringBuilder("("))
												.append(PasswordFlag)
												.append(")").toString());
								return;

							}

						});
	}

	public void SaveSendBuyStep2() {

		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("cart_id", cart_id);
		hashmap.put("address_id", addressId);
		hashmap.put("vat_hash", vat_hash);
		hashmap.put("offpay_hash", offpay_hash);
		hashmap.put("pay_name", pay_name);
		if (!invoice_id.equals("") && !invoice_id.equals("null")
				&& invoice_id != null)
			hashmap.put("invoice_id", invoice_id);
		hashmap.put("voucher", voucher);
		if (cartFlag.equals("cartFlag"))
			hashmap.put("ifcart", "1");
		String s = editviewAvailablePredeposit.getText().toString();
		if (CheckBoxCheckAvailable.isChecked()) {
			if (s.equals("") || s.equals("null") || s == null) {
				Toast.makeText(this, "密码未验证或为空", 0).show();
			}
			if (!PasswordFlag.equals("未验证")) {
				hashmap.put("pd_pay", "1");
				hashmap.put("password", s);
			} else {
				hashmap.put("pd_pay", "0");
			}
		}
		System.out.println((new StringBuilder("购买参数:")).append(cart_id)
				.append(",").append(addressId).append(",").append(vat_hash)
				.append(",").append(offpay_hash).append(",").append(pay_name)
				.append(",").append(invoice_id).append(",").append(voucher)
				.toString());
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_BUY_STEP2,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								String s1;
								if (responsedata.getCode() != 200) {
									Toast.makeText(BuyStep1Activity.this,
											"数据加载失败，请稍后重试", 0).show();
									return;
								}
								s1 = responsedata.getJson();

								try {
									String s2 = (new JSONObject(s1))
											.getString("error");
									if (s2 != null) {
										Toast.makeText(BuyStep1Activity.this,
												s2, 0).show();
									}
									return;
								} catch (JSONException jsonexception) {
									jsonexception.printStackTrace();
								}
								Intent intent = new Intent(
										BuyStep1Activity.this,
										net.asiasofti.android.ui.mystore.OrderListActivity.class);
								startActivity(intent);
								finish();
								return;

							}

						});
		return;

	}

	public void loadingBuyStep1Data() {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("cart_id", cart_id);
		if (cartFlag.equals("cartFlag"))
			hashmap.put("ifcart", "1");
		System.out.println((new StringBuilder("key-->")).append(
				myApp.getLoginKey()).toString());
		System.out.println((new StringBuilder("cart_id-->")).append(cart_id)
				.toString());
		System.out.println("ifcart-->1");
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_BUY_STEP1,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {

								if (responsedata.getCode() != 200) {
									Toast.makeText(BuyStep1Activity.this,
											"数据加载失败，请稍后重试", 0).show();
									return;
								}
								String s = responsedata.getJson();
								if (s.contains("error")) {
									try {
										String s1 = (new JSONObject(s))
												.getString("error");
										if (s1 != null) {
											Toast.makeText(
													BuyStep1Activity.this, s1,
													0).show();
										}
										finish();

										return;
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
										return;
									}

								}

								System.out
										.println((new StringBuilder("json-->"))
												.append(s).toString());
								BuyStep1 buystep1 = BuyStep1.newInstanceList(s);
								InvoiceInFO invoiceinfo = InvoiceInFO
										.newInstanceList(buystep1.getInv_info());
								AddressDetails addressdetails = AddressDetails
										.newInstanceList(buystep1
												.getAddress_info());
								freight_hash = buystep1.getFreight_hash();
								vat_hash = buystep1.getVat_hash();
								String s1;
								if (buystep1.getIfshow_offpay().equals("true"))
									radioButtonISOffPay.setVisibility(0);
								else
									radioButtonISOffPay.setVisibility(8);
								if (invoiceinfo != null) {
									invoice_id = invoiceinfo.getInv_id();
									textInvInfo.setText(invoiceinfo
											.getContent());
								} else {
									textInvInfo.setText("不使用发票");
								}
								if (!buystep1.getAvailable_predeposit().equals(
										"0")
										&& !buystep1.getAvailable_predeposit()
												.equals("null")
										&& buystep1.getAvailable_predeposit() != null) {
									linearLayoutAvailablePredeposit1
											.setVisibility(0);
									textviewAvailablePredeposit
											.setText(buystep1
													.getAvailable_predeposit());
								} else {
									linearLayoutAvailablePredeposit1
											.setVisibility(8);
								}
								try {
									JSONObject jsonobject = new JSONObject(
											buystep1.getStore_cart_list());
									Iterator iterator = jsonobject.keys();
									ArrayList arraylist = new ArrayList();
									goods_total = 0.0D;
									while (iterator.hasNext()) {
										String s2 = iterator.next().toString();
										StoreCartList storecartlist = StoreCartList
												.newInstanceList(jsonobject
														.getString(s2));
										storecartlist.setStore_id(s2);
										BuyStep1Activity buystep1activity = BuyStep1Activity.this;
										buystep1activity.goods_total = buystep1activity.goods_total
												+ Double.parseDouble(storecartlist
														.getStore_goods_total());
										arraylist.add(storecartlist);
									}

									if (addressdetails != null) {
										addressId = addressdetails
												.getAddress_id();
										updataAddress(
												addressdetails.getCity_id(),
												addressdetails.getArea_id());
										linearlayoutYesAddress.setVisibility(0);
										linearlayoutNOAddress.setVisibility(8);
										textAddressName.setText(addressdetails
												.getTrue_name());
										textAddressInfo.setText(addressdetails
												.getArea_info());
										textAddressPhone.setText(addressdetails
												.getMob_phone());
									} else {
										updateVoucher();
										linearlayoutYesAddress.setVisibility(8);
										linearlayoutNOAddress.setVisibility(0);
									}
								} catch (JSONException e) {
									e.printStackTrace();
								}

							}

						});
	}

	public void loadingInvoiceContextData() {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_INVOICE_CONTEXT_LIST,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								if (responsedata.getCode() == 200) {
									String s = responsedata.getJson();
									try {
										ArrayList arraylist = InvoiceContextList
												.newInstanceList((new JSONObject(
														s))
														.getString("invoice_content_list"));
										myListViewAdapter.setDatas(arraylist);
										myListViewAdapter
												.notifyDataSetChanged();
										return;
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
									}
									return;
								} else {
									Toast.makeText(BuyStep1Activity.this,
											"数据加载失败，请稍后重试", 0).show();
									return;
								}
							}

						});
	}

	public void loadingInvoiceListData() {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_INVOICE_LIST,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								if (responsedata.getCode() == 200) {
									String s = responsedata.getJson();
									try {
										ArrayList arraylist = InvoiceList
												.newInstanceList((new JSONObject(
														s))
														.getString("invoice_list"));
										invoiceListViewAdapter
												.setDatas(arraylist);
										invoiceListViewAdapter
												.notifyDataSetChanged();
										return;
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
									}
									return;
								} else {
									Toast.makeText(BuyStep1Activity.this,
											"数据加载失败，请稍后重试", 0).show();
									return;
								}
							}

						});
	}

	public void loadingSaveInvoiceData(String s, String s1, String s2) {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("inv_title_select", s);
		if (s.equals("company"))
			hashmap.put("inv_title", s1);
		hashmap.put("inv_content", s2);
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_INVOICE_ADD,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								if (responsedata.getCode() == 200) {
									String s3 = responsedata.getJson();
									try {
										String s4 = (new JSONObject(s3))
												.getString("inv_id");
										invoice_id = s4;
										return;
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
									}
									return;
								} else {
									Toast.makeText(BuyStep1Activity.this,
											"数据加载失败，请稍后重试", 0).show();
									return;
								}
							}

						});
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.buy_step1_view);
		myApp = (MyApp) getApplication();
		cart_id = getIntent().getStringExtra("cart_id");
		cartFlag = getIntent().getStringExtra("cartFlag");
		linearlayoutYesAddress = (LinearLayout) findViewById(R.id.linearlayoutYesAddress);
		linearlayoutNOAddress = (LinearLayout) findViewById(R.id.linearlayoutNOAddress);
		textAddressName = (TextView) findViewById(R.id.textAddressName);
		textAddressInfo = (TextView) findViewById(R.id.textAddressInfo);
		textAddressPhone = (TextView) findViewById(R.id.textAddressPhone);
		textInvInfo = (TextView) findViewById(R.id.textInvInfo);
		textAddInvoice = (CheckBox) findViewById(R.id.textAddInvoice);
		textInvoiceTitle = (TextView) findViewById(R.id.textInvoiceTitle);
		textInvoiceContent = (TextView) findViewById(R.id.textInvoiceContent);
		linearlayoutEditInvInfo = (LinearLayout) findViewById(R.id.linearlayoutEditInvInfo);
		linearlayoutNewInvInfo = (LinearLayout) findViewById(R.id.linearlayoutNewInvInfo);
		buttonEditInvoice = (Button) findViewById(R.id.buttonEditInvoice);
		buttonSendBuyStep = (Button) findViewById(R.id.buttonSendBuyStep);
		mylistviewInvoice = (MyListView) findViewById(R.id.mylistviewInvoice);
		buttonSaveInvoice = (Button) findViewById(R.id.buttonSaveInvoice);
		editUnitName = (EditText) findViewById(R.id.editUnitName);
		linearLayoutUnit = (LinearLayout) findViewById(R.id.linearLayoutUnit);
		buttonNoInvoice = (Button) findViewById(R.id.buttonNoInvoice);
		radioButtonZaiPay = (RadioButton) findViewById(R.id.radioButtonZaiPay);
		radioButtonISOffPay = (RadioButton) findViewById(R.id.radioButtonISOffPay);
		goodsListView = (MyListView) findViewById(R.id.goodsListView);
		textViewGoodsTotal = (TextView) findViewById(R.id.textViewGoodsTotal);
		textViewGoodsFreight = (TextView) findViewById(R.id.textViewGoodsFreight);
		textviewAllPrice = (TextView) findViewById(R.id.textviewAllPrice);
		textVoucher = (TextView) findViewById(R.id.textVoucher);
		buttonAvailablePredepositPassword = (Button) findViewById(R.id.buttonAvailablePredepositPassword);
		CheckBoxCheckAvailable = (CheckBox) findViewById(R.id.CheckBoxCheckAvailable);
		textviewAvailablePredeposit = (TextView) findViewById(R.id.textviewAvailablePredeposit);
		editviewAvailablePredeposit = (EditText) findViewById(R.id.editviewAvailablePredeposit);
		linearLayoutAvailablePredeposit2 = (LinearLayout) findViewById(R.id.linearLayoutAvailablePredeposit2);
		linearLayoutAvailablePredeposit1 = (LinearLayout) findViewById(R.id.linearLayoutAvailablePredeposit1);
		textCheckPasswordFlag = (TextView) findViewById(R.id.textCheckPasswordFlag);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		invoiceListViewAdapter = new InvoiceListViewAdapter(this, this);
		myListViewAdapter = new MyInvoiceContentListViewAdapter(this);
		aStoreCartListViewAdapter = new StoreCartListViewAdapter(this);
		mylistviewInvoice.setAdapter(invoiceListViewAdapter);
		invoiceListViewAdapter.notifyDataSetChanged();
		goodsListView.setAdapter(aStoreCartListViewAdapter);
		aStoreCartListViewAdapter.notifyDataSetChanged();
		loadingBuyStep1Data();
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				finish();
			}

		});
		buttonEditInvoice
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						linearlayoutEditInvInfo.setVisibility(0);
						invoiceListViewAdapter.jilu = 0;
						textAddInvoice.setChecked(false);
						invoiceListViewAdapter.notifyDataSetChanged();
						loadingInvoiceListData();
					}

				});
		textAddInvoice
				.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						if (!flag) {
							invoiceListViewAdapter.jilu = 0;
							invoiceListViewAdapter.notifyDataSetChanged();
							linearlayoutNewInvInfo.setVisibility(8);
							return;
						} else {
							invoiceListViewAdapter.jilu = -1;
							invoiceListViewAdapter.notifyDataSetChanged();
							linearlayoutNewInvInfo.setVisibility(0);
							return;
						}
					}

				});
		buttonSaveInvoice
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						String s = "";
						String s1;
						if (invoiceListViewAdapter.jilu == -1) {
							String s2 = textInvoiceTitle.getText().toString();
							String s3 = editUnitName.getText().toString();
							String s4 = textInvoiceContent.getText().toString();
							if (s2.equals("个人")) {
								s = (new StringBuilder(String.valueOf(s)))
										.append(s2).toString();
								s2 = "person";
							} else if (s2.equals("单位")) {
								s2 = "company";
								s = (new StringBuilder(String.valueOf(s)))
										.append(s3).toString();
							}
							loadingSaveInvoiceData(s2, s3, s4);
							s1 = (new StringBuilder(String.valueOf(s))).append(
									s4).toString();
						} else {
							s1 = (new StringBuilder(String
									.valueOf(invoiceListViewAdapter.bean
											.getInv_title()))).append(
									invoiceListViewAdapter.bean
											.getInv_content()).toString();
							invoice_id = invoiceListViewAdapter.bean
									.getInv_id();
						}
						textInvInfo.setText(s1);
						linearlayoutEditInvInfo.setVisibility(8);
					}

				});
		buttonNoInvoice
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						textInvInfo.setText("不需要发票");
						invoice_id = "";
						linearlayoutEditInvInfo.setVisibility(8);
					}

				});
		textInvoiceContent
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
								BuyStep1Activity.this);
						final ListView listview = new ListView(
								BuyStep1Activity.this);
						listview.setScrollbarFadingEnabled(false);
						listview.setFastScrollEnabled(false);
						listview.setAdapter(myListViewAdapter);
						builder.setView(listview);
						final AlertDialog dialog = builder.create();
						listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

							public void onItemClick(AdapterView adapterview,
									View view, int i, long l) {
								dialog.dismiss();
								InvoiceContextList invoicecontextlist = (InvoiceContextList) listview
										.getItemAtPosition(i);
								textInvoiceContent.setText(invoicecontextlist
										.getInvoice_content_list());
							}

						});
						loadingInvoiceContextData();
					}

				});
		textInvoiceTitle
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
								BuyStep1Activity.this);
						final ListView listview = new ListView(
								BuyStep1Activity.this);
						listview.setScrollbarFadingEnabled(false);
						listview.setFastScrollEnabled(false);
						listview.setAdapter(myListViewAdapter);
						builder.setView(listview);
						final AlertDialog dialog = builder.create();
						listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

							public void onItemClick(AdapterView adapterview,
									View view, int i, long l) {
								dialog.dismiss();
								InvoiceContextList invoicecontextlist = (InvoiceContextList) listview
										.getItemAtPosition(i);
								textInvoiceTitle.setText(invoicecontextlist
										.getInvoice_content_list());
								if (invoicecontextlist
										.getInvoice_content_list().equals("单位")) {
									linearLayoutUnit.setVisibility(0);
									return;
								} else {
									linearLayoutUnit.setVisibility(8);
									return;
								}
							}

						});
						ArrayList arraylist = new ArrayList();
						arraylist.add(new InvoiceContextList("个人"));
						arraylist.add(new InvoiceContextList("单位"));
						myListViewAdapter.setDatas(arraylist);
						myListViewAdapter.notifyDataSetChanged();
					}

				});
		MyRadioButtonClickListener myradiobuttonclicklistener = new MyRadioButtonClickListener();
		radioButtonZaiPay.setOnClickListener(myradiobuttonclicklistener);
		radioButtonISOffPay.setOnClickListener(myradiobuttonclicklistener);
		linearlayoutYesAddress
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						Intent intent = new Intent(
								BuyStep1Activity.this,
								net.asiasofti.android.ui.type.BuyAddressListActivity.class);
						startActivity(intent);
					}

				});
		linearlayoutNOAddress
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						Intent intent = new Intent(
								BuyStep1Activity.this,
								net.asiasofti.android.ui.type.BuyAddressListActivity.class);
						startActivity(intent);
					}

				});
		buttonSendBuyStep
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (CheckBoxCheckAvailable.isChecked()) {
							Toast.makeText(BuyStep1Activity.this,
									"支付密码不能为空或还没有验证", 0).show();
							return;
						} else {
							SaveSendBuyStep2();
							return;
						}
					}

				});
		CheckBoxCheckAvailable
				.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton compoundbutton,
							boolean flag) {
						if (flag) {
							linearLayoutAvailablePredeposit2.setVisibility(0);
							return;
						} else {
							linearLayoutAvailablePredeposit2.setVisibility(8);
							PasswordFlag = "未验证";
							editviewAvailablePredeposit.setText("");
							textCheckPasswordFlag.setText((new StringBuilder(
									"(")).append(PasswordFlag).append(")")
									.toString());
							return;
						}
					}

				});
		buttonAvailablePredepositPassword
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						String s = editviewAvailablePredeposit.getText()
								.toString();
						if (s != null && !s.equals("") && !s.equals("null")) {
							CheackPassword(s);
							return;
						} else {
							Toast.makeText(BuyStep1Activity.this, "支付密码不能为空", 0)
									.show();
							return;
						}
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
		intentfilter.addAction(Constants.APP_BORADCASTRECEIVER2);
		registerReceiver(mBroadcastReceiver, intentfilter);
	}

	public void updataAddress(String s, String s1) {
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("city_id", s);
		hashmap.put("area_id", s1);
		hashmap.put("freight_hash", freight_hash);
		System.out.println((new StringBuilder("city_id-->")).append(s)
				.append(",area_id->").append(s1).append(",freight_hash->")
				.append(freight_hash).toString());
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_UPDATE_ADDRESS,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								if (responsedata.getCode() != 200) {
									Toast.makeText(BuyStep1Activity.this,
											"数据加载失败，请稍后重试", 0).show();
									return;
								}

								String s2 = responsedata.getJson();
								System.out
										.println((new StringBuilder("json-->"))
												.append(s2).toString());
								UpdateAddress updateaddress = UpdateAddress
										.newInstanceList(s2);
								if (!updateaddress.getAllow_offpay()
										.equals("0")) {
									if (updateaddress.getAllow_offpay().equals(
											"1"))
										radioButtonISOffPay.setVisibility(0);
								} else {
									radioButtonISOffPay.setVisibility(8);
								}
								aStoreCartListViewAdapter.updateAddressContent = updateaddress
										.getContent();
								offpay_hash = updateaddress.getOffpay_hash();
								aStoreCartListViewAdapter
										.notifyDataSetChanged();

								goods_freight = 0.0D;

								try {
									JSONObject jsonobject = new JSONObject(
											updateaddress.getContent());
									Iterator iterator = jsonobject.keys();
									while (iterator.hasNext()) {
										try {
											String s3 = jsonobject
													.getString(iterator.next()
															.toString());
											BuyStep1Activity buystep1activity = BuyStep1Activity.this;
											buystep1activity.goods_freight = buystep1activity.goods_freight
													+ Double.parseDouble(s3);
										} catch (JSONException jsonexception) {
											jsonexception.printStackTrace();
											return;
										}
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								updateVoucher();
								return;

							}

						});
	}

	public void updateVoucher() {
		textVoucher.setText((new StringBuilder(" -￥")).append(voucherPrice)
				.toString());
		textviewAllPrice.setText((new StringBuilder("￥")).append(
				(goods_freight + goods_total) - voucherPrice).toString());
		textViewGoodsTotal.setText((new StringBuilder("￥")).append(goods_total)
				.toString());
		textViewGoodsFreight.setText((new StringBuilder(" +￥")).append(
				goods_freight).toString());
	}

}
