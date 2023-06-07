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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String validateUser() throws Exception{
        this.user = new User(name, date);
        if(checkUsername() && checkDate()){
            error=null;
            return ActionSupport.SUCCESS;
        }
        return ActionSupport.INPUT;        
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

    

    protected boolean checkDate() throws Exception {
        if(user.getDate()==null) {
            error=getText("INVALID_DATE_MESSAGE_STRING");
            addActionError(error);            
            return false;
        }
        if(user.isLegal(Constants.LEGAL_AGE)){
            return true;
        }
        else{
            error = getText("AGE_RESTRICTION_MESSAGE_STRING");
            addActionError(error);
            
            return false;
        }
    }

    protected boolean checkUsername() throws Exception {
        if(user.getName()==null) {
            error=getText("INVALID_NAME_MESSAGE_STRING");
            addActionError(error);            
            return false;        
        }
        return true;        
        }
}