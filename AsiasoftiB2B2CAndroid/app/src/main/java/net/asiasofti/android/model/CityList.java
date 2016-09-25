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

public class CityList
{
	public static class Attr
	{

		public static final String AREA_ID = "area_id";
		public static final String AREA_NAME = "area_name";

		public Attr()
		{
		}
	}


	private String area_id;
	private String area_name;

	public CityList()
	{
	}

	public CityList(String s, String s1)
	{
		area_id = s;
		area_name = s1;
	}

	public static ArrayList newInstanceList(String s)
	{
		ArrayList arraylist = new ArrayList();;

		try
		{
			JSONArray jsonarray = new JSONArray(s);
			for(int i=0; i <= jsonarray.length()-1; i++)
			{
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new CityList(jsonobject.optString("area_id"), jsonobject.optString("area_name")));
			}
		}
		catch (JSONException jsonexception)
		{
			jsonexception.printStackTrace();
		
		}
		
		return arraylist;
	}

	public String getArea_id()
	{
		return area_id;
	}

	public String getArea_name()
	{
		return area_name;
	}

	public void setArea_id(String s)
	{
		area_id = s;
	}

	public void setArea_name(String s)
	{
		area_name = s;
	}

	public String toString()
	{
		return (new StringBuilder("CityList [area_id=")).append(area_id).append(", area_name=").append(area_name).append("]").toString();
	}
}
