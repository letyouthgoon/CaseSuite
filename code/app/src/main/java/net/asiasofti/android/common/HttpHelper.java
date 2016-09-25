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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.*;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.*;
import org.apache.http.util.EntityUtils;

public class HttpHelper {
	private static class FlushedInputStream extends FilterInputStream {
		public FlushedInputStream(InputStream paramInputStream) {
			super(paramInputStream);
		}

		public long skip(long paramLong) throws IOException {
			long l2 = 1L;
			for (long l1 = 0L;; l1 += l2) {
				if (l1 >= paramLong)
					;
				do {
					l2 = this.in.skip(paramLong - l1);
					if (l2 != 0L)
						break;
					return l1;
				} while (read() < 0);

			}
		}
	}

	private static HttpClient httpClient;

	public HttpHelper() {
	}

	public static void download(String paramString, File paramFile)
			throws IOException {
		HttpGet localHttpGet = new HttpGet(paramString);
		HttpResponse localHttpResponse = httpClient.execute(localHttpGet);
		HttpEntity localHttpEntity;
		InputStream localInputStream;
		byte[] arrayOfByte;
		if (200 == localHttpResponse.getStatusLine().getStatusCode()) {
			localHttpEntity = localHttpResponse.getEntity();
			if (localHttpEntity != null) {
				localInputStream = null;
				arrayOfByte = new byte[4096];

				localInputStream = localHttpEntity.getContent();
				BufferedOutputStream localBufferedOutputStream2 = new BufferedOutputStream(
						new FileOutputStream(paramFile));
				try {
					while (true) {
						int i = localInputStream.read(arrayOfByte);
						if (i == -1) {
							localBufferedOutputStream2.flush();
							if (localInputStream != null)
								localInputStream.close();
							if (localBufferedOutputStream2 != null)
								localBufferedOutputStream2.close();
							localHttpEntity.consumeContent();
							return;
						}
						localBufferedOutputStream2.write(arrayOfByte, 0, i);
					}

				} finally {
					localHttpEntity.consumeContent();
					if (localInputStream != null)
						localInputStream.close();
				}
			}
		}
	}

	public static Bitmap downloadBitmap(String paramString) throws IOException {
		HttpGet localHttpGet = new HttpGet(paramString);
		HttpResponse localHttpResponse = httpClient.execute(localHttpGet);
		if (200 == localHttpResponse.getStatusLine().getStatusCode()) {
			HttpEntity localHttpEntity = localHttpResponse.getEntity();
			Object localObject1 = null;
			InputStream localInputStream = null;
			if (localHttpEntity != null)
				localInputStream = null;
			try {
				localInputStream = localHttpEntity.getContent();
				Bitmap localBitmap = BitmapFactory
						.decodeStream(new FlushedInputStream(localInputStream));
				localObject1 = localBitmap;
				return (Bitmap) localObject1;
			} catch (IOException localIOException) {
				throw localIOException;
			} finally {
				if (localInputStream != null)
					localInputStream.close();
				localHttpEntity.consumeContent();
			}
		}
		return null;
	}

	public static String get(String s) throws IOException {
		HttpGet httpget = new HttpGet(s);
		HttpResponse httpresponse = httpClient.execute(httpget);
		int i = httpresponse.getStatusLine().getStatusCode();
		String s1 = null;
		if (200 == i)
			s1 = EntityUtils.toString(httpresponse.getEntity());
		return s1;
	}

	public static HttpClient getHttpClient() {
		return httpClient;
	}

	public static Drawable loadDrawable(String paramString1, String paramString2)
			throws IOException {
		HttpGet localHttpGet = new HttpGet(paramString1);
		HttpResponse localHttpResponse = httpClient.execute(localHttpGet);
		int i = localHttpResponse.getStatusLine().getStatusCode();
		HttpEntity localHttpEntity;
		InputStream localInputStream = null;
		if (200 == i) {
			localHttpEntity = localHttpResponse.getEntity();

			if (localHttpEntity != null)
				localInputStream = null;
			try {
				localInputStream = localHttpEntity.getContent();
				Drawable localDrawable = Drawable.createFromStream(
						localInputStream, paramString2);
				return localDrawable;
			} catch (IOException localIOException) {
				throw localIOException;
			} finally {
				if (localInputStream != null)
					localInputStream.close();
				localHttpEntity.consumeContent();
			}
		}
		return null;

	}

	public static String multipartPost(String s, HashMap hashmap,
			HashMap hashmap1) throws IOException {
		HttpPost httppost;
		MultipartEntity multipartentity;
		httppost = new HttpPost(s);
		multipartentity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		if (hashmap != null) {
			Iterator iterator1 = hashmap.entrySet().iterator();
			while (iterator1.hasNext()) {
				java.util.Map.Entry entry1 = (java.util.Map.Entry) iterator1
						.next();
				multipartentity.addPart(
						(String) entry1.getKey(),
						new StringBody((String) entry1.getValue(), Charset
								.forName("UTF-8")));
			}
		}
		if (hashmap1 != null) {
			Iterator iterator = hashmap1.entrySet().iterator();
			while (iterator.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) iterator
						.next();
				multipartentity.addPart((String) entry.getKey(), new FileBody(
						(File) entry.getValue()));
			}
		}

		httppost.setEntity(multipartentity);
		HttpResponse httpresponse = httpClient.execute(httppost);
		int i = httpresponse.getStatusLine().getStatusCode();
		String s1 = null;
		if (200 == i)
			s1 = EntityUtils.toString(httpresponse.getEntity());
		return s1;
	}

	public static String post(String s, HashMap hashmap) throws IOException {
		HttpPost httppost = new HttpPost(s);
		ArrayList arraylist = new ArrayList();
		if (hashmap != null) {

			Iterator iterator = hashmap.entrySet().iterator();
			while (iterator.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) iterator
						.next();
				arraylist.add(new BasicNameValuePair((String) entry.getKey(),
						(String) entry.getValue()));
			}
		}

		httppost.setEntity(new UrlEncodedFormEntity(arraylist, "UTF-8"));
		HttpResponse httpresponse = httpClient.execute(httppost);
		int i = httpresponse.getStatusLine().getStatusCode();
		String s1 = null;
		if (200 == i)
			s1 = EntityUtils.toString(httpresponse.getEntity());
		return s1;
	}

	static {
		if (httpClient == null) {
			BasicHttpParams basichttpparams = new BasicHttpParams();
			HttpProtocolParams
					.setVersion(basichttpparams, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(basichttpparams, "UTF-8");
			HttpProtocolParams.setUseExpectContinue(basichttpparams, true);
			ConnManagerParams.setMaxTotalConnections(basichttpparams, 10000);
			ConnManagerParams.setTimeout(basichttpparams, 60000L);
			ConnManagerParams.setMaxConnectionsPerRoute(basichttpparams,
					new ConnPerRouteBean(10000));
			HttpConnectionParams.setConnectionTimeout(basichttpparams, 20000);
			HttpConnectionParams.setSoTimeout(basichttpparams, 30000);
			SchemeRegistry schemeregistry = new SchemeRegistry();
			schemeregistry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			schemeregistry.register(new Scheme("https", SSLSocketFactory
					.getSocketFactory(), 443));
			httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(
					basichttpparams, schemeregistry), basichttpparams);
		}
	}
}
