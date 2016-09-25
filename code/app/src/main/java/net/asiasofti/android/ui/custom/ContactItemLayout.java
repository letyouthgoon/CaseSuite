 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.ui.custom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import net.asiasofti.android.ui.type.GoodsTabActivity;

public class ContactItemLayout extends LinearLayout
{

	public ContactItemLayout(final Context context, final String textName)
	{
		super(context);
		LinearLayout linearlayout = (LinearLayout)((LayoutInflater)context.getSystemService("layout_inflater")).inflate(0x7f030030, null);
		((TextView)linearlayout.findViewById(0x7f0900c9)).setText(textName);
		linearlayout.setOnClickListener(new android.view.View.OnClickListener() {

			

			public void onClick(View view)
			{
				if (textName.equals("") || textName.equals("") || textName == null)
				{
					Toast.makeText(context, "请输入搜索内容", 0).show();
					return;
				} else
				{
					Intent intent = new Intent(context, net.asiasofti.android.ui.type.GoodsTabActivity.class);
					intent.putExtra("keyword", textName);
					intent.putExtra("gc_name", textName);
					context.startActivity(intent);
					return;
				}
			}

			

		});
		addView(linearlayout);
	}
}
