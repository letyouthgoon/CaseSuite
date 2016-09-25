package net.asiasofti.android.en;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpInputStream {
	

	public String InputStream(String serverUrl){
		URL url=null;
		BufferedReader bufferedReader = null;
		try {
			url=new URL(serverUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection connection=null;
		java.io.InputStream inputStream=null;
		
		try {
			connection=(HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setReadTimeout(900000);
			inputStream=url.openStream();
			StringBuffer buffer=new StringBuffer();
			String Line=null;
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			while ((Line=bufferedReader.readLine())!=null) {
				buffer.append(Line);			
			}
			return buffer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (connection!=null) {
				connection.disconnect();
			}
			if (bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	
	}

}
