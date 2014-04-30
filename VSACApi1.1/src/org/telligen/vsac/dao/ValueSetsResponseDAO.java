package org.telligen.vsac.dao;

import java.io.File;
import java.io.PrintWriter;

import org.dom4j.Document;
import org.telligen.vsac.VSACSharedConstants;
import org.telligen.vsac.map.mapper.ValueSetsResponseMapper;
import org.telligen.vsac.object.ValueSetsResponse;
import org.telligen.vsac.service.VSACTicketService;
import org.telligen.vsac.service.VSACValueSetService;

public class ValueSetsResponseDAO {

	private ValueSetsResponseMapper mapper;
	private VSACTicketService ticketService;
	private VSACValueSetService valueSetService;
	private String ticketGrantingTicket;

	/**
	 * VSAC Login Credentials: Generates a ticketGenerating ticket
	 * 
	 * @param username
	 * @param password
	 */
	public ValueSetsResponseDAO(String username, String password,
			String proxyHost, int proxyPort) {
		ticketService = new VSACTicketService(proxyHost, proxyPort);
		valueSetService = new VSACValueSetService(proxyHost, proxyPort);
		ticketGrantingTicket = ticketService.getTicketGrantingTicket(username,
				password);
	}

	/**
	 * 
	 * @param ticketGrantingTicket
	 */
	public ValueSetsResponseDAO(String ticketGrantingTicket, String proxyHost,
			int proxyPort) {
		this.ticketGrantingTicket = ticketGrantingTicket;
		ticketService = new VSACTicketService(proxyHost, proxyPort);
		valueSetService = new VSACValueSetService(proxyHost, proxyPort);
	}

	/**
	 * Latest Version Value Set Response by ID
	 * 
	 * @param id
	 * @return
	 */
	public ValueSetsResponse getLatestVersionValueSetsResponseByID(String id) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "ID: " + id;
		String retrievalType = "Retrive Latest Version Value Set with ID";
		String url = VSACSharedConstants.retrieveLatestVersionValueSetURL(id,
				serviceTicket);
		boolean multipleSets = false;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	/**
	 * Specified Value Set Response by ID and Version
	 * 
	 * @param id
	 * @param version
	 * @return
	 */
	public ValueSetsResponse getSpecifiedValueSetsResponseByIDAndVersion(
			String id, String version) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "ID: " + id + ", Version: " + version;
		String retrievalType = "Retrieve Specified Value Set with Version and ID";
		String url = VSACSharedConstants
				.retrieveSpecifiedValueSetWithVersionURL(id, serviceTicket,
						version);
		boolean multipleSets = false;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	/**
	 * Specified Value Set Response by ID and Version
	 * 
	 * @param id
	 * @param effectiveDate
	 * @return
	 */
	public ValueSetsResponse getSpecifiedValueSetsResponseByIDAndEffectiveDate(
			String id, String effectiveDate) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "ID: " + id + ", Effective Date: " + effectiveDate;
		String retrievalType = "Retrieve Specified Value Set with Effective Date and ID";
		String url = VSACSharedConstants
				.retrieveSpecifiedValueSetWithEffectiveDateURL(id,
						serviceTicket, effectiveDate);
		boolean multipleSets = false;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	/**
	 * Multiple Value Sets Resposne by OID
	 * 
	 * @param oid
	 * @return
	 */
	public ValueSetsResponse getMultipleValueSetsResponseByOID(String oid) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "OID: " + oid;
		String retrievalType = "Retrieve Multiple Value Sets with OID";
		String url = VSACSharedConstants.retrieveMultipleValueSetsWithOID(oid,
				serviceTicket);
		boolean multipleSets = true;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	/**
	 * Multiple Value Sets Response by OID and Version.
	 * 
	 * @param oid
	 * @param version
	 * @return
	 */
	public ValueSetsResponse getMultipleValueSetsResponseByOIDAndVersion(
			String oid, String version) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "OID: " + oid + ", Version: " + version;
		String retrievalType = "Retrieve Multiple Value Sets with OID and Version";
		String url = VSACSharedConstants
				.retrieveMultipleValueSetsWithOIDAndVersion(oid, version,
						serviceTicket);
		boolean multipleSets = true;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	/**
	 * Multiple Value Sets Response by OID and effectiveDate.
	 * 
	 * @param oid
	 * @param effectiveDate
	 * @return
	 */
	public ValueSetsResponse getMultipleValueSetsResponseByOIDAndEffectiveDate(
			String oid, String effectiveDate) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "OID: " + oid + ", effectiveDate: " + effectiveDate;
		String retrievalType = "Retrieve Multiple Value Sets with OID and effectiveDate";
		String url = VSACSharedConstants
				.retrieveMultipleValueSetsWithOIDAndEffectiveDate(oid,
						effectiveDate, serviceTicket);
		boolean multipleSets = true;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	/**
	 * Multiple Value Sets Response by CMS eMeasure Identifier
	 * 
	 * @param cmseMeasureIdentifier
	 * @return
	 */
	public ValueSetsResponse getMutlipeValueSetsResponseByCMSeMeasureIdentifier(
			String cmseMeasureIdentifier) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "CMS eMeaure ID: " + cmseMeasureIdentifier;
		String retrievalType = "Retrieve Multiple Value Sets with CMS eMeasure Identifier";
		String url = VSACSharedConstants
				.retrieveMultipleValueSetsWithCMSeMeasureIdentifier(
						cmseMeasureIdentifier, serviceTicket);
		boolean multipleSets = true;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	/**
	 * Multiple Value Sets Response by NQF Number
	 * 
	 * @param nqfNumber
	 * @return
	 */
	public ValueSetsResponse getMultipleValueSetsResponseByNQFNumber(
			String nqfNumber) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "NQF Number: " + nqfNumber;
		String retrievalType = "Retrieve Multiple Value Sets With NQF Number";
		String url = VSACSharedConstants
				.retrieveMultipleValueSetsWithNQFNumber(nqfNumber,
						serviceTicket);
		boolean multipleSets = true;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	/**
	 * Multiple Value Sets Response by Measure ID
	 * 
	 * @param measureID
	 * @return
	 */
	public ValueSetsResponse getMultipleValueSetsResponseByMeasureID(
			String measureID) {
		String serviceTicket = getServiceTicketFromTicketGrantingTicket();
		String parameters = "Measure ID: " + measureID;
		String retrievalType = "Retrieve Multiple Value Sets with Measure ID";
		String url = VSACSharedConstants
				.retrieveMultipleValueSetsWithMeasureID(measureID,
						serviceTicket);
		boolean multipleSets = true;

		return retrieveAndHandleData(url, parameters, retrievalType,
				serviceTicket, multipleSets);
	}

	private String getServiceTicketFromTicketGrantingTicket() {
		String fiveMinTicket = ticketService
				.getServiceTicket(ticketGrantingTicket);
		System.out.println("5 Min ticket ===" + fiveMinTicket);
		return fiveMinTicket;
	}

	private ValueSetsResponse retrieveAndHandleData(String url,
			String parameters, String retrievalType, String serviceTicket,
			boolean multipleSets) {
		try {
			Document valueSetXMLDoc = valueSetService
					.getSpecifiedURLValueSetDocument(url);
			File file;
			PrintWriter fileWriter;

			file = File.createTempFile("XMLData", ".xml");
			fileWriter = new PrintWriter(file);
			fileWriter.println(valueSetXMLDoc.asXML());
			System.out.println("XML:" + valueSetXMLDoc.asXML());
			fileWriter.close();
			mapper = new ValueSetsResponseMapper(file, multipleSets);
			ValueSetsResponse v = new ValueSetsResponse();
			v.setXmlPayLoad(valueSetXMLDoc.asXML());
			return v;
		} catch (Exception e) {
			System.out
					.println("EXCEPTION IN VSAC JAR: ValuseSetResponseDAO.retrieveAndHandleData().."
							+ e.getMessage());
			ValueSetsResponse v = new ValueSetsResponse();
			v.setFailResponse(true);
			if (e instanceof java.net.SocketTimeoutException) {
				v.setFailReason(VSACSharedConstants.REQUEST_TIMEDOUT);
			}else{
				v.setFailReason(VSACSharedConstants.REQUEST_FAILED);
			}
			return v;
		}
	}
}
