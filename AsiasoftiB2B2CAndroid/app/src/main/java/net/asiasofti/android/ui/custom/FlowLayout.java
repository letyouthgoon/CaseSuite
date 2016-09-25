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
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup
{
	public static class LayoutParams extends android.view.ViewGroup.LayoutParams
	{

		public final int horizontal_spacing;
		public final int vertical_spacing;

		public LayoutParams(int i, int j)
		{
			super(0, 0);
			horizontal_spacing = i;
			vertical_spacing = j;
		}
	}


//	static final boolean $assertionsDisabled;
	private int line_height;

	public FlowLayout(Context context)
	{
		super(context);
	}

	public FlowLayout(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
	}

	protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
	{
		return layoutparams instanceof LayoutParams;
	}

	protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
	{
		return new LayoutParams(5, 5);
	}

	protected void onLayout(boolean flag, int i, int j, int k, int l)
	{
		int i1 = getChildCount();
		int j1 = k - i;
		int k1 = getPaddingLeft();
		int l1 = getPaddingTop();
		int i2 = 0;
		do
		{
			if (i2 >= i1)
				return;
			View view = getChildAt(i2);
			if (view.getVisibility() != 8)
			{
				int j2 = view.getMeasuredWidth();
				int k2 = view.getMeasuredHeight();
				LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
				if (k1 + j2 > j1)
				{
					k1 = getPaddingLeft();
					l1 += line_height;
				}
				view.layout(k1, l1, k1 + j2, l1 + k2);
				k1 += j2 + layoutparams.horizontal_spacing;
			}
			i2++;
		} while (true);
	}

	protected void onMeasure(int i, int j)
	{
		assert (View.MeasureSpec.getMode(i) != 0);
		int k = android.view.View.MeasureSpec.getSize(i) - getPaddingLeft() - getPaddingRight();
		int l = android.view.View.MeasureSpec.getSize(j) - getPaddingTop() - getPaddingBottom();
		int i1 = getChildCount();
		int j1 = 0;
		int k1 = getPaddingLeft();
		int l1 = getPaddingTop();
		int i2;
		int j2;
		if (android.view.View.MeasureSpec.getMode(j) == 0x80000000)
			i2 = android.view.View.MeasureSpec.makeMeasureSpec(l, 0x80000000);
		else
			i2 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
		j2 = 0;
		do
		{
			View view;
			LayoutParams layoutparams;
			int k2;
			if (j2 >= i1)
			{
				line_height = j1;
				
				
				if (android.view.View.MeasureSpec.getMode(j) == 0)
					l = l1 + j1;
				else
				if (android.view.View.MeasureSpec.getMode(j) == 0x80000000 && l1 + j1 < l)
					l = l1 + j1;
				setMeasuredDimension(k, l);
				return;
			}
			 view = getChildAt(j2);
			if (view.getVisibility() != 8)
			{
				layoutparams = (LayoutParams)view.getLayoutParams();
				view.measure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x80000000), i2);
				k2 = view.getMeasuredWidth();
				j1 = Math.max(j1, view.getMeasuredHeight() + layoutparams.vertical_spacing);
				if (k1 + k2 > k)
				{
					k1 = getPaddingLeft();
					l1 += j1;
				}
				k1 += k2 + layoutparams.horizontal_spacing;
			}
			j2++;
		} while (true);
	}


}
