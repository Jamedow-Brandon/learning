<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="../common/taglib.jsp" %>
    <%@include file="../common/header.jsp" %>


    <link href="${ctx}/static/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/static/ajax-dialog.js"></script>
    <script src="${ctx}/static/jquery/jquery-3.1.1.min.js"></script>
    <script src="${ctx}/static/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/dialog/dialog-min.js"></script>


    <link href="${ctx}/static/select2/dist/css/select2.min.css" rel="stylesheet">
    <script src="${ctx}/static/select2/dist/js/select2.full.js"></script>
    <script src = "${ctx}/static/layer/layer.js"></script>
</head>
<style>
    #name,#classify{
        width: 50%;
        margin-top: 6%;
    }
    .name-text{
        float: left;
        margin-top: 2%;
        margin-left: 10%;
        margin-right: 8%;
    }
    #addTagsOne,#cancel{
        font-weight: 400;
        margin-top: 13%;
        padding:5px 15px;
        color:#fff;
        background-color:#69b49f;
        margin-left:26%;
    }
    #cancel{
        margin-left: 16%;
    }
    .select2{
        margin-left: 1%;
        padding: 4px 2px;
        margin-top: 8%;
    }
</style>
<script>
    $(document).ready(function(){

        var classifyId = window.parent.document.getElementById("classifyId").value;//原来的所属分类
        $("#classify").select2().val(classifyId).trigger("change");

        var tagsOneId = $("#tagsOneId").val();//原来的名称
        $("#name").val(window.parent.document.getElementById(tagsOneId).innerHTML);

        $("#addTagsOne").click(function(){

            var tagsOneId = $("#tagsOneId").val();
            var name = $("#name").val();
            if(name == ""){
                layer.msg("请填写名称");
                return ;
            }
            var classifyId = $("#classify option:selected").val();
            var url = "${ctx}/tags/editorTagsOne";
            if(tagsOneId!=null){

                ajaxPost(url,{"id":tagsOneId,"name":name,"classifyId":classifyId},
                        function(tagsRelExt){

                            if(tagsRelExt != null){

                                if(tagsRelExt.tagId == 0){
                                    layer.msg(tagsRelExt.tagsName);
                                    return ;
                                }
                                window.parent.location="javascript:changeTagsOne('"+tagsRelExt.tagId+"','"+tagsRelExt.parentId+"','"+tagsRelExt.tagsName+"')";

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
<label class = "name-text" >名称:</label>
<input id = "name" type = "text" class = "form-control"/>
<br>
<label class = "name-text" style="float: left">分类:</label>
<div class="col-md-6" style="margin-top:-7%;">
    <select  id="classify"  class="form-control"  title="请选择分类" >
        <c:forEach items="${classifyList}" var="classifyList" varStatus="status">
            <option value="${classifyList.id}">${classifyList.name}</option>
        </c:forEach>
    </select>
</div>
<div>
    <label id = "addTagsOne" >确认</label>
    <label id = "cancel" >取消</label>
</div>
<input type = "hidden" id="tagsOneId" value="${tagsOneId}"/>
</body>
</html>
