package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.model.Spec;

public class SpecGridViewAdapter extends BaseAdapter
{
	class ViewHolder
	{

		TextView textCheck;

		ViewHolder()
		{
			super();
		}
	}


	private Context context;
	private LayoutInflater inflater;
	private ArrayList listSpec;
	private String old_specID;
	private ArrayList querySpecList;

	public SpecGridViewAdapter(Context context1)
	{
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public int getCount()
	{
		if (listSpec == null)
			return 0;
		else
			return listSpec.size();
	}

	public Object getItem(int i)
	{
		return listSpec.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public ArrayList getListSpec()
	{
		return listSpec;
	}

	public String getOld_specID()
	{
		return old_specID;
	}

	public ArrayList getQuerySpecList()
	{
		return querySpecList;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		ViewHolder viewholder;
		Spec spec;
		int j;
		if (view == null)
		{
			view = inflater.inflate(R.layout.gridview_color_item, null);
			viewholder = new ViewHolder();
			viewholder.textCheck = (TextView)view.findViewById(R.id.textCheck);
			view.setTag(viewholder);
		} else
		{
			viewholder = (ViewHolder)view.getTag();
		}
		spec = (Spec)listSpec.get(i);
		j = 0;
		do
		{
			if (j >= querySpecList.size())
			{
				viewholder.textCheck.setText(spec.getSpecName());
				return view;
			}
			String s = (String)querySpecList.get(j);
			if (s != null && s.equals(spec.getSpecName()))
			{
				old_specID = spec.getSpecID();
				viewholder.textCheck.setBackgroundResource(R.drawable.product_detail_color_size_press);
			}
			j++;
		} while (true);
	}

	public void setListSpec(ArrayList arraylist)
	{
		listSpec = arraylist;
	}

	public void setOld_specID(String s)
	{
		old_specID = s;
	}

	public void setQuerySpecList(ArrayList arraylist)
	{
		querySpecList = arraylist;
	}
}
