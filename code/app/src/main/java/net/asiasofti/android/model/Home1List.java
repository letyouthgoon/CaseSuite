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

import java.util.ArrayList;
import org.json.*;

public class Home1List {
	public static class Attr {

		public static final String DESC = "desc";
		public static final String IMAGE = "image";
		public static final String KEYWORD = "keyword";
		public static final String KEYWORD1 = "keyword1";
		public static final String TITLE = "title";

		public Attr() {
		}
	}

	private String desc;
	private String image;
	private String keyword;
	private String keyword1;
	private String title;

	public Home1List() {
	}

	public Home1List(String s, String s1, String s2, String s3, String s4) {
		image = s;
		title = s1;
		desc = s2;
		keyword = s3;
		keyword1 = s4;
	}

	public static ArrayList newInstanceList(String s) {
		ArrayList arraylist = new ArrayList();

		try {
			JSONArray jsonarray = new JSONArray(s);
			if (jsonarray != null) {
				for (int i = 0; i <= jsonarray.length()-1; i++) {
					JSONObject jsonobject = jsonarray.getJSONObject(i);
					arraylist.add(new Home1List(jsonobject.optString("image"),
							jsonobject.optString("title"), jsonobject
									.optString("desc"), jsonobject
									.optString("keyword"), jsonobject
									.optString("keyword1")));
				}
			}
		} catch (JSONException jsonexception) {
			jsonexception.printStackTrace();
		}

		return arraylist;
	}

	public String getDesc() {
		return desc;
	}

	public String getImage() {
		return image;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public String getTitle() {
		return title;
	}

	public void setDesc(String s) {
		desc = s;
	}

	public void setImage(String s) {
		image = s;
	}

	public void setKeyword(String s) {
		keyword = s;
	}

	public void setKeyword1(String s) {
		keyword1 = s;
	}

	public void setTitle(String s) {
		title = s;
	}

	public String toString() {
		return (new StringBuilder("Home1List [image=")).append(image)
				.append(", title=").append(title).append(", desc=")
				.append(desc).append(", keyword=").append(keyword)
				.append(", keyword1=").append(keyword1).append("]").toString();
	}
}
