package com.itclinical.struts2.actions;

import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.user.User;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport {    
    
    private String name;
    private String date;
    private User user;
    private String error=null;

    
    public String validateUser() throws Exception{
        this.user = new User(name, date);
        if(checkUsername(user.getName()) && checkDate(user.getDate()) && checkLegalAge(user)){
            error=null;
            return ActionSupport.SUCCESS;
        }
        return ActionSupport.INPUT;        
    }
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAgeMessage() {
        return user.getAge() + " Years";
    }    

    protected boolean checkDate(String myDate){
        if(myDate==null) {
            error=getText("INVALID_DATE_MESSAGE_STRING");
            addActionError(getText("INVALID_DATE_MESSAGE_STRING"));            
            return false;
        }
        return true;
    }

    protected boolean checkLegalAge(User user){
        if(user.isLegal(Constants.LEGAL_AGE)){
            return true;
        }
        else{
            error = getText("AGE_RESTRICTION_MESSAGE_STRING");
            addActionError(error);          
            return false;
        }
    }

    protected boolean checkUsername(String name) throws Exception {
        if(name==null) {
            error=getText("INVALID_NAME_MESSAGE_STRING");
            addActionError(error);            
            return false;        
        }
        return true;        
        }
}