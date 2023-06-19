package com.itclinical.struts2.helpers;


import com.itclinical.struts2.user.User;

public class UserHelper {

    public static User createValidUser(){
        return new User(createValidName(),createValidDate());      
    }

    public static User createNullNameUser(){
        return new User(createNullName(),createValidDate());      
    } 

    public static User createEmptyNameUser(){
        return new User(createEmptyName(),createValidDate());      
    } 

    public static User createMinorUser(){
        return new User(createValidName(),createMinorDate());      
    } 
    public static User createInvalidDateUser(){
        return new User(createValidName(),createInvalidDateString());      
    } 


    public static String createValidName(){
        return "Algo";
    }

    public static String createEmptyName(){
        return "";
    }

    public static String createNullName(){
        return null;
    }

    public static String createValidDate(){
        return "31/12/1992";
    }

    public static String createMinorDate(){
        return "07/06/2022";
    }

    public static String createInvalidDate(){
        return "30/02/2022";
    }

    public static String createInvalidDateString(){
        return "30/02/2022";
    }
    
}
