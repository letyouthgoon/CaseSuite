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

import android.os.*;
import android.util.Log;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.*;

import net.asiasofti.android.common.HttpHelper;
import net.asiasofti.android.common.MyApp;
import net.asiasofti.android.model.ResponseData;

import org.json.*;

public class RemoteDataHandler {
	public static interface Callback {

		public abstract void dataLoaded(ResponseData responsedata);
	}

	public static interface StringCallback {

		public abstract void dataLoaded(String s);
	}

	public static final String TAG = "RemoteDataLoader";
	private static final String _CODE = "code";
	private static final String _COUNT = "count";
	private static final String _DATAS = "datas";
	private static final String _HASMORE = "hasmore";
	private static final String _LOGIN = "login";
	private static final String _RESULT = "result";
	private static final String _URL = "url";
	private static ThreadPoolExecutor threadPool;

	private RemoteDataHandler() {
	}

	public static void asyncGet(final String url, final int pagesize,
			final int pageno, final Callback callback) {
		final Handler handler = new Handler() {

			public void handleMessage(Message message) {
				ResponseData responsedata = new ResponseData();
				responsedata.setCode(message.what);
				responsedata
						.setHasMore(message.getData().getBoolean("hasmore"));
				responsedata.setJson((String) message.obj);
				responsedata.setResult(message.getData().getString("result"));
				responsedata.setCount(message.getData().getLong("count"));
				callback.dataLoaded(responsedata);
			}

		};
		threadPool.execute(new Runnable() {

			public void run() {

				Message message = handler.obtainMessage(200);
				message.getData().putBoolean("hasmore", false);
				String s = (new StringBuilder(String.valueOf(url)))
						.append("&page=").append(pagesize).append("&size=")
						.append(pageno).toString();
				try {

					Thread.sleep(1000L);

					JSONObject jsonobject = new JSONObject(HttpHelper.get(s)
							.replaceAll("\\x0a|\\x0d", ""));
					if (jsonobject != null) {
						if (jsonobject.has("code")) {
							message.what = Integer.valueOf(
									jsonobject.getString("code")).intValue();
							if (jsonobject.has("datas")) {
								JSONArray jsonarray = jsonobject
										.getJSONArray("datas");
								message.obj = jsonarray.toString();
								if (pagesize == jsonarray.length())
									message.getData().putBoolean("hasmore",
											true);
							}
							if (jsonobject.has("count"))
								message.getData().putLong(
										"count",
										Long.valueOf(
												jsonobject.getString("count"))
												.longValue());
							if (jsonobject.has("result"))
								message.getData().putString("result",
										jsonobject.getString("result"));
						}
					}

				} catch (IOException ioexception) {
					message.what = 408;
					ioexception.printStackTrace();
				} catch (JSONException jsonexception) {
					message.what = 500;
					jsonexception.printStackTrace();
				} catch (InterruptedException interruptedexception) {
					interruptedexception.printStackTrace();
				}

				handler.sendMessage(message);
				return;

			}
		});
	}

	public static void asyncGet(final String url, final Callback callback) {
		final Handler handler = new Handler() {

			public void handleMessage(Message message) {
				ResponseData responsedata = new ResponseData();
				responsedata.setCode(message.what);
				responsedata
						.setHasMore(message.getData().getBoolean("hasmore"));
				responsedata.setJson((String) message.obj);
				responsedata.setResult(message.getData().getString("result"));
				responsedata.setCount(message.getData().getLong("count"));
				callback.dataLoaded(responsedata);
			}

		};
		threadPool.execute(new Runnable() {

			public void run() {
				Message message = handler.obtainMessage(200);
				message.getData().putBoolean("hasmore", false);
				try {
					String s = HttpHelper.get(url);

					if ((s != null) && (!"".equals(s))
							&& (!"null".equalsIgnoreCase(s))) {
						JSONObject jsonobject = new JSONObject(s.replaceAll(
								"\\x0a|\\x0d", ""));
						if (jsonobject != null) {

							if (jsonobject.has("code")) {
								message.what = Integer.valueOf(
										jsonobject.getString("code"))
										.intValue();
								if (jsonobject.has("datas"))
									message.obj = jsonobject.getJSONArray(
											"datas").toString();
								if (jsonobject.has("hasmore"))
									message.getData().putBoolean("hasmore",
											jsonobject.getBoolean("hasmore"));
								if (jsonobject.has("count"))
									message.getData().putLong("count",
											jsonobject.getLong("count"));
								if (jsonobject.has("result"))
									message.getData().putString("result",
											jsonobject.getString("result"));
							}
						}

					} else {
						message.what = 400;
					}

				} catch (IOException ioexception) {
					message.what = 408;
					ioexception.printStackTrace();
				} catch (JSONException jsonexception) {
					message.what = 500;
					jsonexception.printStackTrace();
				}
				handler.sendMessage(message);
				return;

			}

		});
	}

	public static void asyncGet2(final String url, final Callback callback) {
		final Handler handler = new Handler() {

			public void handleMessage(Message message) {
				ResponseData responsedata = new ResponseData();
				responsedata.setCode(message.what);
				responsedata
						.setHasMore(message.getData().getBoolean("hasmore"));
				responsedata.setJson((String) message.obj);
				responsedata.setResult(message.getData().getString("result"));
				responsedata.setCount(message.getData().getLong("count"));
				callback.dataLoaded(responsedata);
			}

		};
		threadPool.execute(new Runnable() {

			public void run() {
				Message message = handler.obtainMessage(200);
				message.getData().putBoolean("hasmore", false);

				try {
					String s = HttpHelper.get(url);
					if ((s != null) && (!"".equals(s))
							&& (!"null".equalsIgnoreCase(s))) {
						JSONObject jsonobject = new JSONObject(s.replaceAll(
								"\\x0a|\\x0d", ""));
						if (jsonobject != null) {
							if (jsonobject.has("code")) {
								message.what = Integer.valueOf(
										jsonobject.getString("code"))
										.intValue();
								if (jsonobject.has("datas"))
									message.obj = jsonobject.getJSONObject(
											"datas").toString();
								if (jsonobject.has("hasmore"))
									message.getData().putBoolean("hasmore",
											jsonobject.getBoolean("hasmore"));
								if (jsonobject.has("count"))
									message.getData().putLong("count",
											jsonobject.getLong("count"));
								if (jsonobject.has("result"))
									message.getData().putString("result",
											jsonobject.getString("result"));
							}
						}
					} else {
						message.what = 400;
					}
				} catch (IOException ioexception) {
					message.what = 408;
					ioexception.printStackTrace();
				} catch (JSONException jsonexception) {
					message.what = 500;
					jsonexception.printStackTrace();
				}
				handler.sendMessage(message);
				return;
			}

		});
	}

	public static void asyncGet3(final String url, final Callback callback) {
		final Handler handler = new Handler() {

			public void handleMessage(Message message) {
				ResponseData responsedata = new ResponseData();
				responsedata.setCode(message.what);
				responsedata
						.setHasMore(message.getData().getBoolean("hasmore"));
				responsedata.setJson((String) message.obj);
				responsedata.setResult(message.getData().getString("result"));
				responsedata.setCount(message.getData().getLong("count"));
				callback.dataLoaded(responsedata);
			}

		};
		threadPool.execute(new Runnable() {

			public void run() {
				Message message;
				message = handler.obtainMessage(200);
				message.getData().putBoolean("hasmore", false);
				try {
					JSONObject jsonobject = new JSONObject(HttpHelper.get(url)
							.replaceAll("\\x0a|\\x0d", ""));
					if (jsonobject != null) {

						if (jsonobject.has("code")) {
							message.what = Integer.valueOf(
									jsonobject.getString("code")).intValue();
							if (jsonobject.has("datas"))
								message.obj = jsonobject.getJSONObject("datas")
										.toString();
							if (jsonobject.has("hasmore"))
								message.getData().putBoolean("hasmore",
										jsonobject.getBoolean("hasmore"));
							if (jsonobject.has("count"))
								message.getData().putLong("count",
										jsonobject.getLong("count"));
							if (jsonobject.has("result"))
								message.getData().putString("result",
										jsonobject.getString("result"));
						}
					}

				} catch (IOException ioexception) {
					message.what = 408;
					ioexception.printStackTrace();
				} catch (JSONException jsonexception) {
					message.what = 500;
					jsonexception.printStackTrace();
				}
				handler.sendMessage(message);
				return;
			}

		});
	}

	public static void asyncMultipartPost(final String url,
			final HashMap params, final HashMap fileMap, final Callback callback) {
		final Handler handler = new Handler() {

			public void handleMessage(Message message) {
				ResponseData responsedata = new ResponseData();
				responsedata.setCode(message.what);
				responsedata
						.setHasMore(message.getData().getBoolean("hasmore"));
				responsedata.setJson((String) message.obj);
				responsedata.setResult(message.getData().getString("result"));
				responsedata.setCount(message.getData().getLong("count"));
				Log.d("RemoteDataLoader", responsedata.toString());
				callback.dataLoaded(responsedata);
			}

		};
		threadPool.execute(new Runnable() {

			public void run() {
				Message message;
				message = handler.obtainMessage(200);
				message.getData().putBoolean("hasMore", false);
				try {
					JSONObject jsonobject = new JSONObject(HttpHelper
							.multipartPost(url, params, fileMap).replaceAll(
									"\\x0a|\\x0d", ""));
					if (jsonobject != null) {

						if (jsonobject.has("code")) {
							message.what = Integer.valueOf(
									jsonobject.getString("code")).intValue();
							if (jsonobject.has("datas"))
								message.obj = jsonobject.getJSONArray("datas")
										.toString();
							if (jsonobject.has("result"))
								message.getData().putString("result",
										jsonobject.getString("result"));
						}
					}

				} catch (IOException ioexception) {
					message.what = 408;
					ioexception.printStackTrace();
				} catch (JSONException jsonexception) {
					message.what = 500;
					jsonexception.printStackTrace();
				}
				handler.sendMessage(message);
				return;
			}

		});
	}

	public static void asyncPost(final String url, final HashMap params,
			final Callback callback) {
		final Handler handler = new Handler() {

			public void handleMessage(Message message) {
				ResponseData responsedata = new ResponseData();
				responsedata.setCode(message.what);
				responsedata
						.setHasMore(message.getData().getBoolean("hasmore"));
				responsedata.setJson((String) message.obj);
				responsedata.setResult(message.getData().getString("result"));
				responsedata.setCount(message.getData().getLong("count"));
				callback.dataLoaded(responsedata);
			}

		};
		threadPool.execute(new Runnable() {

			public void run() {
				Message message = handler.obtainMessage(200);
				message.getData().putBoolean("hasMore", false);
				try {
					JSONObject jsonobject = new JSONObject(HttpHelper.post(url,
							params).replaceAll("\\x0a|\\x0d", ""));
					if (jsonobject != null) {

						if (jsonobject.has("code")) {
							message.what = Integer.valueOf(
									jsonobject.getString("code")).intValue();
							if (jsonobject.has("datas"))
								message.obj = jsonobject.getJSONArray("datas")
										.toString();
							if (jsonobject.has("result"))
								message.getData().putString("result",
										jsonobject.getString("result"));
						}
					}

				} catch (IOException ioexception) {
					message.what = 408;
					ioexception.printStackTrace();
				} catch (JSONException jsonexception) {
					message.what = 500;
					jsonexception.printStackTrace();
				}
				handler.sendMessage(message);
				return;
			}

		});
	}

	public static void asyncPost2(final String url, final HashMap params,
			final Callback callback) {
		final Handler handler = new Handler() {

			public void handleMessage(Message message) {
				ResponseData responsedata = new ResponseData();
				responsedata.setCode(message.what);
				responsedata
						.setHasMore(message.getData().getBoolean("hasmore"));
				responsedata.setJson((String) message.obj);
				responsedata.setResult(message.getData().getString("result"));
				responsedata.setCount(message.getData().getLong("count"));
				responsedata.setLogin(message.getData().getInt("login"));
				callback.dataLoaded(responsedata);
			}

		};
		threadPool.execute(new Runnable() {

			public void run() {
				Message message = handler.obtainMessage(200);
				message.getData().putBoolean("hasMore", false);
				try {
					JSONObject jsonobject = new JSONObject(HttpHelper.post(url,
							params).replaceAll("\\x0a|\\x0d", ""));
					if (jsonobject != null) {
						if (jsonobject.has("code")) {
							message.what = Integer.valueOf(
									jsonobject.getString("code")).intValue();
							if (jsonobject.has("datas"))
								message.obj = jsonobject.getString("datas");
							if (jsonobject.has("hasmore"))
								message.getData().putBoolean("hasmore",
										jsonobject.getBoolean("hasmore"));
							if (jsonobject.has("login")) {
								message.getData().putInt(
										"login",
										Integer.parseInt(jsonobject
												.getString("login")));
							} else {
								message.getData().putInt("login", -1);
							}
							if (jsonobject.has("result"))
								message.getData().putString("result",
										jsonobject.getString("result"));

						}

					}
				} catch (IOException e) {
					message.what = 408;
					e.printStackTrace();
				} catch (JSONException e) {
					message.what = 500;
					e.printStackTrace();
				}
				handler.sendMessage(message);
				return;
			}

		});
	}

	public static void asyncStringGet(final String url, final Callback callback) {
		final Handler handler = new Handler() {

			public void handleMessage(Message message) {
				ResponseData responsedata = new ResponseData();
				responsedata.setCode(message.what);
				responsedata
						.setHasMore(message.getData().getBoolean("hasmore"));
				responsedata.setJson((String) message.obj);
				responsedata.setResult(message.getData().getString("result"));
				responsedata.setCount(message.getData().getLong("count"));
				callback.dataLoaded(responsedata);
			}

		};
		threadPool.execute(new Runnable() {
			public void run() {

				Message message = handler.obtainMessage(200);
				message.getData().putBoolean("hasmore", false);
				try {
					String s = HttpHelper.get(url);
					System.out.println("列表数据返回：" + s);
					if ((s != null) && (!"".equals(s))
							&& (!"null".equalsIgnoreCase(s))) {
						JSONObject jsonobject = new JSONObject(s.replaceAll(
								"\\x0a|\\x0d", ""));
						if (jsonobject != null) {
							if (jsonobject.has("code")) {
								message.what = Integer.valueOf(
										jsonobject.getString("code"))
										.intValue();
								if (jsonobject.has("datas"))
									message.obj = jsonobject.getString("datas");
								if (jsonobject.has("hasmore"))
									message.getData().putBoolean("hasmore",
											jsonobject.getBoolean("hasmore"));
								if (jsonobject.has("count"))
									message.getData().putLong("count",
											jsonobject.getLong("count"));
								if (jsonobject.has("result"))
									message.getData().putString("result",
											jsonobject.getString("result"));

							}
						}
					} else {
						message.what = 500;
					}
				} catch (IOException ioexception) {
					message.what = 408;
					ioexception.printStackTrace();
				} catch (JSONException jsonexception) {
					message.what = 500;
					jsonexception.printStackTrace();
				}
				handler.sendMessage(message);
				return;

			}

		});
	}

	public static ResponseData get(String s) {
		ResponseData responsedata = new ResponseData();
		JSONObject jsonobject = null;
		try {

			jsonobject = new JSONObject(HttpHelper.get(s).replaceAll(
					"\\x0a|\\x0d", ""));
			if (jsonobject != null) {
				if (jsonobject.has("code")) {
					responsedata.setCode(jsonobject.getInt("code"));
					if (jsonobject.has("datas"))
						responsedata.setJson(jsonobject.getJSONArray("datas")
								.toString());
					if (jsonobject.has("hasmore"))
						responsedata.setHasMore(jsonobject
								.getBoolean("hasmore"));
					if (jsonobject.has("result"))
						responsedata.setResult(jsonobject.getString("result"));
					if (jsonobject.has("count"))
						responsedata.setCount(jsonobject.getLong("count"));
				}
			}
			
		} catch (IOException ioexception) {
			responsedata.setCode(408);
			ioexception.printStackTrace();
			return responsedata;
		} catch (JSONException jsonexception) {
			responsedata.setCode(500);
			jsonexception.printStackTrace();
			return responsedata;
		}

		return responsedata;
	}

	public static ResponseData post(String s, HashMap hashmap) {
		ResponseData responsedata = new ResponseData();
		JSONObject jsonobject = null;
		try {
			jsonobject = new JSONObject(HttpHelper.post(s, hashmap).replaceAll(
					"\\x0a|\\x0d", ""));
			if (jsonobject != null) {
				if (jsonobject.has("code")) {
					responsedata.setCode(jsonobject.getInt("code"));
					if (jsonobject.has("datas"))
						responsedata.setJson(jsonobject.getJSONArray("datas")
								.toString());
					if (jsonobject.has("hasmore"))
						responsedata.setHasMore(jsonobject
								.getBoolean("hasmore"));
					if (jsonobject.has("result"))
						responsedata.setResult(jsonobject.getString("result"));
					if (jsonobject.has("count"))
						responsedata.setCount(jsonobject.getLong("count"));
				}
			}
		} catch (IOException ioexception) {
			responsedata.setCode(408);
			ioexception.printStackTrace();
			return responsedata;
		} catch (JSONException jsonexception) {
			responsedata.setCode(500);
			jsonexception.printStackTrace();
			return responsedata;
		}

		return responsedata;
	}

	static {
		threadPool = new ThreadPoolExecutor(6, 30, 30L, TimeUnit.SECONDS,
				new LinkedBlockingQueue());
	}
}
