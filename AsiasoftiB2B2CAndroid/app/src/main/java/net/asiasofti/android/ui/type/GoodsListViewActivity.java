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

import java.net.URLEncoder;
import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.GoodsListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.GoodsList;
import net.asiasofti.android.model.HistoryBrowse;
import net.asiasofti.android.model.ResponseData;
import net.asiasofti.android.ui.viewpager.ViewPagerDemoActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviPara;

// Referenced classes of package net.asiasofti.android.ui.type:
//			GoodsDetailsActivity

public class GoodsListViewActivity extends Activity implements
		android.widget.AbsListView.OnScrollListener {

	private GoodsListViewAdapter adapter;
	private String gc_id;
	private String gc_type;
	private ListView goodsListView;
	private String keyword;
	private int lastItem;
	private Boolean list_flag;
	private ArrayList lists;
	private BroadcastReceiver mBroadcastReceiver;
	private Handler mHandler;
	private View moreView;
	private String order;
	private int pageno;
	private TextView textNoNoDatas;
     private static Dialog dialog;
     private static View view;
     
 
	public GoodsListViewActivity() {
		list_flag = Boolean.valueOf(false);
		pageno = 1;
		mBroadcastReceiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent) {
				if (intent.getAction().equals(Constants.HOST)) {
					order = intent.getStringExtra("order");
					pageno = 1;
					goodsListView.smoothScrollToPosition(0);
					loadingGoodsListData(gc_id, gc_type, order);
				}
			}

		};
		mHandler = new Handler() {

			public void handleMessage(Message message) {
				GoodsListViewActivity goodslistviewactivity;
				switch (message.what) {
				case 1: // '\001'
				default:
					return;

				case 0: // '\0'
					goodslistviewactivity = GoodsListViewActivity.this;
					break;
				}
				goodslistviewactivity.pageno = 1 + goodslistviewactivity.pageno;
				loadingGoodsListData(gc_id, gc_type, order);
			}

		};
	}

	public void loadingGoodsListData(String s, String s1, String s2) {
		String s3;
		if (keyword != null && !keyword.equals("") && !keyword.equals("null")) {
			String s4 = URLEncoder.encode(keyword);
			s3 = (new StringBuilder(Constants.URL_GOODSLIST + "&keyword="))
					.append(s4).append("&curpage=").append(pageno)
					.append("&page=").append(10).append("&key=").append(s1)
					.toString();
			if (s2 != null && !s2.equals("null") && !s2.equals(""))
				s3 = (new StringBuilder(Constants.URL_GOODSLIST + "&curpage="))
						.append(pageno).append("&keyword=").append(s4)
						.append("&page=").append(10).append("&key=").append(s1)
						.append("&order=").append(s2).toString();
		} else {
			s3 = (new StringBuilder(Constants.URL_GOODSLIST + "&gc_id="))
					.append(s).append("&curpage=").append(pageno)
					.append("&page=").append(10).append("&key=").append(s1)
					.toString();
			if (s2 != null && !s2.equals("null") && !s2.equals(""))
				s3 = (new StringBuilder(Constants.URL_GOODSLIST + "&curpage="))
						.append(pageno).append("&gc_id=").append(s)
						.append("&page=").append(10).append("&key=").append(s1)
						.append("&order=").append(s2).toString();
		}
		if (s3 == null) { 
			Toast.makeText(this, "数据加载失败，请稍后重试", 0).show();
			return;
		} else {
			System.out.println((new StringBuilder("url-->111")).append(s3)
					.toString());
			RemoteDataHandler
					.asyncStringGet(
							s3,
							new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

								public void dataLoaded(ResponseData responsedata) {
									if (responsedata.getCode() != 200) {
										goodsListView
												.removeFooterView(moreView);
										Toast.makeText(
												GoodsListViewActivity.this,
												"数据加载失败，请稍后重试", 0).show();
										return;
									}
									String s5 = responsedata.getJson();
									String s6;
									ArrayList arraylist;
									if (!responsedata.isHasMore()) {
										list_flag = Boolean.valueOf(true);
										goodsListView
												.removeFooterView(moreView);
									} else {
										list_flag = Boolean.valueOf(false);
										moreView.setVisibility(0);
									}
									try {
										s6 = (new JSONObject(s5))
												.getString("goods_list");
										System.out.println((new StringBuilder(
												"array-->")).append(s6)
												.toString());
										if (s6 == "") {
											return;
										}
										if (s6.equals("array") || s6 == null) {
											return;
										}
										if (!s6.equals("[]")) {
											if (pageno == 1)
												lists.clear();
											arraylist = GoodsList
													.newInstanceList(s6);
											lists.addAll(arraylist);
											adapter.setGoodsLists(lists);
											adapter.notifyDataSetChanged();
											return;
										} else {
											textNoNoDatas.setVisibility(0);
										}
										return;
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
									}
									return;

								}

							});
			return;
		}
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.goods_list);
		
		
		view = LayoutInflater.from(this).inflate(R.layout.dituchu,
				null);
		dialog = new Dialog(this, R.style.MyDialog);
		gc_id = getIntent().getStringExtra("gc_id");
		gc_type = getIntent().getStringExtra("gc_type");
		order = getIntent().getStringExtra("order");
		keyword = getIntent().getStringExtra("keyword");
		moreView = getLayoutInflater().inflate(R.layout.list_more_load, null);
		goodsListView = (ListView) findViewById(R.id.goodsListView);
		textNoNoDatas = (TextView) findViewById(R.id.textNoNoDatas);
		goodsListView.addFooterView(moreView);
		lists = new ArrayList();
		adapter = new GoodsListViewAdapter(this);
		goodsListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		goodsListView.setSelection(0);
		loadingGoodsListData(gc_id, gc_type, order);
		goodsListView
				.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView adapterview, View view,
							int i, long l) {
						GoodsList goodslist = (GoodsList) goodsListView
								.getItemAtPosition(i);
						if (goodslist != null) {
							Intent intent = new Intent(
									GoodsListViewActivity.this,
									net.asiasofti.android.ui.type.GoodsDetailsActivity.class);
							intent.putExtra("goods_id", goodslist.getGoods_id());
							startActivity(intent);
//							HistoryBrowse.historyBrowseSava2(goodslist);
						}
					}
				});
		goodsListView.setOnScrollListener(this);
	}

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mBroadcastReceiver);
	}

	public void onScroll(AbsListView abslistview, int i, int j, int k) {
		lastItem = -1 + (i + j);
	}

	public void onScrollStateChanged(AbsListView abslistview, int i) {
		if (lastItem == -1 + goodsListView.getCount() && i == 0
				&& !list_flag.booleanValue())
			mHandler.sendEmptyMessage(0);
	}

	protected void onStart() {
		super.onStart();
		registerBoradcastReceiver();
	}

	public void registerBoradcastReceiver() {
		IntentFilter intentfilter = new IntentFilter();
		intentfilter.addAction(Constants.HOST);
		registerReceiver(mBroadcastReceiver, intentfilter);
	}
	

	/**
	 * 开始导航
	 * 
	 * @param view
	 */
	public void startNavi(View view) {
		 // 天安门坐标
	 	double mLat1 = ViewPagerDemoActivity.nlatitude;
	 	double mLon1 = ViewPagerDemoActivity.nlontitude;
	 	// 百度大厦坐标
	 	double mLat2 = 39.815606;
	 	double mLon2 = 110.014603;
		LatLng pt1 = new LatLng(mLat1, mLon1);
		LatLng pt2 = new LatLng(mLat2, mLon2);
		// 构建 导航参数
		NaviPara para = new NaviPara();
		para.startPoint = pt1;
		para.startName = ViewPagerDemoActivity.AddressName;
		para.endPoint = pt2;
		para.endName = "鄂尔多斯市东胜区中正集团";

		try {

			BaiduMapNavigation.openBaiduMapNavi(para, this);

		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					BaiduMapNavigation.getLatestBaiduMapApp(GoodsListViewActivity.this);
				}
			});
			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});

			builder.create().show();
		}
	}
}
