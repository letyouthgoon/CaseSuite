 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.model;

import java.io.PrintStream;
import java.util.ArrayList;
import org.json.*;

public class AdvertList {
	public static class Attr {

		public static final String IMAGE = "image";
		public static final String KEYWORD = "keyword";

		public Attr() {
		}
	}

	private String image;
	private String keyword;

	public AdvertList() {
	}

	public AdvertList(String s, String s1) {
		image = s;
		keyword = s1;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();
		try {
			JSONArray jsonarray = new JSONArray(s);
			if (jsonarray != null) {
				for (int i = 0; i <= jsonarray.length() - 1; i++) {
					JSONObject jsonobject = jsonarray.getJSONObject(i);
					String img = jsonobject.optString("image");
					String keyword = jsonobject.optString("keyword");
					arraylist.add(new AdvertList(img, keyword));
					System.out.println((new StringBuilder("image=")).append(img)
							.append(",class_id=").append(keyword).toString());
				}
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();

		}
		return arraylist;

	}

	public String getImage() {
		return image;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setImage(String s) {
		image = s;
	}

	public void setKeyword(String s) {
		keyword = s;
	}

	public String toString() {
		return (new StringBuilder("AdvertList [image=")).append(image)
				.append(", keyword=").append(keyword).append("]").toString();
	}
}
