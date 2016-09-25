package net.asiasofti.android.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.asiasofti.android.R;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.en.Classify;
import net.asiasofti.android.en.Classify_O;
import net.asiasofti.android.en.HttpInputStream;
import net.asiasofti.android.en.Order;
import net.asiasofti.android.ui.type.GoodsTabActivity;
import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	RelativeLayout videoListItem;
	LinearLayout layout;
	LinearLayout mList = null;
	List<Classify> list;
   int text=0;
	public HomeAdapter(Context context, List<Classify> list) {
		this.context = context;
		inflater = inflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		if (list == null)
			return 0;
		else
			return list.size();
	}

	@Override
	public Object getItem(int arg0) {
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
		// TODO Auto-generated method stub
		
		convertView = inflater.inflate(R.layout.main_c, null);
		TextView textView = (TextView) convertView.findViewById(R.id.c_text);
		TextPaint paint=textView.getPaint();
		paint.setFakeBoldText(true);
		textView.setText(list.get(position).getGc_name());
		JSON(position);
		layout = (LinearLayout) convertView.findViewById(R.id.linea_m);
		int num = 0;
		if (wlist.size() >= 9) {
			num = 9;
		} else {
			num = wlist.size();
		}
		for (int i = 0; i < num; i++) {
			if (i % 3 == 0) {
				if (i != 0) {
					layout.addView(mList);
				}
				mList = new LinearLayout(context);
			}
			videoListItem = (RelativeLayout) LayoutInflater.from(context)
					.inflate(R.layout.txt, null);
			LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
			params.weight = 1;
			videoListItem.setLayoutParams(params);
			videoListItem.setFocusable(true);
			TextView item = (TextView) videoListItem
					.findViewById(R.id.textview);
			item.setText(wlist.get(i).get("gc_name"));
			item.setOnClickListener(new TextViewOnClick(position,i));
			mList.addView(videoListItem);
			text=text+1;
		}
		if (mList != null) {
			layout.addView(mList);
		}
		if (wlist.size() > 9) {
			Click(position, convertView);
		}
		return convertView;
	}
	// 这里是下拉和缩回的监听
	public void Click(final int position, final View convertView) {
		
		convertView.findViewById(R.id.c_rela).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub\
						System.out.println("最后一行中的view:"+mList.getChildCount());
						System.out.println(position);
						if (!(mList.getChildCount() == 3)) {
							JSON(position);
							LinearLayout layout = (LinearLayout) convertView
									.findViewById(R.id.linea_m);
							layout.removeAllViews();
							for (int i = 0; i < 9; i++) {
								if (i % 3 == 0) {
									if (i != 0) {
										layout.addView(mList);
									}
									mList = new LinearLayout(context);
								}
								LayoutParams params = new LayoutParams(0,
										LayoutParams.WRAP_CONTENT);
								params.weight = 1;
								videoListItem.setLayoutParams(params);
								videoListItem = (RelativeLayout) LayoutInflater
										.from(context).inflate(R.layout.txt,
												null);
								videoListItem.setFocusable(true);
								TextView item = (TextView) videoListItem
										.findViewById(R.id.textview);
								item.setText(wlist.get(i).get("gc_name"));
								item.setOnClickListener(new TextViewOnClick(position,i));
								mList.addView(videoListItem);
								notifyDataSetChanged();
							}
							if (mList != null) {
								layout.addView(mList);
							
							}
						} else {
							JSON(position);
							LinearLayout layout = (LinearLayout) convertView
									.findViewById(R.id.linea_m);
							layout.removeAllViews();
							for (int i = 0; i < wlist.size(); i++) {
								if (i % 3 == 0) {
									if (i != 0) {
										layout.addView(mList);
									}
									mList = new LinearLayout(context);
								}
								videoListItem = (RelativeLayout) LayoutInflater
										.from(context).inflate(R.layout.txt,
												null);
								LayoutParams params = new LayoutParams(0,
										LayoutParams.WRAP_CONTENT);
								params.weight = 1;
								videoListItem.setLayoutParams(params);
								videoListItem.setFocusable(true);
								TextView item = (TextView) videoListItem
										.findViewById(R.id.textview);
								item.setOnClickListener(new TextViewOnClick(position,i));
								item.setText(wlist.get(i).get("gc_name"));
								mList.addView(videoListItem);
							}
							if (mList != null) {
								layout.addView(mList);
							}
						}

					}

				});
	}

	private List<Map<String, String>> wlist = new ArrayList<Map<String, String>>();
	private Map<String, String> map = new HashMap<String, String>();

	public void JSON(int position) {
		try {
			// 这里
			String URL = "{" + "\"" + "data" + "\"" + ":"
					+ list.get(position).getClass_list() + "}";
			if (!list.get(position).getClass_list().isEmpty()) {
				wlist.clear();
				JSONObject jsonObject = new JSONObject(URL);
				JSONArray array = jsonObject.optJSONArray("data"); // 这里得到json数组
				for (int j = 0; j < array.length(); j++) {
					map = new HashMap<String, String>();
					JSONObject object = array.optJSONObject(j);
					map.put("gc_id", object.getString("gc_id"));
					map.put("gc_name", object.getString("gc_name"));
					wlist.add(map);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	class TextViewOnClick implements OnClickListener {
		int position;
        int i;
		public TextViewOnClick(int position,int i) {
			// 构造方法里面就可以传你需要的东西进来了 调用构造还是监听 构造一起调用
			this.position=position;
			this.i=i;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			JSON(position);
			Log.e("Tag", "监听");
			Intent intent = new Intent();
			intent.setClass(context, GoodsTabActivity.class);
			intent.putExtra("gc_id", wlist.get(i).get("gc_id"));
			intent.putExtra("gc_name", "所有商品");
			intent.putExtra("keyword", "");
			context.startActivity(intent);
		}

	}
}
