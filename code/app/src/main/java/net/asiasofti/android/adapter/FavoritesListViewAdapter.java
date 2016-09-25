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
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.common.MyBackAsynaTask;
import net.asiasofti.android.model.FavoritesList;
import net.asiasofti.android.ui.mystore.FavoritesListActivity;

public class FavoritesListViewAdapter extends BaseAdapter {
	public class ViewHolder {

		ImageView imageFacoritesDeleteButton;
		ImageView imageGoodsPic;
		TextView textGoodsName;
		TextView textGoodsPrice;

		public ViewHolder() {

			super();
		}
	}

	private Context context;
	private ArrayList fList;
	private FavoritesListActivity favoritesListActivity;
	private LayoutInflater inflater;
	private boolean isCheckEdit;
	private MyApp myApp;

	public FavoritesListViewAdapter(Context context1,
			FavoritesListActivity favoriteslistactivity) {
		isCheckEdit = false;
		context = context1;
		myApp = (MyApp) context1.getApplicationContext();
		inflater = LayoutInflater.from(context1);
		favoritesListActivity = favoriteslistactivity;
	}

	public int getCount() {
		if (fList == null)
			return 0;
		else
			return fList.size();
	}

	public Object getItem(int i) {
		return fList.get(i);
	}

	public long getItemId(int i) {
		return (long) i;
	}

	public View getView(int i, View view, ViewGroup viewgroup) {
		ViewHolder viewholder;
		final FavoritesList bean;
		if (view == null) {
			view = inflater.inflate(R.layout.listivew_favorites_item, null);
			viewholder = new ViewHolder();
			viewholder.imageGoodsPic = (ImageView) view
					.findViewById(R.id.imageGoodsPic);
			viewholder.imageFacoritesDeleteButton = (ImageView) view
					.findViewById(R.id.imageFacoritesDeleteButton);
			viewholder.textGoodsName = (TextView) view.findViewById(R.id.textGoodsName);
			viewholder.textGoodsPrice = (TextView) view
					.findViewById(R.id.textGoodsName);
			view.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) view.getTag();
		}
		bean = (FavoritesList) fList.get(i);
		viewholder.textGoodsName.setText(bean.getGoods_name());
		viewholder.textGoodsPrice.setText((new StringBuilder("￥")).append(
				bean.getGoods_price()).toString());
		(new MyBackAsynaTask(bean.getGoods_image_url(),
				viewholder.imageGoodsPic)).execute(new String[0]);
		if (isCheckEdit)
		{
			viewholder.imageFacoritesDeleteButton.setVisibility(View.VISIBLE);
		}
		else
		{
			viewholder.imageFacoritesDeleteButton.setVisibility(View.GONE);
		}
		viewholder.imageFacoritesDeleteButton
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view1) {
						favoritesListActivity.DeleteFaavoritesData(bean
								.getFav_id());
					}

				});
		return view;
	}

	public ArrayList getfList() {
		return fList;
	}

	public boolean isCheckEdit() {
		return isCheckEdit;
	}

	public void setCheckEdit(boolean flag) {
		isCheckEdit = flag;
	}

	public void setfList(ArrayList arraylist) {
		fList = arraylist;
	}

}
