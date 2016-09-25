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

import com.activeandroid.annotation.Table;

public class CartList
{
	public static class Attr
	{

		public static final String BL_ID = "bl_id";
		public static final String BUYER_ID = "buyer_id";
		public static final String CART_ID = "cart_id";
		public static final String GOODS_ID = "goods_id";
		public static final String GOODS_IMAGE = "goods_image";
		public static final String GOODS_IMAGE_URL = "goods_image_url";
		public static final String GOODS_NAME = "goods_name";
		public static final String GOODS_NUM = "goods_num";
		public static final String GOODS_PRICE = "goods_price";
		public static final String PREMIUMS = "premiums";
		public static final String STORE_ID = "store_id";
		public static final String STORE_NAME = "store_name";

		public Attr()
		{
		}
	}


	private boolean Selected;
	private String bl_id;
	private String buyer_id;
	private String cart_id;
	private String goods_id;
	private String goods_image;
	private String goods_image_url;
	private String goods_name;
	private String goods_num;
	private String goods_price;
	private String premiums;
	private String store_id;
	private String store_name;

	public CartList()
	{
		Selected = false;
	}

	public CartList(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
			String s7, String s8, String s9, String s10, String s11)
	{
		Selected = false;
		bl_id = s;
		buyer_id = s1;
		goods_price = s2;
		cart_id = s3;
		goods_num = s4;
		goods_id = s5;
		goods_image_url = s6;
		goods_name = s7;
		store_id = s8;
		goods_image = s9;
		store_name = s10;
		premiums = s11;
	}

	public static ArrayList newInstanceList(String s)
	{
		ArrayList arraylist = new ArrayList();

		

		try {
			JSONArray jsonarray = new JSONArray(s);
			for (int i = 0; i <= jsonarray.length() - 1; i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new CartList(jsonobject.optString("bl_id"),
						jsonobject.optString("buyer_id"), jsonobject
								.optString("goods_price"), jsonobject
								.optString("cart_id"), jsonobject
								.optString("goods_num"), jsonobject
								.optString("goods_id"), jsonobject
								.optString("goods_image_url"), jsonobject
								.optString("goods_name"), jsonobject
								.optString("store_id"), jsonobject
								.optString("goods_image"), jsonobject
								.optString("store_name"), jsonobject
								.optString("premiums")));
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();

		}
		return arraylist;
		
	}

	public String getBl_id()
	{
		return bl_id;
	}

	public String getBuyer_id()
	{
		return buyer_id;
	}

	public String getCart_id()
	{
		return cart_id;
	}

	public String getGoods_id()
	{
		return goods_id;
	}

	public String getGoods_image()
	{
		return goods_image;
	}

	public String getGoods_image_url()
	{
		return goods_image_url;
	}

	public String getGoods_name()
	{
		return goods_name;
	}

	public String getGoods_num()
	{
		return goods_num;
	}

	public String getGoods_price()
	{
		return goods_price;
	}

	public String getPremiums()
	{
		return premiums;
	}

	public String getStore_id()
	{
		return store_id;
	}

	public String getStore_name()
	{
		return store_name;
	}

	public boolean isSelected()
	{
		return Selected;
	}

	public void setBl_id(String s)
	{
		bl_id = s;
	}

	public void setBuyer_id(String s)
	{
		buyer_id = s;
	}

	public void setCart_id(String s)
	{
		cart_id = s;
	}

	public void setGoods_id(String s)
	{
		goods_id = s;
	}

	public void setGoods_image(String s)
	{
		goods_image = s;
	}

	public void setGoods_image_url(String s)
	{
		goods_image_url = s;
	}

	public void setGoods_name(String s)
	{
		goods_name = s;
	}

	public void setGoods_num(String s)
	{
		goods_num = s;
	}

	public void setGoods_price(String s)
	{
		goods_price = s;
	}

	public void setPremiums(String s)
	{
		premiums = s;
	}

	public void setSelected(boolean flag)
	{
		Selected = flag;
	}

	public void setStore_id(String s)
	{
		store_id = s;
	}

	public void setStore_name(String s)
	{
		store_name = s;
	}

	public String toString()
	{
		return (new StringBuilder("CartList [bl_id=")).append(bl_id).append(", buyer_id=").append(buyer_id).append(", goods_price=").append(goods_price).append(", cart_id=").append(cart_id).append(", goods_num=").append(goods_num).append(", goods_id=").append(goods_id).append(", goods_image_url=").append(goods_image_url).append(", goods_name=").append(goods_name).append(", store_id=").append(store_id).append(", goods_image=").append(goods_image).append(", store_name=").append(store_name).append(", premiums=").append(premiums).append("]").toString();
	}
}
