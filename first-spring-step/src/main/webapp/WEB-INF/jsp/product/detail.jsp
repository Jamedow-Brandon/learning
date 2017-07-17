<%--
  Created by IntelliJ IDEA.
  User: Jamedow
  Date: 2017/6/6
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../common/header.jsp" %>
    <title>${product.name}</title>
    <link rel="stylesheet" href="${ctx}/static/css/product-detail.css"/>
</head>
<body>
<%@include file="../common/site-nav.jsp" %>
<div id="container" class="container">
    <div class="product-detail col-md-12">
        <img class="col-md-3 col-xs-12 img-thumbnail" src="${product.imgUrl}"/>
        <h1 class="col-md-9 text-center">${product.name}</h1>
        <div class="">
            <div></div>
        </div>
        <article>
            ${product.detail}
        </article>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
