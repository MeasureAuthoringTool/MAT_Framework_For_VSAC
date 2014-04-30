package org.telligen.vsac.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.telligen.vsac.VSACSharedConstants;

public class VSACValueSetService {

	private HttpClient client;
	final HttpParams httpParams = new BasicHttpParams();

	public static final String UTF8_BOM = "\uFEFF";

	/**
	 * PROXY SETTINGS: Telligen default proxy settings
	 */
	public VSACValueSetService() {
		HttpConnectionParams.setSoTimeout(httpParams,
				VSACSharedConstants.TIMEOUT_PERIOD);
		client = new DefaultHttpClient(httpParams);
		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				VSACSharedConstants.PROXY_HTTPHOST);
	}

	/**
	 * PROXY SETTINGS: If no firewall, enter (null, 0)
	 * 
	 * @param hostName
	 * @param port
	 */
	public VSACValueSetService(String hostName, int port) {
		// 5 min time out.
		HttpConnectionParams.setSoTimeout(httpParams,
				VSACSharedConstants.TIMEOUT_PERIOD);
		client = new DefaultHttpClient(httpParams);
		if (hostName != null && port != 0) {
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					VSACSharedConstants.createProxy(hostName, port));
		}
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public Document getSpecifiedURLValueSetDocument(String url)
			throws Exception {
		// hard coding testing webservice url
		// url = "http://localhost:8080/BookStoreDemo/services/bookresource";

		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = null;
		InputStream dataStream = null;
		Document dataDoc = null;

		response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		dataStream = entity.getContent();
		InputStreamReader inputStreamReader = new InputStreamReader(dataStream,
				"UTF-8");
		BufferedReader r = new BufferedReader(inputStreamReader);

		StringBuilder stringBuilder = new StringBuilder();
		boolean firstLine = true;
		for (String s = ""; (s = r.readLine()) != null;) {
			if (firstLine) {
				s = removeUTF8BOM(s);
				firstLine = false;
			}
			stringBuilder.append(s);
		}
		System.out.println("Raw XML :" + stringBuilder.toString());
		Reader rdr = new StringReader(stringBuilder.toString());
		SAXReader reader = new SAXReader();
		dataDoc = reader.read(rdr);

		client.getConnectionManager().shutdown();
		return dataDoc;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public InputStream getSpecifiedURLValueSetStream(String url) {
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = null;
		InputStream dataStream = null;
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			dataStream = entity.getContent();
			Document doc = new SAXReader().read(dataStream);
			doc.asXML();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		client.getConnectionManager().shutdown();
		return dataStream;
	}

	private static String removeUTF8BOM(String s) {
		if (s.startsWith(UTF8_BOM)) {
			s = s.substring(1);
		}
		return s;
	}
}
