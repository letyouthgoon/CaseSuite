package net.asiasofti.android;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TabHost;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.ui.cart.CartActivity;
import net.asiasofti.android.ui.home.HomeActivity;
import net.asiasofti.android.ui.mystore.MyStoreActivity;
import net.asiasofti.android.ui.type.TypeAcitivity;
import net.asiasofti.android.ui.widget.MyOutDialog;

public class MainActivity extends TabActivity
{
	class MyRadioButtonClickListener
		implements android.view.View.OnClickListener
	{

		MyRadioButtonClickListener()
		{
			super();
		}
		public void onClick(View view)
		{
			switch (((RadioButton)view).getId())
			{
			default:
				return;

			case R.id.main_tab_home: 
				oldButton = R.id.main_tab_home;
				tabHost.setCurrentTabByTag("home");
				return;

			case R.id.main_tab_type: 
				oldButton = R.id.main_tab_type;
				tabHost.setCurrentTabByTag("type");
				return;

			case R.id.main_tab_mystore: 
				tabHost.setCurrentTabByTag("mystore");
				return;

			case R.id.main_tab_cart: 
				tabHost.setCurrentTabByTag("cart");
				break;
			}
		}

		
	}


	public static final String TAB_TAG_CART = "cart";
	public static final String TAB_TAG_HOME = "home";
	public static final String TAB_TAG_MYSTORE = "mystore";
	public static final String TAB_TAG_TYPE = "type";
	private RadioButton btn_cart;
	private RadioButton btn_home;
	private RadioButton btn_mystore;
	private RadioButton btn_type;
	private Intent cartIntent;
	private MyOutDialog dialog;
	private Intent homeIntent;
	private MyApp myApp;
	private Intent mystoreIntent;
	private int oldButton;
	private TabHost tabHost;
	private Intent typeIntent;

	public MainActivity()
	{
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		dialog = new MyOutDialog(this);
		myApp = (MyApp)getApplication();
		if (!myApp.isIsCheckLogin())
			myApp.setLoginKey("");
		homeIntent = new Intent(this, net.asiasofti.android.ui.home.HomeActivity.class);
		typeIntent = new Intent(this, net.asiasofti.android.ui.type.TypeAcitivity.class);
		mystoreIntent = new Intent(this, net.asiasofti.android.ui.mystore.MyStoreActivity.class);
		cartIntent = new Intent(this, net.asiasofti.android.ui.cart.CartActivity.class);
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("home").setIndicator("home").setContent(homeIntent));
		tabHost.addTab(tabHost.newTabSpec("type").setIndicator("type").setContent(typeIntent));
		tabHost.addTab(tabHost.newTabSpec("mystore").setIndicator("mystore").setContent(mystoreIntent));
		tabHost.addTab(tabHost.newTabSpec("cart").setIndicator("cart").setContent(cartIntent));
		btn_home = (RadioButton)findViewById(R.id.main_tab_home);
		btn_type = (RadioButton)findViewById(R.id.main_tab_type);
		btn_mystore = (RadioButton)findViewById(R.id.main_tab_mystore);
		btn_cart = (RadioButton)findViewById(R.id.main_tab_cart);
		MyRadioButtonClickListener myradiobuttonclicklistener = new MyRadioButtonClickListener();
		btn_home.setOnClickListener(myradiobuttonclicklistener);
		btn_type.setOnClickListener(myradiobuttonclicklistener);
		btn_mystore.setOnClickListener(myradiobuttonclicklistener);
		btn_cart.setOnClickListener(myradiobuttonclicklistener);
		oldButton = R.id.main_tab_home;
		myApp.setTabHost(tabHost);
		myApp.setMyStoreButton(btn_mystore);
		myApp.setTypeButton(btn_type);
		myApp.setCartButton(btn_cart);
		myApp.setHomeButton(btn_home);
	}


}
