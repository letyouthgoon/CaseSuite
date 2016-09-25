/**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */
package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyBackAsynaTask;
import net.asiasofti.android.model.CommendList;

public class CommendGridViewAdapter extends BaseAdapter {
	class ViewHolder {

		ImageView imageviewPIC;
		TextView textGoodsCommendName;
		TextView textGoodsCommendPrice;

		ViewHolder() {
			super();
		}
	}

	private ArrayList commendLists;
	private Context context;
	private LayoutInflater inflater;

	public CommendGridViewAdapter(Context context1) {
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public ArrayList getCommendLists() {
		return commendLists;
	}

	public int getCount() {
		if (commendLists == null)
			return 0;
		else
			return commendLists.size();
	}

	public Object getItem(int i) {
		return commendLists.get(i);
	}

	public long getItemId(int i) {
		return (long) i;
	}

	public View getView(int i, View view, ViewGroup viewgroup) {
		ViewHolder viewholder;
		CommendList commendlist;
		if (view == null) {
			view = inflater.inflate(R.layout.gridview_commend_item, null);
			viewholder = new ViewHolder();
			viewholder.imageviewPIC = (ImageView) view
					.findViewById(R.id.imageviewPIC);
			viewholder.textGoodsCommendName = (TextView) view
					.findViewById(R.id.textGoodsCommendName);
			viewholder.textGoodsCommendPrice = (TextView) view
					.findViewById(R.id.textGoodsCommendPrice);
			view.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) view.getTag();
		}
		commendlist = (CommendList) commendLists.get(i);
		viewholder.textGoodsCommendName.setText(commendlist.getGoods_name());
		viewholder.textGoodsCommendPrice.setText((new StringBuilder("￥"))
				.append(commendlist.getGoods_price()).toString());
		(new MyBackAsynaTask(commendlist.getGoods_image_url(),
				viewholder.imageviewPIC)).execute(new String[0]);
		return view;
	}

	public void setCommendLists(ArrayList arraylist) {
		commendLists = arraylist;
	}
}
