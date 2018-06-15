package com.delivarius.delivarius_api.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpConnectionResource {
	
	private static HttpConnectionResource singleton;
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private static final int MAX_CONNECTION_POOL = 10;
	
	private static final Map<String, HttpURLConnection> httpConnectionByUrl = new HashMap<>();
	
	//private static final Map<String, Long> callCountByUrl = new HashMap<>();
	
	private HttpConnectionResource() {}
	
	
	public synchronized static HttpConnectionResource getInstance() {
		if(singleton == null)
			singleton = new HttpConnectionResource();
		return singleton;
	}
	
	/**
	 * Execute a call to the {@code url} and return the response as a {@link StringBuffer}
	 * @param jsonBody
	 * @param url
	 * @return the response as a {@link StringBuffer}, empty if the call brought no result or did not work
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public StringBuffer executePostCall(String jsonBody, String url) throws MalformedURLException, IOException {
		StringBuffer response = new StringBuffer();
		
		HttpURLConnection con = getConnection(url); 
		
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setFixedLengthStreamingMode(jsonBody.getBytes().length);
		con.setRequestProperty("Content-Type", "application/json");
		con.connect();

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		bw.write(jsonBody);
		bw.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = "";
		
		while( (line = br.readLine()) != null )
			response.append(line+LINE_SEPARATOR);
		
		bw.close();
		br.close();
		con.disconnect();
		
		return response;
	}


	private synchronized HttpURLConnection getConnection(String url) throws IOException, MalformedURLException {
		HttpURLConnection con = httpConnectionByUrl.get(url);
		if(con == null) {
			int poolSize = httpConnectionByUrl.size();
			if(poolSize >= MAX_CONNECTION_POOL) {
				httpConnectionByUrl.remove(httpConnectionByUrl.keySet().iterator().next());
			}			
			httpConnectionByUrl.put(url, (HttpURLConnection) new URL(url).openConnection());
		}
		return con;
	}
	

}
