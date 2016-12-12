<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello World!</h2>
<form:form  commandName="user" action="/adduser">
    <form:input path="username"/>
    <form:input path="password"/>

    <button type="submit">提交</button>
</form:form>
</body>
</html>
