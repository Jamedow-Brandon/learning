<%--
  Created by IntelliJ IDEA.
  User: yoyo
  Date: 2017/7/12
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <title>标签管理</title>
    <%@include file="../common/taglib.jsp" %>
    <%@include file="../common/header.jsp" %>
    <script src="${ctx}/static/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/ajax-dialog.js"></script>
    <script src = "${ctx}/static/layer/layer.js"></script>
    <script src="${ctx}/static/dialog/dialog-min.js"></script>

</head>
<style>
    .classify,.tags-one,.tags-two{
        margin-top: 1%;
    }

    .classify-items,.tags-one-items,.tags-two-items{
        float: left;
        font-weight: 400;
        margin-right: 1%;
        border: 1px solid #5fa391;
        border-radius: 14px;
        padding:3px 13px;
    }

    .fa{
        color: #5fa391;
        float: right;
        margin-right: 1%;
        margin-top: 1%;
    }

    .classify-title,.tags-one-title,.tags-two-title{
        float: left;
        margin-top: 5px;
    }
    .choose-item{
        background-color: #5fa391;
        color: #fff;
    }

    #container{
        height: 450px;
    }
</style>
<script>
    $(document).ready(function(){

        chooseClassify($(".classify-items:first").attr("id"));

    })

    function chooseClassify(classifyId){//选择分类，显示所包含的一级分类

        $(".classify-items").removeClass("choose-item");
        $("#"+classifyId).addClass("choose-item");
        $("#classifyId").val(classifyId);

        var url =  "${ctx}/tags/searchTagsOneByClassify?classifyId="+classifyId;
        ajaxPost(url,null,
                function(tagsList){

                    if(tagsList.length>0){

                        var appendTags = "";
                        for(var i= 0;i < tagsList.length;i++){
                            appendTags +="<label class = 'tags-one-items' id='"+tagsList[i].id+"' onclick='chooseTagsOne(\""+tagsList[i].id+"\")'>"+tagsList[i].name+"</label>";
                        }
                        $(".tags-one-content").html("");
                        $(".tags-one-content").append(appendTags);
                        chooseTagsOne($(".tags-one-items:first").attr('id'));
                    }

        },null,false);
    }


    function chooseTagsOne(tagsId){

        $(".tags-one-items").removeClass("choose-item");
        $("#"+tagsId).addClass("choose-item");
        $("#tagsOneId").val(tagsId);

        var url =  "${ctx}/tags/searchTagsOneByClassify?classifyId="+tagsId;
        ajaxPost(url,null,
                function(tagsList){

                    if(tagsList.length>0){

                        var appendTags = "";
                        for(var i= 0;i < tagsList.length;i++){
                            appendTags +="<label class = 'tags-two-items' id='"+tagsList[i].id+"' onclick='chooseTagsTwo(\""+tagsList[i].id+"\")'>"+tagsList[i].name+"</label>";
                        }
                        $(".tags-two-content").html("");
                        $(".tags-two-content").append(appendTags);
                        chooseTagsTwo($(".tags-two-items:first").attr('id'));
                    }else{
                        $(".tags-two-content").html("<span style = 'float: left;margin-left: 4px;margin-top: 4px'><b>没有子标签</b></span>");
                    }

                },null,false);
    }


    function chooseTagsTwo(tagsId) {

        $(".tags-two-items").removeClass("choose-item");
        $("#" + tagsId).addClass("choose-item");
        $("#tagsTwoId").val(tagsId);
    }

    function deleteClassify(){


        layer.msg('确定删除该标签吗？', {
            time: 20000, //20s后自动关闭
            btn: ['确定', '取消'],
            yes: function(){

                var classifyId = $("#classifyId").val();
                if(classifyId!=null){
                    var url = "${ctx}/tags/deleteClassify?classifyId="+classifyId;
                    ajaxPost(url,null,
                            function(result){

                                if(result == "删除成功"){

                                    $("#"+classifyId).remove();
                                    chooseClassify($(".classify-items:first").attr("id"))
                                }
                                layer.msg(result);

                            },null,null);
                }
            },
            btn2: function(){}
        });


    }

    function addClassify(){

        layer.open({
            type: 2,
            title: "添加分类",
            area: ['380px', '200px'], //宽高
            content: '${ctx}/tags/toAddClassify'
        });

    }

    function appendClassify(id,name){

        var newClassify = "<label class = 'classify-items' id='"+id+"' onclick='chooseClassify(\""+id+"\")'>"+name+"</label>"
        $(".classify-content").append(newClassify);
        chooseClassify(id);
        layer.closeAll();
    }

    function changeClassify(id,name){

        $("#"+id).html(name);
        layer.closeAll();
    }

    function closeDialog(){

        layer.closeAll();
    }

    function editorClassify(){

        var classifyId = $("#classifyId").val();
        if(classifyId!=null){

            layer.open({
                type: 2,
                title: "编辑分类",
                area: ['380px', '200px'], //宽高
                content: '${ctx}/tags/toEditorClassify?classifyId='+classifyId
            });
        }

    }


    function addTagsOne(){

        layer.open({
            type: 2,
            title: "添加一级",
            area: ['380px', '200px'], //宽高
            content: '${ctx}/tags/toAddTagsOne'
        });
    }

    function appendTagsOne(id,name){

        var newTagsOne = "<label class = 'tags-one-items' id='"+id+"' onclick='chooseTagsOne(\""+id+"\")'>"+name+"</label>"
        $(".tags-one-content").append(newTagsOne);
        chooseTagsOne(id);
        layer.closeAll();
    }

    function editorTagsOne(){

        var tagsOneId = $("#tagsOneId").val();
        if(tagsOneId!=null){

            layer.open({
                type: 2,
                title: "编辑一级",
                area: ['380px', '300px'], //宽高
                content: '${ctx}/tags/toEditorTagsOne?tagsOneId='+tagsOneId
            });
        }
    }

    function deleteTagsOne(){

        layer.msg('确定删除该标签吗？', {
            time: 20000, //20s后自动关闭
            btn: ['确定', '取消'],
            yes: function(){

                var tagsId = $("#tagsOneId").val();
                if(tagsId!=null){
                    var url = "${ctx}/tags/deleteClassify?classifyId="+tagsId;
                    ajaxPost(url,null,
                            function(result){

                                if(result == "删除成功"){

                                    $("#"+tagsId).remove();
                                    chooseTagsOne($(".tags-one-items:first").attr("id"));
                                }
                                layer.msg(result);

                            },null,null);
                }
            },

            btn2: function(){}
        });

    }

    function changeTagsOne(tagsId,parentId,tagsName){

        if(parentId!=$("#classifyId").val()){//父标签改变，当前父标签下的改一级标签移除

            $("#"+tagsId).remove();
            chooseClassify($("#classifyId").val());
        }else{
            $("#"+tagsId).html(tagsName);
        }

        layer.closeAll();
    }

    function addTagsTwo(){

        layer.open({
            type: 2,
            title: "添加二级",
            area: ['500px', '400px'], //宽高
            content: '${ctx}/tags/toAddTagsTwo'
        });
    }

    function appendTagsTwo(){//添加二级标签后更新

        layer.closeAll();
        var tagsOneId = $("#tagsOneId").val();
        chooseTagsOne(tagsOneId);
    }

    function editorTagsTwo(){

        var tagsTwoId = $("#tagsTwoId").val();
        if(tagsTwoId!=null){

            layer.open({
                type: 2,
                title: "编辑二级",
                area: ['500px', '400px'], //宽高
                content: '${ctx}/tags/toEditorTagsTwo?tagsTwoId='+tagsTwoId
            });
        }
    }

    function deleteTagsTwo(){

        layer.msg('确定删除该标签吗？', {
            time: 20000, //20s后自动关闭
            btn: ['确定', '取消'],
            yes: function(){

                var tagsId = $("#tagsTwoId").val();
                if(tagsId!=null){
                    var url = "${ctx}/tags/deleteClassify?classifyId="+tagsId;
                    ajaxPost(url,null,
                            function(result){

                                if(result == "删除成功"){

                                    $("#"+tagsId).remove();
                                    chooseTagsOne($("#tagsOneId").val());
                                }
                                layer.msg(result);

                            },null,null);
                }
            },

            btn2: function(){}
        });

    }
</script>
<body>
<%@include file="../common/site-nav.jsp" %>
<div id="container" class="container">
    <div class = "classify col-md-12">
        <span class = "classify-title" >分类：</span>
        <div class = "classify-content">
            <c:forEach items="${classifyList}" var="classify"  varStatus="status">
                <label class = "classify-items" id="${classify.id}" onclick="chooseClassify('${classify.id}')">${classify.name}</label>
            </c:forEach>
        </div>
        <input id = "classifyId" type = "hidden" />
        <label alt="添加" class = "fa fa-plus fa-lg"  onclick="addClassify()"></label>
        <label alt="修改" class = "fa fa-pencil fa-lg"  onclick="editorClassify()"></label>
        <label alt="删除" class = "fa fa-trash-o fa-lg"  onclick="deleteClassify()"></label>
    </div>

    <div class = "tags-one col-md-12">
        <span class = "tags-one-title">一级：</span>
        <div class = "tags-one-content">

        </div>
        <input id = "tagsOneId" type = "hidden" />
        <label alt="添加" class = "fa fa-plus fa-lg"  onclick="addTagsOne()"></label>
        <label alt="修改" class = "fa fa-pencil fa-lg"  onclick="editorTagsOne()"></label>
        <label alt="删除" class = "fa fa-trash-o fa-lg"  onclick="deleteTagsOne()"></label>
    </div>

    <div class = "tags-two col-md-12">
        <span class = "tags-two-title">二级：</span>
        <div class = "tags-two-content">

        </div>
        <input id = "tagsTwoId" type = "hidden" />
        <label alt="添加" class = "fa fa-plus fa-lg"  onclick="addTagsTwo()"></label>
        <label alt="修改" class = "fa fa-pencil fa-lg"  onclick="editorTagsTwo()"></label>
        <label alt="删除" class = "fa fa-trash-o fa-lg"  onclick="deleteTagsTwo()"></label>
    </div>
</div>
<%@include file="../common/copy-right.jsp" %>
</body>
</html>
