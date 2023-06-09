package com.itclinical.struts2.helpers;

import java.text.ParseException;

import com.itclinical.struts2.user.Date;
import com.itclinical.struts2.user.Name;
import com.itclinical.struts2.user.User;

public class UserHelper {

    public static User createValidUser() throws ParseException{
        return new User(createValidName().getName(),createValidDate().getDate());      
    }

    public static User createNullNameUser() throws ParseException{
        return new User(createNullName().getName(),createValidDate().getDate());      
    } 

    public static User createEmptyNameUser() throws ParseException{
        return new User(createEmptyName().getName(),createValidDate().getDate());      
    } 

    public static User createMinorUser() throws ParseException{
        return new User(createValidName().getName(),createMinorDate().getDate());      
    } 
    public static User createInvalidDateUser() throws ParseException{
        return new User(createValidName().getName(),createInvalidDateString());      
    } 


    public static Name createValidName(){
        return new Name("Algo");
    }

    public static Name createEmptyName(){
        return new Name("");
    }

    public static Name createNullName(){
        return new Name(null);
    }

    public static Date createValidDate(){
        return new Date("31/12/1992");
    }

    public static Date createMinorDate(){
        return new Date("07/06/2022");
    }

    public static Date createInvalidDate(){
        return new Date("30/02/2022");
    }

    public static String createInvalidDateString(){
        return "30/02/2022";
    }



    
}
