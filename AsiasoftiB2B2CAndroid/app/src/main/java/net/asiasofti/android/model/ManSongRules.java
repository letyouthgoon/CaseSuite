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

public class ManSongRules {
	public static class Attr {

		public static final String DISCOUNT = "discount";
		public static final String GOODS_ID = "goods_id";
		public static final String GOODS_IMAGE_URL = "goods_image_url";
		public static final String MANSONG_GOODS_NAME = "mansong_goods_name";
		public static final String PRICE = "price";

		public Attr() {
		}
	}

	private String discount;
	private String goods_id;
	private String goods_image_url;
	private String mansong_goods_name;
	private String price;

	public ManSongRules() {
	}

	public ManSongRules(String s, String s1, String s2, String s3, String s4) {
		price = s;
		discount = s1;
		mansong_goods_name = s2;
		goods_id = s3;
		goods_image_url = s4;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();

		try {
			JSONArray jsonarray = new JSONArray(s);
			for (int i = 0; i <= jsonarray.length() - 1; i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new ManSongRules(jsonobject.optString("price"),
						jsonobject.optString("discount"), jsonobject
								.optString("mansong_goods_name"), jsonobject
								.optString("goods_id"), jsonobject
								.optString("goods_image_url")));
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
			return arraylist;
		}

		return arraylist;
	}

	public String getDiscount() {
		return discount;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public String getGoods_image_url() {
		return goods_image_url;
	}

	public String getMansong_goods_name() {
		return mansong_goods_name;
	}

	public String getPrice() {
		return price;
	}

	public void setDiscount(String s) {
		discount = s;
	}

	public void setGoods_id(String s) {
		goods_id = s;
	}

	public void setGoods_image_url(String s) {
		goods_image_url = s;
	}

	public void setMansong_goods_name(String s) {
		mansong_goods_name = s;
	}

	public void setPrice(String s) {
		price = s;
	}

	public String toString() {
		return (new StringBuilder("ManSongRules [price=")).append(price)
				.append(", discount=").append(discount)
				.append(", mansong_goods_name=").append(mansong_goods_name)
				.append(", goods_id=").append(goods_id)
				.append(", goods_image_url=").append(goods_image_url)
				.append("]").toString();
	}
}
