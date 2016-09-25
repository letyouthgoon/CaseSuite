package net.asiasofti.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.android.msp.demo.ExternalPartner;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.model.OrderGroupList2;
import net.asiasofti.android.model.OrderList;
import net.asiasofti.android.ui.custom.MyListView;
import net.asiasofti.android.ui.mystore.PayMentWebAcivity;

public class OrderGroupListViewAdapter extends BaseAdapter {
	class ViewHolder {

		Button buttonFuKuan;
		MyListView goodsListView;
		LinearLayout linearLayoutFLag;
		TextView textOrderAddTime;

		ViewHolder() {
			super();
		}
	}

	private Context context;
	private LayoutInflater inflater;
	private MyApp myApp;
	private ArrayList orderLists;
    private Activity activity;
	public OrderGroupListViewAdapter(Context context1,Activity activity) {
		context = context1;
		inflater = LayoutInflater.from(context1);
		myApp = (MyApp) context.getApplicationContext();
		this.activity=activity;
	}

	public int getCount() {
		if (orderLists == null)
			return 0;
		else
			return orderLists.size();
	}

	public Object getItem(int i) {
		return orderLists.get(i);
	}

	public long getItemId(int i) {
		return (long) i;
	}

	public ArrayList getOrderLists() {
		return orderLists;
	}

	public View getView(int i, View view, ViewGroup viewgroup) {
		ViewHolder viewholder;
		final OrderGroupList2 bean;
		String s;
		ArrayList arraylist;
		OrderListViewAdapter orderlistviewadapter;
		if (view == null) {
			view = inflater.inflate(R.layout.listivew_order_item, null);
			viewholder = new ViewHolder();
			viewholder.goodsListView = (MyListView) view
					.findViewById(R.id.goodsListView);
			viewholder.textOrderAddTime = (TextView) view
					.findViewById(R.id.textOrderAddTime);
			viewholder.linearLayoutFLag = (LinearLayout) view
					.findViewById(R.id.linearLayoutFLag);
			viewholder.buttonFuKuan = (Button) view
					.findViewById(R.id.buttonFuKuan);
			view.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) view.getTag();
		}
		bean = (OrderGroupList2) orderLists.get(i);
		System.out.println((new StringBuilder("kingkong--->")).append(
				bean.toString()).toString());
		s = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Long
				.valueOf(1000L * Long.parseLong(bean.getAdd_time())));
		viewholder.textOrderAddTime.setText((new StringBuilder("订单时间："))
				.append(s).toString());
		if (!bean.getPay_amount().equals("")
				&& !bean.getPay_amount().equals("null")
				&& !bean.getPay_amount().equals("0")
				&& bean.getPay_amount() != null)
			viewholder.linearLayoutFLag.setVisibility(0);
		else
			viewholder.linearLayoutFLag.setVisibility(8);
		viewholder.buttonFuKuan
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view1) {
//						 Intent intent = new Intent(
//						 context,
//						 net.asiasofti.android.ui.mystore.PayMentWebAcivity.class);
//						 intent.putExtra("pay_sn", bean.getPay_sn());
//						 System.out.println("订单号:" + bean.getPay_sn());
//						 context.startActivity(intent);
						SN_DATA(bean.getOrder_list());
						System.out.println("订单号：" + map.get("order_sn") + ","
								+ "总价:" + map.get("order_amount") + ","
								+ "name:" + map.get("goods_name"));
						ExternalPartner partner=new ExternalPartner(activity, map);
						partner.pay();
					}
				});
		arraylist = OrderList.newInstanceList(bean.getOrder_list());
		orderlistviewadapter = new OrderListViewAdapter(context);
		orderlistviewadapter.setOrderLists(arraylist);
		viewholder.goodsListView.setAdapter(orderlistviewadapter);
		orderlistviewadapter.notifyDataSetChanged();
		return view;
	}

	public void setOrderLists(ArrayList arraylist) {
		orderLists = arraylist;
	}

	private Map<String, String> map = new HashMap<String, String>();

	public void SN_DATA(String string) {
		String URL = "{" + "\"" + "data" + "\"" + ":" + string + "}";
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(URL);
			JSONArray array = jsonObject.optJSONArray("data");
			for (int j = 0; j < array.length(); j++) {
				JSONObject object = array.optJSONObject(j);
				map.put("order_sn", object.getString("order_sn"));
				map.put("order_amount", object.getString("order_amount"));
				map.put("extend_order_goods",
						object.getString("extend_order_goods"));
			}
			String datas = "{" + "\"" + "data" + "\"" + ":"
					+ map.get("extend_order_goods") + "}";
			jsonObject = new JSONObject(datas);
			JSONArray array1 = jsonObject.optJSONArray("data");
			for (int j = 0; j < array1.length(); j++) {
				JSONObject object = array1.optJSONObject(j);
				map.put("goods_name", object.getString("goods_name"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
