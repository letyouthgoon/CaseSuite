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

public class GoodsDetails
{
	public static class Attr
	{

		public static final String AREAID_1 = "areaid_1";
		public static final String AREAID_2 = "areaid_2";
		public static final String COLOR_ID = "color_id";
		public static final String EVALUATION_COUNT = "evaluation_count";
		public static final String EVALUATION_GOOD_STAR = "evaluation_good_star";
		public static final String GOODS_ATTR = "goods_attr";
		public static final String GOODS_CLICK = "goods_click";
		public static final String GOODS_COLLECT = "goods_collect";
		public static final String GOODS_COSTPRICE = "goods_costprice";
		public static final String GOODS_DISCOUNT = "goods_discount";
		public static final String GOODS_FREIGHT = "goods_freight";
		public static final String GOODS_ID = "goods_id";
		public static final String GOODS_JINGLE = "goods_jingle";
		public static final String GOODS_MARKETPRICE = "goods_marketprice";
		public static final String GOODS_NAME = "goods_name";
		public static final String GOODS_PRICE = "goods_price";
		public static final String GOODS_SALENUM = "goods_salenum";
		public static final String GOODS_SERIAL = "goods_serial";
		public static final String GOODS_SPEC = "goods_spec";
		public static final String GOODS_SPECNAME = "goods_specname";
		public static final String GOODS_STCIDS = "goods_stcids";
		public static final String GOODS_STORAGE = "goods_storage";
		public static final String GOODS_VAT = "goods_vat";
		public static final String PLATEID_BOTTOM = "plateid_bottom";
		public static final String PLATEID_TOP = "plateid_top";
		public static final String PROMOTION_PRICE = "promotion_price";
		public static final String PROMOTION_TYPE = "promotion_type";
		public static final String SPEC_NAME = "spec_name";
		public static final String SPEC_VALUE = "spec_value";
		public static final String TRANSPORT_ID = "transport_id";
		public static final String TRANSPORT_TITLE = "transport_title";
		public static final String UPPER_LIMIT = "upper_limit";

		public Attr()
		{
		}
	}


	private String areaid_1;
	private String areaid_2;
	private String color_id;
	private String evaluation_count;
	private String evaluation_good_star;
	private String goods_attr;
	private String goods_click;
	private String goods_collect;
	private String goods_costprice;
	private String goods_discount;
	private String goods_freight;
	private String goods_id;
	private String goods_jingle;
	private String goods_marketprice;
	private String goods_name;
	private String goods_price;
	private String goods_salenum;
	private String goods_serial;
	private String goods_spec;
	private String goods_specname;
	private String goods_stcids;
	private String goods_storage;
	private String goods_vat;
	private String plateid_bottom;
	private String plateid_top;
	private String promotion_price;
	private String promotion_type;
	private String spec_name;
	private String spec_value;
	private String transport_id;
	private String transport_title;
	private String upper_limit;

	public GoodsDetails()
	{
	}

	public GoodsDetails(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
			String s7, String s8, String s9, String s10, String s11, String s12, String s13, 
			String s14, String s15, String s16, String s17, String s18, String s19, String s20, 
			String s21, String s22, String s23, String s24, String s25, String s26, String s27, 
			String s28, String s29, String s30, String s31)
	{
		goods_name = s;
		goods_jingle = s1;
		spec_name = s2;
		spec_value = s3;
		goods_attr = s4;
		goods_specname = s5;
		goods_price = s6;
		goods_marketprice = s7;
		goods_costprice = s8;
		goods_discount = s9;
		goods_serial = s10;
		transport_id = s11;
		transport_title = s12;
		goods_freight = s13;
		goods_vat = s14;
		areaid_1 = s15;
		areaid_2 = s16;
		goods_stcids = s17;
		plateid_top = s18;
		plateid_bottom = s19;
		goods_id = s20;
		goods_click = s21;
		goods_collect = s22;
		goods_salenum = s23;
		goods_spec = s24;
		goods_storage = s25;
		color_id = s26;
		evaluation_good_star = s27;
		evaluation_count = s28;
		promotion_type = s29;
		promotion_price = s30;
		upper_limit = s31;
	}

	public static GoodsDetails newInstanceList(String s) {
		GoodsDetails goodsdetails = null;
		try {
			JSONObject jsonobject = new JSONObject(s);
			if (jsonobject != null && jsonobject.length() > 0) {
				goodsdetails = new GoodsDetails(
						jsonobject.optString("goods_name"),
						jsonobject.optString("goods_jingle"),
						jsonobject.optString("spec_name"),
						jsonobject.optString("spec_value"),
						jsonobject.optString("goods_attr"),
						jsonobject.optString("goods_specname"),
						jsonobject.optString("goods_price"),
						jsonobject.optString("goods_marketprice"),
						jsonobject.optString("goods_costprice"),
						jsonobject.optString("goods_discount"),
						jsonobject.optString("goods_serial"),
						jsonobject.optString("transport_id"),
						jsonobject.optString("transport_title"),
						jsonobject.optString("goods_freight"),
						jsonobject.optString("goods_vat"),
						jsonobject.optString("areaid_1"),
						jsonobject.optString("areaid_2"),
						jsonobject.optString("goods_stcids"),
						jsonobject.optString("plateid_top"),
						jsonobject.optString("plateid_bottom"),
						jsonobject.optString("goods_id"),
						jsonobject.optString("goods_click"),
						jsonobject.optString("goods_collect"),
						jsonobject.optString("goods_salenum"),
						jsonobject.optString("goods_spec"),
						jsonobject.optString("goods_storage"),
						jsonobject.optString("color_id"),
						jsonobject.optString("evaluation_good_star"),
						jsonobject.optString("evaluation_count"),
						jsonobject.optString("promotion_type"),
						jsonobject.optString("promotion_price"),
						jsonobject.optString("upper_limit"));
				System.out.println((new StringBuilder("bean-->")).append(
						goodsdetails.toString()).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return goodsdetails;

	}

	public String getAreaid_1()
	{
		return areaid_1;
	}

	public String getAreaid_2()
	{
		return areaid_2;
	}

	public String getColor_id()
	{
		return color_id;
	}

	public String getEvaluation_count()
	{
		return evaluation_count;
	}

	public String getEvaluation_good_star()
	{
		return evaluation_good_star;
	}

	public String getGoods_attr()
	{
		return goods_attr;
	}

	public String getGoods_click()
	{
		return goods_click;
	}

	public String getGoods_collect()
	{
		return goods_collect;
	}

	public String getGoods_costprice()
	{
		return goods_costprice;
	}

	public String getGoods_discount()
	{
		return goods_discount;
	}

	public String getGoods_freight()
	{
		return goods_freight;
	}

	public String getGoods_id()
	{
		return goods_id;
	}

	public String getGoods_jingle()
	{
		return goods_jingle;
	}

	public String getGoods_marketprice()
	{
		return goods_marketprice;
	}

	public String getGoods_name()
	{
		return goods_name;
	}

	public String getGoods_price()
	{
		return goods_price;
	}

	public String getGoods_salenum()
	{
		return goods_salenum;
	}

	public String getGoods_serial()
	{
		return goods_serial;
	}

	public String getGoods_spec()
	{
		return goods_spec;
	}

	public String getGoods_specname()
	{
		return goods_specname;
	}

	public String getGoods_stcids()
	{
		return goods_stcids;
	}

	public String getGoods_storage()
	{
		return goods_storage;
	}

	public String getGoods_vat()
	{
		return goods_vat;
	}

	public String getPlateid_bottom()
	{
		return plateid_bottom;
	}

	public String getPlateid_top()
	{
		return plateid_top;
	}

	public String getPromotion_price()
	{
		return promotion_price;
	}

	public String getPromotion_type()
	{
		return promotion_type;
	}

	public String getSpec_name()
	{
		return spec_name;
	}

	public String getSpec_value()
	{
		return spec_value;
	}

	public String getTransport_id()
	{
		return transport_id;
	}

	public String getTransport_title()
	{
		return transport_title;
	}

	public String getUpper_limit()
	{
		return upper_limit;
	}

	public void setAreaid_1(String s)
	{
		areaid_1 = s;
	}

	public void setAreaid_2(String s)
	{
		areaid_2 = s;
	}

	public void setColor_id(String s)
	{
		color_id = s;
	}

	public void setEvaluation_count(String s)
	{
		evaluation_count = s;
	}

	public void setEvaluation_good_star(String s)
	{
		evaluation_good_star = s;
	}

	public void setGoods_attr(String s)
	{
		goods_attr = s;
	}

	public void setGoods_click(String s)
	{
		goods_click = s;
	}

	public void setGoods_collect(String s)
	{
		goods_collect = s;
	}

	public void setGoods_costprice(String s)
	{
		goods_costprice = s;
	}

	public void setGoods_discount(String s)
	{
		goods_discount = s;
	}

	public void setGoods_freight(String s)
	{
		goods_freight = s;
	}

	public void setGoods_id(String s)
	{
		goods_id = s;
	}

	public void setGoods_jingle(String s)
	{
		goods_jingle = s;
	}

	public void setGoods_marketprice(String s)
	{
		goods_marketprice = s;
	}

	public void setGoods_name(String s)
	{
		goods_name = s;
	}

	public void setGoods_price(String s)
	{
		goods_price = s;
	}

	public void setGoods_salenum(String s)
	{
		goods_salenum = s;
	}

	public void setGoods_serial(String s)
	{
		goods_serial = s;
	}

	public void setGoods_spec(String s)
	{
		goods_spec = s;
	}

	public void setGoods_specname(String s)
	{
		goods_specname = s;
	}

	public void setGoods_stcids(String s)
	{
		goods_stcids = s;
	}

	public void setGoods_storage(String s)
	{
		goods_storage = s;
	}

	public void setGoods_vat(String s)
	{
		goods_vat = s;
	}

	public void setPlateid_bottom(String s)
	{
		plateid_bottom = s;
	}

	public void setPlateid_top(String s)
	{
		plateid_top = s;
	}

	public void setPromotion_price(String s)
	{
		promotion_price = s;
	}

	public void setPromotion_type(String s)
	{
		promotion_type = s;
	}

	public void setSpec_name(String s)
	{
		spec_name = s;
	}

	public void setSpec_value(String s)
	{
		spec_value = s;
	}

	public void setTransport_id(String s)
	{
		transport_id = s;
	}

	public void setTransport_title(String s)
	{
		transport_title = s;
	}

	public void setUpper_limit(String s)
	{
		upper_limit = s;
	}

	public String toString()
	{
		return (new StringBuilder("GoodsDetails [goods_name=")).append(goods_name).append(", goods_jingle=").append(goods_jingle).append(", spec_name=").append(spec_name).append(", spec_value=").append(spec_value).append(", goods_attr=").append(goods_attr).append(", goods_specname=").append(goods_specname).append(", goods_price=").append(goods_price).append(", goods_marketprice=").append(goods_marketprice).append(", goods_costprice=").append(goods_costprice).append(", goods_discount=").append(goods_discount).append(", goods_serial=").append(goods_serial).append(", transport_id=").append(transport_id).append(", transport_title=").append(transport_title).append(", goods_freight=").append(goods_freight).append(", goods_vat=").append(goods_vat).append(", areaid_1=").append(areaid_1).append(", areaid_2=").append(areaid_2).append(", goods_stcids=").append(goods_stcids).append(", plateid_top=").append(plateid_top).append(", plateid_bottom=").append(plateid_bottom).append(", goods_id=").append(goods_id).append(", goods_click=").append(goods_click).append(", goods_collect=").append(goods_collect).append(", goods_salenum=").append(goods_salenum).append(", goods_spec=").append(goods_spec).append(", goods_storage=").append(goods_storage).append(", color_id=").append(color_id).append(", evaluation_good_star=").append(evaluation_good_star).append(", evaluation_count=").append(evaluation_count).append(", promotion_type=").append(promotion_type).append(", promotion_price=").append(promotion_price).append(", upper_limit=").append(upper_limit).append("]").toString();
	}
}
