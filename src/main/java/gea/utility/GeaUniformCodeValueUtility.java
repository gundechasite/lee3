package gea.utility;

import gea.bean.GeaCodeValueBean;
import gea.model.UniformAd;

import java.util.ArrayList;
/* Changes here should be reflected in getScreenValueFromDatabaseCodes below and DBUtility_TextBookUniform select , orderby ... */
/* This class populates drop down lists for Buy/Sell Uniforms screen */
public class GeaUniformCodeValueUtility {
	
	public static  ArrayList<GeaCodeValueBean> populateVedaList() {
		ArrayList<GeaCodeValueBean> vedaList = new ArrayList<GeaCodeValueBean>();
		vedaList.add(new GeaCodeValueBean("", ""));
		vedaList.add(new GeaCodeValueBean("Y", "Yajurveda"));
		vedaList.add(new GeaCodeValueBean("R", "RigVeda"));
		vedaList.add(new GeaCodeValueBean("S", "SamaVeda"));
		vedaList.add(new GeaCodeValueBean("A", "AtharvaVeda"));
		return vedaList;
	}

	public static  ArrayList<GeaCodeValueBean> populatePartOfUniformList() {
		ArrayList<GeaCodeValueBean> partOfUniformList = new ArrayList<GeaCodeValueBean>();
		partOfUniformList.add(new GeaCodeValueBean("", "Select part of Uniform"));
		partOfUniformList.add(new GeaCodeValueBean("PT", "PE Uniform T-Shirt"));
		partOfUniformList.add(new GeaCodeValueBean("PS", "PE Uniform Shorts"));
		partOfUniformList.add(new GeaCodeValueBean("PP", "PE Uniform Full Pant"));
		partOfUniformList.add(new GeaCodeValueBean("ST", "School Uniform T-Shirt"));
		partOfUniformList.add(new GeaCodeValueBean("SS", "School Uniform Shorts"));
		partOfUniformList.add(new GeaCodeValueBean("SP", "School Uniform Full Pant"));
		partOfUniformList.add(new GeaCodeValueBean("GF", "Girls frock"));
		partOfUniformList.add(new GeaCodeValueBean("HO", "Hoody"));
		partOfUniformList.add(new GeaCodeValueBean("SH", "Shoes"));
		partOfUniformList.add(new GeaCodeValueBean("BZ", "Blazer"));
		partOfUniformList.add(new GeaCodeValueBean("IS", "IGCSE Shirt"));
		partOfUniformList.add(new GeaCodeValueBean("IP", "IGCSE Pant"));
		return partOfUniformList;
	}

	public static  ArrayList<GeaCodeValueBean> populateUniformConditionList () {
		ArrayList<GeaCodeValueBean> uniformConditionList = new ArrayList<GeaCodeValueBean>();
		uniformConditionList.add(new GeaCodeValueBean("", "Condition"));
		uniformConditionList.add(new GeaCodeValueBean("N", "Like New"));
		uniformConditionList.add(new GeaCodeValueBean("VG", "Very Good"));
		uniformConditionList.add(new GeaCodeValueBean("G", "Good"));
		uniformConditionList.add(new GeaCodeValueBean("O", "OK"));
		return uniformConditionList;
	}

	public static  ArrayList<GeaCodeValueBean> populatePriceList() {
		ArrayList<GeaCodeValueBean> uniformPriceList = new ArrayList<GeaCodeValueBean>();
		uniformPriceList.add(new GeaCodeValueBean("", "Select Uniform Price"));
		uniformPriceList.add(new GeaCodeValueBean("F", "Free"));
		uniformPriceList.add(new GeaCodeValueBean("100", "Rs. 100"));
		uniformPriceList.add(new GeaCodeValueBean("200", "Rs. 200"));
		uniformPriceList.add(new GeaCodeValueBean("1/4", "1/4th of New Price"));
		uniformPriceList.add(new GeaCodeValueBean("1/2", "1/2 of New Price"));
		return uniformPriceList;
	}
	
	

	public static UniformAd getScreenValueFromDatabaseCodes(UniformAd uniformAdFromDatabaseWithCodes) {
		UniformAd uniformAdForScreen;
		if (uniformAdFromDatabaseWithCodes != null) {
			uniformAdForScreen = new UniformAd();
			
			if (uniformAdFromDatabaseWithCodes.getVeda() != null) {
				switch(uniformAdFromDatabaseWithCodes.getVeda()) {
			         case "Y" :uniformAdForScreen.setVeda("Yajurveda"); break;
			         case "R" :uniformAdForScreen.setVeda("RigVeda"); break;
			         case "S" :uniformAdForScreen.setVeda("SamaVeda"); break;
			         case "A" :uniformAdForScreen.setVeda("AtharvaVeda"); break;
		        }
			}

			
			if (uniformAdFromDatabaseWithCodes.getPartOfUniform() != null) {
				switch(uniformAdFromDatabaseWithCodes.getPartOfUniform()) {
					 case "PT" :uniformAdForScreen.setPartOfUniform("PE Uniform T-Shirt"); break;
			         case "PS" :uniformAdForScreen.setPartOfUniform("PE Uniform Shorts"); break;
			         case "PP" :uniformAdForScreen.setPartOfUniform("PE Uniform Full Pant"); break;
			         case "ST" :uniformAdForScreen.setPartOfUniform("School Uniform T-Shirt"); break;
			         case "SS" :uniformAdForScreen.setPartOfUniform("School Uniform Shorts"); break;
			         case "SP" :uniformAdForScreen.setPartOfUniform("School Uniform Full Pant"); break;
			         case "GF" :uniformAdForScreen.setPartOfUniform("Girls frock"); break;
					 case "HO" :uniformAdForScreen.setPartOfUniform("Hoody"); break;
			         case "SH" :uniformAdForScreen.setPartOfUniform("Shoes"); break;
			         case "BZ" :uniformAdForScreen.setPartOfUniform("Blazer"); break;
			         case "IS" :uniformAdForScreen.setPartOfUniform("IGCSE Shirt"); break;
			         case "IP" :uniformAdForScreen.setPartOfUniform("IGCSE Pant"); break;
				}
			}
			
			
			if (uniformAdFromDatabaseWithCodes.getUniformCondition() != null) {
				switch(uniformAdFromDatabaseWithCodes.getUniformCondition()) {
					 case "N" :uniformAdForScreen.setUniformCondition("Like New"); break;
			         case "VG" :uniformAdForScreen.setUniformCondition("Very Good"); break;
			         case "G" :uniformAdForScreen.setUniformCondition("Good"); break;
			         case "O" :uniformAdForScreen.setUniformCondition("OK"); break;
		        }
			}
			if (uniformAdFromDatabaseWithCodes.getPrice()!= null) {
				switch(uniformAdFromDatabaseWithCodes.getPrice()) {
				/* Changes here should be reflected in searchUniformAdResponses order by */
					 case "F" :uniformAdForScreen.setPrice("Free"); break;
					 case "100" :uniformAdForScreen.setPrice("Rs. 100"); break;
					 case "200" :uniformAdForScreen.setPrice("Rs. 200"); break;
					 case "1/4" :uniformAdForScreen.setPrice("1/4th of New Price"); break;
			         case "1/2" :uniformAdForScreen.setPrice("1/2 of New Price"); break;
		        }
			}
		
    	    if (uniformAdFromDatabaseWithCodes.getHideContactDetails().equalsIgnoreCase("Y")) {
    	    	uniformAdForScreen.setHideContactDetails("true"); 
    	    } else {
    	    	uniformAdForScreen.setHideContactDetails("false");
    	    }
			uniformAdForScreen.setSellerOrBuyer(uniformAdFromDatabaseWithCodes.getSellerOrBuyer());
			uniformAdForScreen.setLoginId(uniformAdFromDatabaseWithCodes.getLoginId());
			uniformAdForScreen.setComments(uniformAdFromDatabaseWithCodes.getComments());
			uniformAdForScreen.setSize(uniformAdFromDatabaseWithCodes.getSize());
			uniformAdForScreen.setSubmissionDate(uniformAdFromDatabaseWithCodes.getSubmissionDate());
			uniformAdForScreen.setPartOfUniformDBCode(uniformAdFromDatabaseWithCodes.getPartOfUniform());
			uniformAdForScreen.setVedaDBCode(uniformAdFromDatabaseWithCodes.getVedaDBCode());
			uniformAdForScreen.setAdOwnerNamePhoneEmail(uniformAdFromDatabaseWithCodes.getAdOwnerNamePhoneEmail());
			uniformAdForScreen.setAdOwnerName(uniformAdFromDatabaseWithCodes.getAdOwnerName());
			uniformAdForScreen.setAdOwnerPhone(uniformAdFromDatabaseWithCodes.getAdOwnerPhone());
			uniformAdForScreen.setAdOwnerEmail(uniformAdFromDatabaseWithCodes.getAdOwnerEmail());
			
			return uniformAdForScreen;
		} else {
			return null;
		}
	}	
}
