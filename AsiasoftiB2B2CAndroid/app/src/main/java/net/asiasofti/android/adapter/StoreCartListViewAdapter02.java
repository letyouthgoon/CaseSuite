package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyBackAsynaTask;
import net.asiasofti.android.model.CartList;

public class StoreCartListViewAdapter02 extends BaseAdapter
{
	public class ViewHolder
	{

		ImageView imageGoodsPic;
		ImageView imageZengIco;
		TextView textGoodsNUM;
		TextView textGoodsName;
		TextView textGoodsPrice;
	

		public ViewHolder()
		{
			super();
		}
	}


	private Context ctx;
	private ArrayList goodList;
	private LayoutInflater inflater;
	public ViewHolder vh;

	public StoreCartListViewAdapter02(Context context)
	{
		ctx = context;
		inflater = LayoutInflater.from(context);
	}

	public int getCount()
	{
		if (goodList == null)
			return 0;
		else
			return goodList.size();
	}

	public ArrayList getGoodList()
	{
		return goodList;
	}

	public Object getItem(int i)
	{
		return goodList.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		CartList cartlist;
		if (view == null)
		{
			view = inflater.inflate(R.layout.listivew_store_cart_item2, null);
			vh = new ViewHolder();
			vh.imageGoodsPic = (ImageView)view.findViewById(R.id.imageGoodsPic);
			vh.textGoodsName = (TextView)view.findViewById(R.id.textGoodsName);
			vh.textGoodsPrice = (TextView)view.findViewById(R.id.textGoodsPrice);
			vh.textGoodsNUM = (TextView)view.findViewById(R.id.textGoodsNUM);
			vh.imageZengIco = (ImageView)view.findViewById(R.id.imageZengIco);
			view.setTag(vh);
		} else
		{
			vh = (ViewHolder)view.getTag();
		}
		cartlist = (CartList)goodList.get(i);
		vh.textGoodsName.setText(cartlist.getGoods_name());
		vh.textGoodsPrice.setText((new StringBuilder("￥")).append(cartlist.getGoods_price()).toString());
		vh.textGoodsNUM.setText(cartlist.getGoods_num());
		if (cartlist.getPremiums().equals("true"))
			vh.imageZengIco.setVisibility(View.VISIBLE);
		else
			vh.imageZengIco.setVisibility(View.GONE);
		(new MyBackAsynaTask(cartlist.getGoods_image_url(), vh.imageGoodsPic)).execute(new String[0]);
		return view;
	}

	public void setGoodList(ArrayList arraylist)
	{
		goodList = arraylist;
	}
}
