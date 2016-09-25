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

import java.io.*;

import org.json.*;

public class JSONHelper {

	public JSONHelper() {
	}

	public static JSONArray getFaces(Context context) {
		BufferedReader bufferedreader = null;
		try {

			bufferedreader = new BufferedReader(new InputStreamReader(context
					.getAssets().open("faces/facetab.txt"), "UTF-8"));

			StringBuilder stringbuilder = new StringBuilder();
			while (true) {
				String s = bufferedreader.readLine();
				if (s == null) {
					break;
				} else {
					stringbuilder.append(s);
				}
			}

			try {
				JSONObject jsonobject = new JSONObject(stringbuilder.toString());
				boolean flag = jsonobject.has("child");
				JSONArray jsonarray = null;
				if (flag) {
					JSONArray jsonarray1 = jsonobject.getJSONArray("child");
					return jsonarray1;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedreader != null) {
				try {
					bufferedreader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;

	}

	public static String getNickName(String s) {

		try {
			JSONArray jsonarray = new JSONArray(s);
			if (jsonarray != null) {
				int i = jsonarray.length();
				if (i > 0) {
					JSONObject jsonobject = (JSONObject) jsonarray.get(0);
					if (jsonobject.has("name")) {
						return jsonobject.getString("name");
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static void main(String args[]) {
	}

	public static String string2Json(String s) {

		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i <= s.length() - 1; i++) {
			char c = s.charAt(i);
			switch (c) {

			case '"':
				localStringBuffer.append("\\\"");
				continue;
			case '\\':
				localStringBuffer.append("\\\\");
				continue;
			case '/':
				localStringBuffer.append("\\/");
				continue;
			case '\b':
				localStringBuffer.append("\\b");
				continue;
			case '\f':
				localStringBuffer.append("\\f");
				continue;
			case '\n':
				localStringBuffer.append("\\n");
				continue;
			case '\r':
				localStringBuffer.append("\\r");
				continue;
			case '\t':
				localStringBuffer.append("\\t");
				continue;

			default:
				localStringBuffer.append(c);
			}
		}
		return localStringBuffer.toString();
	}
}
