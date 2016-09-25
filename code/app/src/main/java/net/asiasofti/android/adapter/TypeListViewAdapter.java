package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyBackAsynaTask;
import net.asiasofti.android.model.Type;

public class TypeListViewAdapter extends BaseAdapter
{
	class ViewHolder
	{

		ImageView imagePic;

		TextView typeDesc;
		TextView typeTitleName;

		ViewHolder()
		{
			
			super();
		}
	}


	private Context context;
	private LayoutInflater inflater;
	private ArrayList typeList;

	public TypeListViewAdapter(Context context1)
	{
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public int getCount()
	{
		if (typeList == null)
			return 0;
		else
			return typeList.size();
	}

	public Object getItem(int i)
	{
		return typeList.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public ArrayList getTypeList()
	{
		return typeList;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		ViewHolder viewholder;
		Type type;
		if (view == null)
		{
			view = inflater.inflate(R.layout.listivew_type_item, null);
			viewholder = new ViewHolder();
			viewholder.imagePic = (ImageView)view.findViewById(R.id.imagePic);
			viewholder.typeTitleName = (TextView)view.findViewById(R.id.typeTitleName);
			viewholder.typeDesc = (TextView)view.findViewById(R.id.typeDesc);
			view.setTag(viewholder);
		} else
		{
			viewholder = (ViewHolder)view.getTag();
		}
		type = (Type)typeList.get(i);
		(new MyBackAsynaTask(type.getImage(), viewholder.imagePic)).execute(new String[0]);
		viewholder.typeTitleName.setText(type.getGc_name());
		viewholder.typeDesc.setText(type.getText());
		return view;
	}

	public void setTypeList(ArrayList arraylist)
	{
		typeList = arraylist;
	}
}
