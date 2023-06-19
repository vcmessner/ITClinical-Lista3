package com.itclinical.struts2.user;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import org.junit.Test;

import com.itclinical.struts2.exceptions.RegisterException;
import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.helpers.DateConstants;
import com.itclinical.struts2.helpers.UserHelper;

import freemarker.core.ParseException;

public class UserUnit {




    @Test
	public void isValidTest_minorUser() throws ParseException, java.text.ParseException {
        User minorUser = UserHelper.createMinorUser();
        try{
            System.out.println(minorUser.birthDate);
            minorUser.isValid();
            assertFalse(true);
        }
        catch (RegisterException e){
            e.getCode().equals("AGE_RESTRICTION_MESSAGE_STRING");
        }
    }

    @Test
	public void isValidTest_invalidDateUser() throws ParseException, java.text.ParseException {
        User invalidDateUser = UserHelper.createInvalidDateUser();
        try{
            invalidDateUser.isValid();
            assertFalse(true);
        }
        catch (RegisterException e){
            e.getCode().equals("INVALID_DATE_MESSAGE_STRING");
        }

    }

    @Test
	public void isValidTest_invalidNameUser() throws ParseException, java.text.ParseException {
        User invalidNameUser = UserHelper.createEmptyNameUser();
        try{
            invalidNameUser.isValid();
            assertFalse(true);
        }
        catch (RegisterException e){
            e.getCode().equals("INVALID_NAME_MESSAGE_STRING");
        }      
    }

    @Test
    public void calculateAgeTest() throws java.text.ParseException{
        LocalDate today = LocalDate.now(DateConstants.DEFAULT_ZONE_OFFSET);
        LocalDate birthDate = today.minusYears(Constants.LEGAL_AGE);
        LocalDate yesterday = today.minusDays(1);
        LocalDate tomorrow = today.plusDays(1);
        Period myAgeToday = Period.between(birthDate, today);
        Period myAgeYesterday = Period.between(birthDate, yesterday);
        Period myAgeTomorrow = Period.between(birthDate, tomorrow);
        User validUserToday = new User("Algo", birthDate.format(DateConstants.DEFAULT_FORMATTER));
        assertTrue(myAgeToday.getYears()==validUserToday.getAge());
        assertTrue(myAgeTomorrow.getYears()==validUserToday.getAge());
        if((today.getYear()%4==0) && (today.getMonth().getValue()==2) && (today.getDayOfMonth()==29) && (Constants.LEGAL_AGE%4!=0)){
            assertTrue(myAgeYesterday.getYears()==validUserToday.getAge());
        }
        else{
            assertTrue(myAgeYesterday.getYears()==validUserToday.getAge()-1);
        }
    }
    
}