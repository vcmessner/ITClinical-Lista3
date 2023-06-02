package com.itclinical.Struts2.Helpers;

public class StringVerifyHelper {
    
    public static boolean isNameValid(String name) {
        return name.matches(Constants.REGEX_ALPHA);
    }

    public static boolean isAgeValid(String age) {
        return age.matches(Constants.REGEX_NUM);
    } 
    
    public static String trucateName(String name){
        if(name.length()<Constants.NAME_TEXT_FIELD_SIZE){
            return name;
        }
        else{
            return name.substring(0, Constants.NAME_TEXT_FIELD_SIZE);
        }
    }

    public Boolean isUpperCaseAlNum(String text){
       return text.matches("[A-Z0-9]+");
    }

    public Boolean isLowerCaseAlNum(String text){
        return text.matches("[a-z0-9]+");
    }

    public Boolean isAlNum(String text){
        if(text.matches("[A-Za-z0-9]+")){
            return true;
        }
        else{
            return false;
        }    
    }
}