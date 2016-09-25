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
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.*;

public class GoodsDetailsWebAcivity extends Activity
{

	private String goods_id;
	private WebView web;

	public GoodsDetailsWebAcivity()
	{
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(0x7f03000c);
		goods_id = getIntent().getExtras().getString("goods_id");
		web = (WebView)findViewById(R.id.web);
		web.getSettings().setSupportZoom(true);
		web.getSettings().setBuiltInZoomControls(true);
		web.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		web.loadUrl((new StringBuilder(Constants.URL_GOODS_DETAILS_WEB + "&goods_id=")).append(goods_id).toString());
		web.setWebViewClient(new WebViewClient() {


			public void onPageFinished(WebView webview, String s)
			{
				super.onPageFinished(webview, s);
			}

			public void onPageStarted(WebView webview, String s, Bitmap bitmap)
			{
				super.onPageStarted(webview, s, bitmap);
			}

			public void onReceivedError(WebView webview, int i, String s, String s1)
			{
				webview.loadUrl("file:///android_asset/error.html");
			}

			public boolean shouldOverrideUrlLoading(WebView webview, String s)
			{
				webview.loadUrl(s);
				return true;
			}

		
		});
	}
}
