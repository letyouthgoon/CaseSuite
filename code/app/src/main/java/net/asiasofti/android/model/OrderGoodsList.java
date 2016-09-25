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

public class OrderGoodsList {
	public static class Attr {

		public static final String BUYER_ID = "buyer_id";
		public static final String COMMIS_RATE = "commis_rate";
		public static final String GOODS_ID = "goods_id";
		public static final String GOODS_IMAGE = "goods_image";
		public static final String GOODS_IMAGE_URL = "goods_image_url";
		public static final String GOODS_NAME = "goods_name";
		public static final String GOODS_NUM = "goods_num";
		public static final String GOODS_PAY_PRICE = "goods_pay_price";
		public static final String GOODS_PRICE = "goods_price";
		public static final String GOODS_TYPE = "goods_type";
		public static final String ORDER_ID = "order_id";
		public static final String PROMOTIONS_ID = "promotions_id";
		public static final String REC_ID = "rec_id";
		public static final String STORE_ID = "store_id";

		public Attr() {
		}
	}

	private String buyer_id;
	private String commis_rate;
	private String goods_id;
	private String goods_image;
	private String goods_image_url;
	private String goods_name;
	private String goods_num;
	private String goods_pay_price;
	private String goods_price;
	private String goods_type;
	private String order_id;
	private String promotions_id;
	private String rec_id;
	private String store_id;

	public OrderGoodsList() {
	}

	public OrderGoodsList(String s, String s1, String s2, String s3, String s4,
			String s5, String s6, String s7, String s8, String s9, String s10,
			String s11, String s12, String s13) {
		buyer_id = s;
		goods_pay_price = s1;
		promotions_id = s2;
		commis_rate = s3;
		goods_num = s4;
		goods_image_url = s5;
		store_id = s6;
		goods_name = s7;
		goods_image = s8;
		order_id = s9;
		goods_price = s10;
		goods_id = s11;
		goods_type = s12;
		rec_id = s13;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();

		try {
			JSONArray jsonarray = new JSONArray(s);
			for (int i = 0; i <= jsonarray.length() - 1; i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);

				arraylist.add(new OrderGoodsList(jsonobject
						.optString("buyer_id"), jsonobject
						.optString("goods_pay_price"), jsonobject
						.optString("promotions_id"), jsonobject
						.optString("commis_rate"), jsonobject
						.optString("goods_num"), jsonobject
						.optString("goods_image_url"), jsonobject
						.optString("store_id"), jsonobject
						.optString("goods_name"), jsonobject
						.optString("goods_image"), jsonobject
						.optString("order_id"), jsonobject
						.optString("goods_price"), jsonobject
						.optString("goods_id"), jsonobject
						.optString("goods_type"), jsonobject
						.optString("rec_id")));
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
		}

		return arraylist;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public String getCommis_rate() {
		return commis_rate;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public String getGoods_image() {
		return goods_image;
	}

	public String getGoods_image_url() {
		return goods_image_url;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public String getGoods_num() {
		return goods_num;
	}

	public String getGoods_pay_price() {
		return goods_pay_price;
	}

	public String getGoods_price() {
		return goods_price;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public String getOrder_id() {
		return order_id;
	}

	public String getPromotions_id() {
		return promotions_id;
	}

	public String getRec_id() {
		return rec_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setBuyer_id(String s) {
		buyer_id = s;
	}

	public void setCommis_rate(String s) {
		commis_rate = s;
	}

	public void setGoods_id(String s) {
		goods_id = s;
	}

	public void setGoods_image(String s) {
		goods_image = s;
	}

	public void setGoods_image_url(String s) {
		goods_image_url = s;
	}

	public void setGoods_name(String s) {
		goods_name = s;
	}

	public void setGoods_num(String s) {
		goods_num = s;
	}

	public void setGoods_pay_price(String s) {
		goods_pay_price = s;
	}

	public void setGoods_price(String s) {
		goods_price = s;
	}

	public void setGoods_type(String s) {
		goods_type = s;
	}

	public void setOrder_id(String s) {
		order_id = s;
	}

	public void setPromotions_id(String s) {
		promotions_id = s;
	}

	public void setRec_id(String s) {
		rec_id = s;
	}

	public void setStore_id(String s) {
		store_id = s;
	}

	public String toString() {
		return (new StringBuilder("OrderGoodsList [buyer_id="))
				.append(buyer_id).append(", goods_pay_price=")
				.append(goods_pay_price).append(", promotions_id=")
				.append(promotions_id).append(", commis_rate=")
				.append(commis_rate).append(", goods_num=").append(goods_num)
				.append(", goods_image_url=").append(goods_image_url)
				.append(", store_id=").append(store_id).append(", goods_name=")
				.append(goods_name).append(", goods_image=")
				.append(goods_image).append(", order_id=").append(order_id)
				.append(", goods_price=").append(goods_price)
				.append(", goods_id=").append(goods_id).append(", goods_type=")
				.append(goods_type).append(", rec_id=").append(rec_id)
				.append("]").toString();
	}
}
