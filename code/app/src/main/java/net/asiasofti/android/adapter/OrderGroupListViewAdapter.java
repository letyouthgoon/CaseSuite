package net.asiasofti.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.model.OrderGroupList2;
import net.asiasofti.android.model.OrderList;
import net.asiasofti.android.ui.custom.MyListView;
import net.asiasofti.android.ui.mystore.PayMentWebAcivity;



public class OrderGroupListViewAdapter extends BaseAdapter
{
	class ViewHolder
	{

		Button buttonFuKuan;
		MyListView goodsListView;
		LinearLayout linearLayoutFLag;
		TextView textOrderAddTime;

		ViewHolder()
		{
			super();
		}
	}


	private Context context;
	private LayoutInflater inflater;
	private MyApp myApp;
	private ArrayList orderLists;

	public OrderGroupListViewAdapter(Context context1)
	{
		context = context1;
		inflater = LayoutInflater.from(context1);
		myApp = (MyApp)context.getApplicationContext();
	}

	public int getCount()
	{
		if (orderLists == null)
			return 0;
		else
			return orderLists.size();
	}

	public Object getItem(int i)
	{
		return orderLists.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public ArrayList getOrderLists()
	{
		return orderLists;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		ViewHolder viewholder;
		final OrderGroupList2 bean;
		String s;
		ArrayList arraylist;
		OrderListViewAdapter orderlistviewadapter;
		if (view == null)
		{
			view = inflater.inflate(R.layout.listivew_order_item, null);
			viewholder = new ViewHolder();
			viewholder.goodsListView = (MyListView)view.findViewById(R.id.goodsListView);
			viewholder.textOrderAddTime = (TextView)view.findViewById(R.id.textOrderAddTime);
			viewholder.linearLayoutFLag = (LinearLayout)view.findViewById(R.id.linearLayoutFLag);
			viewholder.buttonFuKuan = (Button)view.findViewById(R.id.buttonFuKuan);
			view.setTag(viewholder);
		} else
		{
			viewholder = (ViewHolder)view.getTag();
		}
		bean = (OrderGroupList2)orderLists.get(i);
		System.out.println((new StringBuilder("kingkong--->")).append(bean.toString()).toString());
		s = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(Long.valueOf(1000L * Long.parseLong(bean.getAdd_time())));
		viewholder.textOrderAddTime.setText((new StringBuilder("订单时间：")).append(s).toString());
		if (!bean.getPay_amount().equals("") && !bean.getPay_amount().equals("null") && !bean.getPay_amount().equals("0") && bean.getPay_amount() != null)
			viewholder.linearLayoutFLag.setVisibility(0);
		else
			viewholder.linearLayoutFLag.setVisibility(8);
		viewholder.buttonFuKuan.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view1)
			{
				Intent intent = new Intent(context, net.asiasofti.android.ui.mystore.PayMentWebAcivity.class);
				intent.putExtra("pay_sn", bean.getPay_sn());
				context.startActivity(intent);
			}
		});
		arraylist = OrderList.newInstanceList(bean.getOrder_list());
		orderlistviewadapter = new OrderListViewAdapter(context);
		orderlistviewadapter.setOrderLists(arraylist);
		viewholder.goodsListView.setAdapter(orderlistviewadapter);
		orderlistviewadapter.notifyDataSetChanged();
		return view;
	}

	public void setOrderLists(ArrayList arraylist)
	{
		orderLists = arraylist;
	}

}
