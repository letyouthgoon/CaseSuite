package net.asiasofti.android.ui.mr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.DiaListViewAdapter;
import net.asiasofti.android.ui.mystore.MyStoreActivity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CityActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private ImageView imageView;
	View view;
	Dialog dialog;
	String name;
	int a;
	private ListView listView, listViewFour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.te);
		imageView = (ImageView) findViewById(R.id.imageBack);
		imageView.setOnClickListener(this);
		view = LayoutInflater.from(CityActivity.this).inflate(R.layout.dialog,
				null);
		dialog = new Dialog(CityActivity.this, R.style.MyDialog);
		listView = (ListView) view.findViewById(R.id.dia_list_one);
		listViewFour = (ListView) view.findViewById(R.id.dia_list_two);
		listView.setOverScrollMode(View.OVER_SCROLL_NEVER);// 顶部和底部渐变颜色
		listViewFour.setOverScrollMode(View.OVER_SCROLL_NEVER);
		listViewFour.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (name.equals("石家庄")) {
					if (a == 0) {
						if (arg2 == 0) {
							MyStoreActivity.a = list1.get(arg2).get("1");
						} else if (arg2 == 1) {
							MyStoreActivity.a = list1.get(arg2).get("1");
						}
					} else if (a == 1) {
						if (arg2 == 0) {
							MyStoreActivity.a = list2.get(arg2).get("1");
						} else if (arg2 == 1) {
							MyStoreActivity.a = list2.get(arg2).get("1");
						}
					}
				} else if (name.equals("辛集市")) {
					if (a == 0) {
						if (arg2 == 0) {
							
							MyStoreActivity.a = list3.get(arg2).get("1");
						} else if (arg2 == 1) {
							MyStoreActivity.a = list3.get(arg2).get("1");
						}
					} else if (a == 1) {
						if (arg2 == 0) {
							MyStoreActivity.a = list4.get(arg2).get("1");
						} else if (arg2 == 1) {
							MyStoreActivity.a = list4.get(arg2).get("1");
						}

					}
				} else if (name.equals("海淀区")) {
					if (a == 0) {
						if (arg2 == 0) {
							MyStoreActivity.a = list5.get(arg2).get("1");
						} else if (arg2 == 1) {
							MyStoreActivity.a = list5.get(arg2).get("1");
						}
					} else if (a == 1) {
						if (arg2 == 0) {
							MyStoreActivity.a = list6.get(arg2).get("1");
						} else if (arg2 == 1) {
							MyStoreActivity.a = list6.get(arg2).get("1");
						}

					}
				} else if (name.equals("朝阳区")) {
					if (a == 0) {
						if (arg2 == 0) {
							MyStoreActivity.a = list7.get(arg2).get("1");
						} else if (arg2 == 1) {
							MyStoreActivity.a = list7.get(arg2).get("1");
						}
					} else if (a == 1) {
						if (arg2 == 0) {
							MyStoreActivity.a = list8.get(arg2).get("1");
						} else if (arg2 == 1) {
							MyStoreActivity.a = list8.get(arg2).get("1");
						}

					}
				}

				dialog.dismiss();
				CityActivity.this.finish();

			}
		});
		listView.setOnItemClickListener(this);
		dialog.setContentView(view);
		final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
			LayoutInflater inflater;

			// 设置组视图的图片
			int[] logos = new int[] { R.drawable.www, R.drawable.www,
					R.drawable.www };
			// 设置组视图的显示文字
			private String[] generalsTypes = new String[] { "河北省", "北京市" };
			private String[][] generals = new String[][] { { "石家庄", "辛集市"},
					{ "海淀区", "朝阳区","东城区","石景山区","西城区","丰台区","宣武区","崇文区","顺义区","怀柔区","密云县","延庆县","昌平区","平谷区","文头沟区","房山区","通州区	"}, };

			// 子视图图片
//			public int[][] generallogos = new int[][] {
//					{ R.drawable.www, R.drawable.www, R.drawable.www,
//							R.drawable.www, R.drawable.www, R.drawable.www },
//					{ R.drawable.www, R.drawable.www, R.drawable.www,
//							R.drawable.www, R.drawable.www, R.drawable.www },
//					{ R.drawable.www, R.drawable.www, R.drawable.www,
//							R.drawable.www, R.drawable.www } };

			// 自己定义一个获得文字信息的方法
			TextView getTextView() {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT, 64);
				TextView textView = new TextView(CityActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL); 
				textView.setTextSize(20);
				textView.setTextColor(Color.BLACK);
				return textView;
			}

			// 重写ExpandableListAdapter中的各个方法
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				inflater = inflater.from(CityActivity.this);
				return generalsTypes.length;
			}

			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return generalsTypes[groupPosition];
			}
 
			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return generals[groupPosition].length;
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return generals[groupPosition][childPosition];
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				// LinearLayout ll = new LinearLayout(CityActivity.this);
				// ll.setOrientation(0);
				// ImageView logo = new ImageView(CityActivity.this);
				// logo.setImageResource(logos[groupPosition]);
				// logo.setPadding(50, 0, 0, 0);
				// ll.addView(logo);
				// TextView textView = getTextView();
				// textView.setTextColor(Color.BLACK);
				// textView.setText(getGroup(groupPosition).toString());
				// textView.setPadding(70, 5, 0, 5);
				// ll.addView(textView);
				convertView = inflater.inflate(R.layout.city_item, null);
				TextView textView = (TextView) convertView
						.findViewById(R.id.city_text);
				textView.setText(getGroup(groupPosition).toString());

				return convertView;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub

				// LinearLayout ll = new LinearLayout(CityActivity.this);
				// ll.setOrientation(0);
				// ImageView generallogo = new ImageView(
				// CityActivity.this);
				// generallogo
				// .setImageResource(generallogos[groupPosition][childPosition]);
				// ll.addView(generallogo);
				// TextView textView = getTextView();
				// textView.setText(getChild(groupPosition, childPosition)
				// .toString());
				// textView.setPadding(40, 7, 0, 7);
				// ll.addView(textView);
				convertView = inflater.inflate(R.layout.dialog_item_one, null);
				TextView textView = (TextView) convertView
						.findViewById(R.id.city_text_o);
				textView.setText(getChild(groupPosition, childPosition)
						.toString());
				return convertView;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list);
		expandableListView.setGroupIndicator(null);
		expandableListView.setAdapter(adapter);

		// 设置item点击的监听器
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Toast.makeText(
						CityActivity.this,
						"你点击了" + adapter.getChild(groupPosition, childPosition),
						Toast.LENGTH_SHORT).show();
				name = null;
				a = groupPosition;
				name = (String) adapter.getChild(groupPosition, childPosition);
				Data();
				Datas();
				dialog.show();
				handler.sendEmptyMessage(1);
				return false;
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageBack:
			this.finish();
			break;
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (name.equals("石家庄")) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, lists);
				listView.setAdapter(adapter);
			} else if (name.equals("辛集市")) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, listx);
				listView.setAdapter(adapter);
			} else if (name.equals("海淀区")) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, listh);
				listView.setAdapter(adapter);
			} else if (name.equals("朝阳区")) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, listc);
				listView.setAdapter(adapter);
			}
			System.out.println("777");
		};
	};
	private List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
	private Map<String, String> maps = new HashMap<String, String>();
	private List<Map<String, String>> listx = new ArrayList<Map<String, String>>();
	private Map<String, String> mapx = new HashMap<String, String>();
	private List<Map<String, String>> listh = new ArrayList<Map<String, String>>();
	private Map<String, String> maph = new HashMap<String, String>();
	private List<Map<String, String>> listc = new ArrayList<Map<String, String>>();
	private Map<String, String> mapc = new HashMap<String, String>();

	public void Data() {

		maps = new HashMap<String, String>();
		maps.put("1", "平山县");
		lists.add(maps);

		maps = new HashMap<String, String>();
		maps.put("1", "深泽县");
		lists.add(maps);
		maps = new HashMap<String, String>();
		maps.put("1", "陆集县");
		lists.add(maps);
		maps = new HashMap<String, String>();
		maps.put("1", "正定县");
		lists.add(maps);
		maps = new HashMap<String, String>();
		maps.put("1", "行唐县");
		lists.add(maps);
		maps = new HashMap<String, String>();
		maps.put("1", "灵寿县");
		lists.add(maps);
		maps = new HashMap<String, String>();
		maps.put("1", "赵 县");
		lists.add(maps);
		maps = new HashMap<String, String>();
		maps.put("1", "无极县");
		lists.add(maps);
		maps = new HashMap<String, String>();
		maps.put("1", "元氏县");
		lists.add(maps);
		maps = new HashMap<String, String>();
		maps.put("1", "赞皇县");
		lists.add(maps);

		mapx = new HashMap<String, String>();
		mapx.put("1", "辛集镇");
		listx.add(mapx);

		mapx = new HashMap<String, String>();
		mapx.put("1", "张古庄镇");
		listx.add(mapx);

		maph = new HashMap<String, String>();
		maph.put("1", "中关村");
		listh.add(maph);

		maph = new HashMap<String, String>();
		maph.put("1", "上地");
		listh.add(maph);

		mapc = new HashMap<String, String>();
		mapc.put("1", "来广营");
		listc.add(mapc);
		mapc = new HashMap<String, String>();
		mapc.put("1", "望京");
		listc.add(mapc);
	}

	private List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
	private Map<String, String> map1 = new HashMap<String, String>();
	private List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
	private Map<String, String> map2 = new HashMap<String, String>();
	private List<Map<String, String>> list3 = new ArrayList<Map<String, String>>();
	private Map<String, String> map3 = new HashMap<String, String>();
	private List<Map<String, String>> list4 = new ArrayList<Map<String, String>>();
	private Map<String, String> map4 = new HashMap<String, String>();
	private List<Map<String, String>> list5 = new ArrayList<Map<String, String>>();
	private Map<String, String> map5 = new HashMap<String, String>();
	private List<Map<String, String>> list6 = new ArrayList<Map<String, String>>();
	private Map<String, String> map6 = new HashMap<String, String>();
	private List<Map<String, String>> list7 = new ArrayList<Map<String, String>>();
	private Map<String, String> map7 = new HashMap<String, String>();
	private List<Map<String, String>> list8 = new ArrayList<Map<String, String>>();
	private Map<String, String> map8 = new HashMap<String, String>();

	public void Datas() {

		map1 = new HashMap<String, String>();
		map1.put("1", "平山镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "温塘镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "南甸镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "岗南镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "古月镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "下愧镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "孟家镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "小觉镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "下口镇");
		list1.add(map1);
		map1 = new HashMap<String, String>();
		map1.put("1", "宅北乡");
		list1.add(map1);

		map2 = new HashMap<String, String>();
		map2.put("1", "嘉泽庄");
		list2.add(map2);
		map2 = new HashMap<String, String>();
		map2.put("1", "李家庄");
		list2.add(map2);

		map3 = new HashMap<String, String>();
		map3.put("1", "新城镇");
		list3.add(map3);
		map3 = new HashMap<String, String>();
		map3.put("1", "马道村");
		list3.add(map3);

		map4 = new HashMap<String, String>();
		map4.put("1", "新房村");
		list4.add(map4);
		map4 = new HashMap<String, String>();
		map4.put("1", "方家村");
		list4.add(map4);

		map5 = new HashMap<String, String>();
		map5.put("1", "陆集县");
		list5.add(map5);
		map5 = new HashMap<String, String>();
		map5.put("1", "深泽县");
		list5.add(map5);

		map6 = new HashMap<String, String>();
		map6.put("1", "小璜镇");
		list6.add(map6);
		map6 = new HashMap<String, String>();
		map6.put("1", "六家村");
		list6.add(map6);

		map7 = new HashMap<String, String>();
		map7.put("1", "酒花村");
		list7.add(map7);
		map7 = new HashMap<String, String>();
		map7.put("1", "来广营村");
		list7.add(map7);

		map8 = new HashMap<String, String>();
		map8.put("1", "花家地");
		list8.add(map8);
		map8 = new HashMap<String, String>();
		map8.put("1", "大西洋");
		list8.add(map8);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		a = position;
		if (name.equals("石家庄")) {
			if (position == 0) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, list1);
				listViewFour.setAdapter(adapter);
			} else if (position == 1) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, list2);
				listViewFour.setAdapter(adapter);
			}

		} else if (name.equals("辛集市")) {
			if (position == 0) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, list3);
				listViewFour.setAdapter(adapter);
			} else if (position == 1) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, list4);
				listViewFour.setAdapter(adapter);
			}
		} else if (name.equals("海淀区")) {
			if (position == 0) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, list5);
				listViewFour.setAdapter(adapter);
			} else if (position == 1) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, list6);
				listViewFour.setAdapter(adapter);
			}
		} else if (name.equals("朝阳区")) {
			if (position == 0) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, list7);
				listViewFour.setAdapter(adapter);
			} else if (position == 1) {
				DiaListViewAdapter adapter = new DiaListViewAdapter(
						CityActivity.this, list8);
				listViewFour.setAdapter(adapter);
			}
		}
	}
}
