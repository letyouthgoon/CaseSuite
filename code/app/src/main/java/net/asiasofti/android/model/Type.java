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

public class Type
{
	public static class Attr
	{

		public static final String GC_ID = "gc_id";
		public static final String GC_NAME = "gc_name";
		public static final String IMAGE = "image";
		public static final String TEXT = "text";

		public Attr()
		{
		}
	}


	private String gc_id;
	private String gc_name;
	private String image;
	private String text;

	public Type()
	{
	}

	public Type(String s, String s1, String s2, String s3)
	{
		gc_id = s;
		gc_name = s1;
		image = s2;
		text = s3;
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
				arraylist.add(new Type(jsonobject.optString("gc_id"), jsonobject.optString("gc_name"), jsonobject.optString("image"), jsonobject.optString("text")));
			}
		}
		catch (JSONException jsonexception)
		{
			jsonexception.printStackTrace();
		}
	
		return arraylist;
	}

	public String getGc_id()
	{
		return gc_id;
	}

	public String getGc_name()
	{
		return gc_name;
	}

	public String getImage()
	{
		return image;
	}

	public String getText()
	{
		return text;
	}

	public void setGc_id(String s)
	{
		gc_id = s;
	}

	public void setGc_name(String s)
	{
		gc_name = s;
	}

	public void setImage(String s)
	{
		image = s;
	}

	public void setText(String s)
	{
		text = s;
	}

	public String toString()
	{
		return (new StringBuilder("Type [gc_id=")).append(gc_id).append(", gc_name=").append(gc_name).append(", image=").append(image).append(", text=").append(text).append("]").toString();
	}
}
