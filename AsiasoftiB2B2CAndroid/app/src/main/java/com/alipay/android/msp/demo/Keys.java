/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 * 
 *  提示：如何获取安全校验码和合作身份者id
 *  1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *  2.点击“商家服务”(https://b.alipay.com/order/myorder.htm)
 *  3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
 */

package com.alipay.android.msp.demo;

//
// 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
// 这里签名时，只需要使用生成的RSA私钥。
// Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
public final class Keys {

	//合作身份者id，以2088开头的16位纯数字
	public static final String DEFAULT_PARTNER = "2088411094279814";

	//收款支付宝账号
	public static final String DEFAULT_SELLER = "2088411094279814";

	//商户私钥，自助生成
	public static final String PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN2UoXj5GhOtxk/vC9acsgl1V07F9/2fw7i/qHMVP2iL/epk7TLDp24OtgygRAWS3CK8RwwXIRZmth1sZtsgNXjNce5LgslnP0EACIxILJF6xYXfCIDVX9qLG812mNmsOIjhMkgqzW6AOEWKvlzCadCWe2Rdei0epslhOn1UsTqxAgMBAAECgYAPbXDMrnNdWWUMXbVyTNishen9RxVcKEqFS3rcAGe4zL5yGnLVbP/0TrvoJyR1SuNe28MKmXWpTJ9zbv++VADE9jF71gQRM1asGDecGVqw8LYcbTLqpE89d8mwYNdupK0l1svEQEvy4KjtED4TnEwotdWzQz7djRo0YOfZDecGAQJBAP+Or3yT3Dro5hNNqdWNzKJJBsTsSvbEqN5waw55sKZ6jTphFTNDofm8qbk7oa1b1gKehySGgyWkSl8AxhRGdyECQQDd9uFBgICz1ZLPWTDThclPtAf6HKsOlM+MSfrBAA0+wUOisAoQYYHC6tI6sl7wfPNz6rdbDjZYZjiXunm4XKGRAkEArjUTFWYp85J9/Fyt0GDYRSxoy+aT94H556BUqkp0DBhwm4JAlHCfQwHD5PxsayO2UJTGZTS4w/kppXd+j6W1YQJADqbLO62l0TkZ1cIHbQ75uN8jX6kgyXedM592g+Li/O947XY9U9N740fo1KMNjUZSM6acr9ACWld1CN7wRfEnYQJBAKfq2w8LYhuBlrPFtaGqlpP1VlvpMX4PikKi8YISOzO4+0xOccOn9Snvt+nH8/aUu5VsFn21tTpumqmstxj2JjY=";

	public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
}
