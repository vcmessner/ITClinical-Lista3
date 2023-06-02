<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>Success!</title>
    </head>
    <body>
        <h2>Thank you for giving us your name!</h2>
        <p>
            Your name is: <h3><s:property value="name" /></h3>
        </p>
        <p>
            Your Age is: <h3><s:property value="AgeMessage" /></h3>
        </p>
    </body>
</html>