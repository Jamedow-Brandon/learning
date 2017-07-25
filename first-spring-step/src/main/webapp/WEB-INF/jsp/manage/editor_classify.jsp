<%--
  Created by IntelliJ IDEA.
  User: yoyo
  Date: 2017/7/13
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/taglib.jsp" %>
    <%@include file="../common/header.jsp" %>
    <script src="${ctx}/static/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/ajax-dialog.js"></script>
    <script src = "${ctx}/static/layer/layer.js"></script>
</head>
<style>
    #name-text{
        margin-top: 7%;
        margin-left: 10%;
        margin-right: 8%;
    }
    #addClassify,#cancel{
        font-weight: 400;
        margin-top: 10%;
        padding:5px 15px;
        color:#fff;
        background-color:#69b49f;
        margin-left:26%;
    }
    #cancel{
        margin-left: 16%;
    }
</style>
<script>
    $(document).ready(function(){


        var classifyId = $("#classifyId").val();
        $("#name").val(window.parent.document.getElementById(classifyId).innerHTML);

        $("#addClassify").click(function(){

            var classifyId = $("#classifyId").val();
            var name = $("#name").val();
            if(name == ""){
                layer.msg("请填写名称");
                return ;
            }
            var url = "${ctx}/tags/editorClassify?id="+classifyId+"&name="+name;
            if(classifyId!=null){

                ajaxPost(url,null,
                        function(classify){

                            if(classify != null){

                                if(classify.id == 0){
                                    layer.msg(classify.name);
                                    return ;
                                }

                                window.parent.location="javascript:changeClassify('"+classify.id+"','"+classify.name+"')";

                            }else{
                                layer.msg("修改失败");
                            }
                        },null,false);
            }



        });

        $("#cancel").click(function(){
            window.parent.location="javascript:closeDialog()";
        });

    })
</script>

<body>
<label id = "name-text" >名称:</label>
<input id = "name" type = "text"/>
<div>
    <label id = "addClassify" >确认</label>
    <label id = "cancel" >取消</label>
</div>
<input type = "hidden"id="classifyId" value="${classifyId}"/>
</body>
</html>
