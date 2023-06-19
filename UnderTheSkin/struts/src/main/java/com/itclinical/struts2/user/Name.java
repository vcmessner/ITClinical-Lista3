package com.itclinical.struts2.user;

public class Name {

    public static String validateName(String myName){
        if(!Name.isNamePropertyInvalid(myName)){
           return myName;            
        }
        else{
            return null;
        }
    }

    public static boolean isNamePropertyInvalid(String myName) {
        return myName == null || myName.isEmpty();
    }
    
}
