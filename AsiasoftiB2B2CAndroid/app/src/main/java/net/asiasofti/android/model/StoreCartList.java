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

public class StoreCartList {
	public static class Attr {

		public static final String FREIGHT = "freight";
		public static final String GOODS_LIST = "goods_list";
		public static final String STORE_GOODS_TOTAL = "store_goods_total";
		public static final String STORE_MANSONG_RULE_LIST = "store_mansong_rule_list";
		public static final String STORE_NAME = "store_name";
		public static final String STORE_PREMIUMS_LIST = "store_premiums_list";
		public static final String STORE_VOUCHER_LIST = "store_voucher_list";

		public Attr() {
		}
	}

	private String freight;
	private String freight_val;
	private String goods_list;
	private String store_goods_total;
	private String store_id;
	private String store_mansong_rule_list;
	private String store_name;
	private String store_premiums_list;
	private String store_voucher_list;

	public StoreCartList() {
	}

	public StoreCartList(String s, String s1, String s2, String s3, String s4,
			String s5, String s6) {
		goods_list = s;
		store_goods_total = s1;
		store_premiums_list = s2;
		store_mansong_rule_list = s3;
		store_voucher_list = s4;
		freight = s5;
		store_name = s6;
	}

	public static StoreCartList newInstanceList(String s) {
		StoreCartList storecartlist = null;
		try {
			JSONObject jsonobject = new JSONObject(s);
			if (jsonobject.length() > 0) {
				storecartlist = new StoreCartList(
						jsonobject.optString("goods_list"),
						jsonobject.optString("store_goods_total"),
						jsonobject.optString("store_premiums_list"),
						jsonobject.optString("store_mansong_rule_list"),
						jsonobject.optString("store_voucher_list"),
						jsonobject.optString("freight"),
						jsonobject.optString("store_name"));
				System.out.println((new StringBuilder("bean-->")).append(
						storecartlist.toString()).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return storecartlist;

	}

	public String getFreight() {
		return freight;
	}

	public String getFreight_val() {
		return freight_val;
	}

	public String getGoods_list() {
		return goods_list;
	}

	public String getStore_goods_total() {
		return store_goods_total;
	}

	public String getStore_id() {
		return store_id;
	}

	public String getStore_mansong_rule_list() {
		return store_mansong_rule_list;
	}

	public String getStore_name() {
		return store_name;
	}

	public String getStore_premiums_list() {
		return store_premiums_list;
	}

	public String getStore_voucher_list() {
		return store_voucher_list;
	}

	public void setFreight(String s) {
		freight = s;
	}

	public void setFreight_val(String s) {
		freight_val = s;
	}

	public void setGoods_list(String s) {
		goods_list = s;
	}

	public void setStore_goods_total(String s) {
		store_goods_total = s;
	}

	public void setStore_id(String s) {
		store_id = s;
	}

	public void setStore_mansong_rule_list(String s) {
		store_mansong_rule_list = s;
	}

	public void setStore_name(String s) {
		store_name = s;
	}

	public void setStore_premiums_list(String s) {
		store_premiums_list = s;
	}

	public void setStore_voucher_list(String s) {
		store_voucher_list = s;
	}

	public String toString() {
		return (new StringBuilder("StoreCartList [store_id=")).append(store_id)
				.append(", goods_list=").append(goods_list)
				.append(", store_goods_total=").append(store_goods_total)
				.append(", store_premiums_list=").append(store_premiums_list)
				.append(", store_mansong_rule_list=")
				.append(store_mansong_rule_list)
				.append(", store_voucher_list=").append(store_voucher_list)
				.append(", freight=").append(freight).append(", store_name=")
				.append(store_name).append(", freight_val=")
				.append(freight_val).append("]").toString();
	}
}
