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

    .classify-items{
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

    .classify-title{
        float: left;
        margin-top: 5px;
    }
    .choose-item{
        background-color: #5fa391;
        color: #fff;
    }
</style>
<script>
    $(document).ready(function(){

        $(".classify-items:first").addClass("choose-item");
        $("#classifyId").val($(".classify-items:first").val());
    })

    function chooseClassify(classifyId){

        $(".classify-items").removeClass("choose-item");
        $("#"+classifyId).addClass("choose-item");
        $("#classifyId").val(classifyId);
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

                                if(result = "删除成功"){

                                    $("#"+classifyId).remove();
                                    $(".classify-items:first").addClass("choose-item");
                                    $("#classifyId").val($(".classify-items:first").val());
                                }
                                layer.msg(result);

                            },null,null);
                }
            },
            btn2: function(){}
        });


    }

    function addClassify(){

        var url = "${ctx}/tags/toAddClassify";
        layer.open({
            type: 2,
            title: "添加分类",
            area: ['380px', '200px'], //宽高
            content: '${ctx}/tags/toAddClassify'
        });

    }

    function appendClassify(id,name){

        var newClassify = "<label class = 'classify-items' id='"+id+"' onclick='chooseClassify(\'"+id+"\')'>"+name+"</label>"
        $(".classify-content").append(newClassify);
        chooseClassify(id);
        layer.closeAll();
    }

    function closeDialog(){

        layer.closeAll();
    }

    function editorClassify(){

        layer.prompt({title: '编辑', formType: 2}, function(text, index){

            var classifyId = $("#classifyId").val();
            var url = "${ctx}/tags/editorClassify";

            ajaxPost(url,{"id":classifyId,"name":text},function(classify){

                if(classify.id != 0){

                    $("#"+classifyId).html(text);
                    layer.closeAll();
                }else{

                    layer.msg(classify.name);
                }
            })


        });
    }
</script>
<body>
    <div class = "classify">
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

</body>
</html>
