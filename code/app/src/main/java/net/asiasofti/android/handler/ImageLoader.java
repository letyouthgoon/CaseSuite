 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.handler;

import android.graphics.Bitmap;
import android.os.*;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.concurrent.*;

import net.asiasofti.android.common.*;

public class ImageLoader

{
	public static interface ImageCallback
	{

		public abstract void imageLoaded(Bitmap bitmap, String s);
	}

	private class LoadBitmapRunnable
		implements Runnable
	{

		private Handler handler;
		private String url;
		private int width;

		public void run()
		{
			Message message = handler.obtainMessage(200);
			Bitmap bitmap = null;
			try {
				bitmap = HttpHelper.downloadBitmap(url);
			
			if (bitmap != null)
			{
					if (width > 0)
						bitmap = ImageHelper.zoom(bitmap, width);
					ImageLoader.imageCache.put(url, new SoftReference(bitmap));
					message.obj = bitmap;
					handler.sendMessage(message);
					String s = MD5Encoder.encode(url);
					File file = new File(Constants.CACHE_DIR_IMAGE, s);
					if (!file.exists())
						ImageHelper.write(bitmap, file);
				
				
			}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bitmap != null)
			{
				Log.d("ImageLoader", (new StringBuilder("bmp.width=")).append(bitmap.getWidth()).append(",bmp.height=").append(bitmap.getHeight()).append("-->").append(url).toString());
				return;
			} else
			{
				Log.d("ImageLoader", (new StringBuilder("Bitmap is null -->")).append(url).toString());
				return;
			}
		}

		public LoadBitmapRunnable(String s, int i, Handler handler1)
		{
			super();
			url = s;
			handler = handler1;
			width = i;
		}
	}


	public static final String TAG = "ImageLoader";
	private static ConcurrentHashMap imageCache = new ConcurrentHashMap();
	private static final ImageLoader imageLoader = new ImageLoader();
	private static ThreadPoolExecutor threadPool;

	private ImageLoader()
	{
	}

	public static void downloadBitmap(String s)
	{
		File file;
		String s1 = MD5Encoder.encode(s);
		file = new File(Constants.CACHE_DIR_IMAGE, s1);
		if (!file.exists())
		{
			try {
				HttpHelper.download(s, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private static Bitmap getBitmapFromCache(String s)
	{
		SoftReference softreference = (SoftReference)imageCache.get(s);
		if (softreference != null && softreference.get() != null)
		{
			Log.d("ImageLoader", (new StringBuilder("in cache-->")).append(s).toString());
			return (Bitmap)softreference.get();
		} else
		{
			imageCache.remove(s);
			return null;
		}
	}

	public static Bitmap getBitmapFromDisk(String s)
	{
		String s1 = MD5Encoder.encode(s);
		boolean flag = "mounted".equals(Environment.getExternalStorageState());
		Bitmap bitmap = null;
		if (flag)
		{
			File file = new File(Constants.CACHE_DIR_IMAGE, s1);
			boolean flag1 = file.exists();
			bitmap = null;
			if (flag1)
				try
				{
					bitmap = ImageHelper.loadFromFile(file);
					Log.d("ImageLoader", (new StringBuilder("in SD-->")).append(s).toString());
				}
				catch (IOException ioexception)
				{
					ioexception.printStackTrace();
					return bitmap;
				}
		}
		return bitmap;
	}

	public static ImageLoader getInstance()
	{
		return imageLoader;
	}

	public void asyncLoadBitmap(final String url, int i, final ImageCallback callback)
	{
		Bitmap bitmap = getBitmapFromCache(url);
		if (bitmap != null)
		{
			callback.imageLoaded(bitmap, url);
			return;
		}
		Bitmap bitmap1 = getBitmapFromDisk(url);
		if (bitmap1 != null)
		{
			callback.imageLoaded(bitmap1, url);
			return;
		} else
		{
			Handler handler = new Handler() {


				public void handleMessage(Message message)
				{
					if (200 == message.what)
					{
						Bitmap bitmap2 = (Bitmap)message.obj;
						callback.imageLoaded(bitmap2, url);
					}
				}

			
			};
			threadPool.execute(new LoadBitmapRunnable(url, i, handler));
			return;
		}
	}

	public void asyncLoadBitmap(final String url, final ImageCallback callback)
	{
		Bitmap bitmap = getBitmapFromCache(url);
		if (bitmap != null)
		{
			callback.imageLoaded(bitmap, url);
			return;
		}
		Bitmap bitmap1 = getBitmapFromDisk(url);
		if (bitmap1 != null)
		{
			callback.imageLoaded(bitmap1, url);
			return;
		} else
		{
			Handler handler = new Handler() {

				

				public void handleMessage(Message message)
				{
					if (200 == message.what)
					{
						Bitmap bitmap2 = (Bitmap)message.obj;
						callback.imageLoaded(bitmap2, url);
						return;
					} else
					{
						callback.imageLoaded(null, url);
						return;
					}
				}
			};
			threadPool.execute(new LoadBitmapRunnable(url, -1, handler));
			return;
		}
	}

	static 
	{
		threadPool = new ThreadPoolExecutor(6, 30, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
	}

}
