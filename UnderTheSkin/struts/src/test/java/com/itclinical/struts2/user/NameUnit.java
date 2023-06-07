package com.itclinical.struts2.user;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.helpers.ProbabilityConstants;
import com.itclinical.struts2.helpers.StringGenHelper;

public class NameUnit {
    
    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void nameContructorTest() {
        Name nullName =  new Name(null);
        Name emptyName = new Name("");
        Name blankName = new Name(" ");
        Name validName = new Name(StringGenHelper.createRandomAlphanumericString(ProbabilityConstants.DEFAULT_UPPERCASE_PROBABILITY, ProbabilityConstants.DEFAULT_RANDOM_STR_LEN));
        assertTrue(nullName.getName()==null);
        assertTrue(emptyName.getName()==null);
        assertTrue(blankName.getName()!=null);
        assertTrue(validName.getName()!=null);
        
    }

    
}
