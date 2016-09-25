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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import net.asiasofti.android.R;
import net.asiasofti.android.handler.ImageLoader;

public class GoodsDetailsGalleryAdapter extends BaseAdapter
{
	class ViewHolder
	{

		ImageView imagePic;

		ViewHolder()
		{
			super();
		}
	}


	private Context context;
	private String goods_image[];
	private LayoutInflater inflater;

	public GoodsDetailsGalleryAdapter(Context context1)
	{
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public int getCount()
	{
		if (goods_image == null)
			return 0;
		else
			return goods_image.length;
	}

	public String[] getGoods_image()
	{
		return goods_image;
	}

	public Object getItem(int i)
	{
		return Integer.valueOf(0);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		final ViewHolder holder;
		if (view == null)
		{
			view = inflater.inflate(R.layout.gallery_goods_item, null);
			holder = new ViewHolder();
			holder.imagePic = (ImageView)view.findViewById(R.id.imagePic);
			view.setTag(holder);
		} else
		{
			holder = (ViewHolder)view.getTag();
		}
		ImageLoader.getInstance().asyncLoadBitmap(goods_image[i], new net.asiasofti.android.handler.ImageLoader.ImageCallback() {

			

			public void imageLoaded(Bitmap bitmap, String s)
			{
				if (bitmap != null)
				{
					holder.imagePic.setBackgroundDrawable(new BitmapDrawable(bitmap));
					return;
				} else
				{
					holder.imagePic.setBackgroundResource(R.drawable.category_icon_123);
					return;
				}
			}

			
		
		});
		return view;
	}

	public void setGoods_image(String as[])
	{
		goods_image = as;
	}
}
