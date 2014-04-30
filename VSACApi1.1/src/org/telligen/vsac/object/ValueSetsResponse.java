package org.telligen.vsac.object;

public class ValueSetsResponse {

	// private ArrayList<ValueSet> valueSetList;

	private String xmlPayLoad = "";
	private boolean isFailResponse = false;
	private int failReason;

	public String getXmlPayLoad() {
		return xmlPayLoad;
	}

	public void setXmlPayLoad(String xmlPayLoad) {
		this.xmlPayLoad = xmlPayLoad;
	}

	public void setFailResponse(boolean isFailResponse) {
		this.isFailResponse = isFailResponse;
	}

	public boolean isFailResponse() {
		return isFailResponse;
	}

	public void setFailReason(int failReason) {
		this.failReason = failReason;
	}

	public int getFailReason() {
		return failReason;
	}

}
