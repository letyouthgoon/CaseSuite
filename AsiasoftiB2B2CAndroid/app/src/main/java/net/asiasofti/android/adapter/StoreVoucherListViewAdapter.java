package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.model.StoreVoucherList;

public class StoreVoucherListViewAdapter extends BaseAdapter
{

	private ArrayList datas;
	private LayoutInflater inflater;

	public StoreVoucherListViewAdapter(Context context)
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
			view = inflater.inflate(R.layout.listivew_voucher_item, null);
		((TextView)view.findViewById(R.id.searchKeyWord)).setText(((StoreVoucherList)datas.get(i)).getDesc());
		return view;
	}

	public void setDatas(ArrayList arraylist)
	{
		datas = arraylist;
	}
}
