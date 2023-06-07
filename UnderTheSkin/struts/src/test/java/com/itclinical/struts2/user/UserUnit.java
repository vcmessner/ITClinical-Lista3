package com.itclinical.struts2.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.helpers.ProbabilityConstants;
import com.itclinical.struts2.helpers.StringDateGenHelper;
import com.itclinical.struts2.helpers.StringGenHelper;

import freemarker.core.ParseException;

public class UserUnit {


    @RepeatedTest (Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
	public void User() throws ParseException, java.text.ParseException {
		String validName = StringGenHelper.createRandomAlphanumericString(50, 10) ;
        String blankName = "";
        String nullName = null;
		String validDate = StringDateGenHelper.createValidDateFromNow(Constants.LEGAL_AGE,ProbabilityConstants.RANDOM_AGE_LOWERBOUND);
        String invalidDate = StringDateGenHelper.createInvalidDate();
        String minorDate = StringDateGenHelper.createMinorDateFromNow(Constants.LEGAL_AGE);
		assertTrue(new User(validName,validDate).isValid());
        assertFalse(new User(blankName,validDate).isValid());
        assertFalse(new User(nullName,validDate).isValid());
        assertFalse(new User(validName,invalidDate).isValid());
        assertFalse(new User(blankName,invalidDate).isValid());
        assertFalse(new User(nullName,invalidDate).isValid());
        assertFalse(new User(validName,minorDate).isValid());
        assertFalse(new User(blankName,minorDate).isValid());
        assertFalse(new User(nullName,minorDate).isValid());
	}
    
}