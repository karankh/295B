package edu.sjsu.WebPortal.lab.MySQL;

import org.springframework.stereotype.Repository;

/**
 * This model class contains the properties for the filed on the login.jsp page.
 * @author karan
 *
 */

public class HomePageModel {
 
	
    private String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public Boolean getIsUser() {
		return isUser;
	}

	public void setIsUser(Boolean isUser) {
		this.isUser = isUser;
	}

	private Boolean isUser=false;
   





}