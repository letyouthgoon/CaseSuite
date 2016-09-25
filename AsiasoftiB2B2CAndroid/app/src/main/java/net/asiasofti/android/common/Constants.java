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

import android.os.Environment;
import java.io.File;

public final class Constants {

	public static final String APP = "/mobile";
	public static final String APP_BORADCASTRECEIVER = "tcyd.asiasofti.com";
	public static final String APP_BORADCASTRECEIVER2 = "tcyd.asiasofti.com";
	public static final String CACHE_DIR;
	public static final String CACHE_DIR_IMAGE;
	public static final String CACHE_DIR_SMILEY;
	public static final String CACHE_DIR_UPLOADING_IMG;
	public static final String DB_NAME = "b2b2c_shop.db";
	public static final int DB_VERSION = 2;
//	public static final String HOST = "tcyd.asiasofti.com";
	public static final String HOST = "cainiaoshop.asiasofti.com.cn";
	public static final int PAGESIZE = 10;
	public static final String PORT = "90";
	public static final String PROTOCOL = "http://";
	public static final String SYSTEM_INIT_FILE_NAME = "sysini";
	public static final String URL_ADDRESS_ADD = PROTOCOL + HOST + APP
			+ "/index.php?act=member_address&op=address_add";
	public static final String URL_ADDRESS_DETAILS = PROTOCOL + HOST + APP
			+ "/index.php?act=member_address&op=address_info";
	public static final String URL_ADDRESS_DETELE = PROTOCOL + HOST + APP
			+ "/index.php?act=member_address&op=address_del";
	public static final String URL_ADDRESS_EDIT = PROTOCOL + HOST + APP
			+ "/index.php?act=member_address&op=address_edit";
	public static final String URL_ADDRESS_LIST = PROTOCOL + HOST + APP
			+ "/index.php?act=member_address&op=address_list";
	public static final String URL_ADD_CART = PROTOCOL + HOST + APP
			+ "/index.php?act=member_cart&op=cart_add";
	public static final String URL_ADD_FAVORITES = PROTOCOL + HOST + APP
			+ "/index.php?act=member_favorites&op=favorites_add";
	public static final String URL_BUY_STEP1 = PROTOCOL + HOST + APP
			+ "/index.php?act=member_buy&op=buy_step1";
	public static final String URL_BUY_STEP2 = PROTOCOL + HOST + APP
			+ "/index.php?act=member_buy&op=buy_step2";
	public static final String URL_CART_DETELE = PROTOCOL + HOST + APP
			+ "/index.php?act=member_cart&op=cart_del";
	public static final String URL_CART_LIST = PROTOCOL + HOST + APP
			+ "/index.php?act=member_cart&op=cart_list";
	public static final String URL_CHECK_PASSWORD = PROTOCOL + HOST + APP
			+ "/index.php?act=member_buy&op=check_password";
	public static final String URL_CONTEXTPATH = PROTOCOL + HOST + APP
			+ "/index.php?";
	public static final String URL_FAVORITES_DELETE = PROTOCOL + HOST + APP
			+ "/index.php?act=member_favorites&op=favorites_del";
	public static final String URL_FAVORITES_LIST = PROTOCOL + HOST + APP
			+ "/index.php?act=member_favorites&op=favorites_list";
	public static final String URL_GET_CITY = PROTOCOL + HOST + APP
			+ "/index.php?act=member_address&op=area_list";
	public static final String URL_GOODSCLASS = PROTOCOL + HOST + APP
			+ "/index.php?act=goods_class";
	public static final String URL_GOODSDETAILS = PROTOCOL + HOST + APP
			+ "/index.php?act=goods&op=goods_detail";
	public static final String URL_GOODSLIST = PROTOCOL + HOST + APP
			+ "/index.php?act=goods&op=goods_list";
	public static final String URL_GOODS_DETAILS_WEB = PROTOCOL + HOST + APP
			+ "/index.php?act=goods&op=goods_body";
	public static final String URL_HELP = PROTOCOL + HOST + APP + "/help.html";
	public static final String URL_HOME = PROTOCOL + HOST + APP
			+ "/index.php?act=index";
	public static final String URL_INVOICE_ADD = PROTOCOL + HOST + APP
			+ "/index.php?act=member_invoice&op=invoice_add";
	public static final String URL_INVOICE_CONTEXT_LIST = PROTOCOL + HOST + APP
			+ "/index.php?act=member_invoice&op=invoice_content_list";
	public static final String URL_INVOICE_LIST = PROTOCOL + HOST + APP
			+ "/index.php?act=member_invoice&op=invoice_list";
	public static final String URL_LOGIN = PROTOCOL + HOST + APP
			+ "/index.php?act=login";
	public static final String URL_LOGIN_OUT = PROTOCOL + HOST + APP
			+ "/index.php?act=logout";
	public static final String URL_MYSTOIRE = PROTOCOL + HOST + APP
			+ "/index.php?act=member_index";
	public static final String URL_ORDER_CANCEL = PROTOCOL + HOST + APP
			+ "/index.php?act=member_order&op=order_cancel";
	public static final String URL_ORDER_LIST = PROTOCOL + HOST + APP
			+ "/index.php?act=member_order&op=order_list";
	public static final String URL_ORDER_PAYMENT = PROTOCOL + HOST + APP
			+ "/index.php?act=member_payment&op=pay";
	public static final String URL_ORDER_RECEIVE = PROTOCOL + HOST + APP
			+ "/index.php?act=member_order&op=order_receive";
	public static final String URL_REGISTER = PROTOCOL + HOST + APP
			+ "/index.php?act=login&op=register";
	public static final String URL_UPDATE_ADDRESS = PROTOCOL + HOST + APP
			+ "/index.php?act=member_buy&op=change_address";

	private Constants() {
	}

	static {
		if ("mounted".equals(Environment.getExternalStorageState()))
			CACHE_DIR = (new StringBuilder(String.valueOf(Environment
					.getExternalStorageDirectory().getAbsolutePath()))).append(
					"/ShopNC/").toString();
		else
			CACHE_DIR = (new StringBuilder(String.valueOf(Environment
					.getRootDirectory().getAbsolutePath()))).append("/ShopNC/")
					.toString();
		CACHE_DIR_SMILEY = (new StringBuilder(String.valueOf(CACHE_DIR)))
				.append("/smiley").toString();
		CACHE_DIR_IMAGE = (new StringBuilder(String.valueOf(CACHE_DIR)))
				.append("/pic").toString();
		CACHE_DIR_UPLOADING_IMG = (new StringBuilder(String.valueOf(CACHE_DIR)))
				.append("/uploading_img").toString();
	}
}
