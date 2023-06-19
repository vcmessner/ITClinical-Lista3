package com.itclinical.struts2.user;
import java.time.LocalDate;
import java.time.Period;

import com.itclinical.struts2.exceptions.RegisterException;
import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.helpers.DateConstants;
public class User {

    String birthDate;
    String name;    

    //@Autowired
    public User(String myName, String birthDate) {
        setName(myName);
        setDate(birthDate);
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return birthDate;
    } 

    public void setName(String myName) {
        if(!Name.isNamePropertyInvalid(myName))
            this.name = myName;
        else{
            this.name = null;
        }
    }

    public void setDate(String date) {
        this.birthDate = Date.validateDate(date);
    }

    public boolean isValid() throws RegisterException{
        if(this.getDate()==null){
            throw new RegisterException("INVALID_DATE_MESSAGE_STRING", "INVALID_DATE_MESSAGE_STRING");
        } 
        if(this.getName() ==null){
            throw new RegisterException("INVALID_NAME_MESSAGE_STRING", "INVALID_NAME_MESSAGE_STRING");
            
        }
        if(!this.isLegal(Constants.LEGAL_AGE)){
            throw new RegisterException("AGE_RESTRICTION_MESSAGE_STRING", "AGE_RESTRICTION_MESSAGE_STRING");
        }
        return true;
    }

    public Integer getAge(){
        return calculateAge(this.getDate());
    }

    protected boolean isLegal(int legalAge){
        Integer age=calculateAge(this.getDate());
        return !(age<legalAge);
    }

    protected Integer calculateAge(String date){
        LocalDate currentDate = LocalDate.now(DateConstants.DEFAULT_ZONE_OFFSET);
        date = Date.validateDate(date);
        if(date!=null) {
            LocalDate birthDate;
            try {
                birthDate =Date.GetLocalDate(date);
                Period myAge = Period.between(birthDate, currentDate);
                return (myAge.getYears());
            }
            catch (freemarker.core.ParseException e) {
                e.printStackTrace();
                return 0;
            }
        }            
        return -1;
    }



    
}
