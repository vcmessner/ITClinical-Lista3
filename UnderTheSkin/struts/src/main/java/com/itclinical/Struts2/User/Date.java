package com.itclinical.struts2.user;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import freemarker.core.ParseException;
import java.util.Calendar;

import com.itclinical.struts2.helpers.DateConstants;

public class Date {

    private String date=null;

    public Date(String myDate) {
        if(!isDatePropertyInvalid(myDate)){ 
            SimpleDateFormat myDateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);
            myDateFormat.setLenient(false);
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(myDateFormat.parse(myDate));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            this.date=myDateFormat.format(cal.getTime());
        }
        else{
            this.date = null;
        }
    }


    public LocalDate GetLocalDate() throws ParseException {
        LocalDate userDate = LocalDate.parse(date, DateConstants.DEFAULT_FORMATTER);
        return userDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    protected boolean isDatePropertyInvalid(String date) {
        return date == null || date.isEmpty() || (!isValidDateFormat(date));
    }

    protected boolean isValidDateFormat(String date) {
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
