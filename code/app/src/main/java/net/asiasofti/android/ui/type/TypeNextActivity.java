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
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.TypeNextExpandableListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.HttpHelper;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.ResponseData;
import net.asiasofti.android.model.TypeNext;
import net.asiasofti.android.ui.search.SearchActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class TypeNextActivity extends Activity {

	private TypeNextExpandableListViewAdapter adapter;
	private ImageView imageBack;
	private ImageView imageSearch;
	private TextView textTypeTitleName;
	private ExpandableListView typeNextExpandableListView;

	public TypeNextActivity() {
	}

	public void loadingTypeNextCData(final String gc_id) {
		RemoteDataHandler
				.asyncStringGet(
						(new StringBuilder(
								Constants.URL_GOODSCLASS + "&gc_id="))
								.append(gc_id).toString(),
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {

								if (responsedata.getCode() != 200) {
									Toast.makeText(TypeNextActivity.this,
											"数据加载失败，请稍后重试", 0).show();
									return;
								}
								String s = responsedata.getJson();

								try {
									String s1 = (new JSONObject(s))
											.getString("class_list");
									if (s1.toString().equals("0")
											|| s1.toString().equals("null")) {
										Intent intent = new Intent(
												TypeNextActivity.this,
												net.asiasofti.android.ui.type.GoodsTabActivity.class);
										startActivity(intent);
										finish();
										return;
									}
									ArrayList arraylist = new ArrayList();
									arraylist.add(new TypeNext(gc_id, "所有商品"));
									arraylist.addAll(TypeNext
											.newInstanceList(s1));
									adapter.setTypeNextCList(arraylist);
									adapter.notifyDataSetChanged();
									return;
								} catch (JSONException jsonexception) {
									jsonexception.printStackTrace();
								}
								return;

							}

						});
	}

	public void loadingTypeNextGData(String s) {
		RemoteDataHandler
				.asyncStringGet(
						(new StringBuilder(
								Constants.URL_GOODSCLASS+"&gc_id="))
								.append(s).toString(),
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								if (responsedata.getCode() == 200) {
									String s1 = responsedata.getJson();
									try {
										ArrayList arraylist = TypeNext
												.newInstanceList((new JSONObject(
														s1))
														.getString("class_list"));
										adapter.setTypeNextGList(arraylist);
										adapter.notifyDataSetChanged();
										return;
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
									}
									return;
								} else {
									Toast.makeText(TypeNextActivity.this,
											"数据加载失败，请稍后重试", 0).show();
									return;
								}
							}

						});
	}

	protected void onCreate(Bundle bundle) {

		super.onCreate(bundle);
		String s = getIntent().getStringExtra("gc_id");
		String s1 = getIntent().getStringExtra("gc_name");

		try {
			String s2 = (new JSONObject(
					(new JSONObject(
							HttpHelper
									.get((new StringBuilder(
											Constants.URL_GOODSCLASS + "&gc_id="))
											.append(s).toString())))
							.getString("datas"))).getString("class_list");
			if (s2.toString().equals("0") || s2.toString().equals("null")) {
				Intent intent = new Intent(this,
						net.asiasofti.android.ui.type.GoodsTabActivity.class);
				intent.putExtra("gc_id", s);
				intent.putExtra("gc_name", s1);
				startActivity(intent);
				finish();
				return;
			}
			setContentView(R.layout.type_next);
			typeNextExpandableListView = (ExpandableListView) findViewById(R.id.typeNextExpandableListView);
			textTypeTitleName = (TextView) findViewById(R.id.textTypeTitleName);
			imageBack = (ImageView) findViewById(R.id.imageBack);
			imageSearch = (ImageView) findViewById(R.id.imageSearch);
			adapter = new TypeNextExpandableListViewAdapter(this);
			typeNextExpandableListView.setAdapter(adapter);
			textTypeTitleName.setText(s1);
			imageBack
					.setOnClickListener(new android.view.View.OnClickListener() {

						public void onClick(View view) {
							finish();
						}

					});
			imageSearch
					.setOnClickListener(new android.view.View.OnClickListener() {

						public void onClick(View view) {
							Intent intent1 = new Intent(
									TypeNextActivity.this,
									net.asiasofti.android.ui.search.SearchActivity.class);
							startActivity(intent1);
						}

					});
			loadingTypeNextGData(s);
			typeNextExpandableListView
					.setOnGroupExpandListener(new android.widget.ExpandableListView.OnGroupExpandListener() {

						public void onGroupExpand(int i) {
							int j = 0;
							do {
								if (j >= adapter.getGroupCount()) {
									TypeNext typenext = (TypeNext) adapter
											.getGroup(i);
									loadingTypeNextCData(typenext.getGc_id());
									return;
								}
								if (i != j)
									typeNextExpandableListView.collapseGroup(j);
								j++;
							} while (true);
						}

					});
			typeNextExpandableListView
					.setOnChildClickListener(new android.widget.ExpandableListView.OnChildClickListener() {

						public boolean onChildClick(
								ExpandableListView expandablelistview,
								View view, int i, int j, long l) {
							TypeNext typenext = (TypeNext) adapter.getChild(i,
									j);
							Intent intent1 = new Intent(
									TypeNextActivity.this,
									net.asiasofti.android.ui.type.GoodsTabActivity.class);
							intent1.putExtra("gc_id", typenext.getGc_id());
							intent1.putExtra("gc_name", typenext.getGc_name());
							startActivity(intent1);
							return false;
						}

					});
			return;
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
			return;
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
		}
		return;
	}

}
