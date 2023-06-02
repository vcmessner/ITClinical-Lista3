<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title>Basic Struts 2 Application - Welcome</title>
    </head>
    <body>
        <h1><s:text name="welcome.message"/></h1>
        <s:if test="hasActionErrors()">
        <div id="fieldErrors">
            <s:actionerror />
        </div>
        </s:if>
        <s:form action="RegisterAction" name="nameForm">
            <s:textfield name="name" size="40" maxlength="40" key="please.enter.name.message" />
            <s:textfield name="date" size="40" maxlength="40" key="please.enter.age.message" />
            <s:submit value="submit" />
        </s:form>
    </body>
</html>