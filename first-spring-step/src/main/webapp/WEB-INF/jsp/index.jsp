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

<div class="index-container">
    <div class="index-container_left col-md-8">
        <img channel="1" class="index-container--vertical col-md-4 img-thumbnail"
             src="http://zhaoyuanb2c.sdgxyj.com/res/9999010/img/mall/shop/2016081421033455784.png" alt=""/>
        <img channel="2" class="index-container--square col-md-3 img-thumbnail"
             src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJb0sc4N3A6klswtbfvN06P7D84DT5VXoiAEcoFYTMidauP6MBEA"
             alt=""/>
        <img channel="2" class="index-container--square col-md-3 img-thumbnail"
             src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv6w85Dor0VuGVaGRhZ9QEbdkOqL7kzdyGLeyU5e61ZujrCvdCcg"
             alt=""/>
        <img channel="2" class="index-container--square col-md-3 img-thumbnail"
             src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3O-TATbjP-F9JZTcb6CEYG7htR4tY2GN8OBk_AKzHHyrcim007A"
             alt=""/>
        <img channel="2" class="index-container--square col-md-3 img-thumbnail"
             src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3_PdRbw-luzRhRc3goW-G-BG40q1Mru5GBRh_fowa64Pmr0qHvQ"
             alt=""/>
        <img channel="2" class="index-container--square col-md-11 img-thumbnail"
             src="http://cued.xunlei.com/demos/publ/img/P_000.jpg" alt=""/>
    </div>
    <div class="index-container_right col-md-4">
        <div class="index-container--video col-md-11" style="background-color: pink">玩具专区</div>
        <div class="index-container--video col-md-11">玩具专区</div>
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

    $(".index-container_left img").on("click", function () {
        var channel = $(this).attr("channel");
        window.open("${ctx}/product/introList?channel=" + channel);
    });
</script>
</body>
</html>
