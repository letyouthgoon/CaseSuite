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
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.model.InvoiceList;
import net.asiasofti.android.ui.type.BuyStep1Activity;

public class InvoiceListViewAdapter extends BaseAdapter
{
	class ViewHolder
	{

		TextView textContext;

		ViewHolder()
		{
	
			super();
		}
	}


	public InvoiceList bean;
	private BuyStep1Activity buyStep1Activity;
	private Context context;
	private ArrayList datas;
	private LayoutInflater inflater;
	public int jilu;

	public InvoiceListViewAdapter(Context context1, BuyStep1Activity buystep1activity)
	{
		jilu = -1;
		context = context1;
		inflater = LayoutInflater.from(context1);
		buyStep1Activity = buystep1activity;
	}

	public int getCount()
	{
		if (datas == null)
			return 0;
		else
			return datas.size();
	}

	public ArrayList getDatas()
	{
		return datas;
	}

	public Object getItem(int i)
	{
		return datas.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(final int position, View view, ViewGroup viewgroup)
	{
		final ViewHolder holder;
		InvoiceList invoicelist;
		Drawable drawable;
		final Drawable drawableYES;
		if (view == null)
		{
			view = inflater.inflate(R.layout.listivew_invoice_item, null);
			holder = new ViewHolder();
			holder.textContext = (TextView)view.findViewById(R.id.textContext);
			view.setTag(holder);
		} else
		{
			holder = (ViewHolder)view.getTag();
		}
		invoicelist = (InvoiceList)datas.get(position);
		holder.textContext.setText((new StringBuilder(String.valueOf(invoicelist.getInv_title()))).append("\t").append(invoicelist.getInv_content()).toString());
		drawable = context.getResources().getDrawable(R.drawable.android_click_no_focus);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		drawableYES = context.getResources().getDrawable(R.drawable.android_click_yes_focus);
		drawableYES.setBounds(0, 0, drawableYES.getMinimumWidth(), drawableYES.getMinimumHeight());
		if (jilu != -1 && jilu == position)
		{
			jilu = position;
			bean = invoicelist;
			holder.textContext.setCompoundDrawables(drawableYES, null, null, null);
		} else
		{
			holder.textContext.setCompoundDrawables(drawable, null, null, null);
		}
		holder.textContext.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view1)
			{
				jilu = position;
				holder.textContext.setCompoundDrawables(drawableYES, null, null, null);
				notifyDataSetChanged();
				buyStep1Activity.textAddInvoice.setChecked(false);
				buyStep1Activity.linearlayoutNewInvInfo.setVisibility(View.GONE);
			}

		
		});
		return view;
	}

	public void setDatas(ArrayList arraylist)
	{
		datas = arraylist;
	}

}
