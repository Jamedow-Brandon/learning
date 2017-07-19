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

        $("#addClassify").click(function(){

            var name = $("#name").val();
            if(name == ""){
                layer.msg("请填写名称");
                return ;
            }
            var classifyId = window.parent.document.getElementById("classifyId").value;
            console.log(classifyId);
            var url = "${ctx}/tags/addTagsOne";
            ajaxPost(url,{"name":name,"classifyId":classifyId},
                    function(tags){

                        if(tags != null){

                            if(tags.id == 0){
                                layer.msg(tags.name);
                                return ;
                            }

                            window.parent.location="javascript:appendTagsOne('"+tags.id+"','"+tags.name+"')";

                        }else{
                            layer.msg("添加失败");
                        }
                    },null,false);

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
</body>
</html>
