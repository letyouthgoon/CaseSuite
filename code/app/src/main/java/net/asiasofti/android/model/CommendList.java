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

public class CommendList
{
	public static class Attr
	{

		public static final String GOODS_ID = "goods_id";
		public static final String GOODS_IMAGE_URL = "goods_image_url";
		public static final String GOODS_NAME = "goods_name";
		public static final String GOODS_PRICE = "goods_price";

		public Attr()
		{
		}
	}


	private String goods_id;
	private String goods_image_url;
	private String goods_name;
	private String goods_price;

	public CommendList()
	{
	}

	public CommendList(String s, String s1, String s2, String s3)
	{
		goods_id = s;
		goods_name = s1;
		goods_price = s2;
		goods_image_url = s3;
	}

	public static ArrayList newInstanceList(String s)
	{
		ArrayList arraylist = new ArrayList();
		
		try
		{
			JSONArray jsonarray = new JSONArray(s);
			for(int i=0;i<=jsonarray.length()-1;i++)
			{
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new CommendList(jsonobject.optString("goods_id"), jsonobject.optString("goods_name"), jsonobject.optString("goods_price"), jsonobject.optString("goods_image_url")));
			}
		}
		catch (JSONException jsonexception)
		{
			jsonexception.printStackTrace();
			return arraylist;
		}
		
		return arraylist;
	}

	public String getGoods_id()
	{
		return goods_id;
	}

	public String getGoods_image_url()
	{
		return goods_image_url;
	}

	public String getGoods_name()
	{
		return goods_name;
	}

	public String getGoods_price()
	{
		return goods_price;
	}

	public void setGoods_id(String s)
	{
		goods_id = s;
	}

	public void setGoods_image_url(String s)
	{
		goods_image_url = s;
	}

	public void setGoods_name(String s)
	{
		goods_name = s;
	}

	public void setGoods_price(String s)
	{
		goods_price = s;
	}

	public String toString()
	{
		return (new StringBuilder("CommendList [goods_id=")).append(goods_id).append(", goods_name=").append(goods_name).append(", goods_price=").append(goods_price).append(", goods_image_url=").append(goods_image_url).append("]").toString();
	}
}
