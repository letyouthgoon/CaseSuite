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
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.*;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.PrintStream;

import net.asiasofti.android.R;
import net.asiasofti.android.common.Constants;
import net.asiasofti.android.common.MyApp;

public class PayMentWebAcivity extends Activity {
	final class DemoJavaScriptInterface {

		public void checkPaymentAndroid(final String flag) {
			mHandler.post(new Runnable() {

				public void run() {
					if (flag.equals("success")) {
						Toast.makeText(PayMentWebAcivity.this, "支付成功", 0)
								.show();
						Intent intent = new Intent(
								Constants.APP_BORADCASTRECEIVER);
						sendBroadcast(intent);
						finish();
					} else if (flag.equals("fail")) {
						Toast.makeText(PayMentWebAcivity.this, "支付失败，请稍后重试", 0)
								.show();
						finish();
						return;
					}
				}

			});
		}

		DemoJavaScriptInterface() {

			super();
		}
	}

	private ImageView imageBack;
	private Handler mHandler;
	private MyApp myapp;
	private WebView web;

	public PayMentWebAcivity() {
		mHandler = new Handler();
	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.pay_ment_webview);
		myapp = (MyApp) getApplication();
		String s = getIntent().getExtras().getString("pay_sn");
		web = (WebView) findViewById(R.id.web);
		imageBack = (ImageView) findViewById(R.id.imageBack);
		web.getSettings().setSupportZoom(true);
		web.getSettings().setBuiltInZoomControls(true);
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setLayoutAlgorithm(
				android.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		web.loadUrl((new StringBuilder(Constants.URL_ORDER_PAYMENT + "&key="))
				.append(myapp.getLoginKey()).append("&pay_sn=").append(s)
				.toString());
		System.out.println((new StringBuilder("url-->"
				+ Constants.URL_ORDER_PAYMENT + "&key="))
				.append(myapp.getLoginKey()).append("&pay_sn=").append(s)
				.toString());
		web.setWebViewClient(new WebViewClient() {

			public void onPageFinished(WebView webview, String s1) {
				super.onPageFinished(webview, s1);
				System.out.println("2234");
				
			}

			public void onPageStarted(WebView webview, String s1, Bitmap bitmap) {
				super.onPageStarted(webview, s1, bitmap);
			}

			public void onReceivedError(WebView webview, int i, String s1,
					String s2) {
				webview.loadUrl("file:///android_asset/error.html");
			}

			public boolean shouldOverrideUrlLoading(WebView webview, String s1) {
				webview.loadUrl(s1);
				return true;
			}

		});
		web.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				finish();
			}

		});
	}

}
