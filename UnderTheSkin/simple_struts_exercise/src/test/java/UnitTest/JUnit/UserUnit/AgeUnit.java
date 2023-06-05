package UnitTest.JUnit.UserUnit;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.Struts2.Helpers.Constants;
import com.itclinical.Struts2.Helpers.StringDateGenHelper;
import com.itclinical.Struts2.User.Date;

import freemarker.core.ParseException;

public class AgeUnit {

    
    public void calculateAgeTestEdgeCases(LocalDate today){
        Random random = new Random();
        int difference = random.nextInt(Constants.RANDOM_AGE_LOWERBOUND);
        LocalDate tomorrow = today.plusDays(1);   
        LocalDate yesterday = today.plusDays(-1);
        LocalDate birthDate = today.plusYears(-difference);
        Period myAgeToday = Period.between(birthDate, today);
        Period myAgeYesterday = Period.between(birthDate, yesterday);
        Period myAgeTomorrow = Period.between(birthDate, tomorrow);
        assertTrue(myAgeToday.getYears()==difference);
        assertTrue(myAgeTomorrow.getYears()==difference);
        if((today.getYear()%4==0) && (today.getMonth().getValue()==2) && (today.getDayOfMonth()==29) && (difference%4!=0)){
            assertTrue(myAgeYesterday.getYears()==difference);
        }
        else{
            if(today.equals(birthDate)){
                assertTrue(myAgeYesterday.getYears()==difference);
            }
            else{
                assertTrue(myAgeYesterday.getYears()==difference-1);
            }  
        }
    }

      public void calculateAgeTestYearDiff(LocalDate today){
        Random random = new Random();
        int difference = random.nextInt(Constants.RANDOM_AGE_LOWERBOUND);
        LocalDate birthDate = today.plusYears(-difference);
        Period myAgeToday = Period.between(birthDate, today);
        assertTrue(myAgeToday.getYears()==difference);
      }

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void calculateAgeTest() throws ParseException{       
        String todayString =  StringDateGenHelper.createDateFromNow(Constants.RANDOM_AGE_LOWERBOUND);
        Date todayDate = new Date(todayString);
        LocalDate today = todayDate.GetLocalDate();
        calculateAgeTestEdgeCases(today);
        calculateAgeTestYearDiff(today);     
    }
    
}
