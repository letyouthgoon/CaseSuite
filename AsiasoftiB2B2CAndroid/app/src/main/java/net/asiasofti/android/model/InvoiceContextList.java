package net.asiasofti.android.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

public class InvoiceContextList
{
	public static class Attr
	{

		public static final String INVOICE_CONTENT_LIST = "invoice_content_list";

		public Attr()
		{
		}
	}


	private String invoice_content_list;

	public InvoiceContextList()
	{
	}

	public InvoiceContextList(String s)
	{
		invoice_content_list = s;
	}

	public static ArrayList newInstanceList(String s)
	{
		ArrayList arraylist = new ArrayList();
		
		try
		{
			JSONArray jsonarray = new JSONArray(s);
			for(int i=0;i<=jsonarray.length()-1;i++)
			{
				arraylist.add(new InvoiceContextList(jsonarray.get(i).toString()));
			}

		}
		catch (JSONException jsonexception)
		{
			jsonexception.printStackTrace();
			
		}
		
		return arraylist;
	}

	public String getInvoice_content_list()
	{
		return invoice_content_list;
	}

	public void setInvoice_content_list(String s)
	{
		invoice_content_list = s;
	}

	public String toString()
	{
		return (new StringBuilder("InvoiceContextList [invoice_content_list=")).append(invoice_content_list).append("]").toString();
	}
}
