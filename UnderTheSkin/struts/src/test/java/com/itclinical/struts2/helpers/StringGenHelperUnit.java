package com.itclinical.struts2.helpers;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.RepeatedTest;


public class StringGenHelperUnit {

    void Assert_LowerCaseAlphaNum(){
        String output = StringGenHelper.createRandomAlphanumericString(0,10);
		assertTrue(StringVerifyHelper.isLowerCaseAlphanumeric(output));
	}

    void Assert_UpperCaseAlphaNum(){
        String output = StringGenHelper.createRandomAlphanumericString(100,10);
		assertTrue(StringVerifyHelper.isUpperCaseAlphanumeric(output));
	}

    void Assert_AlphaNum(){
        String output = StringGenHelper.createRandomAlphanumericString(100,10);
		assertTrue(StringVerifyHelper.isAlphanumeric(output));
	}

    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void createRandomAlphaNumStringTest(){
        Assert_LowerCaseAlphaNum();
        Assert_UpperCaseAlphaNum();
        Assert_AlphaNum();
    } 

    
}
