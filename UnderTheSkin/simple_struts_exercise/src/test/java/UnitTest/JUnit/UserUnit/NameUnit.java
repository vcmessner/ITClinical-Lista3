package UnitTest.JUnit.UserUnit;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.RepeatedTest;

import com.itclinical.Struts2.Helpers.Constants;
import com.itclinical.Struts2.Helpers.StringGenHelper;
import com.itclinical.Struts2.User.Name;

public class NameUnit {
    
    @RepeatedTest(Constants.DEFAULT_NUMBER_JUNIT_REPEAT)
    public void nameContructorTest() {
        StringGenHelper strGen = new StringGenHelper();
        Name nullName =  new Name(null);
        Name emptyName = new Name("");
        Name blankName = new Name(" ");
        Name validName = new Name(strGen.createRandomAlphaNumString(Constants.DEFAULT_UPPERCASE_PROBABILITY, Constants.DEFAULT_RANDOM_STR_LEN));
        assertTrue(nullName.getName()==null);
        assertTrue(emptyName.getName()==null);
        assertTrue(blankName.getName()!=null);
        assertTrue(validName.getName()!=null);
        
    }

    
}
