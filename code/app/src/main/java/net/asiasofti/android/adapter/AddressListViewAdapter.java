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
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.model.AddressList;

public class AddressListViewAdapter extends BaseAdapter
{
	class ViewHolder
	{

		TextView textAddressAddress;
		TextView textAddressName;
		TextView textAddressPhone;
		
		ViewHolder()
		{
			
			super();
		}
	}


	private ArrayList addressLists;
	private Context context;
	private LayoutInflater inflater;

	public AddressListViewAdapter(Context context1)
	{
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public ArrayList getAddressLists()
	{
		return addressLists;
	}

	public int getCount()
	{
		if (addressLists == null)
			return 0;
		else
			return addressLists.size();
	}

	public Object getItem(int i)
	{
		return addressLists.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		ViewHolder viewholder;
		AddressList addresslist;
		if (view == null)
		{
			view = inflater.inflate(R.layout.listview_address_item, null);
			viewholder = new ViewHolder();
			viewholder.textAddressName = (TextView)view.findViewById(R.id.textAddressName);
			viewholder.textAddressPhone = (TextView)view.findViewById(R.id.textAddressPhone);
			viewholder.textAddressAddress = (TextView)view.findViewById(R.id.textAddressAddress);
			view.setTag(viewholder);
		} else
		{
			viewholder = (ViewHolder)view.getTag();
		}
		addresslist = (AddressList)addressLists.get(i);
		viewholder.textAddressName.setText(addresslist.getTrue_name());
		viewholder.textAddressPhone.setText(addresslist.getMob_phone());
		viewholder.textAddressAddress.setText(addresslist.getAddress());
		return view;
	}

	public void setAddressLists(ArrayList arraylist)
	{
		addressLists = arraylist;
	}
}
