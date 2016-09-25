/**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */
package net.asiasofti.android.ui.type;

import net.asiasofti.android.R;
import net.asiasofti.android.common.Constants;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class GoodsTabActivity extends TabActivity {
	class MyRadioButtonClickListener implements
			android.view.View.OnClickListener {

		public void onClick(View view) {
			switch (((RadioButton) view).getId()) {
			default:
				return;

			case 2131296345:
				tabHost.setCurrentTabByTag("new");
				price_flag = 0;
				return;

			case 2131296346:
				GoodsTabActivity goodstabactivity = GoodsTabActivity.this;
				goodstabactivity.price_flag = 1 + goodstabactivity.price_flag;
				Drawable drawable = getResources().getDrawable(
						R.drawable.goods_tab_price_bg_1);
				Drawable drawable1 = getResources().getDrawable(
						R.drawable.goods_tab_price_bg_2);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				drawable1.setBounds(0, 0, drawable1.getMinimumWidth(),
						drawable1.getMinimumHeight());
				Intent intent = new Intent();
				intent.setAction(Constants.HOST);
				if (price_flag == 1) {
					btn_price.setCompoundDrawables(drawable, null, null, null);
					intent.putExtra("order", "1");
				} else if (price_flag == 2) {
					price_flag = 0;
					btn_price.setCompoundDrawables(drawable1, null, null, null);
					intent.putExtra("order", "2");
				}
				sendBroadcast(intent);
				tabHost.setCurrentTabByTag("price");
				return;

			case 2131296347:
				price_flag = 0;
				tabHost.setCurrentTabByTag("num");
				return;

			case 2131296348:
				price_flag = 0;
				tabHost.setCurrentTabByTag("man");
				return;
			}
		}

		MyRadioButtonClickListener() {

			super();
		}
	}

	public static final String TAB_TAG_MAN = "man";
	public static final String TAB_TAG_NEW = "new";
	public static final String TAB_TAG_NUM = "num";
	public static final String TAB_TAG_PRICE = "price";
	private RadioButton btn_man;
	private RadioButton btn_new;
	private RadioButton btn_num;
	private RadioButton btn_price;
	private ImageView imageBack;
	private Intent manIntent;
	private Intent newIntent;
	private Intent numIntent;
	private Intent priceIntent;
	private int price_flag;
	private TabHost tabHost;
	private TextView textGoodsTabTitleName;

	public GoodsTabActivity() {
		price_flag = 0;
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.goods_tab);
		textGoodsTabTitleName = (TextView) findViewById(R.id.textGoodsTabTitleName);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		String s = getIntent().getStringExtra("gc_id");
		String s1 = getIntent().getStringExtra("gc_name");
		String s2 = getIntent().getStringExtra("keyword");
		textGoodsTabTitleName.setText(s1);
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				finish();
			}

		});
		newIntent = new Intent(this,
				net.asiasofti.android.ui.type.GoodsListViewActivity.class);
		newIntent.putExtra("gc_id", s);
		newIntent.putExtra("gc_type", "");
		newIntent.putExtra("keyword", s2);
		priceIntent = new Intent(this,
				net.asiasofti.android.ui.type.GoodsListViewActivity.class);
		priceIntent.putExtra("gc_id", s);
		priceIntent.putExtra("gc_type", "3");
		priceIntent.putExtra("order", "1");
		priceIntent.putExtra("keyword", s2);
		numIntent = new Intent(this,
				net.asiasofti.android.ui.type.GoodsListViewActivity.class);
		numIntent.putExtra("gc_id", s);
		numIntent.putExtra("gc_type", "1");
		numIntent.putExtra("keyword", s2);
		manIntent = new Intent(this,
				net.asiasofti.android.ui.type.GoodsListViewActivity.class);
		manIntent.putExtra("gc_id", s);
		manIntent.putExtra("gc_type", "2");
		manIntent.putExtra("keyword", s2);
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("new").setIndicator("new")
				.setContent(newIntent));
		tabHost.addTab(tabHost.newTabSpec("price").setIndicator("price")
				.setContent(priceIntent));
		tabHost.addTab(tabHost.newTabSpec("num").setIndicator("num")
				.setContent(numIntent));
		tabHost.addTab(tabHost.newTabSpec("man").setIndicator("man")
				.setContent(manIntent));
		btn_new = (RadioButton) findViewById(R.id.goods_tab_new);
		btn_price = (RadioButton) findViewById(R.id.goods_tab_price);
		btn_num = (RadioButton) findViewById(R.id.goods_tab_num);
		btn_man = (RadioButton) findViewById(R.id.goods_tab_man);
		MyRadioButtonClickListener myradiobuttonclicklistener = new MyRadioButtonClickListener();
		btn_new.setOnClickListener(myradiobuttonclicklistener);
		btn_price.setOnClickListener(myradiobuttonclicklistener);
		btn_num.setOnClickListener(myradiobuttonclicklistener);
		btn_man.setOnClickListener(myradiobuttonclicklistener);
	}

}
