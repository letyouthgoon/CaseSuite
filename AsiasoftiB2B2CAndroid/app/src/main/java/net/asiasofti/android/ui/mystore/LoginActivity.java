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
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.HashMap;

import net.asiasofti.android.R;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.handler.RemoteDataHandler;
import net.asiasofti.android.model.Login;
import net.asiasofti.android.model.ResponseData;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {

	private Button buttonBack;
	private TextView textView,textViewZ;
	private Button buttonLoginSubmit;
	private CheckBox checkboxMyAuto;
	private EditText editLoginName;
	private EditText editLoginPassWord;
	private MyApp myApp;
	private String tabFlag;

	public LoginActivity() {
	}

	public void infoLoginCheck(String s, String s1) {
		HashMap hashmap = new HashMap();
		hashmap.put("username", s);
		hashmap.put("password", s1);
		hashmap.put("client", "android");
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_LOGIN,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								String s2;
								if (responsedata.getCode() == 200) {
									s2 = responsedata.getJson();
									Login login = Login.newInstanceList(s2);
									myApp.setLoginKey(login.getKey());
									myApp.setLoginName(login.getUsername());
									Intent intent = new Intent(Constants.APP_BORADCASTRECEIVER);
									sendBroadcast(intent);
									finish();
									try {
										String s3 = (new JSONObject(s2))
												.getString("error");
										if (s3 != null)
											Toast.makeText(LoginActivity.this,
													s3, 0).show();
										return;
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
										return;
									}

								}
								Toast.makeText(LoginActivity.this,
										"数据加载失败，请稍后重试", 0).show();
							}
						});
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.login_view);
		myApp = (MyApp) getApplication();
		tabFlag = getIntent().getStringExtra("tabFlag");
		editLoginName = (EditText) findViewById(R.id.editLoginName);
		editLoginPassWord = (EditText) findViewById(R.id.editLoginPassWord);
		buttonLoginSubmit = (Button) findViewById(R.id.buttonLoginSubmit);
//		buttonBack = (Button) findViewById(R.id.buttonBack);
		textView=(TextView) findViewById(R.id.buttonBack);
		textViewZ=(TextView) findViewById(R.id.buttonZ);
		buttonLoginSubmit
				.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {
						String s = editLoginName.getText().toString();
						String s1 = editLoginPassWord.getText().toString();
						infoLoginCheck(s, s1);
					}

				});
		textView.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				finish();
			}

		});
		textViewZ.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						LoginActivity.this,
						net.asiasofti.android.ui.mystore.RegisteredActivity.class);
				startActivity(intent);
			}
		});
	}

}
