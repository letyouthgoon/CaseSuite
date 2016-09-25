package net.asiasofti.android.adapter;

import android.content.Context;
import android.text.Html;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyBackAsynaTask;
import net.asiasofti.android.model.ManSongRules;

public class ManSongRulesListViewAdapter extends BaseAdapter
{
	class ViewHolder
	{

		ImageView imageRules;
		TextView textTitleName;

		ViewHolder()
		{
			super();
		}
	}


	private Context context;
	private LayoutInflater inflater;
	private ArrayList mRules;

	public ManSongRulesListViewAdapter(Context context1)
	{
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public int getCount()
	{
		if (mRules == null)
			return 0;
		else
			return mRules.size();
	}

	public Object getItem(int i)
	{
		return mRules.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		ViewHolder viewholder;
		ManSongRules mansongrules;
		if (view == null)
		{
			view = inflater.inflate(R.layout.mansong_rules_listview_item, null);
			viewholder = new ViewHolder();
			viewholder.imageRules = (ImageView)view.findViewById(R.id.imageRules);
			viewholder.textTitleName = (TextView)view.findViewById(R.id.textTitleName);
			view.setTag(viewholder);
		} else
		{
			viewholder = (ViewHolder)view.getTag();
		}
		mansongrules = (ManSongRules)mRules.get(i);
		viewholder.textTitleName.setText(Html.fromHtml((new StringBuilder("单笔订单满<font color='#FF3300'>")).append(mansongrules.getPrice()).append("</font>元 ， 立减现金<font color='#339900'>").append(mansongrules.getDiscount()).append("</font>元 ， 送礼品").toString()));
		(new MyBackAsynaTask(mansongrules.getGoods_image_url(), viewholder.imageRules)).execute(new String[0]);
		return view;
	}

	public ArrayList getmRules()
	{
		return mRules;
	}

	public void setmRules(ArrayList arraylist)
	{
		mRules = arraylist;
	}
}
