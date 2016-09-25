 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import net.asiasofti.android.R;
import net.asiasofti.android.adapter.SearchListViewAdapter;
import net.asiasofti.android.model.Search;
import net.asiasofti.android.ui.type.GoodsTabActivity;

public class SearchActivity extends Activity
{

	private SearchListViewAdapter adapter;
	private EditText editSearch;
	private ImageView imageBack;
	private ListView searchListView;
	private TextView textSearchButton;

	public SearchActivity()
	{
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.search_view);
		editSearch = (EditText)findViewById(R.id.editSearch);
		textSearchButton = (TextView)findViewById(R.id.textSearchButton);
		imageBack = (ImageView)findViewById(R.id.imageBack);
		searchListView = (ListView)findViewById(R.id.searchListView);
		adapter = new SearchListViewAdapter(this);
		searchListView.setAdapter(adapter);
		adapter.setSearchLists(Search.searchQueryList());
		adapter.notifyDataSetChanged();
		textSearchButton.setOnClickListener(new android.view.View.OnClickListener() {

	

			public void onClick(View view)
			{
				String s = editSearch.getText().toString();
				if (s.equals("") || s.equals("") || s == null)
				{
					Toast.makeText(SearchActivity.this, "请输入搜索内容", 0).show();
					return;
				} else
				{
					Intent intent = new Intent(SearchActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
					intent.putExtra("keyword", s);
					intent.putExtra("gc_name", s);
					startActivity(intent);
					finish();
					Search.searchSava(new Search(s));
					return;
				}
			}

			
		});
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {


			public void onClick(View view)
			{
				finish();
			}

			
		});
		searchListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

		

			public void onItemClick(AdapterView adapterview, View view, int i, long l)
			{
				Search search = (Search)searchListView.getItemAtPosition(i);
				Intent intent = new Intent(SearchActivity.this, net.asiasofti.android.ui.type.GoodsTabActivity.class);
				intent.putExtra("keyword", search.searchKeyWord);
				intent.putExtra("gc_name", search.searchKeyWord);
				startActivity(intent);
				finish();
			}

			
			
		});
	}


}
