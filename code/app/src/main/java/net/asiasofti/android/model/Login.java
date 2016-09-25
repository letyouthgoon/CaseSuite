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

public class Login
{
	public static class Attr
	{

		public static final String KEY = "key";
		public static final String USERNAME = "username";

		public Attr()
		{
		}
	}


	private String key;
	private String username;

	public Login()
	{
	}

	public Login(String s, String s1)
	{
		key = s;
		username = s1;
	}

	public static Login newInstanceList(String s)
	{
		Login login = null;
		try
		{
			JSONObject jsonobject = new JSONObject(s);
			int i = jsonobject.length();
			if (i > 0) {
				 login = new Login(jsonobject.optString("key"),
						jsonobject.optString("username"));
             
			}
		}
		catch (JSONException jsonexception)
		{
			jsonexception.printStackTrace();
			return null;
		}
		
		return login;
	}

	public String getKey()
	{
		return key;
	}

	public String getUsername()
	{
		return username;
	}

	public void setKey(String s)
	{
		key = s;
	}

	public void setUsername(String s)
	{
		username = s;
	}

	public String toString()
	{
		return (new StringBuilder("Login [key=")).append(key).append(", username=").append(username).append("]").toString();
	}
}
