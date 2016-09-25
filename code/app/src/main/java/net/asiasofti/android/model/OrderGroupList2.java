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

public class OrderGroupList2 {
	public static class Attr {

		public static final String ADD_TIME = "add_time";
		public static final String ORDER_LIST = "order_list";
		public static final String PAY_AMOUNT = "pay_amount";
		public static final String PAY_SN = "pay_sn";

		public Attr() {
		}
	}

	private String add_time;
	private String order_list;
	private String pay_amount;
	private String pay_sn;

	public OrderGroupList2() {
	}

	public OrderGroupList2(String s, String s1, String s2, String s3) {
		pay_sn = s;
		order_list = s1;
		pay_amount = s2;
		add_time = s3;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();

		try {
			JSONArray jsonarray = new JSONArray(s);
			if (jsonarray != null) {
				for (int i = 0; i <= jsonarray.length() - 1; i++) {
					JSONObject jsonobject = jsonarray.getJSONObject(i);
					arraylist.add(new OrderGroupList2(jsonobject
							.optString("pay_sn"), jsonobject
							.optString("order_list"), jsonobject
							.optString("pay_amount"), jsonobject
							.optString("add_time")));

				}
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
			return arraylist;
		}

		return arraylist;
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

	public String getPay_sn() {
		return pay_sn;
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

	public void setPay_sn(String s) {
		pay_sn = s;
	}

	public String toString() {
		return (new StringBuilder("OrderGroupList2 [pay_sn=")).append(pay_sn)
				.append(", order_list=").append(order_list)
				.append(", pay_amount=").append(pay_amount)
				.append(", add_time=").append(add_time).append("]").toString();
	}
}
