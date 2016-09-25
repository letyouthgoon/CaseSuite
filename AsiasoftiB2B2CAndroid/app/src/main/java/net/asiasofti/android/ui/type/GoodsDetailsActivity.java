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
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.io.PrintStream;
import java.util.*;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.*;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.*;
import net.asiasofti.android.ui.custom.MyGridView;
import net.asiasofti.android.ui.custom.MyListView;
import net.asiasofti.android.ui.mystore.MyStoreActivity;

import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package net.asiasofti.android.ui.type:
//			GoodsDetailsWebAcivity, BuyStep1Activity

public class GoodsDetailsActivity extends Activity {

	private int GoodsNumberCount;
	private ScrollView SView;
	private GoodsDetailsGalleryAdapter adapter;
	private Button buttonAddShopCart;
	private Button buttonGoodsNumberADD;
	private Button buttonGoodsNumberMinus;
	private Button buttonNowBuy;
	private CommendGridViewAdapter commendAdapter;
	private Gallery goodsDetailsGallery;
	private int goods_kucun;
	private MyGridView gridViewCommend;
	private ImageButton imageBackButton;
	private ImageButton imageButtonFaavoritesAdd;
	private LinearLayout linearLayoutSpecView;
	private LinearLayout linearlayoutManSong;
	private MyListView listViewManSongRules;
	private ManSongRulesListViewAdapter manSongRulesAdapter;
	private MyApp myApp;
	private TextView textGoodsDetailsMarketPrice;
	private TextView textGoodsDetailsName;
	private TextView textGoodsDetailsPrice;
	private TextView textGoodsDetailsStorage;
	private TextView textGoodsDetailsWeb;
	private TextView textGoodsNumberValues;
	private int upper_limit;
	private ImageView imageViewBack;

	public GoodsDetailsActivity() {
		GoodsNumberCount = 1;
		upper_limit = 0;
		goods_kucun = 0;
	}

	public void addFaavoritesData(String s) {
		HashMap hashmap = new HashMap();
		hashmap.put("goods_id", s);
		hashmap.put("key", myApp.getLoginKey());
		RemoteDataHandler.asyncPost2(Constants.URL_ADD_FAVORITES, hashmap,
				new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

					public void dataLoaded(ResponseData responsedata) {
						if (responsedata.getCode() != 200) {
							Toast.makeText(GoodsDetailsActivity.this,
									"数据加载失败，请稍后重试", 0).show();
							return;
						}
						String s1 = responsedata.getJson();
						if (s1.equals("1"))
							Toast.makeText(GoodsDetailsActivity.this, "收藏成功", 0)
									.show();
						String s2;
						try {
							s2 = (new JSONObject(s1)).getString("error");
						} catch (JSONException jsonexception) {
							jsonexception.printStackTrace();
							return;
						}
						if (s2 != null) {
							Toast.makeText(GoodsDetailsActivity.this, s2, 0)
									.show();
						}
						return;

					}

				});
	}

	public void loadingGoodsDetailsData(String s) {
		String url = (new StringBuilder(Constants.URL_GOODSDETAILS
				+ "&goods_id=")).append(s).toString();
		System.out
				.println((new StringBuilder("url-->")).append(url).toString());
		RemoteDataHandler.asyncStringGet(url,
				new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

					public void dataLoaded(ResponseData responsedata) {
						String respStr;
						if (responsedata.getCode() != 200) {
							Toast.makeText(GoodsDetailsActivity.this,
									"数据加载失败，请稍后重试", 0).show();
							return;
						}
						respStr = responsedata.getJson();

						GoodsDetails goodsdetails;
						try {
							JSONObject respJson = new JSONObject(respStr);
							String goodsInfo = respJson.getString("goods_info");
							String goodsImage = respJson
									.getString("goods_image");
							String mansongInfo = respJson
									.getString("mansong_info");
							String goodsCommondList = respJson
									.getString("goods_commend_list");
							final String specListStr = respJson
									.getString("spec_list");
							goodsdetails = GoodsDetails
									.newInstanceList(goodsInfo);
							textGoodsDetailsName.setText(goodsdetails
									.getGoods_name());
							textGoodsDetailsPrice.setText((new StringBuilder(
									"￥")).append(goodsdetails.getGoods_price())
									.toString());
							textGoodsDetailsMarketPrice
									.setText((new StringBuilder("￥"))
											.append(goodsdetails
													.getGoods_marketprice())
											.toString());
							textGoodsDetailsMarketPrice.getPaint().setFlags(16);
							textGoodsDetailsStorage.setText((new StringBuilder(
									String.valueOf(goodsdetails
											.getGoods_storage()))).append("件")
									.toString());
							goods_kucun = Integer.parseInt(goodsdetails
									.getGoods_storage());
							GoodsDetailsActivity.this.adapter
									.setGoods_image(goodsImage.split(","));
							GoodsDetailsActivity.this.adapter
									.notifyDataSetChanged();
							if (goodsdetails.getPromotion_type().equals(
									"groupbuy")) {
								buttonAddShopCart.setVisibility(8);
							}
							if (goodsdetails.getPromotion_price() != null
									&& !goodsdetails.getPromotion_price()
											.equals("")
									&& !goodsdetails.getPromotion_price()
											.equals("null")) {
								textGoodsDetailsPrice
										.setText((new StringBuilder("￥"))
												.append(goodsdetails
														.getPromotion_price())
												.toString());
							}
							if (goodsdetails.getUpper_limit() != null
									&& !goodsdetails.getUpper_limit()
											.equals("")
									&& !goodsdetails.getUpper_limit().equals(
											"null")) {
								upper_limit = Integer.parseInt(goodsdetails
										.getUpper_limit());
							}
							if (!mansongInfo.equals("")
									&& !mansongInfo.equals("null")
									&& mansongInfo != null) {
								linearlayoutManSong.setVisibility(0);
								ArrayList mansongRuleList = ManSongRules
										.newInstanceList(ManSongInFo
												.newInstanceList(mansongInfo)
												.getRules());
								manSongRulesAdapter.setmRules(mansongRuleList);
								manSongRulesAdapter.notifyDataSetChanged();
							}

							ArrayList commendList = CommendList
									.newInstanceList(goodsCommondList);
							commendAdapter.setCommendLists(commendList);
							commendAdapter.notifyDataSetChanged();
							JSONObject specNameJson = new JSONObject(
									goodsdetails.getSpec_name());
							JSONObject specValueJson = new JSONObject(
									goodsdetails.getSpec_value());
							JSONObject currentGoodsSpecJson = new JSONObject(
									goodsdetails.getGoods_spec());
							Iterator specNameIt = specNameJson.keys();
							if (!specNameIt.hasNext()) {
								SView.smoothScrollTo(0, 20);
								return;
							}
							Iterator currentGoodsSpecIt = currentGoodsSpecJson
									.keys();
							final int oldspec[] = new int[specNameJson.length()];

							ArrayList querySpecList = new ArrayList();
							int i = 0;
							while (currentGoodsSpecIt.hasNext()) {

								String currentGoodsSpecId = currentGoodsSpecIt
										.next().toString();
								String currentGoodsSpecVaue = currentGoodsSpecJson
										.getString(currentGoodsSpecId);
								oldspec[i] = Integer
										.parseInt(currentGoodsSpecId);
								i++;
								querySpecList.add(currentGoodsSpecVaue);
							}

							String keyValue = "";

							while (specNameIt.hasNext()) {
								ArrayList allSpecList = new ArrayList();
								String keyName = specNameIt.next().toString();// example{1:"颜色"，2:尺码}
																				// 则
																				// keyname=1
																				// keyvalue="颜色"
								keyValue = specNameJson.getString(keyName);
								String valueStr = specValueJson
										.getString(keyName);// spec
															// value{"464":"金驼","465":"翠绿"}
								JSONObject valueStrJson = new JSONObject(
										valueStr);
								Iterator iterator2 = valueStrJson.keys();
								while (iterator2.hasNext()) {
									String specId = iterator2.next().toString();
									String specName = valueStrJson
											.getString(specId);
									Spec spec = new Spec();
									spec.setSpecID(specId);
									spec.setSpecName(specName);
									allSpecList.add(spec);
								}
								View goodsDetailSpecview = LayoutInflater.from(
										GoodsDetailsActivity.this).inflate(
										R.layout.goods_details_spec_view, null);
								TextView specNameText = (TextView) goodsDetailSpecview
										.findViewById(R.id.textSpecName);
								final MyGridView gridViewSpec = (MyGridView) goodsDetailSpecview
										.findViewById(R.id.gridViewSpec);
								final SpecGridViewAdapter adapter = new SpecGridViewAdapter(
										GoodsDetailsActivity.this);
								specNameText.setText(keyValue);

								adapter.setQuerySpecList(querySpecList);
								adapter.setListSpec(allSpecList);
								gridViewSpec.setAdapter(adapter);
								adapter.notifyDataSetChanged();
								linearLayoutSpecView
										.addView(goodsDetailSpecview);
								gridViewSpec
										.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
											public void onItemClick(
													AdapterView parentview,
													View view, int position,
													long id) {

												String oldIdStr = "";
												String newIdStr = "";
												Spec selectSpec = (Spec) gridViewSpec
														.getItemAtPosition(position);// 当前商品的规格
												Arrays.sort(oldspec);
												for (int k = 0; k <= oldspec.length - 1; k++) {
													if (oldspec[k] != 0)
														oldIdStr = (new StringBuilder(
																String.valueOf(oldIdStr)))
																.append(oldspec[k])
																.append("|")
																.toString();
												}

												for (int j = 0; j <= oldspec.length - 1; j++) {
													if (oldspec[j] != 0) {

														String valueTemp = String
																.valueOf(
																		oldspec[j])
																.replace(
																		adapter.getOld_specID(),
																		selectSpec
																				.getSpecID());

														oldspec[j] = Integer
																.parseInt((String
																		.valueOf(oldspec[j])
																		.replace(
																				adapter.getOld_specID(),
																				selectSpec
																						.getSpecID())));
														newIdStr = (new StringBuilder(
																String.valueOf(newIdStr)))
																.append(oldspec[j])
																.append("|")
																.toString();
													}

												}
												int[] temp2 = oldspec;

												String subNewIdStr = newIdStr.substring(
														0,
														-1 + newIdStr.length());

												String subOldIdStr = oldIdStr.substring(
														0,
														-1 + oldIdStr.length());

												if (subNewIdStr
														.equals(subOldIdStr)) {
													return;
												}

												try {
													JSONObject specListJson = new JSONObject(
															specListStr);// specListStr
																			// 属性列表对象中寻找组合
																			// ID
													Iterator specListJsonIt = specListJson
															.keys();// 格式"465|466":"402"
																	// 每种属性组合对应一个商品ID

													while (specListJsonIt
															.hasNext()) {
														String commonSpecValue = specListJsonIt
																.next()
																.toString();// commonSpecValue
																			// 属性ID
																			// 组合
														System.out
																.println((new StringBuilder(
																		"SpecID-->"))
																		.append(commonSpecValue)
																		.append(",")
																		.append(subOldIdStr)
																		.toString());
														if (commonSpecValue
																.equals(subNewIdStr)) {
															String commonSpecID = specListJson
																	.getString(commonSpecValue); // commonSpecID
																									// 组合对应的最终goodsID
															System.out
																	.println((new StringBuilder(
																			"跳转的GOODSID-->"))
																			.append(commonSpecID)
																			.toString());
															Intent intent = new Intent(
																	GoodsDetailsActivity.this,
																	net.asiasofti.android.ui.type.GoodsDetailsActivity.class);
															intent.putExtra(
																	"goods_id",
																	commonSpecID);
															startActivity(intent);
															finish();
														}
													}
												} catch (JSONException e) {
													e.printStackTrace();
												}

												return;

											}

										});
							}

						}

						catch (JSONException e) {
							e.printStackTrace();
						}

					}
				});
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.goods_details_view);
		final String goods_id = getIntent().getStringExtra("goods_id");
		myApp = (MyApp) getApplication();
		textGoodsDetailsName = (TextView) findViewById(R.id.textGoodsDetailsName);
		textGoodsDetailsPrice = (TextView) findViewById(R.id.textGoodsDetailsPrice);
		textGoodsDetailsMarketPrice = (TextView) findViewById(R.id.textGoodsDetailsMarketPrice);
		textGoodsDetailsStorage = (TextView) findViewById(R.id.textGoodsDetailsStorage);
		textGoodsDetailsWeb = (TextView) findViewById(R.id.textGoodsDetailsWeb);
		linearLayoutSpecView = (LinearLayout) findViewById(R.id.linearLayoutSpecView);
		listViewManSongRules = (MyListView) findViewById(R.id.listViewManSongRules);
		gridViewCommend = (MyGridView) findViewById(R.id.gridViewCommend);
		linearlayoutManSong = (LinearLayout) findViewById(R.id.linearlayoutManSong);
		imageButtonFaavoritesAdd = (ImageButton) findViewById(R.id.imageButtonFaavoritesAdd);
		buttonAddShopCart = (Button) findViewById(R.id.buttonAddShopCart);
		buttonNowBuy = (Button) findViewById(R.id.buttonNowBuy);
		SView = (ScrollView) findViewById(R.id.SView);
		buttonGoodsNumberMinus = (Button) findViewById(R.id.buttonGoodsNumberMinus);
		buttonGoodsNumberADD = (Button) findViewById(R.id.buttonGoodsNumberADD);
		textGoodsNumberValues = (TextView) findViewById(R.id.textGoodsNumberValues);
		manSongRulesAdapter = new ManSongRulesListViewAdapter(this);
		listViewManSongRules.setAdapter(manSongRulesAdapter);
		manSongRulesAdapter.notifyDataSetChanged();
		commendAdapter = new CommendGridViewAdapter(this);
		imageViewBack = (ImageView) findViewById(R.id.imageBack);
		gridViewCommend.setAdapter(commendAdapter);
		commendAdapter.notifyDataSetChanged();
		imageViewBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		gridViewCommend
				.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView adapterview, View view,
							int i, long l) {
						CommendList commendlist = (CommendList) gridViewCommend
								.getItemAtPosition(i);
						Intent intent = new Intent(
								GoodsDetailsActivity.this,
								net.asiasofti.android.ui.type.GoodsDetailsActivity.class);
						intent.putExtra("goods_id", commendlist.getGoods_id());
						startActivity(intent);
						finish();
					}

				});
		goodsDetailsGallery = (Gallery) findViewById(R.id.goodsDetailsGallery);
		adapter = new GoodsDetailsGalleryAdapter(this);
		goodsDetailsGallery.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		loadingGoodsDetailsData(goods_id);
		imageButtonFaavoritesAdd
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						addFaavoritesData(goods_id);
					}

				});
		textGoodsDetailsWeb
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						Intent intent = new Intent(
								GoodsDetailsActivity.this,
								net.asiasofti.android.ui.type.GoodsDetailsWebAcivity.class);
						intent.putExtra("goods_id", goods_id);
						startActivity(intent);
					}

				});
		buttonAddShopCart
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (myApp.getLoginKey() == null
								|| myApp.getLoginKey().equals("")
								|| myApp.getLoginKey().equals("null")) {
							Intent intent = new Intent(
									GoodsDetailsActivity.this,
									net.asiasofti.android.ui.mystore.LoginActivity.class);
							startActivity(intent);
						} else {
							if (goods_kucun == 0) {
								Toast.makeText(GoodsDetailsActivity.this,
										"库存不足", 0).show();
								return;
							} else {
								shopAddCart(goods_id);
								return;
							}
						}

					}

				});
		buttonNowBuy
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						if (myApp.getLoginKey() == null
								|| myApp.getLoginKey().equals("")
								|| myApp.getLoginKey().equals("null")) {
							Intent intent = new Intent(
									GoodsDetailsActivity.this,
									net.asiasofti.android.ui.mystore.LoginActivity.class);
							startActivity(intent);
						} else {
							if (goods_kucun == 0) {
								Toast.makeText(GoodsDetailsActivity.this,
										"库存不足", 0).show();
								return;
							} else {
								Intent intent = new Intent(
										GoodsDetailsActivity.this,
										net.asiasofti.android.ui.type.BuyStep1Activity.class);
								intent.putExtra("cart_id", (new StringBuilder(
										String.valueOf(goods_id))).append("|")
										.append(GoodsNumberCount).toString());
								System.out.println("订单数据："+(new StringBuilder(
										String.valueOf(goods_id))).append("|")
										.append(GoodsNumberCount).toString());
								intent.putExtra("cartFlag", "goodsDetailsFlag");
								startActivity(intent);
							}

						}

					}

				});
		if (GoodsNumberCount <= 1) {
			GoodsNumberCount = 1;
			buttonGoodsNumberMinus
					.setBackgroundResource(R.drawable.edit_product_num_des_no_enable);
		}
		buttonGoodsNumberADD
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						GoodsDetailsActivity goodsdetailsactivity = GoodsDetailsActivity.this;
						goodsdetailsactivity.GoodsNumberCount = 1 + goodsdetailsactivity.GoodsNumberCount;
						if (upper_limit != 0 && GoodsNumberCount >= upper_limit) {
							GoodsNumberCount = upper_limit;
							Toast.makeText(
									GoodsDetailsActivity.this,
									(new StringBuilder("限购"))
											.append(upper_limit).append("个")
											.toString(), 0).show();
							buttonGoodsNumberADD
									.setBackgroundResource(0x7f020021);
						}
					
						if (GoodsNumberCount >= goods_kucun) {
							GoodsNumberCount = goods_kucun;
							Toast.makeText(GoodsDetailsActivity.this, "库存不足", 0)
									.show();
							buttonGoodsNumberADD
									.setBackgroundResource(R.drawable.edit_product_num_add_no_enable);
						}
						textGoodsNumberValues.setText((new StringBuilder())
								.append(GoodsNumberCount).toString());
						buttonGoodsNumberMinus
								.setBackgroundResource(R.drawable.edit_product_num_des_normal);
						
					}

				});
		buttonGoodsNumberMinus
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						GoodsDetailsActivity goodsdetailsactivity = GoodsDetailsActivity.this;
						goodsdetailsactivity.GoodsNumberCount = -1
								+ goodsdetailsactivity.GoodsNumberCount;
						if (GoodsNumberCount <= 1) {
							GoodsNumberCount = 1;
							buttonGoodsNumberMinus
									.setBackgroundResource(R.drawable.edit_product_num_des_no_enable);
						}
						textGoodsNumberValues.setText((new StringBuilder())
								.append(GoodsNumberCount).toString());
						buttonGoodsNumberADD
								.setBackgroundResource(R.drawable.edit_product_num_add_normal);
					}

				});
	}

	public void shopAddCart(String s) {
		HashMap hashmap = new HashMap();
		hashmap.put("goods_id", s);
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("quantity",
				(new StringBuilder(String.valueOf(GoodsNumberCount)))
						.toString());
		RemoteDataHandler.asyncPost2(Constants.URL_ADD_CART, hashmap,
				new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

					public void dataLoaded(ResponseData responsedata) {
						if (responsedata.getCode() != 200) {
							Toast.makeText(GoodsDetailsActivity.this,
									"数据加载失败，请稍后重试", 0).show();
							return;
						}
						String s1 = responsedata.getJson();
						if (s1.equals("1")) {
							Toast.makeText(GoodsDetailsActivity.this,
									"添加购物车成功", 0).show();
							return;
						}
						String s2 = null;
						try {
							s2 = (new JSONObject(s1)).getString("error");
						} catch (JSONException jsonexception) {
							jsonexception.printStackTrace();
						}
						if (s2 != null) {
							Toast.makeText(GoodsDetailsActivity.this, s2, 0)
									.show();
						}

						return;

					}

				});
	}

}
