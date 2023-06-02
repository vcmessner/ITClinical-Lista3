package com.itclinical.Struts2.User;
import java.text.ParseException;
import java.time.LocalDate;

import com.itclinical.Struts2.Helpers.Constants;
public class User {

    public Date birthDate;
    public Age age;
    private Name name;

    //@Autowired
    public User(String myName, String birthDate) throws ParseException {
        this.name = new Name(myName);
        this.birthDate = new Date(birthDate);
        if(this.birthDate.getDate()!=null){
            this.age = new Age(birthDate);
        }
        else{
            LocalDate currentDate = LocalDate.now(Constants.DEFAULT_ZONE_OFFSET);
            String dateString = currentDate.format(Constants.DEFAULT_FORMATTER);
            this.age=new Age(dateString);
        }
    }

    public String getName() {
        return name.getName();
    }

    public int getAge() {
        return age.getAge();
    }

    public String getDate() {
        return birthDate.getDate();
    } 

    public void setName(Name name) {
        this.name = name;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public void setDate(Date date) {
        this.birthDate = date;
    }



    
}
