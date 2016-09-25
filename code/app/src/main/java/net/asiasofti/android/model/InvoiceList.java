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

public class InvoiceList
{
	public static class Attr
	{

		public static final String INV_CONTENT = "inv_content";
		public static final String INV_ID = "inv_id";
		public static final String INV_TITLE = "inv_title";

		public Attr()
		{
		}
	}


	private String inv_content;
	private String inv_id;
	private String inv_title;

	public InvoiceList()
	{
	}

	public InvoiceList(String s, String s1, String s2)
	{
		inv_id = s;
		inv_title = s1;
		inv_content = s2;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();

		try {
			JSONArray jsonarray = new JSONArray(s);
			for (int i = 0; i <= jsonarray.length() - 1; i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new InvoiceList(jsonobject.optString("inv_id"),
						jsonobject.optString("inv_title"), jsonobject
								.optString("inv_content")));
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
		}

		return arraylist;
	}

	public String getInv_content()
	{
		return inv_content;
	}

	public String getInv_id()
	{
		return inv_id;
	}

	public String getInv_title()
	{
		return inv_title;
	}

	public void setInv_content(String s)
	{
		inv_content = s;
	}

	public void setInv_id(String s)
	{
		inv_id = s;
	}

	public void setInv_title(String s)
	{
		inv_title = s;
	}

	public String toString()
	{
		return (new StringBuilder("InvoiceList [inv_id=")).append(inv_id).append(", inv_title=").append(inv_title).append(", inv_content=").append(inv_content).append("]").toString();
	}
}
