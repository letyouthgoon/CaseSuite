/**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */
package net.asiasofti.android.common;

import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.RadioButton;
import android.widget.TabHost;
import com.activeandroid.app.Application;
import java.io.File;
import java.io.PrintStream;

public class MyApp extends Application {

	private RadioButton HomeButton;
	private boolean IsCheckLogin;
	private RadioButton MyStoreButton;
	private RadioButton cartButton;
	private String loginKey;
	private String loginName;
	private SharedPreferences sysInitSharedPreferences;
	private TabHost tabHost;
	private RadioButton typeButton;

	public MyApp() {
	}

	private void createCacheDir() {
		{
			if ("mounted".equals(Environment.getExternalStorageState())) {
				File file = new File(Constants.CACHE_DIR);
				if (file.exists())
					System.out.println("SD锟斤拷锟斤拷锟斤拷目录:锟窖达拷锟斤拷!");
				else if (file.mkdirs())
					System.out.println((new StringBuilder("SD锟斤拷锟斤拷锟斤拷目录:"))
							.append(file.getAbsolutePath()).append("锟窖达拷锟斤拷!")
							.toString());
				else
					System.out.println("SD锟斤拷锟斤拷锟斤拷目录:锟斤拷锟斤拷失锟斤拷!");
				File file1 = new File(Constants.CACHE_DIR_IMAGE);
				if (!file1.exists()) {
					if (file1.mkdirs()) {
						System.out.println((new StringBuilder(
								"SD锟斤拷锟斤拷片锟斤拷锟斤拷目录:"))
								.append(file1.getAbsolutePath())
								.append("锟窖达拷锟斤拷!").toString());
						return;
					} else {
						System.out.println("SD锟斤拷锟斤拷片锟斤拷锟斤拷目录:锟斤拷锟斤拷失锟斤拷!");
						return;
					}
				} else {
					System.out.println("SD锟斤拷锟斤拷片锟斤拷锟斤拷目录:锟窖达拷锟斤拷!");
				}
			}
			return;
		}

	}

	public RadioButton getCartButton() {
		return cartButton;
	}

	public RadioButton getHomeButton() {
		return HomeButton;
	}

	public String getLoginKey() {
		return sysInitSharedPreferences.getString("loginKey", "");
	}

	public String getLoginName() {
		return sysInitSharedPreferences.getString("loginName", "");
	}

	public RadioButton getMyStoreButton() {
		return MyStoreButton;
	}

	public SharedPreferences getSysInitSharedPreferences() {
		return sysInitSharedPreferences;
	}

	public TabHost getTabHost() {
		return tabHost;
	}

	public RadioButton getTypeButton() {
		return typeButton;
	}

	public boolean isIsCheckLogin() {
		return sysInitSharedPreferences.getBoolean("IsCheckLogin", false);
	}

	public void onCreate() {
		super.onCreate();
		sysInitSharedPreferences = getSharedPreferences("sysini", 0);
		loginKey = sysInitSharedPreferences.getString("loginKey", "");
		IsCheckLogin = sysInitSharedPreferences.getBoolean("IsCheckLogin",
				false);
		createCacheDir();
	}

	public void setCartButton(RadioButton radiobutton) {
		cartButton = radiobutton;
	}

	public void setHomeButton(RadioButton radiobutton) {
		HomeButton = radiobutton;
	}

	public void setIsCheckLogin(boolean flag) {
		IsCheckLogin = flag;
		sysInitSharedPreferences.edit()
				.putBoolean("IsCheckLogin", IsCheckLogin).commit();
	}

	public void setLoginKey(String s) {
		loginKey = s;
		sysInitSharedPreferences.edit().putString("loginKey", loginKey)
				.commit();
	}

	public void setLoginName(String s) {
		loginName = s;
		sysInitSharedPreferences.edit().putString("loginName", loginName)
				.commit();
	}

	public void setMyStoreButton(RadioButton radiobutton) {
		MyStoreButton = radiobutton;
	}

	public void setSysInitSharedPreferences(SharedPreferences sharedpreferences) {
		sysInitSharedPreferences = sharedpreferences;
	}

	public void setTabHost(TabHost tabhost) {
		tabHost = tabhost;
	}

	public void setTypeButton(RadioButton radiobutton) {
		typeButton = radiobutton;
	}
}
