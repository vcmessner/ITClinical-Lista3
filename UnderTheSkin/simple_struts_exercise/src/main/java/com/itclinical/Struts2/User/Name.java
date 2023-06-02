package com.itclinical.Struts2.User;

public class Name {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Name(String myName) {
        if (isNamePropertyInvalid(myName)){       
            this.name = null;
        }
        else{        
            this.name = myName;
        }
        
    }

    protected boolean isNamePropertyInvalid(String myName) {
        return myName == null || myName.isEmpty();
    }
    
}
