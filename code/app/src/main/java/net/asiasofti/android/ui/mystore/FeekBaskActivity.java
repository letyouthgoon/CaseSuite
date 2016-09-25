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
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FeekBaskActivity extends Activity
{

	private ImageView imageBack;

	public FeekBaskActivity()
	{
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.more_feekbask);
		imageBack = (ImageView)findViewById(R.id.imageBack);
		imageBack.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view)
			{
				finish();
			}

		});
	}
}
