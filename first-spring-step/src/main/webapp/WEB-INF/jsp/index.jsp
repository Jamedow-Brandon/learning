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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>一叶梧桐落</title>
    <link rel="stylesheet" href="${ctx}/static/css/index.css"/>
</head>
<body>
<div class="index-mask" id="indexPresent">
    <div class="index-mask_top"></div>
    <div class="index-mask_below"></div>
</div>
<script type="application/javascript" src="${ctx}/static/jquery/jquery-3.1.1.js"></script>
<script type="application/javascript" src="${ctx}/static/script/application.js"></script>
<script type="application/javascript">
    $("#indexPresent").on("click", function () {
        $(".index-mask_top").animate({"margin-top": "-62%"});
        $(".index-mask_below").animate({"margin-top": "124%"});
    });
</script>
</body>
</html>
