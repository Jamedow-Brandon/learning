<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/4 0004
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <%@include file="common/header.jsp" %>
    <title>一叶梧桐落</title>
    <meta name="robots" content="index, follow">
    <meta name="googlebot" content="index, follow">
    <link rel="stylesheet" href="${ctx}/static/css/index.css"/>
</head>
<body>

<div class="container">
    <div class="col-xs-12">
        <h2 class="text-center bg-info">儿童玩具</h2>
        <div class="col-md-8 col-xs-12 col-md-offset-2 col-xs-offset-0">
            <div class="col-md-5 col-xs-12 floor-left">aa</div>
            <div class="col-md-3 col-xs-6 floor-right">bb</div>
            <div class="col-md-3 col-xs-6 floor-right">cc</div>
            <div class="col-md-3 col-xs-6 floor-right">cc</div>
            <div class="col-md-3 col-xs-6 floor-right">cc</div>
        </div>
    </div>
    <div class="col-xs-12">
        <h2 class="text-center bg-danger">女生礼物</h2>
    </div>
    <div class="col-xs-12">
        <h2 class="text-center bg-success">男生礼物</h2>
    </div>
</div>


<!-- index mask start-->
<div class="index-mask" id="indexPresent" style="display: none">
    <div class="index-mask_top">
        <h1 class="index-mask--title">还在想过节送什么？快来这里看看吧！</h1>
        <div class="index-mask--open">点击打开</div>
    </div>
    <div class="index-mask_below"></div>
</div>
<!--index mask end-->
<%@include file="common/footer.jsp" %>
<script type="application/javascript">
    $("#indexPresent").on("click", function () {
        $(".index-mask--open").hide();
        $(".index-mask_top").animate({"margin-top": "-62%"}, 1500, null, function () {
            $("#indexPresent").hide();
        });
        $(".index-mask_below").animate({"margin-top": "124%"}, 1500);
    });

    $(".index-container img").on("click", function () {
        var channel = $(this).attr("channel");
        window.open("${ctx}/product/introList?channel=" + channel);
    });
</script>
</body>
</html>
