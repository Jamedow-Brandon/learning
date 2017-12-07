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
    <link rel="stylesheet" href="${ctx}/static/pagination/pagination.css"/>
</head>
<body>
<%@include file="../common/site-nav.jsp" %>
<div class="container">
    <div class="filter-area">
        <div class="pitch-on">
            <ul></ul>
            <input type="hidden" id="choseTags"/>
            <div>只看官方<input type="checkbox" id="isOfficial" onchange="filterRecipes(1);"/></div>
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
    <div id="recipes" class="recipe-list">
        <c:forEach items="${hits}" var="hit">
            <div class="recipe">
                <h3>${hit.sourceAsMap.name}</h3>
                <img class="img-thumbnail"
                     src="${hit.sourceAsMap.imgUrl}"/>
                <p>${hit.sourceAsMap.intro}</p>
            </div>
        </c:forEach>
    </div>
    <div class="M-box"></div>
</div>
<%@include file="../common/copy-right.jsp" %>
<%@include file="../common/footer.jsp" %>
<script type="text/html" id="recipeTemplate">
    {{each hits as hit}}
    <div class="recipe">
        <h3>{{hit.sourceAsMap.name}}</h3>
        <img class="img-thumbnail"
             src="{{hit.sourceAsMap.imgUrl}}"/>
        <p>{{hit.sourceAsMap.intro}}</p>
    </div>
    {{/each}}
</script>
<script type="text/html" id="tagsTemplate">
    {{each tags as tag}}
    <li tagId="{{tag.id}}" isLeaf="{{tag.isLeaf}}">{{tag.name}}</li>
    {{/each}}
</script>
<script type="text/html" id="pitchTemplate">
    <li tagId="{{id}}">{{name}}<span class="fa fa-times-circle-o"></span></li>
</script>
<script type="application/javascript" src="${ctx}/static/script/template.js"></script>
<script type="application/javascript" src="${ctx}/static/pagination/jquery.pagination.js"></script>
<script type="application/javascript">

    var tags = ${tags};
    var records = ${page.records};
    $(function () {
        var tagsHtml = template('tagsTemplate', {tags: tags});
        $(".param-children ul").html(tagsHtml);

        paramBindClick();

        $('.M-box').pagination({
            pageCount: 8, //初始化时总页数8页
            totalData: records,//总记录数
            showData: 6,//每页记录数
            coping: true,
            homePage: '首页',
            endPage: '末页',
            prevContent: '上页',
            nextContent: '下页',
            callback: function (api) {
                searchRecipes(api.getCurrent());
            }
        }, function (api) {
            $('.now').text(api.getCurrent());
        });
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
                var tagsHtml = template('pitchTemplate',
                    {
                        id: $param.attr("tagId"),
                        name: $param.html()
                    });
                $(".pitch-on ul").append(tagsHtml);
                $(".pitch-on ul li span").off();
                $(".pitch-on ul li span").on("click", function () {
                    $(this).parents("li").remove();
                    var tagNames = [];
                    var $patches = $(".pitch-on li");
                    $patches.each(function (n, patch) {
                        var tagName = $(patch).text();
                        tagNames.push(tagName)
                    });
                    $("#choseTags").val(tagNames.join(","));
                    filterRecipes(1);
                });

                var tagNames = [];
                var $patches = $(".pitch-on li");
                $patches.each(function (n, patch) {
                    var tagName = $(patch).text();
                    tagNames.push(tagName)
                });

                $("#choseTags").val(tagNames.join(","));
                filterRecipes(1);
            }
        });
    }

    function searchRecipes(currentPage) {
        $.ajax({
            url: "${ctx}/recipe/getRecipes",
            method: "get",
            data: {
                searchKeyWord: $("#searchKeyWord").val(),
                isOfficial: $("#isOfficial").prop("checked") ? 1 : 0,
                choseTags: $("#choseTags").val(),
                currentPage: currentPage
            },
            success: function (result) {
                var recipesHtml = template('recipeTemplate',
                    {
                        hits: result.hits
                    });
                $("#recipes").html(recipesHtml);
            }
        })
    }

    function filterRecipes(currentPage) {
        $.ajax({
            url: "${ctx}/recipe/getRecipes",
            method: "get",
            data: {
                searchKeyWord: $("#searchKeyWord").val(),
                isOfficial: $("#isOfficial").prop("checked") ? 1 : 0,
                choseTags: $("#choseTags").val(),
                currentPage: currentPage
            },
            success: function (result) {
                var recipesHtml = template('recipeTemplate',
                    {
                        hits: result.hits
                    });
                $("#recipes").html(recipesHtml);
                console.log(result.page.records)
                $('.M-box').pagination({
                    pageCount: 8, //初始化时总页数8页
                    totalData: result.page.records,//总记录数
                    showData: 6,//每页记录数
                    coping: true,
                    homePage: '首页',
                    endPage: '末页',
                    prevContent: '上页',
                    nextContent: '下页',
                    callback: function (api) {
                        searchRecipes(api.getCurrent());
                    }
                }, function (api) {
                    $('.now').text(api.getCurrent());
                });
            }
        })
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
