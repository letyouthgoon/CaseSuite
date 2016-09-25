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

import java.io.*;

public class IOHelper
{

	public IOHelper()
	{
	}
	  public static int clearCacheFolder(File paramFile)
	  {
	    int i = 0;
	    File[] arrayOfFile;
	    if (paramFile != null)
	    {
	      boolean bool = paramFile.isDirectory();
	      i = 0;
	      if (bool)
	      {
	        arrayOfFile = paramFile.listFiles();
	        if (arrayOfFile != null)
	        {
	        	for (int j = 0; j <= arrayOfFile.length-1; j++)
	        	{
	        		 File localFile = arrayOfFile[j];
	        		 if (!localFile.getName().equals("smiley"))
	        		 {    
	        			 i += clearCacheFolder(localFile);  
	        		 
	        		 }
	        		    if (localFile.isFile())
	        		    {
	        		      if (localFile.delete())
	        		      {  
	        		    	  i++;
	        		      
	        		      }
	        		    }
	        	}
	        	return i;
	        		
	        }
	      }
	    }
	    return i;
	  }
 

	public static void copy(File file, File file1)
	{
		BufferedInputStream bufferedinputstream = null;
		BufferedOutputStream bufferedoutputstream = null;
		byte[] abyte0= new byte[1024];;
		try{
		BufferedInputStream bufferedinputstream1 = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bufferedoutputstream1 = new BufferedOutputStream(new FileOutputStream(file1));

	    while(true)
	    {
	    	int i = bufferedinputstream1.read(abyte0);
	    	if (i != -1) {
	    		bufferedoutputstream1.write(abyte0, 0, i);

	    	}else{
	    		break;
	    	}
       }
		bufferedoutputstream1.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try
			{
				if (bufferedinputstream != null)
				{
					bufferedinputstream.close();
				}
				if(bufferedoutputstream != null)
				{
					bufferedoutputstream.close();
				}
			}
			catch (IOException ioexception8)
			{
				ioexception8.printStackTrace();
			}
		}
	

	}

	public static String getExtension(String s)
	{
		return s.substring(1 + s.lastIndexOf("."));
	}

	public static String getName(String s)
	{
		String s1 = null;
		if (s != null)
			s1 = s.substring(7 + s.lastIndexOf("smiley/")).replace("/", "-");
		return s1;
	}

	 public static long totalFileSize(File paramFile)
	  {
	    long l = 0L;
	    File[] arrayOfFile;
	    if ((paramFile != null) && (paramFile.isDirectory()))
	    {
	      arrayOfFile = paramFile.listFiles();
	      if (arrayOfFile != null)
	      {
	    	for (int i = 0;  i < arrayOfFile.length;i++)
	  	    {
	    		File localFile = arrayOfFile[i];
	    		if (localFile.isFile())
	    		{
	    			l += localFile.length();
	    		}
	    		else
	    		{
	    			l += totalFileSize(localFile);
	    		}
	  	      	return l;
	  	    }
	      }
	    }
	    return l;
	 
	  }
	
}
