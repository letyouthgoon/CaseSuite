 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.ui.mystore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.HistoryBrowse;
import net.asiasofti.android.model.ResponseData;
import net.asiasofti.android.ui.more.AboutActivity;

import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package net.asiasofti.android.ui.mystore:
//			FeekBaskActivity, HelpActivity

public class SettingActivity extends Activity
{

	private Button buttonLoingOut;
	private ImageView imageViewBack;
	private LinearLayout linearlayoutAbout;
	private LinearLayout linearlayoutCleanHistoryBrowse;
	private LinearLayout linearlayoutCleanPhoto;
	private LinearLayout linearlayoutFeekBask;
	private LinearLayout linearlayoutHelp;
	private MyApp myApp;

	public SettingActivity()
	{
	}

	public void delAllFile(String s)
	{
		File file;
		file = new File(s);
		if (file.exists() && file.isDirectory())
		{
			String as[] = file.list();
			int i = 0;
			while (i < as.length) 
			{
				File file1;
				if (s.endsWith(File.separator))
					file1 = new File((new StringBuilder(String.valueOf(s))).append(as[i]).toString());
				else
					file1 = new File((new StringBuilder(String.valueOf(s))).append(File.separator).append(as[i]).toString());
				if (file1.isFile())
					file1.delete();
				if (file1.isDirectory())
					delAllFile((new StringBuilder(String.valueOf(s))).append("/").append(as[i]).toString());
				i++;
			}
		}
		return;
	}

	public void loginOut()
	{
		HashMap hashmap = new HashMap();
		hashmap.put("key", myApp.getLoginKey());
		hashmap.put("username", myApp.getLoginName());
		hashmap.put("client", "android");
		finish();
		myApp.setLoginKey("");
		myApp.setLoginName("");
		myApp.getTabHost().setCurrentTab(1);
		myApp.getTypeButton().setChecked(true);
		HistoryBrowse.deleteAll();
		RemoteDataHandler.asyncPost2(Constants.URL_LOGIN_OUT, hashmap, new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

	

			public void dataLoaded(ResponseData responsedata)
			{
				String s;
				if (responsedata.getCode() == 200)
				{
					s = responsedata.getJson();
					if(s.equals("1"))
					{}
					try
					{
						String error = (new JSONObject(s)).getString("error");
						
					}
					catch(JSONException e)
					{
						e.printStackTrace();
					}
					return;
				}
				return;
			}

			
			
		});
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.setting_view);
		myApp = (MyApp)getApplication();
		imageViewBack = (ImageView)findViewById(R.id.imageBack);
		buttonLoingOut = (Button)findViewById(R.id.buttonLoingOut);
		linearlayoutCleanHistoryBrowse = (LinearLayout)findViewById(R.id.linearlayoutCleanHistoryBrowse);
		linearlayoutAbout = (LinearLayout)findViewById(R.id.linearlayoutAbout);
		linearlayoutFeekBask = (LinearLayout)findViewById(R.id.linearlayoutFeekBask);
		linearlayoutHelp = (LinearLayout)findViewById(R.id.linearlayoutHelp);
		linearlayoutCleanPhoto = (LinearLayout)findViewById(R.id.linearlayoutCleanPhoto);
		imageViewBack.setOnClickListener(new android.view.View.OnClickListener() {

		

			public void onClick(View view)
			{
				finish();
			}

			
		
		});
		buttonLoingOut.setOnClickListener(new android.view.View.OnClickListener() {

		
			public void onClick(View view)
			{
				loginOut();
			}

			
		
		});
		linearlayoutCleanHistoryBrowse.setOnClickListener(new android.view.View.OnClickListener() {


			public void onClick(View view)
			{
				HistoryBrowse.deleteAll();
				Toast.makeText(SettingActivity.this, "删除浏览历史成功", 0).show();
			}

			
		
		});
		linearlayoutAbout.setOnClickListener(new android.view.View.OnClickListener() {

		
			public void onClick(View view)
			{
				Intent intent = new Intent(SettingActivity.this, net.asiasofti.android.ui.more.AboutActivity.class);
				startActivity(intent);
			}

		
		});
		linearlayoutFeekBask.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view)
			{
				Intent intent = new Intent(SettingActivity.this, net.asiasofti.android.ui.mystore.FeekBaskActivity.class);
				startActivity(intent);
			}

		
		});
		linearlayoutHelp.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view)
			{
				Intent intent = new Intent(SettingActivity.this, net.asiasofti.android.ui.mystore.HelpActivity.class);
				startActivity(intent);
			}

		
		});
		linearlayoutCleanPhoto.setOnClickListener(new android.view.View.OnClickListener() {

		
			public void onClick(View view)
			{
				delAllFile(Constants.CACHE_DIR_IMAGE);
				Toast.makeText(SettingActivity.this, "删除缓存图片成功", 0).show();
			}

		});
	}
}
