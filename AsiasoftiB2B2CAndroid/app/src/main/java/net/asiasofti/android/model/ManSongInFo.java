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
import org.json.JSONException;
import org.json.JSONObject;

public class ManSongInFo
{
	public static class Attr
	{

		public static final String END_TIME = "end_time";
		public static final String MANSONG_NAME = "mansong_name";
		public static final String RULES = "rules";
		public static final String START_TIME = "start_time";

		public Attr()
		{
		}
	}


	private String end_time;
	private String mansong_name;
	private String rules;
	private String start_time;

	public ManSongInFo()
	{
	}

	public ManSongInFo(String s, String s1, String s2, String s3)
	{
		mansong_name = s;
		start_time = s1;
		end_time = s2;
		rules = s3;
	}

	public static ManSongInFo newInstanceList(String s)
	{
		ManSongInFo mansonginfo = null;
		try
		{
			JSONObject jsonobject = new JSONObject(s);
			int i = jsonobject.length();
			mansonginfo = null;
			if (i > 0)
			{	
				 mansonginfo = new ManSongInFo(jsonobject.optString("mansong_name"), jsonobject.optString("start_time"), jsonobject.optString("end_time"), jsonobject.optString("rules"));
				System.out.println((new StringBuilder("bean-->")).append(mansonginfo.toString()).toString());				
			}
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return mansonginfo;

	}

	public String getEnd_time()
	{
		return end_time;
	}

	public String getMansong_name()
	{
		return mansong_name;
	}

	public String getRules()
	{
		return rules;
	}

	public String getStart_time()
	{
		return start_time;
	}

	public void setEnd_time(String s)
	{
		end_time = s;
	}

	public void setMansong_name(String s)
	{
		mansong_name = s;
	}

	public void setRules(String s)
	{
		rules = s;
	}

	public void setStart_time(String s)
	{
		start_time = s;
	}

	public String toString()
	{
		return (new StringBuilder("ManSongInFo [mansong_name=")).append(mansong_name).append(", start_time=").append(start_time).append(", end_time=").append(end_time).append(", rules=").append(rules).append("]").toString();
	}
}
