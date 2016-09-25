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

public class InvoiceInFO {
	public static class Attr {

		public static final String CONTENT = "content";
		public static final String INV_CODE = "inv_code";
		public static final String INV_COMPANY = "inv_company";
		public static final String INV_CONTENT = "inv_content";
		public static final String INV_GOTO_ADDR = "inv_goto_addr";
		public static final String INV_ID = "inv_id";
		public static final String INV_REC_MOBPHONE = "inv_rec_mobphone";
		public static final String INV_REC_NAME = "inv_rec_name";
		public static final String INV_REC_PROVINCE = "inv_rec_province";
		public static final String INV_REG_ADDR = "inv_reg_addr";
		public static final String INV_REG_BACCOUNT = "inv_reg_baccount";
		public static final String INV_REG_BNAME = "inv_reg_bname";
		public static final String INV_REG_PHONE = "inv_reg_phone";
		public static final String INV_STATE = "inv_state";
		public static final String INV_TITLE = "inv_title";
		public static final String MEMBER_ID = "member_id";

		public Attr() {
		}
	}

	private String content;
	private String inv_code;
	private String inv_company;
	private String inv_content;
	private String inv_goto_addr;
	private String inv_id;
	private String inv_rec_mobphone;
	private String inv_rec_name;
	private String inv_rec_province;
	private String inv_reg_addr;
	private String inv_reg_baccount;
	private String inv_reg_bname;
	private String inv_reg_phone;
	private String inv_state;
	private String inv_title;
	private String member_id;

	public InvoiceInFO() {
	}

	public InvoiceInFO(String s, String s1, String s2, String s3, String s4,
			String s5, String s6, String s7, String s8, String s9, String s10,
			String s11, String s12, String s13, String s14, String s15) {
		inv_id = s;
		member_id = s1;
		inv_state = s2;
		inv_title = s3;
		inv_content = s4;
		inv_company = s5;
		inv_code = s6;
		inv_reg_addr = s7;
		inv_reg_phone = s8;
		inv_reg_bname = s9;
		inv_reg_baccount = s10;
		inv_rec_name = s11;
		inv_rec_mobphone = s12;
		inv_rec_province = s13;
		inv_goto_addr = s14;
		content = s15;
	}

	public static InvoiceInFO newInstanceList(String s) {
		InvoiceInFO invoiceinfo = null;
		try {
			JSONObject jsonobject = new JSONObject(s);
			if (jsonobject.length() > 0) {
				invoiceinfo = new InvoiceInFO(jsonobject.optString("inv_id"),
						jsonobject.optString("member_id"),
						jsonobject.optString("inv_state"),
						jsonobject.optString("inv_title"),
						jsonobject.optString("inv_content"),
						jsonobject.optString("inv_company"),
						jsonobject.optString("inv_code"),
						jsonobject.optString("inv_reg_addr"),
						jsonobject.optString("inv_reg_phone"),
						jsonobject.optString("inv_reg_bname"),
						jsonobject.optString("inv_reg_baccount"),
						jsonobject.optString("inv_rec_name"),
						jsonobject.optString("inv_rec_mobphone"),
						jsonobject.optString("inv_rec_province"),
						jsonobject.optString("inv_goto_addr"),
						jsonobject.optString("content"));
				System.out.println((new StringBuilder("bean-->")).append(
						invoiceinfo.toString()).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return invoiceinfo;

	}

	public String getContent() {
		return content;
	}

	public String getInv_code() {
		return inv_code;
	}

	public String getInv_company() {
		return inv_company;
	}

	public String getInv_content() {
		return inv_content;
	}

	public String getInv_goto_addr() {
		return inv_goto_addr;
	}

	public String getInv_id() {
		return inv_id;
	}

	public String getInv_rec_mobphone() {
		return inv_rec_mobphone;
	}

	public String getInv_rec_name() {
		return inv_rec_name;
	}

	public String getInv_rec_province() {
		return inv_rec_province;
	}

	public String getInv_reg_addr() {
		return inv_reg_addr;
	}

	public String getInv_reg_baccount() {
		return inv_reg_baccount;
	}

	public String getInv_reg_bname() {
		return inv_reg_bname;
	}

	public String getInv_reg_phone() {
		return inv_reg_phone;
	}

	public String getInv_state() {
		return inv_state;
	}

	public String getInv_title() {
		return inv_title;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setContent(String s) {
		content = s;
	}

	public void setInv_code(String s) {
		inv_code = s;
	}

	public void setInv_company(String s) {
		inv_company = s;
	}

	public void setInv_content(String s) {
		inv_content = s;
	}

	public void setInv_goto_addr(String s) {
		inv_goto_addr = s;
	}

	public void setInv_id(String s) {
		inv_id = s;
	}

	public void setInv_rec_mobphone(String s) {
		inv_rec_mobphone = s;
	}

	public void setInv_rec_name(String s) {
		inv_rec_name = s;
	}

	public void setInv_rec_province(String s) {
		inv_rec_province = s;
	}

	public void setInv_reg_addr(String s) {
		inv_reg_addr = s;
	}

	public void setInv_reg_baccount(String s) {
		inv_reg_baccount = s;
	}

	public void setInv_reg_bname(String s) {
		inv_reg_bname = s;
	}

	public void setInv_reg_phone(String s) {
		inv_reg_phone = s;
	}

	public void setInv_state(String s) {
		inv_state = s;
	}

	public void setInv_title(String s) {
		inv_title = s;
	}

	public void setMember_id(String s) {
		member_id = s;
	}

	public String toString() {
		return (new StringBuilder("InvoiceInFO [inv_id=")).append(inv_id)
				.append(", member_id=").append(member_id)
				.append(", inv_state=").append(inv_state)
				.append(", inv_title=").append(inv_title)
				.append(", inv_content=").append(inv_content)
				.append(", inv_company=").append(inv_company)
				.append(", inv_code=").append(inv_code)
				.append(", inv_reg_addr=").append(inv_reg_addr)
				.append(", inv_reg_phone=").append(inv_reg_phone)
				.append(", inv_reg_bname=").append(inv_reg_bname)
				.append(", inv_reg_baccount=").append(inv_reg_baccount)
				.append(", inv_rec_name=").append(inv_rec_name)
				.append(", inv_rec_mobphone=").append(inv_rec_mobphone)
				.append(", inv_rec_province=").append(inv_rec_province)
				.append(", inv_goto_addr=").append(inv_goto_addr)
				.append(", content=").append(content).append("]").toString();
	}
}
