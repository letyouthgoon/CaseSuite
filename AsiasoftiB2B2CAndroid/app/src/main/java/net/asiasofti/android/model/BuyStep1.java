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

public class BuyStep1 {
	public static class Attr {

		public static final String ADDRESS_INFO = "address_info";
		public static final String AVAILABLE_PREDEPOSIT = "available_predeposit";
		public static final String FREIGHT_HASH = "freight_hash";
		public static final String IFSHOW_OFFPAY = "ifshow_offpay";
		public static final String INV_INFO = "inv_info";
		public static final String STORE_CART_LIST = "store_cart_list";
		public static final String VAT_HASH = "vat_hash";

		public Attr() {
		}
	}

	private String address_info;
	private String available_predeposit;
	private String freight_hash;
	private String ifshow_offpay;
	private String inv_info;
	private String store_cart_list;
	private String vat_hash;

	public BuyStep1() {
	}

	public BuyStep1(String s, String s1, String s2, String s3, String s4,
			String s5, String s6) {
		store_cart_list = s;
		freight_hash = s1;
		address_info = s2;
		ifshow_offpay = s3;
		vat_hash = s4;
		inv_info = s5;
		available_predeposit = s6;
	}

	public static BuyStep1 newInstanceList(String s) {
		BuyStep1 buystep1 = null;
		try {
			JSONObject jsonobject = new JSONObject(s);
			if (jsonobject.length() > 0) {
				buystep1 = new BuyStep1(
						jsonobject.optString("store_cart_list"),
						jsonobject.optString("freight_hash"),
						jsonobject.optString("address_info"),
						jsonobject.optString("ifshow_offpay"),
						jsonobject.optString("vat_hash"),
						jsonobject.optString("inv_info"),
						jsonobject.optString("available_predeposit"));
				System.out.println((new StringBuilder("bean-->")).append(
						buystep1.toString()).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return buystep1;
	}

	public String getAddress_info() {
		return address_info;
	}

	public String getAvailable_predeposit() {
		return available_predeposit;
	}

	public String getFreight_hash() {
		return freight_hash;
	}

	public String getIfshow_offpay() {
		return ifshow_offpay;
	}

	public String getInv_info() {
		return inv_info;
	}

	public String getStore_cart_list() {
		return store_cart_list;
	}

	public String getVat_hash() {
		return vat_hash;
	}

	public void setAddress_info(String s) {
		address_info = s;
	}

	public void setAvailable_predeposit(String s) {
		available_predeposit = s;
	}

	public void setFreight_hash(String s) {
		freight_hash = s;
	}

	public void setIfshow_offpay(String s) {
		ifshow_offpay = s;
	}

	public void setInv_info(String s) {
		inv_info = s;
	}

	public void setStore_cart_list(String s) {
		store_cart_list = s;
	}

	public void setVat_hash(String s) {
		vat_hash = s;
	}

	public String toString() {
		return (new StringBuilder("BuyStep1 [store_cart_list="))
				.append(store_cart_list).append(", freight_hash=")
				.append(freight_hash).append(", address_info=")
				.append(address_info).append(", ifshow_offpay=")
				.append(ifshow_offpay).append(", vat_hash=").append(vat_hash)
				.append(", inv_info=").append(inv_info)
				.append(", available_predeposit=").append(available_predeposit)
				.append("]").toString();
	}
}
