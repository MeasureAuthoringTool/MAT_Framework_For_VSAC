package org.telligen.vsac;

import org.apache.http.HttpHost;

/**
 * Link: https://vsac.nlm.nih.gov/ - go to Help tab - select Using the VSAC REST
 * API to obtain value sets
 * **/
public class VSACSharedConstants {

	// Proxy Constants (at Telligen)
	public static final String PROXY_HOSTNAME = "127.0.0.1";
	public static final int PROXY_PORT = 8080;
	public static final int REQUEST_TIMEDOUT = 3;
	public static final int REQUEST_FAILED = 4;
	public static final int TIMEOUT_PERIOD = 5 * 60 * 1000;

	public static final HttpHost PROXY_HTTPHOST = new HttpHost(
			VSACSharedConstants.PROXY_HOSTNAME, VSACSharedConstants.PROXY_PORT);

	// Set up own proxy
	public static HttpHost createProxy(String hostName, int port) {
		return new HttpHost(hostName, port);
	}

	// URL constants
	/*
	 * public static final String SERVER_TICKET =
	 * "https://vsac.nlm.nih.gov/vsac/ws/Ticket"; public static final String
	 * SERVER_SINGLE_VALUESET =
	 * "https://vsac.nlm.nih.gov/vsac/ws/RetrieveValueSet?"; public static final
	 * String SERVER_MULTIPLE_VALUESET =
	 * "https://vsac.nlm.nih.gov/vsac/ws/RetrieveMultipleValueSets?"; public
	 * static final String SERVICE = "http://umlsks.nlm.nih.gov";
	 */

	public static final String SERVER_TICKET = System
			.getProperty("SERVER_TICKET_URL");
	public static final String SERVER_SINGLE_VALUESET = System
			.getProperty("SERVER_SINGLE_VALUESET_URL");
	public static final String SERVER_MULTIPLE_VALUESET = System
			.getProperty("SERVER_MULTIPLE_VALUESET_URL");
	public static final String SERVICE = System.getProperty("SERVICE_URL");

	// RetriveValueSet URLs
	public static String retrieveLatestVersionValueSetURL(String id,
			String serviceTicket) {
		return SERVER_SINGLE_VALUESET + "id=" + id + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes";
	}

	public static String retrieveSpecifiedValueSetWithVersionURL(String id,
			String serviceTicket, String version) {
		System.out.println("VSAC URL:" + SERVER_SINGLE_VALUESET + "id=" + id
				+ "&version=" + version + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes");
		return SERVER_SINGLE_VALUESET + "id=" + id + "&version=" + version
				+ "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes";
	}

	public static String retrieveSpecifiedValueSetWithEffectiveDateURL(
			String id, String serviceTicket, String effectiveDate) {
		System.out.println("VSAC URL:" + SERVER_SINGLE_VALUESET + "id=" + id
				+ "&effectiveDate=" + effectiveDate + "&ticket="
				+ serviceTicket + "&ReleaseType=VSAC&IncludeDraft=yes");
		return SERVER_SINGLE_VALUESET + "id=" + id + "&effectiveDate="
				+ effectiveDate + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes";
	}

	// RetrieveMultipleValueSets URLs
	public static String retrieveMultipleValueSetsWithOID(String id,
			String serviceTicket) {
		System.out.println("VSAC URL:" + SERVER_MULTIPLE_VALUESET + "id=" + id
				+ "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes");
		return SERVER_MULTIPLE_VALUESET + "id=" + id + "&ticket="
				+ serviceTicket + "&ReleaseType=VSAC&IncludeDraft=yes";
	}

	public static String retrieveMultipleValueSetsWithOIDAndVersion(String id,
			String version, String serviceTicket) {
		System.out.println("VSAC URL:" + SERVER_MULTIPLE_VALUESET + "id=" + id
				+ "&version=" + version + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes");
		return SERVER_MULTIPLE_VALUESET + "id=" + id + "&version=" + version
				+ "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes";
	}

	public static String retrieveMultipleValueSetsWithOIDAndEffectiveDate(
			String id, String version, String serviceTicket) {
		System.out.println("VSAC URL:" + SERVER_MULTIPLE_VALUESET + "id=" + id
				+ "&effectiveDate=" + version + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes");
		return SERVER_MULTIPLE_VALUESET + "id=" + id + "&effectiveDate="
				+ version + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes";
	}

	public static String retrieveMultipleValueSetsWithCMSeMeasureIdentifier(
			String CMSeMeasureID, String serviceTicket) {
		System.out.println("VSAC URL:" + SERVER_MULTIPLE_VALUESET
				+ "cmsemeasureid=" + CMSeMeasureID + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes");
		return SERVER_MULTIPLE_VALUESET + "cmsemeasureid=" + CMSeMeasureID
				+ "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes";
	}

	public static String retrieveMultipleValueSetsWithNQFNumber(
			String NQFNumber, String serviceTicket) {
		System.out.println("VSAC URL:" + SERVER_MULTIPLE_VALUESET
				+ "NQFNumber=" + NQFNumber + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes");
		return SERVER_MULTIPLE_VALUESET + "NQFNumber=" + NQFNumber + "&ticket="
				+ serviceTicket + "&ReleaseType=VSAC&IncludeDraft=yes";
	}

	public static String retrieveMultipleValueSetsWithMeasureID(
			String MeasureID, String serviceTicket) {
		System.out.println("VSAC URL:" + SERVER_MULTIPLE_VALUESET
				+ "measureid=" + MeasureID + "&ticket=" + serviceTicket
				+ "&ReleaseType=VSAC&IncludeDraft=yes");
		return SERVER_MULTIPLE_VALUESET + "measureid=" + MeasureID + "&ticket="
				+ serviceTicket + "&ReleaseType=VSAC&IncludeDraft=yes";
	}
}
