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

import org.json.JSONException;
import org.json.JSONObject;

public class StoreVoucherList {
	public static class Attr {

		public static final String DESC = "desc";
		public static final String STORE_ID = "voucher_store_id";
		public static final String VOUCHER_PRICE = "voucher_price";
		public static final String VOUCHER_T_ID = "voucher_t_id";

		public Attr() {
		}
	}

	private String desc;
	private String store_id;
	private String voucher_price;
	private String voucher_t_id;

	public StoreVoucherList() {
	}

	public StoreVoucherList(String s, String s1, String s2, String s3) {
		voucher_t_id = s;
		store_id = s1;
		voucher_price = s2;
		desc = s3;
	}

	public static StoreVoucherList newInstanceList(String s) {
		JSONObject jsonobject;
		int i;
		StoreVoucherList storevoucherlist;
		StoreVoucherList storevoucherlist1;
		try {
			jsonobject = new JSONObject(s);
			i = jsonobject.length();
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
			return null;
		}
		storevoucherlist = null;
		if (i > 0) {
			storevoucherlist = new StoreVoucherList(
					jsonobject.optString("voucher_t_id"),
					jsonobject.optString("voucher_store_id"),
					jsonobject.optString("voucher_price"),
					jsonobject.optString("desc"));
		}
		return storevoucherlist;
	}

	public String getDesc() {
		return desc;
	}

	public String getStore_id() {
		return store_id;
	}

	public String getVoucher_price() {
		return voucher_price;
	}

	public String getVoucher_t_id() {
		return voucher_t_id;
	}

	public void setDesc(String s) {
		desc = s;
	}

	public void setStore_id(String s) {
		store_id = s;
	}

	public void setVoucher_price(String s) {
		voucher_price = s;
	}

	public void setVoucher_t_id(String s) {
		voucher_t_id = s;
	}

	public String toString() {
		return (new StringBuilder("StoreVoucherList [voucher_t_id="))
				.append(voucher_t_id).append(", store_id=").append(store_id)
				.append(", voucher_price=").append(voucher_price)
				.append(", desc=").append(desc).append("]").toString();
	}
}
