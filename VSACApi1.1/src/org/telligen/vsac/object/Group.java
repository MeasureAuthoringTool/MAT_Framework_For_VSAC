package org.telligen.vsac.object;

import java.util.ArrayList;

public class Group {

	private String ID;
	private String sourceOrganization;
	private String displayName;
	private ArrayList<String> keywordList;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSourceOrganization() {
		return sourceOrganization;
	}

	public void setSourceOrganization(String sourceOrganization) {
		this.sourceOrganization = sourceOrganization;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setKeywordList(ArrayList<String> keywordList) {
		this.keywordList = keywordList;
	}

	public ArrayList<String> getKeywordList() {
		return keywordList;
	}

}
