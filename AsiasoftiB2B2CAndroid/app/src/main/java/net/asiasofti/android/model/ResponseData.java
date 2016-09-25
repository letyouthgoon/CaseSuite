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

public class ResponseData {
	public static final class Attr {

		public static final String CODE = "code";
		public static final String COUNT = "count";
		public static final String HASMORE = "hasmore";
		public static final String JSON = "json";
		public static final String LOGIN = "login";
		public static final String RESULT = "result";

		public Attr() {
		}
	}

	private int code;
	private long count;
	private boolean hasMore;
	private String json;
	private int login;
	private String result;

	public ResponseData() {
		login = -1;
	}

	public int getCode() {
		return code;
	}

	public long getCount() {
		return count;
	}

	public String getJson() {
		return json;
	}

	public int getLogin() {
		return login;
	}

	public String getResult() {
		return result;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public void setCode(int i) {
		code = i;
	}

	public void setCount(long l) {
		count = l;
	}

	public void setHasMore(boolean flag) {
		hasMore = flag;
	}

	public void setJson(String s) {
		json = s;
	}

	public void setLogin(int i) {
		login = i;
	}

	public void setResult(String s) {
		result = s;
	}

	public String toString() {
		return (new StringBuilder("ResponseData [code=")).append(code)
				.append(", hasMore=").append(hasMore).append(", json=")
				.append(json).append(", result=").append(result)
				.append(", count=").append(count).append("]").toString();
	}
}
