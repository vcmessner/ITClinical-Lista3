package com.itclinical.struts2.user;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.helpers.StringDateGenHelper;




public class DateUnit {

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void dateContructorTest(){
        Date nullDate =  new Date(null);
        Date emptyDate = new Date("");
        Date invalidDate = new Date(StringDateGenHelper.createInvalidDate());
        Date validMinorDate =  new Date(StringDateGenHelper.createMinorDateFromNow(Constants.LEGAL_AGE));
        Date validLegalDate =  new Date(StringDateGenHelper.createValidDateFromNow(Constants.LEGAL_AGE, Constants.LEGAL_AGE));
        assertTrue(nullDate.getDate()==null);
        assertTrue(emptyDate.getDate()==null);
        assertTrue(invalidDate.getDate()==null);
        assertTrue(validMinorDate.getDate()!=null);
        assertTrue(validLegalDate.getDate()!=null);

        
    }
    
}
