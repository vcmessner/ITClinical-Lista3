package UnitTest.JUnit.HelpersUnit;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.Struts2.Helpers.Constants;
import com.itclinical.Struts2.Helpers.StringGenHelper;
import com.itclinical.Struts2.Helpers.StringVerifyHelper;


public class StringGenHelperUnit {

    void Assert_LowerCaseAlphaNum(){
        String output = StringGenHelper.createRandomAlphaNumString(0,10);
		assertTrue(StringVerifyHelper.isLowerCaseAlNum(output));
	}

    void Assert_UpperCaseAlphaNum(){
        String output = StringGenHelper.createRandomAlphaNumString(100,10);
		assertTrue(StringVerifyHelper.isUpperCaseAlNum(output));
	}

    void Assert_AlphaNum(){
        String output = StringGenHelper.createRandomAlphaNumString(100,10);
		assertTrue(StringVerifyHelper.isAlNum(output));
	}

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void createRandomAlphaNumStringTest(){
        Assert_LowerCaseAlphaNum();
        Assert_UpperCaseAlphaNum();
        Assert_AlphaNum();
    } 

    
}
