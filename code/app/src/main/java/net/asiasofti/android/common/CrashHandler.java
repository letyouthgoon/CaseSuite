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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.*;
import android.os.Process;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

	private static final String CRASH_REPORTER_EXTENSION = ".cr";
	public static final boolean DEBUG = false;
	private static CrashHandler INSTANCE;
	private static final String STACK_TRACE = "STACK_TRACE";
	public static final String TAG = "CrashHandler";
	private static final String VERSION_CODE = "versionCode";
	private static final String VERSION_NAME = "versionName";
	private Context mContext;
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	private Properties mDeviceCrashInfo;

	private CrashHandler() {
		mDeviceCrashInfo = new Properties();
	}

	private String[] getCrashReportFiles(Context context) {
		return context.getFilesDir().list(new FilenameFilter() {

			public boolean accept(File file, String s) {
				return s.endsWith(".cr");
			}

		});
	}

	public static CrashHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CrashHandler();
		return INSTANCE;
	}

	private boolean handleException(Throwable throwable) {
		if (throwable == null) {
			Log.w("CrashHandler", "handleException --- ex==null");
			return true;
		}
		final String msg = throwable.getLocalizedMessage();
		if (msg == null) {
			return false;
		} else {
			(new Thread() {

				public void run() {
					Looper.prepare();
					Toast toast = Toast.makeText(mContext, (new StringBuilder(
							"程序出错，即将退出:\r\n")).append(msg).toString(), 1);
					toast.setGravity(17, 0, 0);
					toast.show();
					Looper.loop();
				}

			}).start();
			collectCrashDeviceInfo(mContext);
			saveCrashInfoToFile(throwable);
			return true;
		}
	}

	private void postReport(File file) {
	}

	private void promptMessageDialog() {
		(new Thread() {

			public void run() {
				(new android.app.AlertDialog.Builder(mContext))
						.setTitle("Error")
						.setCancelable(false)
						.setMessage(
								"The system was crashed. Please restart it later.")
						.setNeutralButton(
								"OK",
								new android.content.DialogInterface.OnClickListener() {

									public void onClick(
											DialogInterface dialoginterface,
											int i) {
										System.exit(-1);
									}

								}).create().show();
			}

		}).start();
	}

	private String saveCrashInfoToFile(Throwable throwable) {
		StringWriter stringwriter = new StringWriter();
		PrintWriter printwriter = new PrintWriter(stringwriter);
		throwable.printStackTrace(printwriter);
		Throwable throwable1 = throwable.getCause();
		do {
			if (throwable1 == null) {
				String s = stringwriter.toString();
				printwriter.close();
				mDeviceCrashInfo.put("EXEPTION",
						throwable.getLocalizedMessage());
				mDeviceCrashInfo.put("STACK_TRACE", s);
				String s1;
				try {
					Time time = new Time("GMT+8");
					time.setToNow();
					int i = 10000 * time.year + 100 * time.month
							+ time.monthDay;
					int j = 10000 * time.hour + 100 * time.minute + time.second;
					s1 = (new StringBuilder("crash-")).append(i).append("-")
							.append(j).append(".cr").toString();
					FileOutputStream fileoutputstream = mContext
							.openFileOutput(s1, 0);
					mDeviceCrashInfo.store(fileoutputstream, "");
					fileoutputstream.flush();
					fileoutputstream.close();
				} catch (Exception exception) {
					Log.e("CrashHandler",
							"an error occured while writing report file...",
							exception);
					return null;
				}
				return s1;
			}
			throwable1.printStackTrace(printwriter);
			throwable1 = throwable1.getCause();
		} while (true);
	}

	private void sendCrashReportsToServer(Context context) {
		String as[] = getCrashReportFiles(context);
		if (as == null || as.length <= 0) {
			return;
		}
		TreeSet treeset = new TreeSet();
		treeset.addAll(Arrays.asList(as));
		Iterator iterator = treeset.iterator();

		while (iterator.hasNext()) {
			String s = (String) iterator.next();
			File file = new File(context.getFilesDir(), s);
			postReport(file);
			file.delete();
		}
	}

	public void collectCrashDeviceInfo(Context context) {
		try {
			PackageInfo packageinfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 1);
			String s = "";
			if (packageinfo == null) {
				Field[] afield = Build.class.getDeclaredFields();
				for (int i = 0; i <= afield.length - 1; i++) {
					Field field = afield[i];
					try {
						field.setAccessible(true);
						mDeviceCrashInfo.put(field.getName(),
								(new StringBuilder()).append(field.get(null))
										.toString());
					} catch (Exception exception) {
						Log.e("CrashHandler", "Error while collect crash info",
								exception);
					}
				}

			} else {
				Properties properties = mDeviceCrashInfo;
				if (packageinfo.versionName != null) {
		
					s = packageinfo.versionName;

				} else {
		
					s = "not set";
				}

				properties.put("versionName", s);
				mDeviceCrashInfo.put("versionCode", (new StringBuilder())
						.append(packageinfo.versionCode).toString());

			}
		} catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) {
			Log.e("CrashHandler", "Error while collect package info",
					namenotfoundexception);
		}

	}

	public void init(Context context) {
		mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public void sendPreviousReportsToServer() {
		sendCrashReportsToServer(mContext);
	}

	 public void uncaughtException(Thread paramThread, Throwable paramThrowable)
	  {
	    if ((!handleException(paramThrowable)) && (this.mDefaultHandler != null))
	    {
	      this.mDefaultHandler.uncaughtException(paramThread, paramThrowable);
	      return;
	    }
	    try
	    {
	      Thread.sleep(5000L);
	      Process.killProcess(Process.myPid());
	      System.exit(10);
	      return;
	    }
	    catch (InterruptedException localInterruptedException)
	    {
	        Log.e("CrashHandler", "Error : ", localInterruptedException);
	    }
	  }

}
