package com.itclinical.struts2.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.itclinical.struts2.helpers.UserHelper;

public class NameUnit {
    
    @Test
    public void nameContructorTest() {
    }

    @Test
    public void isNamePropertyInvalidTest_validateNullName() {
        assertTrue(Name.isNamePropertyInvalid(UserHelper.createNullName()));
    }


    @Test
    public void isNamePropertyInvalidTest_validateEmptyName() {
        assertTrue(Name.isNamePropertyInvalid(UserHelper.createEmptyName()));
    }


    @Test
    public void isNamePropertyInvalidTest_validateBlankName() {
        assertFalse(Name.isNamePropertyInvalid(" "));
    }

    @Test
    public void isNamePropertyInvalidTest_validateValidName() {
        assertFalse(Name.isNamePropertyInvalid(UserHelper.createValidName()));
    }
    



    
}
