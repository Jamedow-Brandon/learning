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
    <title>老豆坊</title>
    <meta name="robots" content="index, follow">
    <meta name="googlebot" content="index, follow">
    <link rel="stylesheet" href="${ctx}/static/css/index.css"/>
</head>
<body>

<header class="site-header">
    <div>
        <h1>老豆坊</h1>
    </div>
</header>

<c:forEach items="${categories}" var="category">
    <section class="floor ${category.categoryCode}">
        <c:forEach items="${category.childrenCategories}" var="childCategory">
            <div class="block" categoryId="${childCategory.id}">
                <img src="${childCategory.imgUrl}"/>
                <div>
                    <h2>${category.name}<b>${childCategory.name}</b></h2>
                    <p>
                            ${childCategory.intro}</p>
                </div>
                <div class="clear"></div>
                <div class="ahead-food">向美食进军</div>
            </div>
        </c:forEach>
    </section>
</c:forEach>

<footer id="footer" class="footer hidden-print">
    <div class="copy-right"><span>© 2013-2017</span> <a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备11008151号</a>
        <span>京公网安备11010802014853</span></div>
</footer>
<!-- index mask start-->
<div class="index-mask" id="indexPresent" style="display: none">
    <div class="index-mask_top">
        <h1 class="index-mask--title">还在想过节送什么？快来这里看看吧！</h1>
        <div class="index-mask--open">点击打开</div>
    </div>
    <div class="index-mask_below"></div>
</div>
<!--index mask end-->

<a href="#" id="back-to-top" style="display: none;">
    <i class="fa fa-angle-up"></i>
</a>
<%@include file="common/footer.jsp" %>
<script type="application/javascript">
    !function (t) {
        t(window).scroll(function () {
            t(this).scrollTop() > 100 ? t("#back-to-top").fadeIn() : t("#back-to-top").fadeOut()
        }), t("#back-to-top").on("click", function (e) {
            return e.preventDefault(), t("html, body").animate({scrollTop: 0}, 100), !1
        });

    }(jQuery)

    $("#indexPresent").on("click", function () {
        $(".index-mask--open").hide();
        $(".index-mask_top").animate({"margin-top": "-62%"}, 1500, null, function () {
            $("#indexPresent").hide();
        });
        $(".index-mask_below").animate({"margin-top": "124%"}, 1500);
    });

    $(".block .ahead-food").on("click", function () {
        var categoryId = $(this).parent().attr("categoryId");
        window.open("${ctx}/product/introList?categoryId=" + categoryId);
    });
</script>
</body>
</html>
