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
                <li>香辣<span class="fa fa-times-circle-o"></span></li>
                <li>对虾<span class="fa fa-times-circle-o"></span></li>
                <li>油炸<span class="fa fa-times-circle-o"></span></li>
                <li>油炸<span class="fa fa-times-circle-o"></span></li>
                <li>油炸油油油油油油<span class="fa fa-times-circle-o"></span></li>
            </ul>
            <div>只看官方<input type="checkbox"/></div>
        </div>
        <div class="clear"></div>
        <div class="waiting-for-selection">
            <p>待选属性：</p>
            <div class="param-parent">
                <ul>
                    <li class="toggle-down">食材</li>
                    <li>海味</li>
                </ul>
            </div>
            <div class="param-children">
                <ul>
                    <li>虾</li>
                    <li>蟹</li>
                    <li>青菜</li>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
        <div class="sort-by"></div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
<script type="text/html" id="tagsTemplate">
    {{each tags as tag}}
    <li tagId="{{tag.id}}" isLeaf="{{tag.isLeaf}}">{{tag.name}}</li>
    {{/each}}
</script>
<script type="application/javascript" src="${ctx}/static/script/template.js"></script>
<script type="application/javascript">

    var tags = ${tags};
    $(function () {
        var tagsHtml = template('tagsTemplate', {tags: tags});
        $(".param-children ul").html(tagsHtml);

        paramBindClick();
    });

    function paramBindClick() {
        $(".param-children li").on("click", function () {
            console.log($(this));
            var $param = $(this);
            if ($param.attr("isLeaf") === "0") {
                $param.off();
                $param.on("click", function () {
                    getBrothersTags($(this).attr("tagId"));
                });
                $(".param-parent ul").append($param);
            }
        });
    }

    function getBrothersTags(_tagsId) {
        $.ajax({
            url: "${ctx}/product/getBrothersByTagsId",
            data: {
                tagsId: _tagsId
            },
            dataType: "application/json",
            success: function (data) {
                var tagsHtml = template('tagsTemplate', {tags: data});
                $(".param-children ul").html(tagsHtml);

                paramBindClick();
            }

        });
    }
</script>
</body>
</html>
