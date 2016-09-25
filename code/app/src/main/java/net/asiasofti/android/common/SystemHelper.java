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

import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.*;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;

public class SystemHelper
{

	private SystemHelper()
	{
	}

	public static void createShortcut(Context context, Class class1)
	{
		Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		intent.putExtra("android.intent.extra.shortcut.NAME", context.getString(0x7f060000));
		intent.putExtra("duplicate", false);
		Intent intent1 = new Intent(context, class1);
		intent1.setAction("android.intent.action.MAIN");
		intent1.addCategory("android.intent.category.LAUNCHER");
		intent.putExtra("android.intent.extra.shortcut.INTENT", intent1);
		intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", android.content.Intent.ShortcutIconResource.fromContext(context, 0x7f020041));
		context.sendBroadcast(intent);
	}

	public static int getAppVersionCode(Context context)
	{
		int i;
		try
		{
			i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		}
		catch (Exception exception)
		{
			Log.e("VersionInfo", "Exception", exception);
			return -1;
		}
		return i;
	}

	public static String getAppVersionName(Context context)
	{
		String s;
		try
		{
			s = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		}
		catch (Exception exception)
		{
			Log.e("VersionInfo", "Exception", exception);
			return "";
		}
		return s;
	}

	public static String getMobileNumber(Context context)
	{
		return ((TelephonyManager)context.getSystemService("phone")).getLine1Number();
	}

	 public static int getNetworkType(Context paramContext)
	  {
	    try
	    {
	      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
	      if (localConnectivityManager != null)
	      {
	        NetworkInfo.State localState1 = localConnectivityManager.getNetworkInfo(1).getState();
	        if (NetworkInfo.State.CONNECTED == localState1)
	          return 1;
	        NetworkInfo.State localState2 = localConnectivityManager.getNetworkInfo(0).getState();
	        NetworkInfo.State localState3 = NetworkInfo.State.CONNECTED;
	        if (localState3 == localState2)
	          return 0;
	      }
	    }
	    catch (Exception localException)
	    {
	      Log.e("NetworkInfo", "Exception", localException);
	    }
	    return -1;
	  }

	public static DisplayMetrics getScreenInfo(Context context)
	{
		WindowManager windowmanager = (WindowManager)context.getSystemService("window");
		DisplayMetrics displaymetrics = new DisplayMetrics();
		windowmanager.getDefaultDisplay().getMetrics(displaymetrics);
		return displaymetrics;
	}

	public static boolean hasShortCut(Context context)
	{
		String s;
		ContentResolver contentresolver;
		Uri uri;
		String as[];
		Cursor cursor;
		if (android.os.Build.VERSION.SDK_INT < 8)
			s = "content://com.android.launcher.settings/favorites?notify=true";
		else
			s = "content://com.android.launcher2.settings/favorites?notify=true";
		contentresolver = context.getContentResolver();
		uri = Uri.parse(s);
		as = new String[1];
		as[0] = context.getString(0x7f060000);
		cursor = contentresolver.query(uri, null, "title=?", as, null);
		if (cursor != null && cursor.moveToFirst())
		{
			cursor.close();
			return true;
		} else
		{
			return false;
		}
	}

	public static void installAPK(Context context, String s)
	{
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setDataAndType(Uri.fromFile(new File(s)), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	  public static boolean isConnected(Context paramContext)
	  {
	    try
	    {
	      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
	      boolean bool1 = false;
	      if (localConnectivityManager != null)
	      {
	        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
	        bool1 = false;
	        if (localNetworkInfo != null)
	        {
	          boolean bool2 = localNetworkInfo.isAvailable();
	          bool1 = false;
	          if (bool2)
	            bool1 = true;
	        }
	      }
	      return bool1;
	    }
	    catch (Exception localException)
	    {
	      Log.e("NetworkInfo", "Exception", localException);
	    }
	    return false;
	  }
}
