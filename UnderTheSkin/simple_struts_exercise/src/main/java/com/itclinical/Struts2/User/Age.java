package com.itclinical.Struts2.User;

import java.time.LocalDate;
import java.time.Period;

import com.itclinical.Struts2.Helpers.Constants;

import freemarker.core.ParseException;

public class Age {
    private int age=-1;

    public Age(String birthDate) throws java.text.ParseException {
        this.age = calculateAge(birthDate);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int calculateAge(String date){
        LocalDate currentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
        Date myBirthDate = new Date(date);
        if(myBirthDate.getDate()!=null) {
            LocalDate birthDate;
            try {
                birthDate =myBirthDate.GetLocalDate();
                Period myAge = Period.between(birthDate, currentDate);
                return (myAge.getYears());
            } 
            catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }            
        }
        return 0;
    }

    public boolean isLegal(int legalAge){
        return !(age<legalAge);
    }

    
    
}
