package com.itclinical.struts2.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.itclinical.struts2.helpers.UserHelper;




public class DateUnit {

    @Test
    public void dateContructorTest(){
       Date validDate = UserHelper.createValidDate();
       Date invalidDate = UserHelper.createInvalidDate();
       Date minorDate = UserHelper.createMinorDate();
       assertNull(invalidDate.getDate());
       assertNotNull(validDate.getDate());
       assertNotNull(minorDate.getDate());        
    }

    @Test
    public void isValidDateFormatTest() {
        Date invalidDate = UserHelper.createInvalidDate();
        assertFalse(invalidDate.isValidDateFormat(UserHelper.createInvalidDateString()));
        assertTrue(invalidDate.isValidDateFormat(UserHelper.createValidDate().getDate()));
        assertTrue(invalidDate.isValidDateFormat(UserHelper.createMinorDate().getDate()));    
    }

    @Test
    public void isDatePropertyInvalidTest() {
        Date invalidDate = UserHelper.createInvalidDate();
        assertTrue(invalidDate.isDatePropertyInvalid(UserHelper.createInvalidDateString()));
        assertTrue(invalidDate.isDatePropertyInvalid(null));
        assertTrue(invalidDate.isDatePropertyInvalid(""));
        assertTrue(invalidDate.isDatePropertyInvalid(" "));
        assertTrue(invalidDate.isDatePropertyInvalid("         "));
        assertFalse(invalidDate.isDatePropertyInvalid(UserHelper.createValidDate().getDate()));
        assertFalse(invalidDate.isDatePropertyInvalid(UserHelper.createMinorDate().getDate()));
    }

}
