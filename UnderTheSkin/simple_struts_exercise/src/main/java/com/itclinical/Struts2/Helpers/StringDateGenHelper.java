package com.itclinical.Struts2.Helpers;

import java.time.LocalDate;
import java.util.Random;

public class StringDateGenHelper {
    
    public String createRandomDateFromRange(LocalDate initialDate, LocalDate endDate){
        Random random = new Random();
        long days = endDate.toEpochDay() - initialDate.toEpochDay() + 1;
        long randomDays = random.nextInt((int) days);
        LocalDate randomDate = initialDate.plusDays(randomDays);      
        return randomDate.format(Constants.DEFAULT_FORMATTER);
     }

     public String createInvalidDate(){
        Random random = new Random();
        int day = random.nextInt(31) + 32;
        int month = random.nextInt(12) + 13;
        int year = random.nextInt(9000) + 1000;
        return day+"/"+month+"/"+year;
    }

    public String createValidDateFromNow(int LegalAge, int lowerbound){
        LocalDate currentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
        currentDate = currentDate.minusYears(LegalAge);
        LocalDate startDate = currentDate.minusYears(lowerbound);
        return  createRandomDateFromRange(startDate,currentDate);
    }
    
    public String createMinorDateFromNow(int LegalAge){
        LocalDate currentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
        LocalDate startDate = currentDate.minusYears(LegalAge);
        currentDate=currentDate.plusDays(1); //01/06/2005
        startDate=startDate.plusDays(1);
        return  createRandomDateFromRange(startDate,currentDate);
    }

    public String createDateFromNow(int lowerbound){        
        LocalDate currentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
        LocalDate startDate = currentDate.minusYears(lowerbound);
        return  createRandomDateFromRange(startDate,currentDate);
    }
    
}
