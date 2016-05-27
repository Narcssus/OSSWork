package com.WebSpider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


abstract public class WebSpider {

	protected int rows = 0;
	protected Map<String, Integer> map = new HashMap<String, Integer>();
	protected int beginwith = 1;
	protected String[] urls;
	
	private String beginWords;
	private String endWords;
	
	
	
	public void setWords(String beginWords,String endWords){
		this.beginWords=beginWords;
		this.endWords=endWords;
	}
	public void setBeginNum(int begin){
		this.beginwith=begin;
	}
	public void setUrls(String[] urls){
		this.urls=urls;
	}
	
	
	abstract public void start() throws Exception;
	abstract public String getLine(String s, int num);
	abstract public String getNextUrl(String url,int num);
	

	public StringBuffer MySpider(String baseurl, int sum) throws Exception {
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		URL localURL;
		URLConnection connection;
		HttpURLConnection httpURLConnection;
		String theUrl;
		try {
			int finishedNum = beginwith;
			theUrl = getNextUrl(baseurl , finishedNum);
			while (theUrl.length() > 0 && sum >= finishedNum) {
				localURL = new URL(theUrl);
				connection = localURL.openConnection();
				httpURLConnection = (HttpURLConnection) connection;
				httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
				httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				if (httpURLConnection.getResponseCode() >= 300) {
					System.out.println("ERROR-"+httpURLConnection.getResponseCode());
					finishedNum++;
					theUrl = getNextUrl(baseurl , finishedNum);
					continue;
				}
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);
				
				resultBuffer.append(doGet(resultBuffer, reader, finishedNum)+"\n");
				System.out.println(finishedNum + "/" + sum);
				finishedNum++;
				theUrl = getNextUrl(baseurl , finishedNum);
				
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return resultBuffer;
	}
	
	public String doGet(StringBuffer resultBuffer, BufferedReader reader,int number) throws Exception {
		String nextLine = null;
		String line = "";
		boolean begin = false;
		while ((nextLine = reader.readLine()) != null) {
			nextLine = nextLine.replaceFirst("[ ]+", "");
			if (nextLine.contains(beginWords)) {
				begin = true;
				continue;
			}
			if (nextLine.length() > 0 && begin) {
				line += nextLine + "\n";
			}
			if (begin && nextLine.contains(endWords)) {
				line = getLine(line, number);
				break;
			}

		}
		return line;
	}

}
