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

import android.graphics.Bitmap;
import java.io.*;

public class FileHelper
{

	public FileHelper()
	{
	}

	public static void saveFile(Bitmap bitmap, String s)
		throws IOException
	{
		FileOutputStream fileoutputstream1 = new FileOutputStream(new File(s));
		bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileoutputstream1);
		
	}
}
