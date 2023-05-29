package com.itclinical.simple_struts_exercise;

public class StringHelper {
    public static final String REGEX_ALPHA = "[a-zA-Z]+";
    public static final String REGEX_NUM ="[0-9]+";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final int NAME_TEXT_FIELD_SIZE = 40;


    public boolean isNameValid(String name) {
        return name.matches(REGEX_ALPHA) ? true : false;
    }

    public boolean isAgeValid(String age) {
        return age.matches(REGEX_NUM) ? true : false;
    } 
    
    public String trucateName(String name){
        if(name.length()<NAME_TEXT_FIELD_SIZE){
            return name;
        }
        else{
            return name.substring(0, NAME_TEXT_FIELD_SIZE);
        }
    }
}