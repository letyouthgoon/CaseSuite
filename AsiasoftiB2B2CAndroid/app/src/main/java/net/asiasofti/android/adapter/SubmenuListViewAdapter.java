package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

import net.asiasofti.android.R;

public class SubmenuListViewAdapter extends BaseAdapter {

	public static final String TAG_ITEM_ICON = "icon";
	public static final String TAG_ITEM_TEXT = "text";
	private ArrayList datas;
	private LayoutInflater inflater;
	private int size;

	public SubmenuListViewAdapter(Context context, ArrayList arraylist) {
		inflater = LayoutInflater.from(context);
		datas = arraylist;
		int i;
		if (arraylist == null)
			i = 0;
		else
			i = arraylist.size();
		size = i;
	}

	public int getCount() {
		return size;
	}

	public Object getItem(int i) {
		return datas.get(i);
	}

	public long getItemId(int i) {
		Integer integer = (Integer) ((HashMap) datas.get(i)).get("icon");
		if (integer != null)
			return (long) integer.intValue();
		else
			return (long) i;
	}

	public View getView(int i, View view, ViewGroup viewgroup) {
		if (view == null)
			view = inflater.inflate(R.layout.listview_item_icon_text_icon02,
					null);
		ImageView imageview = (ImageView) view.findViewById(R.id.item_image);
		TextView textview = (TextView) view.findViewById(R.id.item_text);
		HashMap hashmap = (HashMap) datas.get(i);
		imageview.setImageResource(((Integer) hashmap.get("icon")).intValue());
		textview.setText((String) hashmap.get("text"));
		// if (size == 1)
		// {
		// view.setBackgroundResource(R.drawable.list_item_single);
		// return view;
		// }
		// if (i == 0)
		// {
		// view.setBackgroundResource(R.drawable.list_item_first);
		// return view;
		// }
		// if (i == -1 + size)
		// {
		// view.setBackgroundResource(R.drawable.list_item_last);
		// return view;
		// } else
		{
			// view.setBackgroundResource(R.drawable.list_item_single);
			return view;
		}
	}
}
