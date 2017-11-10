<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/25
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <%@include file="../common/header.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/recipe-detail.css"/>
    <title>${recipe.name}</title>
</head>
<body>
<%@include file="../common/site-nav.jsp" %>

<div class="container">
    <div class="detail recipe-header">
        <div class="images">
            <img class="img-thumbnail" src="${recipe.imgUrl}"/>
        </div>
        <div class="params">
            <h2>${recipe.name}</h2>

        </div>
    </div>
    <div class="detail recipe-left">

    </div>
    <div class="recipe-content">
        <div class="detail recipe-material">
            <div class="intro">${recipe.intro}</div>
            <div class="material"></div>
        </div>
        <div class="detail recipe-detail">
            <div class="tables">
                <ul>
                    <li id="detail-content" class="tables-active">详情</li>
                    <li id="comments">评论</li>
                </ul>
            </div>
            <div class="tables-child detail-content">${recipe.detail}</div>
            <div class="tables-child comments"></div>
        </div>
    </div>
</div>

<%@include file="../common/copy-right.jsp" %>
<%@include file="../common/footer.jsp" %>
<script type="text/html" id="commentsTemplate">
    {{each comments as comment}}
    <div class="comment">
        <div class="comment-top">
            <span class="comment-user">
                <img src="{{comment.userPhoto}}"/>
                <b>{{comment.username}}</b>
            </span>
            <span class="comment-time">
                    4年前
            </span>
        </div>
        <div class="comment-content">
            <p>{{comment.content}}</p>
        </div>
        <div class="comment-bottom">
            <i class="fa fa-thumbs-o-up"></i><span>0</span>
        </div>
    </div>
    <hr/>
    {{/each}}
</script>
<script type="application/javascript" src="${ctx}/static/script/template.js"></script>
<script>
    var recipeId = '${recipe.id}';
    $(function () {
        $(".tables li").on("click", function () {
            var tableId = $(this).attr("id");
            $(".tables ul li").removeClass("tables-active");
            $(".tables-child").hide();
            $(this).addClass("tables-active");
            $("." + tableId).show();
        });

        $.ajax({
            url: "${ctx}/comment/recipeComment/comments",
            method: "get",
            data: {
                recipeId: recipeId
            },
            success: function (comments) {
                var commentsHtml = template('commentsTemplate', {comments: comments});
                $(".comments").html(commentsHtml);
            }
        });
    });
</script>
</body>
</html>
