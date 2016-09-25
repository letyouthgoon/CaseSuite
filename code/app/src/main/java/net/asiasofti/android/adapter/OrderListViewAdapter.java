package net.asiasofti.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.*;
import android.widget.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.*;
import net.asiasofti.android.ui.custom.MyListView;

import org.json.JSONException;
import org.json.JSONObject;


public class OrderListViewAdapter extends BaseAdapter
{
	class ViewHolder
	{

		MyListView goodsListView;
		TextView textOrderAllPrice;
		TextView textOrderOperation;
		TextView textOrderSN;
		TextView textOrderShippingFee;
		TextView textOrderStoreName;
		TextView textOrderSuccess;
		

		ViewHolder()
		{
			super();
		}
	}


	private Context context;
	private LayoutInflater inflater;
	private MyApp myApp;
	private ArrayList orderLists;

	public OrderListViewAdapter(Context context1)
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
		final ViewHolder holder;
		final OrderList bean;
		ArrayList arraylist;
		OrderItemListViewAdapter orderitemlistviewadapter;
		if (view == null)
		{
			view = inflater.inflate(R.layout.listivew_order2_item, null);
			holder = new ViewHolder();
			holder.goodsListView = (MyListView)view.findViewById(R.id.goodsListView);
			holder.textOrderStoreName = (TextView)view.findViewById(R.id.textOrderStoreName);
			holder.textOrderSN = (TextView)view.findViewById(R.id.textOrderSN);
			holder.textOrderAllPrice = (TextView)view.findViewById(R.id.textOrderAllPrice);
			holder.textOrderShippingFee = (TextView)view.findViewById(R.id.textOrderShippingFee);
			holder.textOrderOperation = (TextView)view.findViewById(R.id.textOrderOperation);
			holder.textOrderSuccess = (TextView)view.findViewById(R.id.textOrderSuccess);
			view.setTag(holder);
		} else
		{
			holder = (ViewHolder)view.getTag();
		}
		bean = (OrderList)orderLists.get(i);
		holder.textOrderStoreName.setText((new StringBuilder("店铺名称：")).append(bean.getStore_name()).toString());
		holder.textOrderSN.setText((new StringBuilder("订单编号：")).append(bean.getOrder_sn()).toString());
		holder.textOrderAllPrice.setText((new StringBuilder("￥")).append(bean.getOrder_amount()).toString());
		holder.textOrderShippingFee.setText((new StringBuilder("￥")).append(bean.getShipping_fee()).toString());
		arraylist = OrderGoodsList.newInstanceList(bean.getExtend_order_goods());
		if (bean.getIf_cancel().equals("true"))
		{
			holder.textOrderOperation.setText(Html.fromHtml("<a href='#'>取消</a>"));
			System.out.println((new StringBuilder("holder.textOrderOperation-->")).append(holder.textOrderOperation.getText().toString()).toString());
		} else if (bean.getIf_deliver().equals("true"))
		{
			holder.textOrderOperation.setText(Html.fromHtml("<a href='#'>确认收货</a>"));
		}
		else
		if (bean.getIf_lock().equals("true"))
			holder.textOrderOperation.setText(Html.fromHtml("<a href='#'>锁定中</a>"));
		else
		if (bean.getIf_receive().equals("true"))
			holder.textOrderOperation.setText(Html.fromHtml("<a href='#'>查看物流</a>"));
		else
			holder.textOrderOperation.setText("");
		if (bean.getState_desc() != null && !bean.getState_desc().equals(""))
		{
			holder.textOrderSuccess.setVisibility(0);
			holder.textOrderSuccess.setText(bean.getState_desc());
		} else
		{
			holder.textOrderSuccess.setVisibility(8);
		}
		holder.textOrderOperation.setOnClickListener(new android.view.View.OnClickListener() {


			public void onClick(View view1)
			{
				String s;
				String s1;
				s = holder.textOrderOperation.getText().toString();
				s1 = "";
				if (!s.equals("取消")) 
                {
					if (s.equals("确认收货"))
						s1 = Constants.URL_ORDER_RECEIVE;
                } else
                {
                	s1 = Constants.URL_ORDER_CANCEL;
                }
                	
				if (!s1.equals(""))
					loadingSaveOrderData(s1, bean.getOrder_id());
				return;
				
			}			
			
		});
		orderitemlistviewadapter = new OrderItemListViewAdapter(context);
		orderitemlistviewadapter.setGoodsDatas(arraylist);
		holder.goodsListView.setAdapter(orderitemlistviewadapter);
		orderitemlistviewadapter.notifyDataSetChanged();
		return view;
	}

	 public void loadingSaveOrderData(String paramString1, String paramString2)
	  {
	    HashMap localHashMap = new HashMap();
	    localHashMap.put("key", this.myApp.getLoginKey());
	    localHashMap.put("order_id", paramString2);
	    RemoteDataHandler.asyncPost2(paramString1, localHashMap, new RemoteDataHandler.Callback()
	    {
	      public void dataLoaded(ResponseData paramAnonymousResponseData)
	      {
	        if (paramAnonymousResponseData.getCode() == 200)
	        {
	          String str1 = paramAnonymousResponseData.getJson();
	          if (str1.equals("1"))
	          {
	            Toast.makeText(OrderListViewAdapter.this.context, "操作成功", 0).show();
	            Intent localIntent = new Intent(Constants.APP_BORADCASTRECEIVER);
	            OrderListViewAdapter.this.context.sendBroadcast(localIntent);
	          }
	          try
	          {
	            String str2 = new JSONObject(str1).getString("error");
	            if (str2 != null)
	              Toast.makeText(OrderListViewAdapter.this.context, str2, 0).show();
	            return;
	          }
	          catch (JSONException localJSONException)
	          {
	            localJSONException.printStackTrace();
	          }
	        }
	        Toast.makeText(OrderListViewAdapter.this.context, "数据加载失败，请稍后重试", 0).show();
	      }
	    });
	  }

	public void setOrderLists(ArrayList arraylist)
	{
		orderLists = arraylist;
	}

}
