package gea.utility;
import gea.bean.ContactMeBean;
import gea.bean.SiteFeedbackBean;
import gea.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUtility {
	
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/mysql";

	//  Database credentials
	static final String USER = "gea";
	static final String PASS = "gundecha";
	   
	   
	public static Connection getDatabaseConnection() throws SQLException, ClassNotFoundException  {

		  Connection connection = null;
		  
		  /* Register JDBC driver */
		  Class.forName("com.mysql.jdbc.Driver");
		
		  /* Open a connection */
		  connection = DriverManager.getConnection(DB_URL, USER, PASS);
		  return connection;
		   
	}
		
	public static void signupUser (String name, String email, String phone, String password) throws SQLException, ClassNotFoundException {
		
		String insertStmt = 
		  "INSERT INTO registeredUsers (loginId, password, parentName, parentPhone, parentEmail, creationDate) " +
		  "VALUES(?, ?, ?, ?, ?, NOW())";

		  Connection conn = getDatabaseConnection();
	      PreparedStatement preparedStmt = conn.prepareStatement(insertStmt);
	      preparedStmt.setString(1, phone);
	      preparedStmt.setString(2, password);
	      preparedStmt.setString(3, name);
	      preparedStmt.setString(4, phone);
	      preparedStmt.setString(5, email);
	      preparedStmt.execute();
	      conn.close();
	}
	
	
	public static User validateLogin(String loginId, String password)  throws SQLException, ClassNotFoundException {
		  String sql = "select * from registeredUsers where loginId='"+loginId+"' and password='"+password+"'";
		  Connection conn = getDatabaseConnection();
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      if (rs.next()) {
		    	User loggedUser = new User();
				loggedUser.setEmail(rs.getString("parentEmail"));
				loggedUser.setCreationDate(rs.getString("creationDate"));
				loggedUser.setLoginId(rs.getString("loginId"));
				loggedUser.setName(rs.getString("parentName"));
				loggedUser.setPhone(rs.getString("parentPhone"));
				conn.close();
				return loggedUser;
	      } else {
	    	  	return null;
	      }
	}
	
	
	public static boolean isThisPhoneRegistered(String phone)  throws SQLException, ClassNotFoundException {
			String sql = "select * from registeredUsers where loginId='"+phone+"'";
			Connection conn = getDatabaseConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
	      
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
	}
	
	
	public static void saveContactMeMessage(String loginId, String message) throws SQLException, ClassNotFoundException {
		  String insertStmt = "INSERT INTO ContactMe (loginId, message, submissionDate) VALUES(?, ?, NOW())";
		  Connection conn = getDatabaseConnection();
	      PreparedStatement preparedStmt = conn.prepareStatement(insertStmt);
	      preparedStmt.setString(1, loginId);
	      preparedStmt.setString(2, message);
	      System.out.println(preparedStmt);
	      preparedStmt.execute();
	      conn.close();
	}

	public static void saveSiteFeedback(
			String loggedUserLoginId,
			String siteUsefullness, 
			String pricesOk, 
			String howToImprove,
			String whichOtherItems, 
			String whichOtherSites, 
			String exchange)  throws SQLException, ClassNotFoundException {
		
		  String insertStmt = 
				  " INSERT INTO SiteFeedBack (loginId, siteUsefullness, pricesOk, howToImprove, whichOtherItems, " +
				  " whichOtherSites, exchange, submissionDate) VALUES(?, ?, ?, ?, ?, ?, ?, NOW())";

		  Connection conn = getDatabaseConnection();
	      PreparedStatement preparedStmt = conn.prepareStatement(insertStmt);
	      preparedStmt.setString(1, loggedUserLoginId);
	      preparedStmt.setString(2, siteUsefullness);
	      preparedStmt.setString(3, pricesOk);
	      preparedStmt.setString(4, howToImprove);
	      preparedStmt.setString(5, whichOtherItems);
	      preparedStmt.setString(6, whichOtherSites);
	      preparedStmt.setString(7, exchange);
	      System.out.println(preparedStmt);
	      preparedStmt.execute();
	      conn.close();
	}
	
	
	public static ArrayList<User> getResisteredUsers()  throws SQLException, ClassNotFoundException {
		  String sql = "select loginId, parentName, parentPhone, parentEmail, creationDate from registeredUsers order by creationDate desc";
		  ArrayList<User> resisteredUsers = new ArrayList<User>();
		  Connection conn = getDatabaseConnection();
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      while (rs.next()) {
		    	User loggedUser = new User();
				loggedUser.setEmail(rs.getString("parentEmail"));
				loggedUser.setCreationDate(rs.getString("creationDate"));
				loggedUser.setLoginId(rs.getString("loginId"));
				loggedUser.setName(rs.getString("parentName"));
				loggedUser.setPhone(rs.getString("parentPhone"));
				resisteredUsers.add(loggedUser);
		  } 
	      conn.close();
	      return resisteredUsers;
	}

	
	public static ArrayList<ContactMeBean> getContactMeMessages()  throws SQLException, ClassNotFoundException {
		  String sql = "select c.submissionDate, c.message, CONCAT(r.parentName,' ',r.parentPhone,' ',r.parentEmail) parent " +
		  		" from ContactMe c, registeredUsers r where c.loginId=r.loginId order by c.submissionDate desc";
		  ArrayList<ContactMeBean> contactMeMessages = new ArrayList<ContactMeBean>();
		  Connection conn = getDatabaseConnection();
		  System.out.println("getContactMeMessages :: "+ sql);
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      while (rs.next()) {
	    	  ContactMeBean contactMeBean = new ContactMeBean();
	    	  contactMeBean.setMessage(rs.getString("message"));
	    	  contactMeBean.setParent(rs.getString("parent"));
	    	  contactMeBean.setSubmissionDate(rs.getString("submissionDate"));
	    	  contactMeMessages.add(contactMeBean);
	      } 
	      conn.close();
	      return contactMeMessages;
	}
	

	public static ArrayList<SiteFeedbackBean> getSiteFeedbackList()  throws SQLException, ClassNotFoundException {
		  String sql = " select s.submissionDate, s.siteUsefullness, s.pricesOk, s.howToImprove, s.whichOtherItems, s.whichOtherSites,s.exchange, " +
		  		" CONCAT(r.parentName,' ',r.parentPhone,' ',r.parentEmail) parent from SiteFeedBack s, registeredUsers r " +
		  		" where s.loginId=r.loginId order by s.submissionDate desc";
		  ArrayList<SiteFeedbackBean> siteFeedbackList = new ArrayList<SiteFeedbackBean>();
		  Connection conn = getDatabaseConnection();
		  System.out.println("getSiteFeedbackList :: "+ sql);
	      ResultSet rs = conn.createStatement().executeQuery(sql);
	      while (rs.next()) {
	    	  SiteFeedbackBean siteFeedback = new SiteFeedbackBean();
	    	  siteFeedback.setHowToImprove(rs.getString("howToImprove"));
	    	  siteFeedback.setParent(rs.getString("parent"));
	    	  siteFeedback.setPricesOk(rs.getString("pricesOk"));
	    	  siteFeedback.setSiteUsefullness(getSiteUsefullnessScreenValue(rs.getString("siteUsefullness")));
	    	  siteFeedback.setSubmissionDate(rs.getString("submissionDate"));
	    	  siteFeedback.setWhichOtherItems(rs.getString("whichOtherItems"));
	    	  siteFeedback.setWhichOtherSites(rs.getString("whichOtherSites"));
	    	  siteFeedback.setExchange(rs.getString("exchange"));
	    	  siteFeedbackList.add(siteFeedback);
	      } 
	      conn.close();
	      return siteFeedbackList;
	}


	
	private static String getSiteUsefullnessScreenValue(String siteUsefullness) {
		switch (siteUsefullness) {
			case "NO":  return "Not Much helpful";
			case "MAY": return "Might be helpful";
			case "HLP": return "Will be very helpful";
			case "CHG": return "Will be helpful if some changes are done";
		}
		return "";
	}
}