package net.asiasofti.android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.model.TypeNext;

public class TypeNextExpandableListViewAdapter extends BaseExpandableListAdapter
{
	class ChildViewHolder
	{

		TextView textTitleName;

		ChildViewHolder()
		{
			super();
		}
	}

	class GroupViewHolder
	{

		TextView textTitleName;
		GroupViewHolder()
		{			
			super();
		}
	}


	private LayoutInflater childInflater;
	public Context context;
	private ChildViewHolder cvh;
	private LayoutInflater groupInflater;
	private GroupViewHolder gvh;
	private ArrayList typeNextCList;
	private ArrayList typeNextGList;

	public TypeNextExpandableListViewAdapter(Context context1)
	{
		context = context1;
		childInflater = LayoutInflater.from(context1);
		groupInflater = LayoutInflater.from(context1);
	}

	public Object getChild(int i, int j)
	{
		return typeNextCList.get(j);
	}

	public long getChildId(int i, int j)
	{
		return (long)j;
	}

	public View getChildView(int i, int j, boolean flag, View view, ViewGroup viewgroup)
	{
		TypeNext typenext;
		if (view == null)
		{
			view = childInflater.inflate(R.layout.expandable_listview_type_next_child_item, null);
			cvh = new ChildViewHolder();
			cvh.textTitleName = (TextView)view.findViewById(R.id.textTitleName);
			view.setTag(cvh);
		} else
		{
			cvh = (ChildViewHolder)view.getTag();
		}
		typenext = (TypeNext)typeNextCList.get(j);
		cvh.textTitleName.setText(typenext.getGc_name());
		if (j % 2 == 0)
		{
			view.setBackgroundColor(Color.rgb(245, 245, 245));
			return view;
		} else
		{
			view.setBackgroundColor(Color.rgb(238, 238, 238));
			return view;
		}
	}

	public int getChildrenCount(int i)
	{
		if (typeNextCList == null)
			return 0;
		else
			return typeNextCList.size();
	}

	public Object getGroup(int i)
	{
		return typeNextGList.get(i);
	}

	public int getGroupCount()
	{
		if (typeNextGList == null)
			return 0;
		else
			return typeNextGList.size();
	}

	public long getGroupId(int i)
	{
		return (long)i;
	}

	public View getGroupView(int i, boolean flag, View view, ViewGroup viewgroup)
	{
		TypeNext typenext;
		if (view == null)
		{
			view = groupInflater.inflate(R.layout.expandable_listview_type_next_group_item, null);
			gvh = new GroupViewHolder();
			gvh.textTitleName = (TextView)view.findViewById(R.id.textTitleName);
			view.setTag(gvh);
		} else
		{
			gvh = (GroupViewHolder)view.getTag();
		}
		typenext = (TypeNext)typeNextGList.get(i);
		gvh.textTitleName.setText(typenext.getGc_name());
		return view;
	}

	public ArrayList getTypeNextCList()
	{
		return typeNextCList;
	}

	public ArrayList getTypeNextGList()
	{
		return typeNextGList;
	}

	public boolean hasStableIds()
	{
		return false;
	}

	public boolean isChildSelectable(int i, int j)
	{
		return true;
	}

	public void setTypeNextCList(ArrayList arraylist)
	{
		typeNextCList = arraylist;
	}

	public void setTypeNextGList(ArrayList arraylist)
	{
		typeNextGList = arraylist;
	}
}
