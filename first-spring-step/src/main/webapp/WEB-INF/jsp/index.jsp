<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/4 0004
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>

Hello <a href="javascript:HISTORY.goToUrl('aaa')">${name}</a>!
</body>

<script src="${ctx}/static/jquery/jquery-3.1.1.js"></script>
<script src="${ctx}/static/application.js"></script>
</html>
