package com.itclinical.struts2.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.itclinical.struts2.helpers.UserHelper;

public class NameUnit {
    
    @Test
    public void nameContructorTest() {
        Name nullName =  new Name(UserHelper.createNullName().getName());
        Name emptyName = new Name(UserHelper.createEmptyName().getName());
        Name blankName = new Name(" ");
        Name validName = new Name(UserHelper.createValidName().getName());
        assertNull(nullName.getName());
        assertNull(emptyName.getName());
        assertNotNull(blankName.getName());
        assertNotNull(validName.getName());      
    }

    @Test
    public void isNamePropertyInvalidTest() {
        Name name =  new Name(UserHelper.createNullName().getName());
        assertTrue(name.isNamePropertyInvalid(UserHelper.createNullName().getName()));
        assertTrue(name.isNamePropertyInvalid(UserHelper.createEmptyName().getName()));
        assertFalse(name.isNamePropertyInvalid(" "));
        assertFalse(name.isNamePropertyInvalid(UserHelper.createValidName().getName()));
    }



    
}
