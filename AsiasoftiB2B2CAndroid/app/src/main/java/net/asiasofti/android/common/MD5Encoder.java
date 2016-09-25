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

import java.security.MessageDigest;

public class MD5Encoder
{

	public MD5Encoder()
	{
	}

	private static final String byte2hexString(byte abyte0[])
	{
		StringBuffer stringbuffer = new StringBuffer(2 * abyte0.length);
		int i = 0;
		do
		{
			if (i >= abyte0.length)
				return stringbuffer.toString();
			if ((0xff & abyte0[i]) < 16)
				stringbuffer.append("0");
			stringbuffer.append(Long.toString(0xff & abyte0[i], 16));
			i++;
		} while (true);
	}

	public static String encode(String s)
	{
		String s1;
		String s2;
		try
		{
			s1 = new String(s);
		}
		catch (Exception exception1)
		{
			return null;
		}
		try
		{
			s2 = byte2hexString(MessageDigest.getInstance("MD5").digest(s1.getBytes()));
		}
		catch (Exception exception)
		{
			return s1;
		}
		return s2;
	}
}
