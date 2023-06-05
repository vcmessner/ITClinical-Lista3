package UnitTest.JUnit.UserUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.Struts2.Helpers.Constants;
import com.itclinical.Struts2.Helpers.StringDateGenHelper;
import com.itclinical.Struts2.Helpers.StringGenHelper;
import com.itclinical.Struts2.User.User;

import freemarker.core.ParseException;

public class UserUnit {


    @RepeatedTest (Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
	public void User() throws ParseException, java.text.ParseException {
		String validName = StringGenHelper.createRandomAlphaNumString(50, 10) ;
        String blankName = "";
        String nullName = null;
		String validDate = StringDateGenHelper.createValidDateFromNow(Constants.LEGAL_AGE,Constants.RANDOM_AGE_LOWERBOUND);
        String invalidDate = StringDateGenHelper.createInvalidDate();
        String minorDate = StringDateGenHelper.createMinorDateFromNow(Constants.LEGAL_AGE);
		assertTrue(new User(validName,validDate).isValid());
        assertFalse(new User(blankName,validDate).isValid());
        assertFalse(new User(nullName,validDate).isValid());
        assertFalse(new User(validName,invalidDate).isValid());
        assertFalse(new User(blankName,invalidDate).isValid());
        assertFalse(new User(nullName,invalidDate).isValid());
        assertFalse(new User(validName,minorDate).isValid());
        assertFalse(new User(blankName,minorDate).isValid());
        assertFalse(new User(nullName,minorDate).isValid());
	}
    
}