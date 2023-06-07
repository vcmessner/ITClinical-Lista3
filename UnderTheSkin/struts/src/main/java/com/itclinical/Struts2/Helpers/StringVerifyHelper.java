package com.itclinical.struts2.helpers;

public class StringVerifyHelper {
    
    public static boolean isNameValid(String name) {
        return name.matches(RegexConstants.REGEX_ALPHA);
    }

    public static boolean isAgeValid(String age) {
        return age.matches(RegexConstants.REGEX_NUM);
    } 
    
    public static String trucateName(String name){
        if(name.length()<Constants.NAME_TEXT_FIELD_SIZE){
            return name;
        }
        else{
            return name.substring(0, Constants.NAME_TEXT_FIELD_SIZE);
        }
    }

    public static Boolean isUpperCaseAlphanumeric(String text){
       return text.matches("[A-Z0-9]+");
    }

    public static Boolean isLowerCaseAlphanumeric(String text){
        return text.matches("[a-z0-9]+");
    }

    public static Boolean isAlphanumeric(String text){
        return text.matches("[A-Za-z0-9]+");
}
}