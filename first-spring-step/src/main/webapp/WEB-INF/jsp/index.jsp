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

<section class="floor night">
    <div class="block">
        <img src=""/>
        <div>
            <h2>深夜吃不胖系列</h2>
            <p>深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列
                深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列
                深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列</p>
        </div>
        <div class="clear"></div>
        <div class="ahead-food">向美食进军</div>
    </div>
</section>
<section class="floor hot">
    <div class="block">
        <img src=""/>
        <div>
            <h2>深夜吃不胖系列</h2>
            <p>深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列
                深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列
                深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列深夜吃不胖系列</p>
        </div>
        <div class="clear"></div>
        <div class="ahead-food">向美食进军</div>
    </div>
</section>

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
