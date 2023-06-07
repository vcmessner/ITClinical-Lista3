package com.itclinical.struts2.helpers;

import java.time.LocalDate;
import java.util.Random;

public class StringDateGenHelper {
    
    public static String createRandomDateFromRange(LocalDate initialDate, LocalDate endDate){
        Random random = new Random();
        long days = endDate.toEpochDay() - initialDate.toEpochDay() + 1;
        long randomDays = random.nextInt((int) days);
        LocalDate randomDate = endDate.minusDays(randomDays); 
        return randomDate.format(DateConstants.DEFAULT_FORMATTER);
     }

     public static String createInvalidDate(){
        Random random = new Random();
        int day = random.nextInt(31) + 32;
        int month = random.nextInt(12) + 13;
        int year = random.nextInt(9000) + 1000;
        return day+"/"+month+"/"+year;
    }

    public static String createValidDateFromNow(int LegalAge, int lowerbound){
        LocalDate currentDate = LocalDate.now(DateConstants.DEFAULT_ZONE_OFFSET);
        currentDate = currentDate.minusYears(LegalAge);
        LocalDate startDate = currentDate.minusYears(lowerbound);
        return  createRandomDateFromRange(startDate,currentDate);
    }
    
    public static String createMinorDateFromNow(int LegalAge){
        LocalDate currentDate = LocalDate.now(DateConstants.DEFAULT_ZONE_OFFSET);
        LocalDate startDate = currentDate.minusYears(LegalAge);
        return  createRandomDateFromRange(startDate.plusDays(1),currentDate.minusDays(1));
    }

    public static String createDateFromNow(int lowerbound){        
        LocalDate currentDate = LocalDate.now(DateConstants.DEFAULT_ZONE_OFFSET);
        LocalDate startDate = currentDate.minusYears(lowerbound);
        return  createRandomDateFromRange(startDate,currentDate);
    }
    
}
