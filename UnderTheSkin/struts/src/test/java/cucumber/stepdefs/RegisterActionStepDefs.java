package cucumber.stepdefs;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

import com.itclinical.struts2.actions.RegisterAction;
import com.itclinical.struts2.helpers.TestConstants;
import com.itclinical.struts2.helpers.DateConstants;
import com.itclinical.struts2.user.Date;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.core.ParseException;
import helpers.UnderTheSkinHelpers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class RegisterActionStepDefs {
    private UnderTheSkinHelpers helper = new UnderTheSkinHelpers();
    private Map<String, String> inputParameterMap = new HashMap<>();
    private Map<String, String> assertParameterMap = new HashMap<>();
    private Period dateDiff;
  

    @Given("I want to access the success page")
    public void i_want_to_acess_the_success_page() {
        helper.initServletRequestMockObject();
        try{
            helper.setUp();
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Given("Today is {string}")
    public void today_is(String today) throws ParseException {
        LocalDate todayLocalDate = Date.GetLocalDate(today);
        LocalDate currentDate = LocalDate.now(DateConstants.DEFAULT_ZONE_OFFSET);
        dateDiff = Period.between(todayLocalDate,currentDate);
    }

    


    @When("I input my name {string}")
    public void i_input_my(String name) {
        inputParameterMap.put("name", name);
    }

    @When("I input my Birthdate {string}")
    public void i_input_my_birthdate(String birthDate) throws ParseException {
        birthDate = Date.validateDate(birthDate);
        String formattedDate = "";
        if(birthDate!=null){
            LocalDate todayLocalDate = Date.GetLocalDate(birthDate);
            todayLocalDate =todayLocalDate.plus(dateDiff);
            formattedDate = todayLocalDate.format(DateConstants.DEFAULT_FORMATTER);
        }
        inputParameterMap.put("date", formattedDate);
    }

    @When("I click the submit button")
    public void i_click_the_submit_button() throws Exception {
        helper.getRequest().addParameters(inputParameterMap);
        ActionSupport myAction = helper.createAction(TestConstants.REGISTER_ACTION_URI, true);
        executeAndSaveOutputs((RegisterAction)myAction);
    }

    @Then("My access request will be {string}")
    public void my_acess_request_will_be(String answer) {
        assertEquals(answer, assertParameterMap.get("allowed"));      
}

    @Then("I will be shown the error message: {string}")
    public void i_will_be_shown_the_error_message(String error) {
        assertEquals(assertParameterMap.get("error"), error);
    }

    private String mapActionStatus(String code){
        switch(code){
            case("input"):
                return "Denied";
            case("success"):
                return "Approved";
            default:
                return"";
        }
    }

    private void executeAndSaveOutputs(RegisterAction myAction) throws Exception{
        String status = mapActionStatus(helper.executeProxy());
        assertParameterMap.put("allowed",status);
        assertParameterMap.put("error",(myAction).getError());
        assertParameterMap.put("name",(myAction).getName());
        if(status.equals("Approved")){
            assertParameterMap.put("age",(myAction).getAgeMessage());
        }
    }

}

