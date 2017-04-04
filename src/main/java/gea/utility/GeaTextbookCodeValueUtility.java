package gea.utility;

import gea.bean.GeaCodeValueBean;
import gea.model.TextbookAd;

import java.util.ArrayList;

/* Changes here should be reflected in getScreenValueFromDatabaseCodes below and DBUtility_TextBookUniform select , orderby ... */
/* This class populates drop down lists for Buy/Sell Textbooks screen */
public class GeaTextbookCodeValueUtility {

	public static  ArrayList<GeaCodeValueBean> populateTextbookConditionList () {
		ArrayList<GeaCodeValueBean> textbookConditionList = new ArrayList<GeaCodeValueBean>();
		textbookConditionList.add(new GeaCodeValueBean("", "Select Textbook Condition"));
		textbookConditionList.add(new GeaCodeValueBean("N", "Like New"));
		textbookConditionList.add(new GeaCodeValueBean("VG", "Very Good"));
		textbookConditionList.add(new GeaCodeValueBean("G", "Good"));
		textbookConditionList.add(new GeaCodeValueBean("O", "OK"));
		return textbookConditionList;
	}
	
	public static  ArrayList<GeaCodeValueBean> populatePriceList() {
		ArrayList<GeaCodeValueBean> textbookPriceList = new ArrayList<GeaCodeValueBean>();
		textbookPriceList.add(new GeaCodeValueBean("", "Select Textbook Price"));
		textbookPriceList.add(new GeaCodeValueBean("F",   "Free"));
		textbookPriceList.add(new GeaCodeValueBean("20",  "Rs. 20"));
		textbookPriceList.add(new GeaCodeValueBean("50",  "Rs. 50"));
		textbookPriceList.add(new GeaCodeValueBean("100", "Rs. 100"));
		textbookPriceList.add(new GeaCodeValueBean("200", "Rs. 200"));
		return textbookPriceList;
	}
		
	public static ArrayList<GeaCodeValueBean> populateChildsClassList() {
		ArrayList<GeaCodeValueBean> childsClassList = new ArrayList<GeaCodeValueBean>();
		childsClassList.add(new GeaCodeValueBean("", "Select Class"));
		childsClassList.add(new GeaCodeValueBean("S", "Sr. Kg."));
		childsClassList.add(new GeaCodeValueBean("1", "1"));
		childsClassList.add(new GeaCodeValueBean("2", "2"));
		childsClassList.add(new GeaCodeValueBean("3", "3"));
		childsClassList.add(new GeaCodeValueBean("4", "4"));
		childsClassList.add(new GeaCodeValueBean("5", "5"));
		childsClassList.add(new GeaCodeValueBean("6", "6"));
		childsClassList.add(new GeaCodeValueBean("7", "7"));
		childsClassList.add(new GeaCodeValueBean("8", "8"));
		childsClassList.add(new GeaCodeValueBean("9", "9"));
		childsClassList.add(new GeaCodeValueBean("10", "10"));
		childsClassList.add(new GeaCodeValueBean("11", "11"));
		childsClassList.add(new GeaCodeValueBean("12", "12"));	
		return childsClassList;
	}

	public static ArrayList<GeaCodeValueBean> populateBooksTypeList() {
		ArrayList<GeaCodeValueBean> booksTypeList = new ArrayList<GeaCodeValueBean>();
		booksTypeList.add(new GeaCodeValueBean("",   "Select Book"));
		booksTypeList.add(new GeaCodeValueBean("S",  "School Textbooks"));
		booksTypeList.add(new GeaCodeValueBean("SO", "Science Olympiad book"));
		booksTypeList.add(new GeaCodeValueBean("EO", "English Olympiad book"));
		booksTypeList.add(new GeaCodeValueBean("MO", "Maths Olympiad book"));
		booksTypeList.add(new GeaCodeValueBean("CO", "Computer Olympiad book"));
		booksTypeList.add(new GeaCodeValueBean("N",  "NSTSE books"));
		return booksTypeList;
	}
	
	
	public static TextbookAd getScreenValueFromDatabaseCodes(TextbookAd textbookAdFromDatabaseWithCodes) {
		TextbookAd textbookAdForScreen;
		
		if (textbookAdFromDatabaseWithCodes == null) {
			return null;
		}
		
		textbookAdForScreen = new TextbookAd();
		
		if (textbookAdFromDatabaseWithCodes.getChildsClass().equalsIgnoreCase("S")) {
				textbookAdForScreen.setChildsClass("Sr. Kg.");	
		} else {
				textbookAdForScreen.setChildsClass(textbookAdFromDatabaseWithCodes.getChildsClass());
		}
		
		if (textbookAdFromDatabaseWithCodes.getBookType() != null ) {
			switch(textbookAdFromDatabaseWithCodes.getBookType()) {
		         case "S" :textbookAdForScreen.setBookType("School Textbooks"); break;
		         case "SO" :textbookAdForScreen.setBookType("Science Olympiad book"); break;
		         case "EO" :textbookAdForScreen.setBookType("English Olympiad book"); break;
		         case "MO" :textbookAdForScreen.setBookType("Maths Olympiad book"); break;
		         case "CO" :textbookAdForScreen.setBookType("Computer Olympiad book"); break;
		         case "N" :textbookAdForScreen.setBookType("NSTSE books"); break;
	        }
		}
		if (textbookAdFromDatabaseWithCodes.getCondition() != null) {
			switch(textbookAdFromDatabaseWithCodes.getCondition()) {
				 case "N" :textbookAdForScreen.setCondition("Like New"); break;
		         case "VG" :textbookAdForScreen.setCondition("Very Good"); break;
		         case "G" :textbookAdForScreen.setCondition("Good"); break;
		         case "O" :textbookAdForScreen.setCondition("OK"); break;
	        }
		}
		if (textbookAdFromDatabaseWithCodes.getPrice()!= null) {
			switch(textbookAdFromDatabaseWithCodes.getPrice()) {
				 case "F" :textbookAdForScreen.setPrice("Free"); break;
		         case "20" :textbookAdForScreen.setPrice("Rs. 20"); break;
		         case "50" :textbookAdForScreen.setPrice("Rs. 50"); break;
		         case "100" :textbookAdForScreen.setPrice("Rs. 100"); break;
		         case "200" :textbookAdForScreen.setPrice("Rs. 200"); break;
	        }
		}
		
		if (textbookAdFromDatabaseWithCodes.getHideContactDetails().equalsIgnoreCase("Y")) {
    		  textbookAdForScreen.setHideContactDetails("true"); 
		} else {
    		  textbookAdForScreen.setHideContactDetails("false");
		}
		textbookAdForScreen.setAdOwnerNamePhoneEmail(textbookAdFromDatabaseWithCodes.getAdOwnerNamePhoneEmail());
		textbookAdForScreen.setAdOwnerName(textbookAdFromDatabaseWithCodes.getAdOwnerName());
		textbookAdForScreen.setAdOwnerPhone(textbookAdFromDatabaseWithCodes.getAdOwnerPhone());
		textbookAdForScreen.setAdOwnerEmail(textbookAdFromDatabaseWithCodes.getAdOwnerEmail());
		
		textbookAdForScreen.setBookTypeDBCode(textbookAdFromDatabaseWithCodes.getBookType());
		textbookAdForScreen.setSellerOrBuyer(textbookAdFromDatabaseWithCodes.getSellerOrBuyer());
		textbookAdForScreen.setLoginId(textbookAdFromDatabaseWithCodes.getLoginId());
		textbookAdForScreen.setComments(textbookAdFromDatabaseWithCodes.getComments());
		textbookAdForScreen.setSubmissionDate(textbookAdFromDatabaseWithCodes.getSubmissionDate());
		return textbookAdForScreen;
	} 
	
}