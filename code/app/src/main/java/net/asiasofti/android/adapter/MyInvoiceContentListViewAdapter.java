package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.model.InvoiceContextList;

public class MyInvoiceContentListViewAdapter extends BaseAdapter
{

	private ArrayList datas;
	private LayoutInflater inflater;

	public MyInvoiceContentListViewAdapter(Context context)
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

	public ArrayList getDatas()
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
		((TextView)view.findViewById(0x7f0900a0)).setText(((InvoiceContextList)datas.get(i)).getInvoice_content_list());
		return view;
	}

	public void setDatas(ArrayList arraylist)
	{
		datas = arraylist;
	}
}
