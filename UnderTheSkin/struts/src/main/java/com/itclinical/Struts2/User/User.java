package com.itclinical.struts2.user;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

import com.itclinical.struts2.helpers.Constants;
import com.itclinical.struts2.helpers.DateConstants;
public class User {

    private Date birthDate;
    private Integer age=null;
    private Name name;
    //private boolean isValid;

    //@Autowired
    public User(String myName, String birthDate) throws ParseException {
        this.name = new Name(myName);
        this.birthDate = new Date(birthDate);
        if(this.birthDate.getDate()!=null){
            this.age = calculateAge(birthDate);
        }
    }

    public String getName() {
        return name.getName();
    }

    public int getAge() {
        return age;
    }

    public String getDate() {
        return birthDate.getDate();
    } 

    public void setName(Name name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDate(Date date) {
        this.birthDate = date;
    }

    public boolean isValid(){
        if(this.getDate()==null || this.getName() ==null || this.getAge()==-1 || this.getAge()<Constants.LEGAL_AGE){
            return false;
        }
        return true;

    }

    public boolean isLegal(int legalAge){
        return !(age<legalAge);
    }

    protected int calculateAge(String date){
        LocalDate currentDate = LocalDate.now(DateConstants.DEFAULT_ZONE_OFFSET);
        Date myBirthDate = new Date(date);
        if(myBirthDate.getDate()!=null) {
            LocalDate birthDate;
            try {
                birthDate =myBirthDate.GetLocalDate();
                Period myAge = Period.between(birthDate, currentDate);
                return (myAge.getYears());
            }
            catch (freemarker.core.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return 0;
            }
        }            
        return -1;
    }



    
}
