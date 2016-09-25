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

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
@Table(name = "AddressList")
public class AddressList
{
	public static class Attr
	{

		public static final String ADDRESS = "address";
		public static final String ADDRESS_ID = "address_id";
		public static final String AREA_ID = "area_id";
		public static final String AREA_INFO = "area_info";
		public static final String CITY_ID = "city_id";
		public static final String MEMBER_ID = "member_id";
		public static final String MOB_PHONE = "mob_phone";
		public static final String TEL_PHONE = "tel_phone";
		public static final String TRUE_NAME = "true_name";

		public Attr()
		{
		}
	}

	@Column(name = "address")
	private String address;
	
	@Column(name = "address_id")
	private String address_id;
	
	@Column(name = "area_id")
	private String area_id;
	
	@Column(name = "area_info")
	private String area_info;
	
	@Column(name = "city_id")
	private String city_id;
	
	@Column(name = "member_id")
	private String member_id;
	
	@Column(name = "mob_phone")
	private String mob_phone;
	
	@Column(name = "tel_phone")
	private String tel_phone;
	
	@Column(name = "true_name")
	private String true_name;

	public AddressList()
	{
	}

	public AddressList(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
			String s7, String s8)
	{
		area_info = s;
		address = s1;
		tel_phone = s2;
		mob_phone = s3;
		area_id = s4;
		city_id = s5;
		address_id = s6;
		member_id = s7;
		true_name = s8;
	}

	public static ArrayList newInstanceList(String s)
	{		
		ArrayList arraylist = new ArrayList();		
		try
		{
			JSONArray jsonarray = new JSONArray(s);
			for(int i=0;i <= jsonarray.length()-1;i++)
			{
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				arraylist.add(new AddressList(jsonobject.optString("area_info"), jsonobject.optString("address"), jsonobject.optString("tel_phone"), jsonobject.optString("mob_phone"), jsonobject.optString("area_id"), jsonobject.optString("city_id"), jsonobject.optString("address_id"), jsonobject.optString("member_id"), jsonobject.optString("true_name")));
			}
		}
		catch (JSONException jsonexception)
		{
			jsonexception.printStackTrace();
		}

		return arraylist;
	}

	public String getAddress()
	{
		return address;
	}

	public String getAddress_id()
	{
		return address_id;
	}

	public String getArea_id()
	{
		return area_id;
	}

	public String getArea_info()
	{
		return area_info;
	}

	public String getCity_id()
	{
		return city_id;
	}

	public String getMember_id()
	{
		return member_id;
	}

	public String getMob_phone()
	{
		return mob_phone;
	}

	public String getTel_phone()
	{
		return tel_phone;
	}

	public String getTrue_name()
	{
		return true_name;
	}

	public void setAddress(String s)
	{
		address = s;
	}

	public void setAddress_id(String s)
	{
		address_id = s;
	}

	public void setArea_id(String s)
	{
		area_id = s;
	}

	public void setArea_info(String s)
	{
		area_info = s;
	}

	public void setCity_id(String s)
	{
		city_id = s;
	}

	public void setMember_id(String s)
	{
		member_id = s;
	}

	public void setMob_phone(String s)
	{
		mob_phone = s;
	}

	public void setTel_phone(String s)
	{
		tel_phone = s;
	}

	public void setTrue_name(String s)
	{
		true_name = s;
	}

	public String toString()
	{
		return (new StringBuilder("AddressList [area_info=")).append(area_info).append(", address=").append(address).append(", tel_phone=").append(tel_phone).append(", mob_phone=").append(mob_phone).append(", area_id=").append(area_id).append(", city_id=").append(city_id).append(", address_id=").append(address_id).append(", member_id=").append(member_id).append(", true_name=").append(true_name).append("]").toString();
	}
}
