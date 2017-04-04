package gea.actions.admin;

import gea.bean.ContactMeBean;
import gea.bean.SiteFeedbackBean;
import gea.model.TextbookAd;
import gea.model.UniformAd;
import gea.model.User;
import gea.utility.DBUtility;
import gea.utility.DBUtility_TextBookUniform;
import gea.utility.GeaUtility;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ShowDatabaseDataAction  extends ActionSupport implements SessionAware {
	/* Form Fields */
	private String tableCode;
	
	ArrayList<User> resisteredUsers ;
	ArrayList<ContactMeBean> contactMeMessages;
	ArrayList<SiteFeedbackBean> siteFeedbackList;
	ArrayList<TextbookAd> textbookAdList;
	ArrayList<UniformAd> uniformAdList;
	
	/* Execute */
	public String execute(){ 
		 if (GeaUtility.hasUserNotLoggedIn(sessionMap)) {
			 addActionError(" Please login first. ");
			 return "login";
		 }
		 
		 try {
			 switch(tableCode) {
				 case("R") : resisteredUsers = DBUtility.getResisteredUsers();
				 			 return "registeredUsers";
				 case("U") : uniformAdList = DBUtility_TextBookUniform.getUniformAdsListForAdmin();
					 		 return "UniformAds";
				 case("T") : textbookAdList = DBUtility_TextBookUniform.getTextbookAdsListForAdmin();
				 			 return "TextbooksAds";
				 case("C") : contactMeMessages = DBUtility.getContactMeMessages();
					 		 return "ContactMe";
				 case("F") : siteFeedbackList = DBUtility.getSiteFeedbackList();
					 		 return "SiteFeedBack";
			 }
			 return "error"; /* if tableCode is not received as expected */
		 } catch (Exception e) {
			System.out.println("Error while retrieving tables data for admin"+tableCode+"  "+e);
			addActionError("Error while retrieving tables data for admin");
			return "error";
		}
	 } 

	
	
	public String getTableCode() {
		return tableCode;
	}
	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	public ArrayList<User> getResisteredUsers() {
		return resisteredUsers;
	}
	public void setResisteredUsers(ArrayList<User> resisteredUsers) {
		this.resisteredUsers = resisteredUsers;
	}
	public ArrayList<ContactMeBean> getContactMeMessages() {
		return contactMeMessages;
	}
	public void setContactMeMessages(ArrayList<ContactMeBean> contactMeMessages) {
		this.contactMeMessages = contactMeMessages;
	}
	public ArrayList<SiteFeedbackBean> getSiteFeedbackList() {
		return siteFeedbackList;
	}
	public void setSiteFeedbackList(ArrayList<SiteFeedbackBean> siteFeedbackList) {
		this.siteFeedbackList = siteFeedbackList;
	}
	public ArrayList<TextbookAd> getTextbookAdList() {
		return textbookAdList;
	}
	public void setTextbookAdList(ArrayList<TextbookAd> textbookAdList) {
		this.textbookAdList = textbookAdList;
	}
	public ArrayList<UniformAd> getUniformAdList() {
		return uniformAdList;
	}
	public void setUniformAdList(ArrayList<UniformAd> uniformAdList) {
		this.uniformAdList = uniformAdList;
	}

	/* Required for implements SessionAware */
	private Map<String, Object> sessionMap;
    public void setSession(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
