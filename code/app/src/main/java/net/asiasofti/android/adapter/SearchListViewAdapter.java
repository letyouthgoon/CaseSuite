package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import net.asiasofti.android.R;
import net.asiasofti.android.model.Search;

public class SearchListViewAdapter extends BaseAdapter
{
	class ViewHolder
	{

		TextView searchKeyWord;
	

		ViewHolder()
		{
			super();
		}
	}


	private Context context;
	private LayoutInflater inflater;
	private List searchLists;

	public SearchListViewAdapter(Context context1)
	{
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public int getCount()
	{
		int i;
		if (searchLists == null)
			i = 0;
		else
			i = searchLists.size();
		if (i >= 20)
			i = 20;
		return i;
	}

	public Object getItem(int i)
	{
		return searchLists.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public List getSearchLists()
	{
		return searchLists;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		ViewHolder viewholder;
		Search search;
		if (view == null)
		{
			view = inflater.inflate(R.layout.listivew_search_item, null);
			viewholder = new ViewHolder();
			viewholder.searchKeyWord = (TextView)view.findViewById(R.id.searchKeyWord);
			view.setTag(viewholder);
		} else
		{
			viewholder = (ViewHolder)view.getTag();
		}
		search = (Search)searchLists.get(i);
		viewholder.searchKeyWord.setText(search.searchKeyWord);
		return view;
	}

	public void setSearchLists(List list)
	{
		searchLists = list;
	}
}
