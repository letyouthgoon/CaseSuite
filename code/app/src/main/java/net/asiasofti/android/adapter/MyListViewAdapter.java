package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import net.asiasofti.android.R;
import net.asiasofti.android.model.CityList;

public class MyListViewAdapter extends BaseAdapter
{

	private List datas;
	private LayoutInflater inflater;

	public MyListViewAdapter(Context context)
	{
		inflater = LayoutInflater.from(context);
	}

	public int getCount()
	{
		if (datas == null)
			return 0;
		else
			return datas.size();
	}

	public List getDatas()
	{
		return datas;
	}

	public Object getItem(int i)
	{
		return datas.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		if (view == null)
			view = inflater.inflate(R.layout.listview_select_city_item, null);
		((TextView)view.findViewById(R.id.title)).setText(((CityList)datas.get(i)).getArea_name());
		return view;
	}

	public void setDatas(List list)
	{
		datas = list;
	}
}
