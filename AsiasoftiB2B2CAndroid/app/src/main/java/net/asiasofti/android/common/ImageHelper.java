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

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.*;
import android.view.View;
import android.widget.ImageView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageHelper
{
	public static class MyViewBinder
		implements android.widget.SimpleAdapter.ViewBinder
	{

		public boolean setViewValue(View view, Object obj, String s)
		{
			if ((view instanceof ImageView) && (obj instanceof Bitmap))
			{
				((ImageView)view).setImageBitmap((Bitmap)obj);
				return true;
			} else
			{
				return false;
			}
		}

		public MyViewBinder()
		{
		}
	}


	public ImageHelper()
	{
	}

	public static Bitmap getBitmap(Context context, int i)
	{
		return BitmapFactory.decodeResource(context.getResources(), i);
	}

	public static Bitmap getHttpBitmap(String s)
	{
		InputStream inputstream = null;
		URL url;
		try {
			url = new URL(s);
		
		Bitmap bitmap1;
		HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
		httpurlconnection.setConnectTimeout(0);
		httpurlconnection.setDoInput(true);
		httpurlconnection.connect();
		inputstream = httpurlconnection.getInputStream();
		bitmap1 = BitmapFactory.decodeStream(inputstream);
		Bitmap bitmap = bitmap1;
		
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (inputstream != null) 
			{
				try {
					inputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static Bitmap getImageFromAssetsFile(Context context, String s)
	{
		AssetManager assetmanager;
		InputStream inputstream = null;
		assetmanager = context.getResources().getAssets();
		Bitmap bitmap1;
		try {
			inputstream = assetmanager.open(s);
		
		bitmap1 = BitmapFactory.decodeStream(inputstream);
		return bitmap1;
	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (inputstream != null)
				try {
					inputstream.close();
				} catch (IOException ioexception3) {
					ioexception3.printStackTrace();
				
				}
		}
		return null;

	}

	public static Bitmap getLoacalBitmap(String s)
	{
		Bitmap bitmap;
		try
		{
			bitmap = BitmapFactory.decodeStream(new FileInputStream(s));
		}
		catch (FileNotFoundException filenotfoundexception)
		{
			filenotfoundexception.printStackTrace();
			return null;
		}
		return bitmap;
	}

	public static Bitmap loadFromFile(File file)
		throws IOException
	{

		FileInputStream fileinputstream1 = new FileInputStream(file);
		Bitmap bitmap = BitmapFactory.decodeStream(fileinputstream1);
		if (fileinputstream1 != null){
			try
			{
				fileinputstream1.close();
			}
			catch (IOException ioexception2)
			{
				throw ioexception2;
			}
		}
		return bitmap;
	
		
	}

	public static Bitmap loadFromFile(String s)
		throws IOException
	{
		FileInputStream fileinputstream1 = new FileInputStream(s);
		Bitmap bitmap = BitmapFactory.decodeStream(fileinputstream1);
		if (fileinputstream1 != null)
		{
			try
			{
				fileinputstream1.close();
			}
			catch (IOException ioexception2)
			{
				throw ioexception2;
			}
		}
		return bitmap;
		
	}

	public static void write(Bitmap bitmap, File file) {
		FileOutputStream fileoutputstream = null;
		boolean flag = file.exists();
		fileoutputstream = null;
		try {
			if (!flag) {
				file.createNewFile();
			}
			FileOutputStream fileoutputstream1 = new FileOutputStream(file);
			bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 75,
					fileoutputstream1);
			fileoutputstream1.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void write(Bitmap bitmap, String s)
	{
		
		String s1 = IOHelper.getExtension(s);

		File file = new File(s);
		boolean flag = file.exists();
		try{
			if (!flag)
			{
				file.createNewFile();
			}
			FileOutputStream fileoutputstream1=null ;
			try
			{
			fileoutputstream1 = new FileOutputStream(file);
			if ("png".equalsIgnoreCase(s1))
			{
				bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 75, fileoutputstream1);
			}
			else
			{
				if (!"jpg".equalsIgnoreCase(s1) && !"jpeg".equalsIgnoreCase(s1)) 
				{
					bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 75, fileoutputstream1);
				}
			}
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(fileoutputstream1!=null)
				{
					fileoutputstream1.close();
				}
			}

			fileoutputstream1.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	public static void write(Bitmap bitmap, String s, String s1)
	{
		String s2 = IOHelper.getExtension(s1);
		FileOutputStream fileoutputstream1 = null;
		
		try {	
			fileoutputstream1 = new FileOutputStream(new File(s, s1));
		
		if ("png".equalsIgnoreCase(s2)) {bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 75, fileoutputstream1);}
		else
		{
			if (!"jpg".equalsIgnoreCase(s2) && !"jpeg".equalsIgnoreCase(s2))
			{
				fileoutputstream1.flush();
			}
			else
			{
				bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 75, fileoutputstream1);
			}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
			try {
				if (fileoutputstream1 != null) {
					fileoutputstream1.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	public static Bitmap zoom(Bitmap bitmap, int i)
	{
		int j = bitmap.getWidth();
		int k = bitmap.getHeight();
		if (j == i)
		{
			j--;
			k--;
		}
		float f = (float)i / (float)j;
		Matrix matrix = new Matrix();
		matrix.postScale(f, f);
		Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, j, k, matrix, true);
		bitmap.recycle();
		return bitmap1;
	}

	public static Bitmap zoom(Bitmap bitmap, int i, int j)
	{
		int k = bitmap.getWidth();
		int l = bitmap.getHeight();
		if (k == i || l == j)
		{
			k--;
			l--;
		}
		float f = (float)i / (float)k;
		Matrix matrix = new Matrix();
		matrix.postScale(f, f);
		Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, k, l, matrix, true);
		bitmap.recycle();
		return bitmap1;
	}
}
