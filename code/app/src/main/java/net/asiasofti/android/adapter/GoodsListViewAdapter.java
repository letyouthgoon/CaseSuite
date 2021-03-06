 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.adapter;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyBackAsynaTask;
import net.asiasofti.android.model.GoodsList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GoodsListViewAdapter extends BaseAdapter
{
	private static final String TAG = GoodsListViewAdapter.class.getSimpleName();

	class ViewHolder
	{

		ImageView imageGoodsPic;
		ImageView imageGroupPricePromotionType;
		ImageView imageXianShiPricePromotionType;
		LinearLayout imageXingJi;
		TextView textEvaluationCount;
		TextView textGoodsName;
		TextView textGoodsPrice;


		ViewHolder()
		{
			
			super();
		}
	}


	private Context context;
	private ArrayList goodsLists;
	private LayoutInflater inflater;

	public GoodsListViewAdapter(Context context1)
	{
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public int getCount()
	{
		if (goodsLists == null)
			return 0;
		else
			return goodsLists.size();
	}

	public ArrayList getGoodsLists()
	{
		return goodsLists;
	}

	public Object getItem(int i)
	{
		return goodsLists.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		GoodsList goodslist;
		ViewHolder viewholder;
		goodslist = (GoodsList)goodsLists.get(i);
		if (view != null)
		{
			viewholder = (ViewHolder)view.getTag();
		}
		else
		{
			viewholder = new ViewHolder();
		}
		view = inflater.inflate(R.layout.listivew_goods_item, null);
		
		viewholder.imageGoodsPic = (ImageView)view.findViewById(R.id.imageGoodsPic);
		viewholder.textGoodsName = (TextView)view.findViewById(R.id.textGoodsName);
		viewholder.textGoodsPrice = (TextView)view.findViewById(R.id.textGoodsPrice);
		viewholder.imageXingJi = (LinearLayout)view.findViewById(R.id.imageXingJi);
		viewholder.textEvaluationCount = (TextView)view.findViewById(R.id.textEvaluationCount);
		viewholder.imageGroupPricePromotionType = (ImageView)view.findViewById(R.id.imageGroupPricePromotionType);
		viewholder.imageXianShiPricePromotionType = (ImageView)view.findViewById(R.id.imageXianShiPricePromotionType);
		Log.i(TAG, "imageGroupPricePromotionType '" + viewholder.imageGroupPricePromotionType);
	
		for(int k=0;k<Integer.parseInt(goodslist.getEvaluation_good_star());k++)
		{

			ImageView imageview = new ImageView(context);
			imageview.setBackgroundResource(0x7f020091);
			viewholder.imageXingJi.addView(imageview);
		}
		view.setTag(viewholder);


		(new MyBackAsynaTask(goodslist.getGoods_image_url(), viewholder.imageGoodsPic)).execute(new String[0]);
		viewholder.textGoodsName.setText(goodslist.getGoods_name());
		viewholder.textGoodsPrice.setText((new StringBuilder("￥")).append(goodslist.getGoods_price()).toString());
		viewholder.textEvaluationCount.setText((new StringBuilder("(")).append(goodslist.getEvaluation_count()).append("次)").toString());
		if (Boolean.valueOf(goodslist.getGroup_flag()).booleanValue())
			viewholder.imageGroupPricePromotionType.setBackgroundResource(R.drawable.product_price_tuan);
		else
			viewholder.imageGroupPricePromotionType.setBackgroundResource(0);
		if (Boolean.valueOf(goodslist.getXianshi_flag()).booleanValue())
		{
			viewholder.imageXianShiPricePromotionType.setBackgroundResource(0x7f02007c);
			return view;
		} else
		{
			viewholder.imageXianShiPricePromotionType.setBackgroundResource(0);
			return view;
		}
	}

	public void setGoodsLists(ArrayList arraylist)
	{
		goodsLists = arraylist;
	}
}
