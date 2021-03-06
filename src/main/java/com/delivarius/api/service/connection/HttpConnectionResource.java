package com.delivarius.api.service.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.delivarius.api.service.exception.HttpConnectionException;

public class HttpConnectionResource {
	
	private static HttpConnectionResource singleton;
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	//private static final int MAX_CONNECTION_POOL = 10;
	
	//private static final int ERROR_CODE = -1;
	
	//private static final Map<String, HttpURLConnection> httpConnectionByUrl = new HashMap<>();
	
	//private static final Map<String, Long> callCountByUrl = new HashMap<>();
	
	private HttpConnectionResource() {}
	
	
	public synchronized static HttpConnectionResource getInstance() {
		if(singleton == null)
			singleton = new HttpConnectionResource();
		return singleton;
	}
	
	/**
	 * Execute a post call to the {@code url} and return the http response code 
	 * the response data will be in the {@link StringBuffer} passed by parameter if the call worked out
	 * @param jsonBody represents the data that will be in the body of the post request, must be a JSON string
	 * @param url
	 * @param response
	 * @return the http response code
	 * @throws HttpConnectionException 
	 */
	public int executePost(String jsonBody, String url, StringBuffer response) throws HttpConnectionException {
		HttpURLConnection con = null;
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try {
			con = getConnection(url); 
			
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setFixedLengthStreamingMode(jsonBody.getBytes().length);
			con.setRequestProperty("Content-Type", "application/json");
			con.connect();
	
			bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
			bw.write(jsonBody);
			bw.flush();
			
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			
			while( (line = br.readLine()) != null )
				response.append(line+LINE_SEPARATOR);
			
			return con.getResponseCode();			
			
		} catch (IOException e) {
			throw new HttpConnectionException(e);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				throw new HttpConnectionException(e);
			}
			if(con != null)
				con.disconnect();
		}
		
	}
	
	/**
	 * Execute a GET call to the {@code url} and write the response in the {@link StringBuffer} passed by parameter
	 * @param response it will have the response data if the call worked out
	 * @param url
	 * @return the http response code 
	 * @throws HttpConnectionException 
	 */
	public int executeGet( String url, StringBuffer response) throws HttpConnectionException {
		HttpURLConnection con = null; 
		BufferedReader br = null;
		
		try {
			con = getConnection(url);
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.connect();
			
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			
			while( (line = br.readLine()) != null )
				response.append(line+LINE_SEPARATOR);
			
			return con.getResponseCode();
		
		} catch (IOException e) {
			throw new HttpConnectionException(e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				throw new HttpConnectionException(e);
			}
			if(con != null)
				con.disconnect();
		}
		
	}
	
	/**
	 * Execute a get call to the {@code url} and return the response code
	 * @param url
	 * @return the http response code
	 * @throws HttpConnectionException 
	 */
	public int executeGet( String url) throws HttpConnectionException {
		HttpURLConnection con = null; 
		
		try {
			con = getConnection(url);
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.connect();
			
			return con.getResponseCode();
			
		} catch (IOException e) {
			throw new HttpConnectionException(e);
		} finally {
			if(con != null)
				con.disconnect();
		}
		
	}
	
	/**
	 * Execute a delete call to the {@code url} and worked out or not
	 * @param response it will have the response data if the call worked out
	 * @param url
	 * @return the http response code
	 * @throws HttpConnectionException 
	 */
	public int executeDelete( String url, StringBuffer response) throws HttpConnectionException {
		HttpURLConnection con = null; 
		BufferedReader br = null;
		
		try {
			con = getConnection(url); 
			con.setRequestMethod("DELETE");
			con.connect();
			
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			
			while( (line = br.readLine()) != null )
				response.append(line+LINE_SEPARATOR);
		
			return con.getResponseCode();
		}  catch (IOException e) {
			throw new HttpConnectionException(e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				throw new HttpConnectionException(e);
			}
			if(con!= null)
				con.disconnect();
		}
	}
	
	/**
	 * Execute a DELETE call to the {@code url}
	 * @param url
	 * @return the http response code
	 * @throws HttpConnectionException 
	 */
	public int executeDelete( String url) throws HttpConnectionException {
		HttpURLConnection con = null; 
		
		try {
			con = getConnection(url); 
			con.setRequestMethod("DELETE");
			con.connect();
			
			return con.getResponseCode();
		}  catch (IOException e) {
			throw new HttpConnectionException(e);
		} finally {
			if(con!= null)
				con.disconnect();
		}
	}

	
	

	private synchronized HttpURLConnection getConnection(String url) throws IOException, MalformedURLException {
		//TODO correct this
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
	/*	HttpURLConnection con = httpConnectionByUrl.get(url);
		if(con == null) {
			int poolSize = httpConnectionByUrl.size();
			if(poolSize >= MAX_CONNECTION_POOL) {
				httpConnectionByUrl.remove(httpConnectionByUrl.keySet().iterator().next());
			}			
			con = (HttpURLConnection) new URL(url).openConnection();
			httpConnectionByUrl.put(url, con);
		}*/
		return con;
	}
	

}
