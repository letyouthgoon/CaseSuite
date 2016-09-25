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
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.HistoryBrowseListViewAdapter;
import net.asiasofti.android.model.HistoryBrowse;

public class HistoryBrowseListActivity extends Activity
	implements android.widget.AbsListView.OnScrollListener
{

	private HistoryBrowseListViewAdapter adapter;
	private ImageView imageBack;
	private int lastItem;
	private ListView listViewHostoryBrowse;
	private Boolean list_flag;
	private List lists;
	private Handler mHandler;
	private View moreView;
	private int pnageno;

	public HistoryBrowseListActivity()
	{
		pnageno = 1;
		list_flag = Boolean.valueOf(false);
		mHandler = new Handler() {


			public void handleMessage(Message message)
			{
				HistoryBrowseListActivity historybrowselistactivity;
				switch (message.what)
				{
				default:
					return;

				case 0: // '\0'
					historybrowselistactivity = HistoryBrowseListActivity.this;
					break;
				}
				historybrowselistactivity.pnageno = 1 + historybrowselistactivity.pnageno;
				loadingGoodsListData();
			}

			
			
		};
	}

	private void loadingGoodsListData()
	{
		List list;
		if (!HistoryBrowse.HistortBrowseMoreFlag(pnageno, 10))
		{
			list_flag = Boolean.valueOf(true);
			listViewHostoryBrowse.removeFooterView(moreView);
		} else
		{
			list_flag = Boolean.valueOf(false);
			moreView.setVisibility(0);
		}
		if (pnageno == 1)
			lists.clear();
		list = HistoryBrowse.searchQueryList(pnageno, 10);
		lists.addAll(list);
		adapter.setList(lists);
		adapter.notifyDataSetChanged();
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.history_browse_listview);
		listViewHostoryBrowse = (ListView)findViewById(R.id.listViewHostoryBrowse);
		imageBack = (ImageView)findViewById(R.id.imageBack);
		moreView = getLayoutInflater().inflate(R.layout.list_more_load, null);
		adapter = new HistoryBrowseListViewAdapter(this);
		lists = new ArrayList();
		listViewHostoryBrowse.addFooterView(moreView);
		listViewHostoryBrowse.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		loadingGoodsListData();
		listViewHostoryBrowse.setOnScrollListener(this);
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view)
			{
				finish();
			}

			
		
		});
	}

	public void onScroll(AbsListView abslistview, int i, int j, int k)
	{
		lastItem = -1 + (i + j);
	}

	public void onScrollStateChanged(AbsListView abslistview, int i)
	{
		if (lastItem == -1 + listViewHostoryBrowse.getCount() && i == 0 && !list_flag.booleanValue())
			mHandler.sendEmptyMessage(0);
	}



}
