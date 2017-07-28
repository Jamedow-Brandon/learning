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
    <link rel="stylesheet" href="${ctx}/static/css/re-product-list.css"/>
</head>
<body>
<%@include file="../common/site-nav.jsp" %>
<div class="container">
    <div class="filter-area">
        <div class="pitch-on">
            <ul></ul>
            <div>只看官方<input type="checkbox"/></div>
        </div>
        <div class="clear"></div>
        <div class="waiting-for-selection">
            <div class="param-parent">
                <ul></ul>
            </div>
            <div class="param-children">
                <ul></ul>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="sort-by"></div>
    </div>
</div>
<%@include file="../common/copy-right.jsp" %>
<%@include file="../common/footer.jsp" %>
<script type="text/html" id="tagsTemplate">
    {{each tags as tag}}
    <li tagId="{{tag.id}}" isLeaf="{{tag.isLeaf}}">{{tag.name}}</li>
    {{/each}}
</script>
<script type="text/html" id="pitchTemplate">
    <li tagId="{{id}}">{{name}}<span class="fa fa-times-circle-o"></span></li>
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
            var $param = $(this);
            if ($param.attr("isLeaf") === "0") {
                getChildrenTags($param.attr("tagId"));
                $param.off();
                $param.on("click", function () {
                    $(this).nextAll().remove();
                    $(this).remove();
                    getBrothersTags($(this).attr("tagId"));
                });
                $(".param-parent ul").append($param);
            } else {
                if (!checkTags($param.attr("tagId"))) {
                    return false;
                }
                console.log(!checkTags($param.attr("tagId")))

                var tagsHtml = template('pitchTemplate',
                    {
                        id: $param.attr("tagId"),
                        name: $param.html()
                    });
                $(".pitch-on ul").append(tagsHtml);
                $(".pitch-on ul li span").off();
                $(".pitch-on ul li span").on("click", function () {
                    $(this).parents("li").remove();
                })

            }
        });
    }

    /**
     * 校验重复标签
     * @param id 点击标签id
     * @returns {boolean}
     */
    function checkTags(id) {
        var selectedTags = $(".pitch-on li");
        var flag = true;
        selectedTags.each(function (index, tag) {
            if ($(tag).attr("tagId") === id) {
                flag = false;
                return false;
            }
        });
        return flag;
    }

    function getChildrenTags(_tagsId) {
        $.ajax({
            type: "get",
            url: "${ctx}/recipe/getChildrenTags",
            data: {
                tagsId: _tagsId
            },
            dataType: "json",
            success: function (data) {
                var tagsHtml = template('tagsTemplate', {tags: data});
                $(".param-children ul").html(tagsHtml);

                paramBindClick();
            }

        });
    }
    function getBrothersTags(_tagsId) {
        $.ajax({
            type: "get",
            url: "${ctx}/recipe/getBrothersByTagsId",
            data: {
                tagsId: _tagsId
            },
            dataType: "json",
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
