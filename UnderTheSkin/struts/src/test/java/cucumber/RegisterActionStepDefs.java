package cucumber;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

import com.itclinical.struts2.actions.RegisterAction;
import com.itclinical.struts2.helpers.Constants;
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
  

    @Given("i want to access the success page")
    public void i_want_to_acess_the_success_page() {
        helper.initServletRequestMockObject();
        try{
            helper.setUp();
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Given("today is {string}")
    public void today_is(String today) throws ParseException {
        Date myDate = new Date(today);
        LocalDate todayLocalDate = myDate.GetLocalDate();
        LocalDate currentDate = LocalDate.now(DateConstants.DEFAULT_ZONE_OFFSET);
        dateDiff = Period.between(todayLocalDate,currentDate);
    }


    @When("i input my name {string}")
    public void i_input_my(String name) {
        inputParameterMap.put("name", name);
    }

    @When("i input my Birthdate {string}")
    public void i_input_my_birthdate(String birthDate) throws ParseException {
        Date myDate = new Date(birthDate);
        String formattedDate = "";
        if(myDate.getDate()!=null){
            LocalDate todayLocalDate = myDate.GetLocalDate();
            todayLocalDate =todayLocalDate.plus(dateDiff);
            formattedDate = todayLocalDate.format(DateConstants.DEFAULT_FORMATTER);
        }
        inputParameterMap.put("date", formattedDate);
    }

    @When("i click the submit button")
    public void i_click_the_submit_button() throws Exception {
        helper.getRequest().addParameters(inputParameterMap);
        ActionSupport myAction = helper.createAction(Constants.REGISTER_ACTION_URI, true);
        executeAndSaveOutputs((RegisterAction)myAction);
    }

    @Then("my access request will be {string}")
    public void my_acess_request_will_be(String answer) {
        assertEquals(answer, assertParameterMap.get("allowed"));      
}

@Then("I will be shown the error message: {string}")
public void i_must_be_greeted_with_an_error_message(String error) {
    assertEquals(assertParameterMap.get("error"), error);
}

@Then("i must be see a page containing my {string} and {string}")
public void i_must_be_see_a_page_containing_my_name_and_age(String name, String age) {
    assertEquals(name, assertParameterMap.get("name"));
    assertEquals(age, assertParameterMap.get("age"));
    assertEquals(age, assertParameterMap.get("age"));
    assertEquals(assertParameterMap.get("error"), null);
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
