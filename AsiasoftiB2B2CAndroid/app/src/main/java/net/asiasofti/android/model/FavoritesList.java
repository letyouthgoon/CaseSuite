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

public class FavoritesList {
	public static class Attr {

		public static final String FAV_ID = "fav_id";
		public static final String GOODS_IMAGE_URL = "goods_image_url";
		public static final String GOODS_NAME = "goods_name";
		public static final String GOODS_PRICE = "goods_price";

		public Attr() {
		}
	}

	private String fav_id;
	private String goods_image_url;
	private String goods_name;
	private String goods_price;

	public FavoritesList() {
	}

	public FavoritesList(String s, String s1, String s2, String s3) {
		goods_name = s;
		goods_image_url = s1;
		goods_price = s2;
		fav_id = s3;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();

		try {
			JSONArray jsonarray = new JSONArray(s);
			for (int i = 0; i <= jsonarray.length() - 1; i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new FavoritesList(jsonobject
						.optString("goods_name"), jsonobject
						.optString("goods_image_url"), jsonobject
						.optString("goods_price"), jsonobject
						.optString("fav_id")));
			}

		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();

		}

		return arraylist;
	}

	public String getFav_id() {
		return fav_id;
	}

	public String getGoods_image_url() {
		return goods_image_url;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public String getGoods_price() {
		return goods_price;
	}

	public void setFav_id(String s) {
		fav_id = s;
	}

	public void setGoods_image_url(String s) {
		goods_image_url = s;
	}

	public void setGoods_name(String s) {
		goods_name = s;
	}

	public void setGoods_price(String s) {
		goods_price = s;
	}

	public String toString() {
		return (new StringBuilder("FavoritesList [goods_name="))
				.append(goods_name).append(", goods_image_url=")
				.append(goods_image_url).append(", goods_price=")
				.append(goods_price).append(", fav_id=").append(fav_id)
				.append("]").toString();
	}
}
