 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.common;

import java.io.PrintStream;

public class ImageZoom
{

	private boolean isZoom;
	private int maxHeight;
	private int maxWidth;
	private int newHeight;
	private int newWidth;
	private int srcHeight;
	private int srcWidth;

	public ImageZoom()
	{
	}

	private void calc()
	{
		if (isZoom)
		{
			if (srcWidth / srcHeight >= maxWidth / maxHeight)
				if (srcWidth > maxWidth)
				{
					newWidth = maxWidth;
					newHeight = (srcHeight * maxWidth) / srcWidth;
					return;
				} else
				{
					newWidth = srcWidth;
					newHeight = srcHeight;
					return;
				}
			if (srcHeight > maxHeight)
			{
				newHeight = maxHeight;
				newWidth = (srcWidth * maxHeight) / srcHeight;
				return;
			} else
			{
				newWidth = srcWidth;
				newHeight = srcHeight;
				return;
			}
		} else
		{
			newWidth = 0;
			newHeight = 0;
			return;
		}
	}

	public static void main(String args[])
	{
		ImageZoom imagezoom = new ImageZoom();
		imagezoom.zoomImage(1024, 768, 480, 854);
		System.out.println((new StringBuilder("width: ")).append(imagezoom.getWidth()).append(", height: ").append(imagezoom.getHeight()).toString());
	}

	public int getHeight()
	{
		return newHeight;
	}

	public int getWidth()
	{
		return newWidth;
	}

	public void zoomImage(int i, int j, int k, int l)
	{
		srcWidth = i;
		srcHeight = j;
		maxWidth = k;
		maxHeight = l;
		if (srcWidth > 0 && srcWidth > 0)
			isZoom = true;
		else
			isZoom = false;
		calc();
	}
}
