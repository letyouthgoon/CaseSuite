package net.asiasofti.android.adapter;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.List;

import net.asiasofti.android.R;
import net.asiasofti.android.common.MyBackAsynaTask;
import net.asiasofti.android.model.HistoryBrowse;

public class HistoryBrowseListViewAdapter extends BaseAdapter
{
	public class ViewHolder
	{

		ImageView imageFacoritesDeleteButton;
		ImageView imageGoodsPic;
		TextView textGoodsName;
		TextView textGoodsPrice;

		public ViewHolder(){

			super();
		}
	}


	private Context context;
	private LayoutInflater inflater;
	private boolean isCheckEdit;
	private List list;

	public HistoryBrowseListViewAdapter(Context context1)
	{
		isCheckEdit = false;
		context = context1;
		inflater = LayoutInflater.from(context1);
	}

	public int getCount()
	{
		if (list == null)
			return 0;
		else
			return list.size();
	}

	public Object getItem(int i)
	{
		return list.get(i);
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public List getList()
	{
		return list;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		ViewHolder viewholder;
		HistoryBrowse historybrowse;
		if (view == null)
		{
			view = inflater.inflate(R.layout.listivew_favorites_item, null);
			viewholder = new ViewHolder();
			viewholder.imageGoodsPic = (ImageView)view.findViewById(R.id.imageGoodsPic) ;
			viewholder.imageFacoritesDeleteButton = (ImageView)view.findViewById(R.id.imageFacoritesDeleteButton);
			viewholder.textGoodsName = (TextView)view.findViewById(R.id.textGoodsName);
			viewholder.textGoodsPrice = (TextView)view.findViewById(R.id.textGoodsPrice);
			view.setTag(viewholder);
		} else
		{
			viewholder = (ViewHolder)view.getTag();
		}
		historybrowse = (HistoryBrowse)list.get(i);
		viewholder.textGoodsName.setText(historybrowse.goodsName);
		viewholder.textGoodsPrice.setText((new StringBuilder("￥")).append(historybrowse.goodsPrice).toString());
		(new MyBackAsynaTask(historybrowse.imageURL, viewholder.imageGoodsPic)).execute(new String[0]);
		return view;
	}

	public boolean isCheckEdit()
	{
		return isCheckEdit;
	}

	public void setCheckEdit(boolean flag)
	{
		isCheckEdit = flag;
	}

	public void setList(List list1)
	{
		list = list1;
	}
}
