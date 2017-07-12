<%--
  Created by IntelliJ IDEA.
  User: Jamedow
  Date: 2017/6/6
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="../common/header.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/product-list.css"/>
</head>
<body>
<header class="site-header">
    <div>
        <h1>老豆坊</h1>
    </div>
</header>
<div class="container">
    <div class="filter-area">
        <div class="pitch-on">
            <p>已选属性：</p>
            <ul>
                <li>香辣<span class="icon-remove-circle"></span></li>
                <li>对虾<span class="icon-remove-circle"></span></li>
                <li>油炸<span class="icon-remove-circle"></span></li>
                <li>油炸<span class="icon-remove-circle"></span></li>
                <li>油炸油油油油油油<span class="icon-remove-circle"></span></li>
            </ul>
        </div>
        <div class="clear"></div>
        <div class="waiting-for-selection">
            <p>待选属性：</p>
            <ul>
                <li>香辣<span class="icon-remove-circle"></span></li>
                <li>对虾<span class="icon-remove-circle"></span></li>
                <li>油炸<span class="icon-remove-circle"></span></li>
                <li>油炸<span class="icon-remove-circle"></span></li>
                <li>油炸油油油油油油<span class="icon-remove-circle"></span></li>
            </ul>
        </div>
        <div class="sort-by"></div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
