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

public class GoodsList
{
	public static class Attr
	{

		public static final String EVALUATION_COUNT = "evaluation_count";
		public static final String EVAUATION_GOOD_STAR = "evaluation_good_star";
		public static final String GOODS_ID = "goods_id";
		public static final String GOODS_IMAGE = "goods_image";
		public static final String GOODS_IMAGES_URL = "goods_image_url";
		public static final String GOODS_MARKETPRICE = "goods_marketprice";
		public static final String GOODS_NAME = "goods_name";
		public static final String GOODS_PRICE = "goods_price";
		public static final String GOODS_SALENUM = "goods_salenum";
		public static final String GROUP_FLAG = "group_flag";
		public static final String XIANSHI_FLAG = "xianshi_flag";

		public Attr()
		{
		}
	}


	private String evaluation_count;
	private String evaluation_good_star;
	private String goods_id;
	private String goods_image;
	private String goods_image_url;
	private String goods_marketprice;
	private String goods_name;
	private String goods_price;
	private String goods_salenum;
	private String group_flag;
	private String xianshi_flag;

	public GoodsList()
	{
	}

	public GoodsList(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
			String s7, String s8, String s9, String s10)
	{
		goods_id = s;
		goods_price = s1;
		goods_marketprice = s2;
		goods_image = s3;
		goods_salenum = s4;
		evaluation_good_star = s5;
		evaluation_count = s6;
		group_flag = s7;
		xianshi_flag = s8;
		goods_image_url = s9;
		goods_name = s10;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();
		try {
			JSONArray jsonarray = new JSONArray(s);
			for (int i = 0; i <= jsonarray.length() - 1; i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new GoodsList(jsonobject.optString("goods_id"),
						jsonobject.optString("goods_price"), jsonobject
								.optString("goods_marketprice"), jsonobject
								.optString("goods_image"), jsonobject
								.optString("goods_salenum"), jsonobject
								.optString("evaluation_good_star"), jsonobject
								.optString("evaluation_count"), jsonobject
								.optString("group_flag"), jsonobject
								.optString("xianshi_flag"), jsonobject
								.optString("goods_image_url"), jsonobject
								.optString("goods_name")));
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
		}

		return arraylist;
	}

	public String getEvaluation_count()
	{
		return evaluation_count;
	}

	public String getEvaluation_good_star()
	{
		return evaluation_good_star;
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

	public String getGoods_marketprice()
	{
		return goods_marketprice;
	}

	public String getGoods_name()
	{
		return goods_name;
	}

	public String getGoods_price()
	{
		return goods_price;
	}

	public String getGoods_salenum()
	{
		return goods_salenum;
	}

	public String getGroup_flag()
	{
		return group_flag;
	}

	public String getXianshi_flag()
	{
		return xianshi_flag;
	}

	public void setEvaluation_count(String s)
	{
		evaluation_count = s;
	}

	public void setEvaluation_good_star(String s)
	{
		evaluation_good_star = s;
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

	public void setGoods_marketprice(String s)
	{
		goods_marketprice = s;
	}

	public void setGoods_name(String s)
	{
		goods_name = s;
	}

	public void setGoods_price(String s)
	{
		goods_price = s;
	}

	public void setGoods_salenum(String s)
	{
		goods_salenum = s;
	}

	public void setGroup_flag(String s)
	{
		group_flag = s;
	}

	public void setXianshi_flag(String s)
	{
		xianshi_flag = s;
	}

	public String toString()
	{
		return (new StringBuilder("GoodsList [goods_id=")).append(goods_id).append(", goods_price=").append(goods_price).append(", goods_marketprice=").append(goods_marketprice).append(", goods_image=").append(goods_image).append(", goods_salenum=").append(goods_salenum).append(", evaluation_good_star=").append(evaluation_good_star).append(", evaluation_count=").append(evaluation_count).append(", group_flag=").append(group_flag).append(", xianshi_flag=").append(xianshi_flag).append(", goods_image_url=").append(goods_image_url).append(", goods_name=").append(goods_name).append("]").toString();
	}
}
