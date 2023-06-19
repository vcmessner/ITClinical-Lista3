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
    public void isNamePropertyInvalidTest() {
        assertTrue(Name.isNamePropertyInvalid(UserHelper.createNullName()));
        assertTrue(Name.isNamePropertyInvalid(UserHelper.createEmptyName()));
        assertFalse(Name.isNamePropertyInvalid(" "));
        assertFalse(Name.isNamePropertyInvalid(UserHelper.createValidName()));
    }



    
}
