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
    <title>${category.name}</title>
    <link rel="stylesheet" href="${ctx}/static/css/product-intro.css"/>
</head>
<body>
<div id="container" class="container">
    <c:forEach items="${products}" var="product" varStatus="index">
        <div class="product-intro col-md-12">
            <img class="col-md-3 col-xs-12 img-thumbnail" src="${product.imgUrl}"/>
            <h1 class="col-md-9 text-center" onclick="window.open('${ctx}/product/detail?productId=${product.id}')">
                    ${product.name}
            </h1>
            <article class="col-md-9">
                <p class="bg-warning">${product.intro}</p>
            </article>
            <div class="go-buy" onclick="window.open('${product.linkUrl}')">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBdZInLq1pEw9u0oIoNMBwKfdttINQ2sk1MUzWGZLDXzpFtZpp"/>
            </div>
        </div>
    </c:forEach>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
