package net.asiasofti.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import net.asiasofti.android.R;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.model.*;
import net.asiasofti.android.ui.custom.MyListView;

import org.json.JSONException;
import org.json.JSONObject;


public class StoreCartListViewAdapter extends BaseAdapter {
	public class ViewHolder {

		MyListView mylistview;
		TextView textStoreCartFreight;
		TextView textStoreCartName;
		Spinner textVoucherList;


		public ViewHolder() {
			super();
		}
	}

	private ArrayList Voucherlist;
	private Context ctx;
	private LayoutInflater inflater;
	private ArrayList storeCartLists;
	public String updateAddressContent;
	public ViewHolder vh;
	private StoreVoucherListViewAdapter voucherListViewAdapter;

	public StoreCartListViewAdapter(Context context) {
		Voucherlist = new ArrayList();
		updateAddressContent = "";
		ctx = context;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		if (storeCartLists == null)
			return 0;
		else
			return storeCartLists.size();
	}

	public Object getItem(int i) {
		return storeCartLists.get(i);
	}

	public long getItemId(int i) {
		return (long) i;
	}

	public ArrayList getStoreCartLists() {
		return storeCartLists;
	}

	public String getUpdateAddressContent() {
		return updateAddressContent;
	}

	public View getView(int i, View view, ViewGroup viewgroup) {
		JSONObject jsonobject;
		Iterator iterator;
		ArrayList arraylist1;
		StoreCartList storecartlist;
		ArrayList arraylist;
		String s;
		StoreCartListViewAdapter02 storecartlistviewadapter02;
		if (view == null) {
			view = inflater.inflate(R.layout.listivew_store_cart_item, null);
			vh = new ViewHolder();
			vh.mylistview = (MyListView) view.findViewById(R.id.goodsListView);
			vh.textStoreCartName = (TextView) view.findViewById(R.id.textStoreCartName);
			vh.textStoreCartFreight = (TextView) view.findViewById(R.id.textStoreCartFreight);
			vh.textVoucherList = (Spinner) view.findViewById(R.id.textVoucherList);
			ViewHolder viewholder = vh;
			view.setTag(viewholder);
		} else {
			vh = (ViewHolder) view.getTag();
		}
		storecartlist = (StoreCartList) storeCartLists.get(i);
		vh.textStoreCartName.setText((new StringBuilder("店铺名称:")).append(
				storecartlist.getStore_name()).toString());
		arraylist = CartList.newInstanceList(storecartlist.getGoods_list());
		if (!updateAddressContent.equals("")
				&& !updateAddressContent.equals("null")
				&& updateAddressContent != null)
			try {
				String s3 = (new JSONObject(updateAddressContent))
						.getString(storecartlist.getStore_id());
				vh.textStoreCartFreight.setText((new StringBuilder("运费:￥"))
						.append(s3).toString());
			} catch (JSONException jsonexception1) {
				jsonexception1.printStackTrace();
			}
		s = storecartlist.getStore_voucher_list();
		if (!s.contains("[]"))
			vh.textVoucherList.setVisibility(View.VISIBLE);
		else
			vh.textVoucherList.setVisibility(View.GONE);
		storecartlistviewadapter02 = new StoreCartListViewAdapter02(ctx);
		vh.mylistview.setAdapter(storecartlistviewadapter02);
		storecartlistviewadapter02.setGoodList(arraylist);
		storecartlistviewadapter02.notifyDataSetChanged();
		if (s.contains("[]")) {
			return view;
		}
		voucherListViewAdapter = new StoreVoucherListViewAdapter(ctx);
		vh.textVoucherList.setAdapter(voucherListViewAdapter);
		try {
			jsonobject = new JSONObject(s);
		
		iterator = jsonobject.keys();
		arraylist1 = new ArrayList();
		arraylist1.add(new StoreVoucherList("0", "0", "0", "选择代金券"));
		while (iterator.hasNext()) {
			try {
				String s1 = iterator.next().toString();
				String s2 = jsonobject.getString(s1);
				System.out.println((new StringBuilder(String.valueOf(s1)))
						.append(",").append(s2).toString());
				arraylist1.add(StoreVoucherList.newInstanceList(s2));
			} catch (JSONException jsonexception) {
				jsonexception.printStackTrace();
//				return view;
			}
		}
		voucherListViewAdapter.setDatas(arraylist1);
		voucherListViewAdapter.notifyDataSetChanged();
		vh.textVoucherList
				.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

					public void onItemSelected(AdapterView adapterview,
							View view1, int j, long l) {
						StoreVoucherList storevoucherlist = (StoreVoucherList) vh.textVoucherList
								.getItemAtPosition(j);
						if (storevoucherlist != null) {
							Intent intent = new Intent(Constants.APP_BORADCASTRECEIVER2);
							intent.putExtra("voucher_price",
									storevoucherlist.getVoucher_price());
							intent.putExtra("voucher_t_id",
									storevoucherlist.getVoucher_t_id());
							intent.putExtra("store_id",
									storevoucherlist.getStore_id());
							ctx.sendBroadcast(intent);
						}
					}

					public void onNothingSelected(AdapterView adapterview) {
					}

				});
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return view;

	}

	public ArrayList getVoucherlist() {
		return Voucherlist;
	}

	public void setStoreCartLists(ArrayList arraylist) {
		storeCartLists = arraylist;
	}

	public void setUpdateAddressContent(String s) {
		updateAddressContent = s;
	}

	public void setVoucherlist(ArrayList arraylist) {
		Voucherlist = arraylist;
	}

}
