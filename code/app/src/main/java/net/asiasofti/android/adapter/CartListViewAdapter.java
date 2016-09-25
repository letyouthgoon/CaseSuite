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
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.common.MyBackAsynaTask;
import net.asiasofti.android.model.CartList;
import net.asiasofti.android.ui.cart.CartActivity;

public class CartListViewAdapter extends BaseAdapter {
	public class ViewHolder {

		public CheckBox checkBoxCart;
		ImageView imageCartDetele;
		ImageView imageCartPic;
		TextView textCartGoodsName;
		TextView textCartGoodsPrice;
		TextView textCartNumber;
		TextView textCartStoreName;

		public ViewHolder() {
			super();
		}
	}

	private CartActivity cartActivity;
	public ArrayList cartLists;
	private Context context;
	public ViewHolder holder;
	private LayoutInflater inflater;
	private MyApp myApp;

	public CartListViewAdapter(Context context1, CartActivity cartactivity) {
		context = context1;
		cartActivity = cartactivity;
		inflater = LayoutInflater.from(context1);
		myApp = (MyApp) context1.getApplicationContext();
	}

	public ArrayList getCartLists() {
		return cartLists;
	}

	public int getCount() {
		if (cartLists == null)
			return 0;
		else
			return cartLists.size();
	}

	public Object getItem(int i) {
		return cartLists.get(i);
	}

	public long getItemId(int i) {
		return (long) i;
	}

	public View getView(int position, View view, ViewGroup viewgroup) {
		final CartList bean;
		Object obj;
		boolean flag;
		if (view == null) {
			view = inflater.inflate(R.layout.listivew_cart_item, null);
			holder = new ViewHolder();
			holder.textCartStoreName = (TextView) view.findViewById(R.id.textCartStoreName);
			holder.textCartGoodsName = (TextView) view.findViewById(R.id.textCartGoodsName);
			holder.textCartGoodsPrice = (TextView) view
					.findViewById(R.id.textCartGoodsPrice);
			holder.textCartNumber = (TextView) view.findViewById(R.id.textCartNumber);
			holder.imageCartPic = (ImageView) view.findViewById(R.id.imageCartPic);
			holder.imageCartDetele = (ImageView) view.findViewById(R.id.imageCartDetele);
			holder.checkBoxCart = (CheckBox) view.findViewById(R.id.checkBoxCart);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		bean = (CartList) cartLists.get(position);
		holder.textCartStoreName.setText((new StringBuilder("店铺名称：")).append(
				bean.getStore_name()).toString());
		holder.textCartGoodsName.setText(bean.getGoods_name());
		holder.textCartGoodsPrice.setText(bean.getGoods_price());
		holder.textCartNumber.setText(bean.getGoods_num());
		obj = cartActivity.selectedMap.get(bean.getCart_id());
		flag = false;
		if (obj != null)
			flag = ((Boolean) cartActivity.selectedMap.get(bean.getCart_id()))
					.booleanValue();
		holder.checkBoxCart.setChecked(flag);
		(new MyBackAsynaTask(bean.getGoods_image_url(), holder.imageCartPic))
				.execute(new String[0]);
		holder.imageCartDetele
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view1) {
						cartActivity.cartDetele(bean.getCart_id());
					}

				});
		return view;
	}

	public void setCartLists(ArrayList arraylist) {
		cartLists = arraylist;
	}

}
