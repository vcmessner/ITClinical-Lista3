package com.itclinical.simple_struts_exercise;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import com.opensymphony.xwork2.ActionSupport;
import freemarker.core.ParseException;
@SuppressWarnings("serial")
public class HelloWorldAction extends ActionSupport {
    
    public static final int LEGAL_AGE = 18;
    public static final String INVALID_AGE_MESSAGE_STRING = "Please enter a valid Age!";
    public static final String INVALID_NAME_MESSAGE_STRING = "Please enter a valid Name!";
    public static final String AGE_RESTICTION_MESSAGE_STRING = "Minors are not allowed!";
    public static final String INVALID_DATE_MESSAGE_STRING = "Invalid Date. Date should be in format DD/MM/YYYY!";
    StringHelper myChecker = new StringHelper();

    private String name;
    private String date;
    private String age;

    public HelloWorldAction(String name, String date) {
        this.name = name;
        this.date = date;
        age = calculateAge(date);
    }

    public HelloWorldAction() {
        
    }
    public String execute() throws Exception{
        if(saveDate() && saveUsername()){
            age = calculateAge(date);
            return ActionSupport.SUCCESS;
        }
        return ActionSupport.INPUT;        
    }

    public boolean saveDate() throws Exception {
        if(isDatePropertyInvalid()) {
            addActionError(INVALID_DATE_MESSAGE_STRING);
            return false;
        }
        if(checkLegalAge(LEGAL_AGE,stringToDate(date))){
            return true;
        }
        else{
            addActionError(AGE_RESTICTION_MESSAGE_STRING);
            return false;
        }
    }

    public boolean saveUsername() throws Exception {
        if(isNamePropertyInvalid()) {
            addActionError(INVALID_NAME_MESSAGE_STRING);
            return false;        
        }
        return true;        
        }

    public LocalDate stringToDate(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringHelper.DATE_FORMAT);
        LocalDate userDate = LocalDate.parse(date, formatter);
        return userDate;
    }

    public boolean checkLegalAge(int legalAge, LocalDate date){
        LocalDate currentDate = LocalDate.now(ZoneOffset.UTC);
        currentDate = currentDate.minusYears(legalAge);
        if(currentDate.isBefore(date)){
            return false;
        }             
        return true;
    }

    public String calculateAge(String date){
        LocalDate currentDate = LocalDate.now(ZoneOffset.UTC);
        if(!isDatePropertyInvalid()) {
            LocalDate birthDate;
            try {
                birthDate = stringToDate(date);
                Period myAge = Period.between(birthDate, currentDate);
            return (myAge.getYears() + " years");
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }            
        }
        else{
            return "";
        }
    }

    public boolean isValidDateFormat(String date) {
        SimpleDateFormat myDateFormat = new SimpleDateFormat(StringHelper.DATE_FORMAT);
        myDateFormat.setLenient(false);        
        try {
            myDateFormat.parse(date);
            return true;
        }
        catch (java.text.ParseException e) {
            return false;
        }
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    protected boolean isNamePropertyInvalid() {
        return name == null || name.isEmpty();
    }

    protected boolean isDatePropertyInvalid() {
        return date == null || date.isEmpty() || (!isValidDateFormat(date));
    }
}