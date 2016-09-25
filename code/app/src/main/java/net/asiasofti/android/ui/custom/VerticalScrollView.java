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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class VerticalScrollView extends ScrollView
{
	class YScrollDetector extends android.view.GestureDetector.SimpleOnGestureListener
	{


		public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
		{
			return Math.abs(f1) > Math.abs(f);
		}

		YScrollDetector()
		{
		
			super();
		}
	}


	private GestureDetector mGestureDetector;

	public VerticalScrollView(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		mGestureDetector = new GestureDetector(context, new YScrollDetector());
	}

	public boolean onInterceptTouchEvent(MotionEvent motionevent)
	{
		return super.onInterceptTouchEvent(motionevent) && mGestureDetector.onTouchEvent(motionevent);
	}
}
