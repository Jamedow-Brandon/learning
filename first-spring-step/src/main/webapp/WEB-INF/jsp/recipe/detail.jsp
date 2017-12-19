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
    <link rel="stylesheet" href="${ctx}/static/common/css/recipe-detail.css"/>
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
            <div class="detail recipe-material">
                <div class="intro">${recipe.intro}</div>
                <div class="material"></div>
            </div>
        </div>
    </div>
    <div class="detail recipe-left">

    </div>
    <div class="recipe-content">

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
        <div class="comment-vote">
            <span class="vote-button" objId="{{comment.id}}" onclick="vote(this)">
                {{if comment.voteStatus == 1}}
                    <i class="fa fa-thumbs-up"></i>
                {{else}}
                    <i class="fa fa-thumbs-o-up"></i>
                {{/if}}
                <span id="voteCount{{comment.id}}">{{comment.voteCount}}</span>
            </span>
            <span class="vote-button" objId="{{comment.id}}" onclick="vote(this)">
                {{if comment.voteStatus == -1}}
                    <i class="fa fa-thumbs-down"></i>
                {{else}}
                    <i class="fa fa-thumbs-o-down"></i>
                {{/if}}
            </span>
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

    function getStatus(_this) {
        var status;
        var $voteButton = $(_this);
        var className = $voteButton.children("i").attr("class");
        var currentClass, destClass;
        if (className.indexOf("fa-thumbs-up") > 0) {
            currentClass = "fa-thumbs-up";
            destClass = "fa-thumbs-o-up";
            status = 0;
        } else if (className.indexOf("fa-thumbs-o-up") > 0) {
            currentClass = "fa-thumbs-o-up";
            destClass = "fa-thumbs-up";
            $(_this).parent("div").children("span").children(".fa-thumbs-down").removeClass("fa-thumbs-down").addClass("fa-thumbs-o-down")
            status = 1;
        } else if (className.indexOf("fa-thumbs-down") > 0) {
            currentClass = "fa-thumbs-down";
            destClass = "fa-thumbs-o-down";
            status = 0;
        } else if (className.indexOf("fa-thumbs-o-down") > 0) {
            currentClass = "fa-thumbs-o-down";
            destClass = "fa-thumbs-down";
            $(_this).parent("div").children("span").children(".fa-thumbs-up").removeClass("fa-thumbs-up").addClass("fa-thumbs-o-up")
            status = -1;
        }
        $voteButton.children("i").removeClass(currentClass).addClass(destClass);
        return status;
    }

    function vote(_this) {
        var $voteButton = $(_this);
        var objId = $voteButton.attr("objId");
        var status = getStatus(_this);
        $.ajax({
            url: "${ctx}/recipe/vote",
            method: "get",
            data: {
                status: status,
                objId: objId
            },
            success: function (result) {
                if (result.code === "10000") {
                    $("#voteCount" + objId).text(result.totalCount);
                } else {
                    alert(result.msg);
                }
            }
        });
    }
</script>
</body>
</html>
