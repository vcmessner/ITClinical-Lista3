package UnitTest.JUnit.HelpersUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.Struts2.Helpers.Constants;
import com.itclinical.Struts2.Helpers.StringDateGenHelper;

public class StringDateGenHelperUnit {


    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void createRandomDateFromRangeTest(){
        LocalDate presentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
        LocalDate pastDate =  presentDate.minusYears(Constants.RANDOM_AGE_LOWERBOUND);
        LocalDate futureDate = presentDate.plusYears(Constants.RANDOM_AGE_LOWERBOUND);
        createDateTestEdgeCase(presentDate, 0, 0);
        LocalDate presentToFuture = returnNonSideMatch(presentDate,futureDate);
        LocalDate pastToPresent = returnNonSideMatch (pastDate,presentDate);     
        assertTrue(presentToFuture.isAfter(presentDate));
        assertTrue(presentToFuture.isBefore(futureDate));
        assertTrue(pastToPresent.isAfter(pastDate));
        assertTrue(pastToPresent.isBefore(presentDate));
    }

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void createInvalidDateTest(){
        String myDate = StringDateGenHelper.createInvalidDate();
        boolean output =true;
        SimpleDateFormat myDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        myDateFormat.setLenient(false);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(myDateFormat.parse(myDate));
        }
        catch (java.text.ParseException e) {
            output = false;
        }
        assertFalse(output);
    }

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void createDateFromNowTest(){
        LocalDate currentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
        createDateTestEdgeCase(currentDate,0,3);
        LocalDate myDate = LocalDate.parse(StringDateGenHelper.createDateFromNow(Constants.RANDOM_AGE_LOWERBOUND),Constants.DEFAULT_FORMATTER);
        while(myDate.equals(currentDate)||myDate.equals(currentDate.minusYears(Constants.RANDOM_AGE_LOWERBOUND))){
            myDate = LocalDate.parse(StringDateGenHelper.createDateFromNow(Constants.RANDOM_AGE_LOWERBOUND),Constants.DEFAULT_FORMATTER);
        }
        assertTrue(myDate.isBefore(currentDate));
        assertTrue(myDate.isAfter(currentDate.minusYears(Constants.RANDOM_AGE_LOWERBOUND)));
    }

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void createValidDateFromNowTest(){
        LocalDate currentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
        currentDate = currentDate.minusYears(Constants.LEGAL_AGE);
        createDateTestEdgeCase(currentDate,0,1);        
        LocalDate myDate = LocalDate.parse(StringDateGenHelper.createValidDateFromNow(Constants.LEGAL_AGE,Constants.RANDOM_AGE_LOWERBOUND),Constants.DEFAULT_FORMATTER);
        LocalDate startDate = currentDate.minusYears(Constants.RANDOM_AGE_LOWERBOUND);
        while(myDate.equals(currentDate)||myDate.equals(startDate)){
            myDate = LocalDate.parse(StringDateGenHelper.createValidDateFromNow(Constants.LEGAL_AGE,Constants.RANDOM_AGE_LOWERBOUND),Constants.DEFAULT_FORMATTER);
        } 
        assertTrue(myDate.isAfter(startDate));
        assertTrue(myDate.isBefore(currentDate)); 
    }

   

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void createMinorDateFromNowTest(){
        LocalDate currentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
        //createDateTestEdgeCase(currentDate,0,2);        
        LocalDate startDate = currentDate.minusYears(Constants.LEGAL_AGE);               
        LocalDate myDate = LocalDate.parse(StringDateGenHelper.createMinorDateFromNow(Constants.LEGAL_AGE),Constants.DEFAULT_FORMATTER);
        while(myDate.equals(currentDate)|| myDate.equals(startDate)){
            myDate = LocalDate.parse(StringDateGenHelper.createMinorDateFromNow(Constants.LEGAL_AGE),Constants.DEFAULT_FORMATTER);
        }
        assertTrue(myDate.isAfter(startDate));
        assertTrue(myDate.isBefore(currentDate)); 
    }

    
    private void createDateTestEdgeCase(LocalDate currentDate, int lowerbound, int function){
        LocalDate myDate;
        switch(function){
            case 0:
                myDate =LocalDate.parse(StringDateGenHelper.createRandomDateFromRange(currentDate, currentDate.minusYears(lowerbound)), Constants.DEFAULT_FORMATTER);          
                assertTrue(myDate.equals(currentDate));
                break;
            case 1:
                myDate = LocalDate.parse(StringDateGenHelper.createValidDateFromNow(Constants.LEGAL_AGE,lowerbound),Constants.DEFAULT_FORMATTER);
                assertTrue(myDate.equals(currentDate)); 
                break;
            case 2:
                //myDate = LocalDate.parse(StringDateGenHelper.createMinorDateFromNow(Constants.LEGAL_AGE),Constants.DEFAULT_FORMATTER);
                //assertTrue(myDate.equals(currentDate));
                break;
            case 3:
                myDate = LocalDate.parse(StringDateGenHelper.createDateFromNow(0),Constants.DEFAULT_FORMATTER);
                assertTrue(myDate.equals(currentDate)); 
            default:            
        }
    }

    private LocalDate returnNonSideMatch(LocalDate begin, LocalDate end){
        LocalDate inputDate = LocalDate.parse(StringDateGenHelper.createRandomDateFromRange(begin, end),Constants.DEFAULT_FORMATTER);
        while(inputDate.equals(begin)|| inputDate.equals(end)){
            inputDate = LocalDate.parse(StringDateGenHelper.createRandomDateFromRange(begin, end),Constants.DEFAULT_FORMATTER);
        }
        return inputDate;
    }
    
}
