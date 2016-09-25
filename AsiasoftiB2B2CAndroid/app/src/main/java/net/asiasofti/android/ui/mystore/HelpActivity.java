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

import net.asiasofti.android.R;
import net.asiasofti.android.common.Constants;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.*;
import android.widget.ImageView;

public class HelpActivity extends Activity
{

	private ImageView imageBack;
	private WebView webviewHelp;

	public HelpActivity()
	{
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.more_help);
		webviewHelp = (WebView)findViewById(R.id.webviewHelp);
		imageBack = (ImageView)findViewById(R.id.imageBack);
		webviewHelp.getSettings().setSupportZoom(true);
		webviewHelp.getSettings().setBuiltInZoomControls(true);
		webviewHelp.getSettings().setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webviewHelp.loadUrl(Constants.URL_HELP);
		webviewHelp.setWebViewClient(new WebViewClient() {


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
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {


			public void onClick(View view)
			{
				finish();
			}

			
		});
	}
}
