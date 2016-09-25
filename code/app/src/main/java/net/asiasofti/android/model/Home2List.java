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

public class Home2List
{
	public static class Attr
	{

		public static final String DESC = "desc";
		public static final String IMAGE = "image";
		public static final String KEYWORD = "keyword";
		public static final String TITLE = "title";

		public Attr()
		{
		}
	}


	private String desc;
	private String image;
	private String keyword;
	private String title;

	public Home2List()
	{
	}

	public Home2List(String s, String s1, String s2, String s3)
	{
		image = s;
		title = s1;
		desc = s2;
		keyword = s3;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();

		try {
			JSONArray jsonarray = new JSONArray(s);
			for (int i = 0; i <= jsonarray.length() - 1; i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new Home2List(jsonobject.optString("image"),
						jsonobject.optString("title"), jsonobject
								.optString("desc"), jsonobject
								.optString("keyword")));
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();

		}

		return arraylist;
	}

	public String getDesc()
	{
		return desc;
	}

	public String getImage()
	{
		return image;
	}

	public String getKeyword()
	{
		return keyword;
	}

	public String getTitle()
	{
		return title;
	}

	public void setDesc(String s)
	{
		desc = s;
	}

	public void setImage(String s)
	{
		image = s;
	}

	public void setKeyword(String s)
	{
		keyword = s;
	}

	public void setTitle(String s)
	{
		title = s;
	}

	public String toString()
	{
		return (new StringBuilder("Home2List [image=")).append(image).append(", title=").append(title).append(", desc=").append(desc).append(", keyword=").append(keyword).append("]").toString();
	}
}
