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

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.*;

import java.util.ArrayList;
import java.util.List;


@Table(name = "HistoryBrowse")
public class HistoryBrowse extends Model
{
	@Column(name = "goodsID")
	public String goodsID;
	
	@Column(name = "goodsName")
	public String goodsName;
	
	@Column(name = "goodsPrice")
	public String goodsPrice;
	
	@Column(name = "imageURL")
	public String imageURL;

	public HistoryBrowse()
	{
	}

	public HistoryBrowse(String s, String s1, String s2, String s3)
	{
		goodsID = s;
		imageURL = s1;
		goodsName = s2;
		goodsPrice = s3;
	}

	public static boolean HistortBrowseMoreFlag(int i, int j)
	{
		return (new Select()).from(net.asiasofti.android.model.HistoryBrowse.class).execute().size() - i * j > 0;
	}

	public static void deleteAll()
	{
		(new Delete()).from(net.asiasofti.android.model.HistoryBrowse.class).execute();
	}

	public static void deleteID(String s)
	{
		(new Delete()).from(net.asiasofti.android.model.HistoryBrowse.class).where("goodsID = ?", new Object[] {
			s
		}).execute();
	}

	public static void historyBrowseSava(GoodsList goodslist)
	{
		HistoryBrowse historybrowse = new HistoryBrowse();
		historybrowse.goodsID = goodslist.getGoods_id();
		historybrowse.goodsName = goodslist.getGoods_name();
		historybrowse.goodsPrice = goodslist.getGoods_price();
		historybrowse.imageURL = goodslist.getGoods_image_url();
		historybrowse.save();
	}

	public static void historyBrowseSava2(GoodsList goodslist)
	{
		From from = (new Select()).from(net.asiasofti.android.model.HistoryBrowse.class);
		Object aobj[] = new Object[1];
		aobj[0] = goodslist.getGoods_id();
		if (from.where("goodsID = ?", aobj).execute().size() <= 0)
		{
			ArrayList arraylist = (ArrayList) (new Select()).from(net.asiasofti.android.model.HistoryBrowse.class).execute();
			if (arraylist.size() >= 30)
				deleteID(((HistoryBrowse)arraylist.get(0)).goodsID);
			HistoryBrowse historybrowse = new HistoryBrowse();
			historybrowse.goodsID = goodslist.getGoods_id();
			historybrowse.goodsName = goodslist.getGoods_name();
			historybrowse.goodsPrice = goodslist.getGoods_price();
			historybrowse.imageURL = goodslist.getGoods_image_url();
			historybrowse.save();
		}
	}

	public static List searchQueryList(int i, int j)
	{
		int k;
		if (i == 1)
			k = 0;
		else
			k = -1 + (i * j - j);
		return (new Select()).from(net.asiasofti.android.model.HistoryBrowse.class).limit((new StringBuilder(String.valueOf(k))).append(",").append(j).toString()).execute();
	}

	public String toString()
	{
		return (new StringBuilder("HistoryBrowse [goodsID=")).append(goodsID).append(", imageURL=").append(imageURL).append(", goodsName=").append(goodsName).append(", goodsPrice=").append(goodsPrice).append("]").toString();
	}
}
