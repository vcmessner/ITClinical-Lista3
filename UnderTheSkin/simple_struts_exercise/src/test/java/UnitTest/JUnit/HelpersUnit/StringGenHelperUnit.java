package UnitTest.JUnit.HelpersUnit;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.Struts2.Helpers.Constants;
import com.itclinical.Struts2.Helpers.StringGenHelper;
import com.itclinical.Struts2.Helpers.StringVerifyHelper;


public class StringGenHelperUnit {
    
    StringGenHelper genStr = new StringGenHelper();
    StringVerifyHelper verificator = new StringVerifyHelper();

    void Assert_LowerCaseAlphaNum(){
        String output = genStr.createRandomAlphaNumString(0,10);
		assertTrue(verificator.isLowerCaseAlNum(output));
	}

    void Assert_UpperCaseAlphaNum(){
        String output = genStr.createRandomAlphaNumString(100,10);
		assertTrue(verificator.isUpperCaseAlNum(output));
	}

    void Assert_AlphaNum(){
        String output = genStr.createRandomAlphaNumString(100,10);
		assertTrue(verificator.isAlNum(output));
	}

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void createRandomAlphaNumStringTest(){
        Assert_LowerCaseAlphaNum();
        Assert_UpperCaseAlphaNum();
        Assert_AlphaNum();
    } 

    
}
