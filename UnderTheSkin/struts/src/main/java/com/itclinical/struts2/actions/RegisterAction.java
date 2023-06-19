package com.itclinical.struts2.actions;

import com.itclinical.struts2.exceptions.RegisterException;
import com.itclinical.struts2.user.User;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport {    
    
    protected String name;
    protected  String date;
    protected User user;
    
    public String validateUser(){
        try{
            this.user = new User(name, date);
            if(user.isValid()){
                this.getActionErrors().clear();
                return ActionSupport.SUCCESS;
            }
        }
        catch(RegisterException e){
            this.addActionError(getText(e.getCode()));
            return ActionSupport.INPUT;
        }
        return ActionSupport.INPUT;
    }
    
    public String getError() {
        if(!this.getActionErrors().isEmpty()){;
            return this.getActionErrors().iterator().next();
        }
        else{
            return "";
        }
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
}