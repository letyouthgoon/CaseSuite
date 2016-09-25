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

public class RegisteredActivity extends Activity {

	private Button buttonSend;
	private EditText editEmail;
	private EditText editPassword;
	private EditText editPasswordConfirm;
	private EditText editUserName;
	private MyApp myApp;

	public RegisteredActivity() {
	}

	public void SendData(String s, String s1, String s2, String s3) {
		HashMap hashmap = new HashMap();
		hashmap.put("username", s);
		hashmap.put("password", s1);
		hashmap.put("password_confirm", s2);
		hashmap.put("email", s3);
		hashmap.put("client", "android");
		RemoteDataHandler
				.asyncPost2(
						Constants.URL_REGISTER,
						hashmap,
						new net.asiasofti.android.handler.RemoteDataHandler.Callback() {

							public void dataLoaded(ResponseData responsedata) {
								String s4;
								if (responsedata.getCode() == 200) {
									s4 = responsedata.getJson();

									try {
										String s5 = (new JSONObject(s4))
												.getString("error");
										if (s5 != null) {
											Toast.makeText(
													RegisteredActivity.this,
													s5, 0).show();
											return;
										}
									} catch (JSONException jsonexception) {
										jsonexception.printStackTrace();
									}
									Login login = Login.newInstanceList(s4);
									myApp.setLoginKey(login.getKey());
									myApp.setLoginName(login.getUsername());
									Intent intent = new Intent(Constants.APP_BORADCASTRECEIVER);
									sendBroadcast(intent);
									finish();
									return;
								}
								Toast.makeText(RegisteredActivity.this,
										"数据加载失败，请稍后重试", 0).show();
								return;
							}

						});
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.registered_view);
		myApp = (MyApp) getApplication();
		editUserName = (EditText) findViewById(R.id.editUserName);
		editPassword = (EditText) findViewById(R.id.editPassword);
		editPasswordConfirm = (EditText) findViewById(R.id.editPasswordConfirm);
		editEmail = (EditText) findViewById(R.id.editEmail);
		buttonSend = (Button) findViewById(R.id.buttonSend);
		buttonSend.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				String s = editUserName.getText().toString();
				String s1 = editPassword.getText().toString();
				String s2 = editPasswordConfirm.getText().toString();
				String s3 = editEmail.getText().toString();
				if (s.equals("") || s == null) {
					Toast.makeText(RegisteredActivity.this, "用户名不能为空", 0)
							.show();
					return;
				}
				if (s1.equals("") || s1 == null) {
					Toast.makeText(RegisteredActivity.this, "密码不能为空", 0).show();
					return;
				}
				if (s2.equals("") || s2 == null) {
					Toast.makeText(RegisteredActivity.this, "确认密码不能为空", 0)
							.show();
					return;
				}
				if (!s1.equals(s2)) {
					Toast.makeText(RegisteredActivity.this, "两次密码不同", 0).show();
					return;
				}
				if (s3.equals("") || s3 == null) {
					Toast.makeText(RegisteredActivity.this, "邮箱不能为空", 0).show();
					return;
				} else {
					SendData(s, s1, s2, s3);
					return;
				}
			}

		});
	}

}
