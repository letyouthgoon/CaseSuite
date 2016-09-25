package net.asiasofti.android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.handler.ImageLoader;
import net.asiasofti.android.model.OrderGoodsList;

public class OrderItemListViewAdapter extends BaseAdapter
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
	private ArrayList goodsDatas;
	private LayoutInflater inflater;
	public ViewHolder vh;

	public OrderItemListViewAdapter(Context context)
	{
		ctx = context;
		inflater = LayoutInflater.from(context);
	}

	public Bitmap getBitmap(Bitmap bitmap, int i)
	{
		int j = bitmap.getWidth();
		int k = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float f = (float)i / (float)j;
		matrix.postScale(f, f);
		return Bitmap.createBitmap(bitmap, 0, 0, j, k, matrix, true);
	}

	public int getCount()
	{
		if (goodsDatas == null)
			return 0;
		else
			return goodsDatas.size();
	}

	public ArrayList getGoodsDatas()
	{
		return goodsDatas;
	}

	public Object getItem(int i)
	{
		return goodsDatas.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		OrderGoodsList ordergoodslist;
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
		ordergoodslist = (OrderGoodsList)goodsDatas.get(i);
		vh.textGoodsName.setText(ordergoodslist.getGoods_name());
		vh.textGoodsPrice.setText((new StringBuilder("￥")).append(ordergoodslist.getGoods_price()).toString());
		vh.textGoodsNUM.setText(ordergoodslist.getGoods_num());
		ImageLoader.getInstance().asyncLoadBitmap(ordergoodslist.getGoods_image_url(), new net.asiasofti.android.handler.ImageLoader.ImageCallback() {


			public void imageLoaded(Bitmap bitmap, String s)
			{
				if (bitmap != null)
				{
					Bitmap bitmap1 = getBitmap(bitmap, bitmap.getWidth());
					vh.imageGoodsPic.setBackgroundDrawable(new BitmapDrawable(bitmap1));
					return;
				} else
				{
					vh.imageGoodsPic.setBackgroundResource(R.drawable.category_icon_123);
					return;
				}
			}

		});
		return view;
	}

	public void setGoodsDatas(ArrayList arraylist)
	{
		goodsDatas = arraylist;
	}
}
