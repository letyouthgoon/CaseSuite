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
import android.content.Intent;
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.FavoritesListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.FavoritesList;
import net.asiasofti.android.model.ResponseData;
import net.asiasofti.android.ui.type.GoodsDetailsActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class FavoritesListActivity extends Activity
	implements android.widget.AbsListView.OnScrollListener
{

	private FavoritesListViewAdapter adapter;
	private ImageView imageBack;
	private int lastItem;
	private ListView listViewFavorites;
	private Boolean list_flag;
	private ArrayList lists;
	private Handler mHandler;
	private View moreView;
	private MyApp myApp;
	public int pageno;
	private TextView textFavritesEditButton;

	public FavoritesListActivity()
	{
		list_flag = Boolean.valueOf(false);
		pageno = 1;
		mHandler = new Handler() {

			

			public void handleMessage(Message message)
			{
				FavoritesListActivity favoriteslistactivity;
				switch (message.what)
				{
					case 1: // '\001'
					default:
					return;

				case 0: // '\0'
					favoriteslistactivity = FavoritesListActivity.this;
					break;
				}
				favoriteslistactivity.pageno = 1 + favoriteslistactivity.pageno;
				loadingfavoritesListData();
			}

			
			
		};
	}

	public void DeleteFaavoritesData(String s)
	{
		HashMap hashmap = new HashMap();
		hashmap.put("fav_id", s);
		hashmap.put("key", myApp.getLoginKey());
		RemoteDataHandler.asyncPost2(Constants.URL_FAVORITES_DELETE, hashmap, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {


			 public void dataLoaded(ResponseData paramAnonymousResponseData)
		      {
		        if (paramAnonymousResponseData.getCode() == 200)
		        {
		          String str1 = paramAnonymousResponseData.getJson();
		          if (str1.equals("1"))
		          {
		            Toast.makeText(FavoritesListActivity.this, "删除成功", 0).show();
		            FavoritesListActivity.this.loadingfavoritesListData();
		          }
		          try
		          {
		            String str2 = new JSONObject(str1).getString("error");
		            if (str2 != null)
		              Toast.makeText(FavoritesListActivity.this, str2, 0).show();
		            return;
		          }
		          catch (JSONException localJSONException)
		          {
		            localJSONException.printStackTrace();
		            return;
		          }
		        }
		        Toast.makeText(FavoritesListActivity.this, "数据加载失败，请稍后重试", 0).show();
		      }
		    });
	}

	public void loadingfavoritesListData()
	{
		String s = (new StringBuilder(Constants.URL_FAVORITES_LIST + "&curpage=")).append(pageno).append("&page=").append(10).toString();
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		RemoteDataHandler.asyncPost2(s, hashmap, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {



			public void dataLoaded(ResponseData responsedata)
			{
				if (responsedata.getCode() == 200)
				{
				String s1 = responsedata.getJson();
				if (responsedata.getLogin() == 0)
					myApp.setLoginKey("");
				
				if (!responsedata.isHasMore())
				{
					list_flag = Boolean.valueOf(true);
					listViewFavorites.removeFooterView(moreView);
				} else
				{
					list_flag = Boolean.valueOf(false);
					moreView.setVisibility(0);
				}
				try
				{
					String s3 = (new JSONObject(s1)).getString("favorites_list");
					if (pageno == 1)
						lists.clear();
					ArrayList arraylist = FavoritesList.newInstanceList(s3);
					lists.addAll(arraylist);
					adapter.setfList(lists);
					adapter.notifyDataSetChanged();
				}
				catch (JSONException jsonexception)
				{
					jsonexception.printStackTrace();
				}
				try
				{
					String s2 = (new JSONObject(s1)).getString("error");
					if(s2!=null)
					{
						Toast.makeText(FavoritesListActivity.this, s2, 0).show();
					}
					return;
				}
				catch (JSONException jsonexception1)
				{
					jsonexception1.printStackTrace();
					return;
				}
				
				
				
			}
				Toast.makeText(FavoritesListActivity.this, "数据加载失败，请稍后重试", 0).show();

			
		
			}});
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.favorites_listview);
		myApp = (MyApp)getApplication();
		listViewFavorites = (ListView)findViewById(R.id.listViewFavorites);
		imageBack = (ImageView)findViewById(R.id.imageBack);
		textFavritesEditButton = (TextView)findViewById(R.id.textFavritesEditButton);
		moreView = getLayoutInflater().inflate(R.layout.list_more_load, null);
		adapter = new FavoritesListViewAdapter(this, this);
		lists = new ArrayList();
		listViewFavorites.addFooterView(moreView);
		listViewFavorites.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		loadingfavoritesListData();
		listViewFavorites.setOnScrollListener(this);
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {


			public void onClick(View view)
			{
				finish();
			}
		});
		textFavritesEditButton.setOnClickListener(new android.view.View.OnClickListener() {


			public void onClick(View view)
			{
				if (adapter.isCheckEdit())
				{
					adapter.setCheckEdit(false);
					textFavritesEditButton.setText("编辑");
				} else
				{
					adapter.setCheckEdit(true);
					textFavritesEditButton.setText("完成");
				}
				adapter.notifyDataSetChanged();
			}

		});
		listViewFavorites.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {


			public void onItemClick(AdapterView adapterview, View view, int i, long l)
			{
				FavoritesList favoriteslist = (FavoritesList)listViewFavorites.getItemAtPosition(i);
				if (favoriteslist != null)
				{
					Intent intent = new Intent(FavoritesListActivity.this, net.asiasofti.android.ui.type.GoodsDetailsActivity.class);
					intent.putExtra("goods_id", favoriteslist.getFav_id());
					startActivity(intent);
				}
			}

		});
	}

	public void onScroll(AbsListView abslistview, int i, int j, int k)
	{
		lastItem = -1 + (i + j);
	}

	public void onScrollStateChanged(AbsListView abslistview, int i)
	{
		if (lastItem == -1 + listViewFavorites.getCount() && i == 0 && !list_flag.booleanValue())
			mHandler.sendEmptyMessage(0);
	}

}
