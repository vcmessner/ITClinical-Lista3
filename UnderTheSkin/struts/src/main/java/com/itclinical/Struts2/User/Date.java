package com.itclinical.struts2.user;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import freemarker.core.ParseException;
import java.util.Calendar;

import com.itclinical.struts2.helpers.DateConstants;

public class Date {

    public static String validateDate(String date){
        if(!Date.isDatePropertyInvalid(date)){
           return date;            
        }
        else{
            return null;
        }
    }


    public static LocalDate GetLocalDate(String date) throws ParseException {
        return LocalDate.parse(date, DateConstants.DEFAULT_FORMATTER);
    }

    public static boolean isDatePropertyInvalid(String date) {
        return date == null || date.isEmpty() || (!isValidDateFormat(date));
    }

    protected static boolean isValidDateFormat(String date) {
        SimpleDateFormat myDateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);
        myDateFormat.setLenient(false);
        Calendar cal = Calendar.getInstance();       
        try {
            cal.setTime(myDateFormat.parse(date));
            return true;
        }
        catch (java.text.ParseException e) {

            return false;
        }
    }
    
}
