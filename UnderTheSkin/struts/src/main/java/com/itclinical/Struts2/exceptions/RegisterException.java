package com.itclinical.struts2.exceptions;

public class RegisterException extends Exception  {
    private String code;

    public RegisterException(String code, String message) {
        super(message);
        this.setCode(code);
    }

    public RegisterException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
