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

import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderGroupList {
	public static class Attr {

		public static final String ADD_TIME = "add_time";
		public static final String ORDER_LIST = "order_list";
		public static final String PAY_AMOUNT = "pay_amount";

		public Attr() {
		}
	}

	private String add_time;
	private String order_list;
	private String pay_amount;

	public OrderGroupList() {
	}

	public OrderGroupList(String s, String s1, String s2) {
		order_list = s;
		pay_amount = s1;
		add_time = s2;
	}

	public static OrderGroupList newInstanceList(String s) {
		OrderGroupList ordergrouplist = null;
		try {
			JSONObject jsonobject = new JSONObject(s);
			
			int i = jsonobject.length();
			ordergrouplist = null;
			if (i > 0) {
				ordergrouplist = new OrderGroupList(
						jsonobject.optString("order_list"),
						jsonobject.optString("pay_amount"),
						jsonobject.optString("add_time"));
				System.out.println((new StringBuilder("bean-->")).append(
						ordergrouplist.toString()).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ordergrouplist;

	}

	public String getAdd_time() {
		return add_time;
	}

	public String getOrder_list() {
		return order_list;
	}

	public String getPay_amount() {
		return pay_amount;
	}

	public void setAdd_time(String s) {
		add_time = s;
	}

	public void setOrder_list(String s) {
		order_list = s;
	}

	public void setPay_amount(String s) {
		pay_amount = s;
	}

	public String toString() {
		return (new StringBuilder("OrderGroupList [order_list="))
				.append(order_list).append(", pay_amount=").append(pay_amount)
				.append(", add_time=").append(add_time).append("]").toString();
	}
}
