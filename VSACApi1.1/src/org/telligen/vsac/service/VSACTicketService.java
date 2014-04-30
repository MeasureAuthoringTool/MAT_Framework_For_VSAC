package org.telligen.vsac.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.telligen.vsac.VSACSharedConstants;

/** Example link : https://wiki.jasig.org/display/CASUM/RESTful+API */

public class VSACTicketService {

	private HttpClient client;
	final HttpParams httpParams = new BasicHttpParams();

	/**
	 * PROXY SETTINGS: Telligen default proxy settings
	 */
	public VSACTicketService() {
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
	public VSACTicketService(String hostName, int port) {
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
	 * @param server
	 * @param username
	 * @param password
	 * @return
	 */
	public String getTicketGrantingTicket(String server, String username,
			String password) {
		return getNewTicketGrantingTicket(server, username, password);
	}

	/**
	 * Uses predefined server string
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public String getTicketGrantingTicket(String username, String password) {
		return getNewTicketGrantingTicket(VSACSharedConstants.SERVER_TICKET,
				username, password);
	}

	/**
	 * 
	 * @param server
	 * @param ticketGrantingTicket
	 * @param service
	 * @return
	 */
	public String getServiceTicket(String server, String ticketGrantingTicket,
			String service) {
		return getNewServiceTicket(server, ticketGrantingTicket, service);
	}

	/**
	 * Uses predefined server and service strings
	 * 
	 * @param ticketGrantingTicket
	 * @return
	 */
	public String getServiceTicket(String ticketGrantingTicket) {
		return getNewServiceTicket(VSACSharedConstants.SERVER_TICKET,
				ticketGrantingTicket, VSACSharedConstants.SERVICE);
	}

	private String getNewTicketGrantingTicket(String server, String username,
			String password) {
		HttpPost post = new HttpPost(server);
		HttpResponse response = null;
		BasicResponseHandler responseHandler = new BasicResponseHandler();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			response = client.execute(post);
			return responseHandler.handleResponse(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.reset();
		}
		return null;
	}

	private String getNewServiceTicket(String server,
			String ticketGrantingTicket, String service) {
		String url = server + "/" + ticketGrantingTicket;
		HttpPost post = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("service", service));
		String responseString = null;
		BasicResponseHandler responseHandler = new BasicResponseHandler();
		HttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			response = client.execute(post);
			responseString = responseHandler.handleResponse(response);
			return responseString;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return null;
	}
}
