 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.model;

import java.util.ArrayList;
import org.json.*;

public class OrderList
{
	public static class Attr
	{

		public static final String EXTEND_ORDER_GOODS = "extend_order_goods";
		public static final String IF_CANCEL = "if_cancel";
		public static final String IF_DELIVER = "if_deliver";
		public static final String IF_LOCK = "if_lock";
		public static final String IF_RECEIVE = "if_receive";
		public static final String ORDER_AMOUNT = "order_amount";
		public static final String ORDER_ID = "order_id";
		public static final String ORDER_SN = "order_sn";
		public static final String ORDER_STATE = "order_state";
		public static final String PAYMENT_NAME = "payment_name";
		public static final String SHIPPING_FEE = "shipping_fee";
		public static final String STATE_DESC = "state_desc";
		public static final String STORE_NAME = "store_name";

		public Attr()
		{
		}
	}


	private String extend_order_goods;
	private String if_cancel;
	private String if_deliver;
	private String if_lock;
	private String if_receive;
	private String order_amount;
	private String order_id;
	private String order_sn;
	private String order_state;
	private String payment_name;
	private String shipping_fee;
	private String state_desc;
	private String store_name;

	public OrderList()
	{
	}

	public OrderList(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
			String s7, String s8, String s9, String s10, String s11, String s12)
	{
		order_id = s;
		order_sn = s1;
		order_state = s2;
		store_name = s3;
		state_desc = s4;
		order_amount = s5;
		extend_order_goods = s6;
		payment_name = s7;
		shipping_fee = s8;
		if_cancel = s9;
		if_receive = s10;
		if_lock = s11;
		if_deliver = s12;
	}

	public static ArrayList newInstanceList(String s)
	{
		ArrayList arraylist = new ArrayList();

		try
		{
			JSONArray jsonarray = new JSONArray(s);
			for(int i=0;i<=jsonarray.length()-1;i++)
			{
			 JSONObject jsonobject = jsonarray.getJSONObject(i);
			arraylist.add(new OrderList(jsonobject.optString("order_id"), jsonobject.optString("order_sn"), jsonobject.optString("order_state"), jsonobject.optString("store_name"), jsonobject.optString("state_desc"), jsonobject.optString("order_amount"), jsonobject.optString("extend_order_goods"), jsonobject.optString("payment_name"), jsonobject.optString("shipping_fee"), jsonobject.optString("if_cancel"), jsonobject.optString("if_receive"), jsonobject.optString("if_lock"), jsonobject.optString("if_deliver")));
			}
		}
		catch (JSONException jsonexception)
		{
			jsonexception.printStackTrace();
		}
	
		return arraylist;
	}

	public String getExtend_order_goods()
	{
		return extend_order_goods;
	}

	public String getIf_cancel()
	{
		return if_cancel;
	}

	public String getIf_deliver()
	{
		return if_deliver;
	}

	public String getIf_lock()
	{
		return if_lock;
	}

	public String getIf_receive()
	{
		return if_receive;
	}

	public String getOrder_amount()
	{
		return order_amount;
	}

	public String getOrder_id()
	{
		return order_id;
	}

	public String getOrder_sn()
	{
		return order_sn;
	}

	public String getOrder_state()
	{
		return order_state;
	}

	public String getPayment_name()
	{
		return payment_name;
	}

	public String getShipping_fee()
	{
		return shipping_fee;
	}

	public String getState_desc()
	{
		return state_desc;
	}

	public String getStore_name()
	{
		return store_name;
	}

	public void setExtend_order_goods(String s)
	{
		extend_order_goods = s;
	}

	public void setIf_cancel(String s)
	{
		if_cancel = s;
	}

	public void setIf_deliver(String s)
	{
		if_deliver = s;
	}

	public void setIf_lock(String s)
	{
		if_lock = s;
	}

	public void setIf_receive(String s)
	{
		if_receive = s;
	}

	public void setOrder_amount(String s)
	{
		order_amount = s;
	}

	public void setOrder_id(String s)
	{
		order_id = s;
	}

	public void setOrder_sn(String s)
	{
		order_sn = s;
	}

	public void setOrder_state(String s)
	{
		order_state = s;
	}

	public void setPayment_name(String s)
	{
		payment_name = s;
	}

	public void setShipping_fee(String s)
	{
		shipping_fee = s;
	}

	public void setState_desc(String s)
	{
		state_desc = s;
	}

	public void setStore_name(String s)
	{
		store_name = s;
	}

	public String toString()
	{
		return (new StringBuilder("OrderList [order_id=")).append(order_id).append(", order_sn=").append(order_sn).append(", order_state=").append(order_state).append(", store_name=").append(store_name).append(", state_desc=").append(state_desc).append(", order_amount=").append(order_amount).append(", extend_order_goods=").append(extend_order_goods).append(", payment_name=").append(payment_name).append(", shipping_fee=").append(shipping_fee).append(", if_cancel=").append(if_cancel).append(", if_receive=").append(if_receive).append(", if_lock=").append(if_lock).append(", if_deliver=").append(if_deliver).append("]").toString();
	}
}
