package net.asiasofti.android.ui.viewpager;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class SyncImageLoader {

	private static final String TAG = "SyncImageLoader";
	private Object lock = new Object();
	
	private boolean mAllowLoad = true;
	
	private boolean firstLoad = true;
	
	private int mStartLoadLimit = 0;
	
	private int mStopLoadLimit = 0;
	
	final Handler handler = new Handler();
	
	private HashMap<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();   
	
	public interface OnImageLoadListener {
		public void onImageLoad(Integer t, Drawable drawable);
		public void onError(Integer t);
	}
	
	public void setLoadLimit(int startLoadLimit,int stopLoadLimit){
		if(startLoadLimit > stopLoadLimit){
			return;
		}
		mStartLoadLimit = startLoadLimit;
		mStopLoadLimit = stopLoadLimit;
	}
	
	public void restore(){
		mAllowLoad = true;
		firstLoad = true;
	}
		
	public void lock(){
		mAllowLoad = false;
		firstLoad = false;
	}
	
	public void unlock(){
		mAllowLoad = true;
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	public void loadImage(Integer t, String imageUrl,OnImageLoadListener listener) {
		final OnImageLoadListener mListener = listener;
		final String mImageUrl = imageUrl;
		final Integer mt = t;
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Log.i(TAG,"进入线程，下载图片,url="+ mImageUrl);
				if(!mAllowLoad){
					//DebugUtil.debug("prepare to load");
					synchronized (lock) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				if(mAllowLoad && firstLoad){
					//Log.i(TAG,"下载图片。。。");
					loadImage(mImageUrl, mt, mListener);
				}
				
				if(mAllowLoad && mt <= mStopLoadLimit && mt >= mStartLoadLimit){
					loadImage(mImageUrl, mt, mListener);
				}
			}

		}).start();
	}
	
	private void loadImage(final String mImageUrl,final Integer mt,final OnImageLoadListener mListener){
		
		if (imageCache.containsKey(mImageUrl)) {  //检查图片是否存在
            SoftReference<Drawable> softReference = imageCache.get(mImageUrl);  
            final Drawable d = softReference.get();  
            if (d != null) {  
            	handler.post(new Runnable() {
    				@Override
    				public void run() {
    					if(mAllowLoad){
    						mListener.onImageLoad(mt, d);
    					}
    				}
    			});
                return;  
            }  
        }  
		try {
			final Drawable d = loadImageFromUrl(mImageUrl);
			if(d != null){
				Log.i(TAG,"下载图片非空");
                imageCache.put(mImageUrl, new SoftReference<Drawable>(d));
			}
			handler.post(new Runnable() {
				@Override
				public void run() {
					if(mAllowLoad){
						mListener.onImageLoad(mt, d);
					}
				}
			});
		} catch (IOException e) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					mListener.onError(mt);
				}
			});
			e.printStackTrace();
		}
	}

	public static Drawable loadImageFromUrl(String url) throws IOException {
		//如果有外部SD卡存储，则将缓存在SD卡中
		//Log.i(TAG,"IMG URL="+ url);
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			String s[] = url.split("/");
			Log.d(TAG, "URL=" + url);
			Log.d(TAG,"s.length=" + s.length);
			String filename = s[s.length-1];
			Log.i(TAG,"filename="+ filename);
			File f = new File(Environment.getExternalStorageDirectory()+"/aDemo/"+filename);
			File parent = f.getParentFile();
			if(!parent.exists()){
				parent.mkdirs();
			}
			if(f.exists()){ //如果文件存在，则返回文件Drawable
				FileInputStream fis = new FileInputStream(f);
				Drawable d = Drawable.createFromStream(fis, "src");
				return d;
			}
			URL m = new URL(url);
			InputStream i = (InputStream) m.getContent();
			DataInputStream in = new DataInputStream(i);
			//Log.i(TAG,"写入SD卡缓存之");
			FileOutputStream out = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int   byteread=0;
			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			in.close();
			out.close();
			Drawable d = Drawable.createFromStream(i, "src");
			return loadImageFromUrl(url);
		}else{
			URL m = new URL(url);
			InputStream i = (InputStream) m.getContent();
			Drawable d = Drawable.createFromStream(i, "src");
			return d;
		}
		
	}
}