package com.itclinical.Struts2.Actions;

import com.itclinical.Struts2.Helpers.Constants;
import com.itclinical.Struts2.User.User;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport {    
    
    String name;
    String date;
    private User user;
    int errorCode=-1;

    public String execute() throws Exception{
        this.user = new User(name, date);
        if(checkUsername() && checkDate()){
            errorCode=0;
            return ActionSupport.SUCCESS;
        }
        return ActionSupport.INPUT;        
    }

    public boolean checkDate() throws Exception {
        if(user.getDate()==null) {
            addActionError(getText("INVALID_DATE_MESSAGE_STRING"));
            errorCode=2;
            return false;
        }
        if(user.age.isLegal(Constants.LEGAL_AGE)){
            return true;
        }
        else{
            addActionError(getText("AGE_RESTICTION_MESSAGE_STRING"));
            errorCode = 3;
            return false;
        }
    }

    public boolean checkUsername() throws Exception {
        if(user.getName()==null) {
            addActionError(getText("INVALID_NAME_MESSAGE_STRING"));
            errorCode=1;
            return false;        
        }
        return true;        
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

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}