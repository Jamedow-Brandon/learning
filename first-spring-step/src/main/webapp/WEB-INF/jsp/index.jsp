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
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>一叶梧桐落</title>
    <link rel="stylesheet" href="${ctx}/static/css/index.css"/>
</head>
<body>

<div class="index-container">
    <div class="index-container_left">
        <div class="index-container--square" style="background-color: red">玩具专区</div>
        <div class="index-container--across" style="background-color: orange">玩具专区</div>
        <div class="index-container--vertical" style="background-color: yellow">玩具专区</div>
        <div class="index-container--square" style="background-color: green">玩具专区</div>
        <div class="index-container--square" style="background-color: blue">玩具专区</div>
        <div class="index-container--across" style="background-color: purple">玩具专区</div>
    </div>
    <div class="index-container_right">
        <div class="index-container--video" style="background-color: pink">玩具专区</div>
        <div class="index-container--video">玩具专区</div>
    </div>
</div>


<!-- index mask start-->
<div class="index-mask" id="indexPresent">
    <div class="index-mask_top">
        <h1 class="index-mask--title">还在想过节送什么？快来这里看看吧！</h1>
        <div class="index-mask--open">点击打开</div>
    </div>
    <div class="index-mask_below"></div>
</div>
<!--index mask end-->
<script type="application/javascript" src="${ctx}/static/jquery/jquery-3.1.1.js"></script>
<script type="application/javascript" src="${ctx}/static/script/application.js"></script>
<script type="application/javascript">
    $("#indexPresent").on("click", function () {
        $(".index-mask--open").hide();
        $(".index-mask_top").animate({"margin-top": "-62%"}, 1500, null, function () {
            $("#indexPresent").hide();
        });
        $(".index-mask_below").animate({"margin-top": "124%"}, 1500);
    });
</script>
</body>
</html>
