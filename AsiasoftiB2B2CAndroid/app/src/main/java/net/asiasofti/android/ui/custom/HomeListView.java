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
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Toast;

public class HomeListView extends ListView
{
	class YScrollDetector extends android.view.GestureDetector.SimpleOnGestureListener
	{


		public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
		{
			if (Math.abs(f1) >= Math.abs(f))
			{
				Log.e("jj", "上下....");
				return true;
			} else
			{
				Log.e("jj", "左右....");
				return false;
			}
		}

		public boolean onSingleTapUp(MotionEvent motionevent)
		{
			Toast.makeText(context, (new StringBuilder("图")).append(viewPager.getCurrentItem()).toString(), 1).show();
			return super.onSingleTapUp(motionevent);
		}
	}


	private Context context;
	private GestureDetector mGestureDetector;
	android.view.View.OnTouchListener mGestureListener;
	private ViewPager viewPager;

	public HomeListView(Context context1, AttributeSet attributeset)
	{
		super(context1, attributeset);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);
	}

	public boolean onInterceptTouchEvent(MotionEvent motionevent)
	{
		return super.onInterceptTouchEvent(motionevent) && mGestureDetector.onTouchEvent(motionevent);
	}

	public void setViewPager(ViewPager viewpager)
	{
		viewPager = viewpager;
	}


}
