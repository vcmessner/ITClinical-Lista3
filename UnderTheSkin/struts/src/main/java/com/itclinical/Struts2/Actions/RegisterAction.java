package com.itclinical.struts2.actions;

import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.user.User;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport {    
    
    private String name;
    private String date;
    private User user;
    private int errorCode=-1;

    public String validateUser() throws Exception{
        this.user = new User(name, date);
        if(checkUsername() && checkDate()){
            errorCode=0;
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

    public int getErrorCode() {
        return errorCode;
    }

    protected boolean checkDate() throws Exception {
        if(user.getDate()==null) {
            addActionError(getText("INVALID_DATE_MESSAGE_STRING"));
            errorCode=2;
            return false;
        }
        if(user.isLegal(Constants.LEGAL_AGE)){
            return true;
        }
        else{
            addActionError(getText("AGE_RESTRICTION_MESSAGE_STRING"));
            errorCode = 3;
            return false;
        }
    }

    protected boolean checkUsername() throws Exception {
        if(user.getName()==null) {
            addActionError(getText("INVALID_NAME_MESSAGE_STRING"));
            errorCode=1;
            return false;        
        }
        return true;        
        }
}