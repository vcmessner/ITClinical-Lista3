package com.itclinical.struts2.actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.helpers.UserHelper;
import com.itclinical.struts2.user.Date;
import com.itclinical.struts2.user.Name;
import com.itclinical.struts2.user.User;

import helpers.UnderTheSkinHelpers;


public class RegisterActionUnit {

    private UnderTheSkinHelpers helper = new UnderTheSkinHelpers();
    private Map<String, String> inputParameterMap = new HashMap<>();
    RegisterAction myAction;

    @Before
    public void setUpTest() throws Exception {
        helper.initServletRequestMockObject();
        try{
            helper.setUp();
            }
        catch(Exception e){
            e.printStackTrace();
        }
        inputParameterMap.put("name", "algo");
        inputParameterMap.put("date", "31/12/1992");
        helper.getRequest().addParameters(inputParameterMap);
        myAction = (RegisterAction)helper.createAction(Constants.REGISTER_ACTION_URI, true);
        helper.executeProxy();
    }



    @Test
    public void checkDateTest() throws Exception{        
        assertTrue(myAction.checkDate(new Date("31/12/1992").getDate()));
        assertFalse(myAction.checkDate(new Date("31/13/1992").getDate()));  
    }

    @Test
    public void checkNameTest(){
        assertTrue(myAction.checkDate(new Name("Algo").getName()));
        assertFalse(myAction.checkDate(new Name("").getName()));
        assertFalse(myAction.checkDate(new Name(null).getName()));
    }

    @Test
    public void checkLegalAgeTest() throws ParseException{
        User validUser = UserHelper.createValidUser();
        User minorUser = UserHelper.createMinorUser();
        assertTrue(myAction.checkLegalAge(validUser));
        assertFalse(myAction.checkLegalAge(minorUser));
    }
}
