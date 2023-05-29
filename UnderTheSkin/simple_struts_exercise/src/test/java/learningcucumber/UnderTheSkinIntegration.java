package learningcucumber;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import com.itclinical.simple_struts_exercise.StringHelper;
import com.opensymphony.xwork2.ActionSupport;
import Helpers.UnderTheSkinHelpers;
import freemarker.core.ParseException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UnderTheSkinIntegration {
    private String userIsAllowed;
    private StringHelper stringHelper = new StringHelper();
    private UnderTheSkinHelpers helper = new UnderTheSkinHelpers();
    private String myName;
    private String myDate;
    Map<String, String> fieldParameterMap = new HashMap<>();

    @Given("user submits name {string} and date {string}")
    public void userSubmitsInput(String name, String date) throws ParseException {
        myName = stringHelper.trucateName(name);
        myDate = date;
        helper.initServletRequestMockObject();
        try{
            helper.setUp();
            }
        catch(Exception e){
            e.printStackTrace();
        }
        fieldParameterMap.put("name", myName);
        fieldParameterMap.put("date", myDate);        
    }

    @When("i ask whether user the input is valid")
    public void iAskWhetherUserIsAllowed() {
        String URI ="/simple_struts_exercise/helloWorldAction.action";
        ActionSupport myAction;
        try{
            //helper.getRequest().addParameter("name", myName);
            helper.getRequest().addParameters(fieldParameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            myAction = helper.createAction(URI, true);
            userIsAllowed = helper.executeProxy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Then("input is valid = {string}")
    public void userAllowedValueIs(String answer) {
        assertEquals(answer, userIsAllowed);
    }
}
