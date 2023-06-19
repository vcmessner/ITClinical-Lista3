package com.itclinical.struts2.user;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.itclinical.struts2.helpers.UserHelper;

public class DateUnit {

    @Test
    public void dateContructorTest(){    
    }

    @Test
    public void isValidDateFormatTest_validateInvalidDateString() {
        assertFalse(Date.isValidDateFormat(UserHelper.createInvalidDateString()));
    }

    @Test
    public void isValidDateFormatTest_validateValidDateString() {
        assertTrue(Date.isValidDateFormat(UserHelper.createValidDate()));  
    }

    @Test
    public void isValidDateFormatTest_validateMinorDateString() {
        assertTrue(Date.isValidDateFormat(UserHelper.createMinorDate()));    
    }

    @Test
    public void isDatePropertyInvalidTest_validateNullDate() {
        assertTrue(Date.isDatePropertyInvalid(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ","  "}) // six numbers
    public void isDatePropertyInvalidTest_validateWhitespaceDate(String date) {
        assertTrue(Date.isDatePropertyInvalid(date));
    }
    @Test
    public void isDatePropertyInvalidTest_validateInvalidDateString() {
        assertTrue(Date.isDatePropertyInvalid(UserHelper.createInvalidDateString()));
    }
    @Test
    public void isDatePropertyInvalidTest_validateMinorDateString() {
        assertFalse(Date.isDatePropertyInvalid(UserHelper.createMinorDate()));
    }
    @Test
    public void isDatePropertyInvalidTest_validateValidDateString() {
        assertFalse(Date.isDatePropertyInvalid(UserHelper.createValidDate()));
    }
}
