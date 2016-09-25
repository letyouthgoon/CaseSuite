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
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateAddress {
	public static class Attr {

		public static final String ALLOW_OFFPAY = "allow_offpay";
		public static final String CONTENT = "content";
		public static final String OFFPAY_HASH = "offpay_hash";

		public Attr() {
		}
	}

	private String allow_offpay;
	private String content;
	private String offpay_hash;

	public UpdateAddress() {
	}

	public UpdateAddress(String s, String s1, String s2) {
		allow_offpay = s;
		content = s1;
		offpay_hash = s2;
	}

	public static UpdateAddress newInstanceList(String s) {
		UpdateAddress updateaddress = null;
		try {
			JSONObject jsonobject = new JSONObject(s);
			int i = jsonobject.length();
			if (i > 0) {
				updateaddress = new UpdateAddress(
						jsonobject.optString("allow_offpay"),
						jsonobject.optString("content"),
						jsonobject.optString("offpay_hash"));
				System.out.println((new StringBuilder("bean-->")).append(
						updateaddress.toString()).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return updateaddress;

	}

	public String getAllow_offpay() {
		return allow_offpay;
	}

	public String getContent() {
		return content;
	}

	public String getOffpay_hash() {
		return offpay_hash;
	}

	public void setAllow_offpay(String s) {
		allow_offpay = s;
	}

	public void setContent(String s) {
		content = s;
	}

	public void setOffpay_hash(String s) {
		offpay_hash = s;
	}

	public String toString() {
		return (new StringBuilder("UpdateAddress [allow_offpay="))
				.append(allow_offpay).append(", content=").append(content)
				.append(", offpay_hash=").append(offpay_hash).append("]")
				.toString();
	}
}
