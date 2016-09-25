package net.asiasofti.android.adapter;



import java.util.List;
import java.util.Map;

import net.asiasofti.android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DiaListViewAdapter extends BaseAdapter{
	Context context;
	LayoutInflater layoutInflater;
	 List<Map<String, String>> list;
	public DiaListViewAdapter(Context context,List<Map<String, String>> list){
		this.context=context;
		layoutInflater=layoutInflater.from(context);
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView==null) {
			convertView=layoutInflater.inflate(R.layout.dialog_item, null);
			holder=new Holder();
			holder.textView=(TextView) convertView.findViewById(R.id.dia_text);
			convertView.setTag(holder);
		}else {
			holder=(Holder) convertView.getTag();
		}
		holder.textView.setText(list.get(position).get("1"));
		// TODO Auto-generated method stub
		return convertView;
	}
	class Holder{
		TextView textView;
	}

}
