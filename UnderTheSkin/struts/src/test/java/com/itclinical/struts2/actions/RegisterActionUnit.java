package com.itclinical.struts2.actions;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;
import com.itclinical.struts2.helpers.UserHelper;
import com.itclinical.struts2.user.User;
import com.opensymphony.xwork2.ActionSupport;


public class RegisterActionUnit extends RegisterAction {


    RegisterActionUnit myAction;

    @Before
    public void setUpTest(){
        myAction = new RegisterActionUnit();
    }

    @Override
    public String getText(String key){
        return key;
    }

    @Test
    public void getError_getInvalidDateError(){
        User user =  UserHelper.createInvalidDateUser();
        this.name = user.getName();
        this.date = user.getDate();
        this.user = user;
        String response = this.validateUser();
        assertEquals(response,ActionSupport.INPUT);
        assertEquals(this.getError(),"INVALID_DATE_MESSAGE_STRING");       
    }

    @Test
    public void getError_getInvalidNameError(){
        User user =  UserHelper.createEmptyNameUser();
        this.name = user.getName();
        this.date = user.getDate();
        this.user = user;
        String response = this.validateUser();
        assertEquals(response,ActionSupport.INPUT);
        assertEquals(this.getError(),"INVALID_NAME_MESSAGE_STRING");
    }

    
    @Test
    public void getError_getMinorAgeError(){
        User user =  UserHelper.createMinorUser();
        this.name = user.getName();
        this.date = user.getDate();
        this.user = user;
        String response = this.validateUser();
        assertEquals(response,ActionSupport.INPUT);
        assertEquals(this.getError(),"AGE_RESTRICTION_MESSAGE_STRING");
    }

    @Test
    public void getError_getValid(){
        User user =  UserHelper.createValidUser();
        this.name = user.getName();
        this.date = user.getDate();
        this.user = user;
        String response = this.validateUser();
        assertEquals(response,ActionSupport.SUCCESS);
        assertEquals(this.getError(),"");
    }

}
