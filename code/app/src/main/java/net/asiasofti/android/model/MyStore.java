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

import org.json.JSONException;
import org.json.JSONObject;

public class MyStore
{
	public static class Attr
	{

		public static final String AVATOR = "avator";
		public static final String POINT = "point";
		public static final String PREDEPOIT = "predepoit";
		public static final String USERNAME = "user_name";

		public Attr()
		{
		}
	}


	private String avator;
	private String point;
	private String predepoit;
	private String username;

	public MyStore()
	{
	}

	public MyStore(String s, String s1, String s2, String s3)
	{
		username = s;
		avator = s1;
		point = s2;
		predepoit = s3;
	}

	public static MyStore newInstanceList(String s)
	{

		MyStore mystore = null;
		try
		{
			JSONObject jsonobject = new JSONObject(s);
			int i = jsonobject.length();
			if(i>0)
			{
				mystore = new MyStore(jsonobject.optString("user_name"), jsonobject.optString("avator"), jsonobject.optString("point"), jsonobject.optString("predepoit"));
			}
		}
		catch (JSONException jsonexception)
		{
			jsonexception.printStackTrace();

		}

		return mystore;
	}

	public String getAvator()
	{
		return avator;
	}

	public String getPoint()
	{
		return point;
	}

	public String getPredepoit()
	{
		return predepoit;
	}

	public String getUsername()
	{
		return username;
	}

	public void setAvator(String s)
	{
		avator = s;
	}

	public void setPoint(String s)
	{
		point = s;
	}

	public void setPredepoit(String s)
	{
		predepoit = s;
	}

	public void setUsername(String s)
	{
		username = s;
	}

	public String toString()
	{
		return (new StringBuilder("MyStore [username=")).append(username).append(", avator=").append(avator).append(", point=").append(point).append(", predepoit=").append(predepoit).append("]").toString();
	}
}
