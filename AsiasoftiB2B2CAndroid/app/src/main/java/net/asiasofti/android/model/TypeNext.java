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

import java.io.PrintStream;
import java.util.ArrayList;
import org.json.*;

public class TypeNext
{
	public static class Attr
	{

		public static final String GC_ID = "gc_id";
		public static final String GC_NAME = "gc_name";

		public Attr()
		{
		}
	}


	private String gc_id;
	private String gc_name;

	public TypeNext()
	{
	}

	public TypeNext(String s, String s1)
	{
		gc_id = s;
		gc_name = s1;
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
			TypeNext typenext = new TypeNext(jsonobject.optString("gc_id"), jsonobject.optString("gc_name"));
			arraylist.add(typenext);
			System.out.println((new StringBuilder("t-->")).append(typenext.toString()).toString());
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

	public void setGc_id(String s)
	{
		gc_id = s;
	}

	public void setGc_name(String s)
	{
		gc_name = s;
	}

	public String toString()
	{
		return (new StringBuilder("Type [gc_id=")).append(gc_id).append(", gc_name=").append(gc_name).append("]").toString();
	}
}
