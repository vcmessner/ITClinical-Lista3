package UnitTest.JUnit.ActionsUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.Struts2.Helpers.Constants;
import com.itclinical.Struts2.Helpers.StringDateGenHelper;
import com.itclinical.Struts2.Helpers.StringGenHelper;
import com.itclinical.Struts2.User.User;


public class RegisterActionUnit {

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void checkDateTest() throws Exception {
        String userName = "algo";
        String legalAge = StringDateGenHelper.createValidDateFromNow(Constants.LEGAL_AGE,Constants.RANDOM_AGE_LOWERBOUND);
        String minorAge = StringDateGenHelper.createMinorDateFromNow(Constants.LEGAL_AGE);
        String invalidDate =StringDateGenHelper.createInvalidDate();
        User legalAgeUser = new User(userName,legalAge); 
        User minorAgeUser = new User(userName,minorAge); 
        User invalidDateUser = new User(userName,invalidDate);  
        User nullDateUser = new User (userName,null);
        User emptyDateUser = new User (userName,"");  
        assertTrue(legalAgeUser.age.isLegal(Constants.LEGAL_AGE));
        assertFalse(minorAgeUser.age.isLegal(Constants.LEGAL_AGE));
        assertFalse(invalidDateUser.age.isLegal(Constants.LEGAL_AGE));
        assertFalse(nullDateUser.age.isLegal(Constants.LEGAL_AGE));
        assertFalse(emptyDateUser.age.isLegal(Constants.LEGAL_AGE));
        assertTrue(nullDateUser.getDate()==null);
        assertTrue(emptyDateUser.getDate()==null);
        assertFalse(legalAgeUser.getDate()==null);
        assertFalse(minorAgeUser.getDate()==null);
        assertTrue(invalidDateUser.getDate()==null);
    }

    @Test
    public void checkUsernameTest() throws Exception {
        String userName = StringGenHelper.createRandomAlphaNumString(Constants.DEFAULT_UPPERCASE_PROBABILITY, Constants.DEFAULT_RANDOM_STR_LEN);
        String BirthDate = "01/01/1992";
        User validUsernameUser = new User(userName,BirthDate);
        User nullUsernameUser = new User(null,BirthDate);
        User emptyUsernameUser = new User("",BirthDate);
        assertTrue(validUsernameUser.getName()!=null);
        assertTrue(nullUsernameUser.getName()==null);
        assertTrue(emptyUsernameUser.getName()==null);
    }

}
