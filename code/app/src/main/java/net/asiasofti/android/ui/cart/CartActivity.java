 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.ui.cart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.adapter.CartListViewAdapter;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.CartList;
import net.asiasofti.android.model.ResponseData;
import net.asiasofti.android.ui.type.BuyStep1Activity;

import org.json.JSONException;
import org.json.JSONObject;

public class CartActivity extends Activity
{

	private CartListViewAdapter adapter;
	private Button buttonSendCart;
	private Button buttonVisitThe;
	public ArrayList cartIDList;
	private LinearLayout linearLayoutCartList;
	private ListView listViewShopCart;
	private MyApp myApp;
	private RelativeLayout relativeLayoutNoGoods;
	public HashMap selectedMap;
	public double sumPrice;
	public TextView textCartGoodsAllPrice;

	public CartActivity()
	{
		selectedMap = new HashMap();
		sumPrice = 0.0D;
		cartIDList = new ArrayList();
	}

	public void cartDetele(String s)
	{
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("cart_id", s);
		RemoteDataHandler.asyncPost2(Constants.URL_CART_DETELE, hashmap, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {


			public void dataLoaded(ResponseData responsedata)
			{
				if (responsedata.getCode() != 200)
				{
					Toast.makeText(CartActivity.this, "数据加载失败，请稍后重试", 0).show();
					return;
				}
				String s1 = responsedata.getJson();
				if (s1.equals("1"))
				{
					Toast.makeText(CartActivity.this, "删除成功", 0).show();
					loadingCartListData();
				}
				String s2;
				try
				{
					s2 = (new JSONObject(s1)).getString("error");
				}
				catch (JSONException jsonexception)
				{
					jsonexception.printStackTrace();
					return;
				}
				if (s2 != null)
				{
					Toast.makeText(CartActivity.this, s2, 0).show();
				}
				return;
				
			}

		});
	}

	public void loadingCartListData()
	{
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		RemoteDataHandler.asyncPost2(Constants.URL_CART_LIST, hashmap, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

			
			public void dataLoaded(ResponseData responsedata)
			{
				
				if (responsedata.getCode() != 200)
				{
					linearLayoutCartList.setVisibility(8);
					relativeLayoutNoGoods.setVisibility(0);
					Toast.makeText(CartActivity.this, "数据加载失败，请稍后重试", 0).show();
					return;
				}
				
				String s = responsedata.getJson();
				String error = null;
				try
				{
					error = (new JSONObject(s)).getString("error");
					
				}
				catch (JSONException jsonexception1)
				{
					jsonexception1.printStackTrace();

				}
				if(error != null && !"".equals(error))
				{
					Toast.makeText(CartActivity.this, error, 0).show();
					return;
				}
				if (responsedata.getLogin() == 0)
				{	myApp.setLoginKey("");}
				try
				{
				String s2 = (new JSONObject(s)).getString("cart_list");
				if (s2 == null || s2.equals("") || s2.equals("[]"))
				{
					linearLayoutCartList.setVisibility(8);
					relativeLayoutNoGoods.setVisibility(0);
				}
				else
				{
					linearLayoutCartList.setVisibility(0);
					relativeLayoutNoGoods.setVisibility(8);
				}
				
				cartIDList.clear();
				ArrayList arraylist = CartList.newInstanceList(s2);
				for(int i=0;i<=arraylist.size()-1;i++)
				{
					selectedMap.put(((CartList)arraylist.get(i)).getCart_id(), Boolean.valueOf(true));
					CartActivity cartactivity = CartActivity.this;
					cartactivity.sumPrice = cartactivity.sumPrice + Double.parseDouble(((CartList)arraylist.get(i)).getGoods_price()) * (double)Integer.parseInt(((CartList)arraylist.get(i)).getGoods_num());
					cartIDList.add((new StringBuilder(",")).append(((CartList)arraylist.get(i)).getCart_id()).append("|").append(((CartList)arraylist.get(i)).getGoods_num()).toString());
				}

				textCartGoodsAllPrice.setText((new StringBuilder(String.valueOf(sumPrice))).toString());
				adapter.setCartLists(arraylist);
				adapter.notifyDataSetChanged();
				}
				catch(JSONException e)
				{
					linearLayoutCartList.setVisibility(8);
					relativeLayoutNoGoods.setVisibility(0);
					Toast.makeText(CartActivity.this, "数据加载失败，请稍后重试", 0).show();
				}
			}
			
		});
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.cart_view);
		myApp = (MyApp)getApplicationContext();
		listViewShopCart = (ListView)findViewById(R.id.listViewShopCart);
		textCartGoodsAllPrice = (TextView)findViewById(R.id.textCartGoodsAllPrice);
		relativeLayoutNoGoods = (RelativeLayout)findViewById(R.id.relativeLayoutNoGoods);
		linearLayoutCartList = (LinearLayout)findViewById(R.id.linearLayoutCartList);
		buttonVisitThe = (Button)findViewById(R.id.buttonVisitThe);
		buttonSendCart = (Button)findViewById(R.id.buttonSendCart);
		adapter = new CartListViewAdapter(this, this);
		listViewShopCart.setCacheColorHint(0);
		listViewShopCart.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		buttonVisitThe.setOnClickListener(new android.view.View.OnClickListener() {
			public void onClick(View view)
			{
				myApp.getTabHost().setCurrentTab(1);
				myApp.getTypeButton().setChecked(true);
			}

		});
		buttonSendCart.setOnClickListener(new android.view.View.OnClickListener() {

	

			public void onClick(View view)
			{
				String s = "";
				int i = 0;
				do
				{
					if (i >= cartIDList.size())
						if (cartIDList.size() > 0)
						{
							String s1 = s.substring(1, s.length());
							System.out.println((new StringBuilder("cartID-->")).append(s1).toString());
							Intent intent = new Intent(CartActivity.this, net.asiasofti.android.ui.type.BuyStep1Activity.class);
							intent.putExtra("cart_id", s1);
							intent.putExtra("cartFlag", "cartFlag");
							startActivity(intent);
							return;
						} else
						{
							Toast.makeText(CartActivity.this, "您还没有勾选商品", 0).show();
							return;
						}
					s = (new StringBuilder(String.valueOf(s))).append((String)cartIDList.get(i)).toString();
					i++;
				} while (true);
			}

			
		
		});
		listViewShopCart.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

		
			public void onItemClick(AdapterView adapterview, View view, int i, long l)
			{
				CartList cartlist = (CartList)listViewShopCart.getItemAtPosition(i);
				CheckBox checkbox = (CheckBox)view.findViewById(R.id.checkBoxCart);
				checkbox.toggle();
				selectedMap.put(cartlist.getCart_id(), Boolean.valueOf(checkbox.isChecked()));
				if (checkbox.isChecked())
				{
					CartActivity cartactivity1 = CartActivity.this;
					cartactivity1.sumPrice = cartactivity1.sumPrice + Double.parseDouble(cartlist.getGoods_price()) * (double)Integer.parseInt(cartlist.getGoods_num());
					cartIDList.add((new StringBuilder(",")).append(cartlist.getCart_id()).append("|").append(cartlist.getGoods_num()).toString());
				} else
				{
					CartActivity cartactivity = CartActivity.this;
					cartactivity.sumPrice = cartactivity.sumPrice - Double.parseDouble(cartlist.getGoods_price()) * (double)Integer.parseInt(cartlist.getGoods_num());
					cartIDList.remove((new StringBuilder(",")).append(cartlist.getCart_id()).append("|").append(cartlist.getGoods_num()).toString());
				}
				textCartGoodsAllPrice.setText((new StringBuilder()).append(sumPrice).toString());
			}
		});
	}

	public boolean onKeyDown(int i, KeyEvent keyevent)
	{
		if (i == 4)
		{
			myApp.getTabHost().setCurrentTab(0);
			myApp.getHomeButton().setChecked(true);
			return true;
		} else
		{
			return super.onKeyDown(i, keyevent);
		}
	}

	protected void onResume()
	{
		super.onResume();
		cartIDList.clear();
		sumPrice = 0.0D;
		if (myApp.getLoginKey() == null || myApp.getLoginKey().equals("") || myApp.getLoginKey().equals("null"))
		{
			linearLayoutCartList.setVisibility(8);
			relativeLayoutNoGoods.setVisibility(0);
			return;
		} else
		{
			linearLayoutCartList.setVisibility(0);
			relativeLayoutNoGoods.setVisibility(8);
			loadingCartListData();
			return;
		}
	}
}
