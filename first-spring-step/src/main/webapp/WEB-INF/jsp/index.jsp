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
    <meta name="keywords" content="老豆坊,美食,零食">
    <meta name=”description” content="下午茶吃什么？夜宵吃什么？看电影吃什么？聚会吃什么？来老豆坊，一切的问题都会得到解决。"/>
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
        <div class="category-all">
            <c:forEach items="${category.childrenCategories}" var="childCategory">
                <div class="col-md-6 col-xs-12">
                    <div class="block" categoryId="${childCategory.id}">
                        <img src="${childCategory.imgUrl}"/>
                        <div class="category col-md-12">
                            <h2 class="category-name">${category.name}<b>${childCategory.name}</b></h2>
                            <div class="intro">${childCategory.intro}</div>
                            <div class="clear"></div>
                            <div class="ahead-food">向美食进军</div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</c:forEach>

<footer id="footer">
    <div class="copy-right">
        <p>浙ICP备17018975号-1</p>
    </div>
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

<a href="#2" id="back-to-top" style="display: none;">
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
        var categoryId = $(this).parent().parent().attr("categoryId");
        window.open("${ctx}/product/introList?categoryId=" + categoryId);
    });

    $(document).ready(function () {
        var intro = $(".intro");
        for (var i = 0; i < intro.length; i++) {
            var content = intro[i].innerHTML;
            if (content.length > 130)
                content = content.substr(0, 130) + "...";
            intro[i].innerHTML = content;
        }

    })

</script>
</body>
</html>
