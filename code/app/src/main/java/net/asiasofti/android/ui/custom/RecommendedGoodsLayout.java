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

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

import net.asiasofti.android.model.Test;

public class RecommendedGoodsLayout
{

	android.view.View.OnClickListener SlideMenuOnClickListener;
	private Activity activity;
	private ArrayList menuList;

	public RecommendedGoodsLayout(Activity activity1)
	{
		menuList = null;
		activity = activity1;
		menuList = new ArrayList();
		SlideMenuOnClickListener = new android.view.View.OnClickListener() {

			public void onClick(View view)
			{
				view.getTag().toString();
				if (!view.isClickable())
				{
					return;
				}
				for (int i = 0; ; i++)
				{
			        if (i >= menuList.size())
			        {
			        	return;
			        }
				}

			}

			
			
		};
		
	}

	public View getSlideMenuLinerLayout(Object aobj[], int i)
	{
		LinearLayout linearlayout = new LinearLayout(activity);
		linearlayout.setOrientation(0);
		android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2, 1.0F);
		layoutparams.gravity = 1;
		android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-2, -2);
		int j = 0;
		do
		{
			if (j >= aobj.length)
				return linearlayout;
			LinearLayout linearlayout1 = new LinearLayout(activity);
			linearlayout1.setGravity(17);
			linearlayout1.setOrientation(1);
			linearlayout1.setPadding(5, 5, 5, 0);
			ImageView imageview = new ImageView(activity);
			imageview.setLayoutParams(layoutparams1);
			imageview.setBackgroundResource(0x7f02009f);
			Test test = (Test)aobj[j];
			if (test != null)
			{
				TextView textview = new TextView(activity);
				textview.setLayoutParams(layoutparams1);
				textview.setPadding(5, 0, 5, 0);
				textview.setText(test.getName());
				textview.setTextColor(Color.rgb(144, 144, 144));
				textview.setGravity(17);
				textview.setMaxLines(2);
				TextView textview1 = new TextView(activity);
				textview1.setLayoutParams(layoutparams1);
				textview1.setPadding(5, 5, 5, 0);
				textview1.setText("$15.50");
				textview1.setTextColor(Color.rgb(155, 33, 12));
				textview1.setGravity(17);
				linearlayout1.addView(imageview, new android.widget.LinearLayout.LayoutParams(150, 150));
				linearlayout1.addView(textview, layoutparams1);
				linearlayout1.addView(textview1, layoutparams1);
				linearlayout.addView(linearlayout1, layoutparams);
				menuList.add(linearlayout);
			}
			j++;
		} while (true);
	}

}
