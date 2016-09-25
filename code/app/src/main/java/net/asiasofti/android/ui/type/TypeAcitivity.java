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
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import net.asiasofti.android.R;
import net.asiasofti.android.adapter.TypeListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.ResponseData;
import net.asiasofti.android.model.Type;
import net.asiasofti.android.ui.custom.MyListView;
import net.asiasofti.android.ui.search.SearchActivity;

import org.json.JSONException;
import org.json.JSONObject;


public class TypeAcitivity extends Activity
{

	private TypeListViewAdapter adapter;
	private ImageView imageSearch;
	private MyApp myApp;
	private MyListView typeListView;

	public TypeAcitivity()
	{
	}

	public void loadingTypeData()
	{
		RemoteDataHandler.asyncStringGet(Constants.URL_GOODSCLASS, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {


			public void dataLoaded(ResponseData responsedata)
			{
				if (responsedata.getCode() == 200)
				{
					String s = responsedata.getJson();
					try
					{
						java.util.ArrayList arraylist = Type.newInstanceList((new JSONObject(s)).getString("class_list"));
						adapter.setTypeList(arraylist);
						adapter.notifyDataSetChanged();
						return;
					}
					catch (JSONException jsonexception)
					{
						jsonexception.printStackTrace();
					}
					return;
				} else
				{
					Toast.makeText(TypeAcitivity.this, "数据加载失败，请稍后重试", 0).show();
					return;
				}
			}

			
			
		});
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.tab_type);
		myApp = (MyApp)getApplication();
		typeListView = (MyListView)findViewById(R.id.typeListView);
		imageSearch = (ImageView)findViewById(R.id.imageSearch);
		adapter = new TypeListViewAdapter(this);
		typeListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		loadingTypeData();
		typeListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {



			public void onItemClick(AdapterView adapterview, View view, int i, long l)
			{
				Type type = (Type)typeListView.getItemAtPosition(i);
				Intent intent = new Intent(TypeAcitivity.this, net.asiasofti.android.ui.type.TypeNextActivity.class);
				intent.putExtra("gc_id", type.getGc_id());
				intent.putExtra("gc_name", type.getGc_name());
				startActivity(intent);
			}

			
			
		});
		imageSearch.setOnClickListener(new android.view.View.OnClickListener() {

		

			public void onClick(View view)
			{
				Intent intent = new Intent(TypeAcitivity.this, net.asiasofti.android.ui.search.SearchActivity.class);
				startActivity(intent);
			}

			
			
		});
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


}
