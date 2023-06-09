package com.itclinical.struts2.user;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import org.junit.Test;

import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.helpers.DateConstants;
import com.itclinical.struts2.helpers.UserHelper;

import freemarker.core.ParseException;

public class UserUnit {


    @Test
	public void userCreationTest() throws ParseException, java.text.ParseException {
        User validUser = UserHelper.createValidUser();
        User minorUser = UserHelper.createMinorUser();
        User invalidDateUser = UserHelper.createInvalidDateUser();
        User invalidNameUser = UserHelper.createEmptyNameUser();
        assertTrue(validUser.isValid());
        assertFalse(minorUser.isValid());
        assertFalse(invalidDateUser.isValid());
        assertFalse(invalidNameUser.isValid());       

    }

    @Test
    public void isValidTest() throws java.text.ParseException{
        User validUser = UserHelper.createValidUser();
        User invalidNameUser = UserHelper.createNullNameUser();
        User minorUser = UserHelper.createMinorUser();
        User invalidDateUser = UserHelper.createInvalidDateUser();
        assertTrue(validUser.isValid());
        assertFalse(invalidNameUser.isValid());
        assertFalse(minorUser.isValid());
        assertFalse(invalidDateUser.isValid());
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