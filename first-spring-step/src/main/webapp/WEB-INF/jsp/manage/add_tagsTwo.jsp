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
    #name,#tagsTwo{
        width: 50%;
        margin-top: 6%;
    }
    .name-text{
        float: left;
        margin-top: 2%;
        margin-left: 10%;
        margin-right: 8%;
    }
    #addTagsTwo,#cancel{
        font-weight: 400;
        margin-top: 29%;
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

        $("#tagsTwo").select2();

        $("#addTagsTwo").click(function(){

            /*var tagsTwoId = window.parent.document.getElementById("tagsTwoId").value;*/
            var name = $("#name").val();
            if(name == ""){
                layer.msg("请填写名称");
                return ;
            }
            var tagsOneIds = [];
            var tagsOnes = $("#tagsTwo option:selected");

            for(var i = 0;i<tagsOnes.length;i++){
                tagsOneIds.push(tagsOnes[i].value);
            }
            if(tagsOneIds.length == 0){
                layer.msg("请选择父标签");
                return ;
            }
            var url = "${ctx}/tags/addTagsTwo";
            if(tagsOneIds!=null){

                console.log(name+","+tagsOneIds);
                ajaxPost(url,{name:name,tagsOneIds:tagsOneIds.join(",")},
                        function(result){

                            if(result != null){

                                if(result != "保存成功"){
                                    layer.msg(result);
                                    return ;
                                }
                                window.parent.location="javascript:appendTagsTwo()";

                            }else{
                                layer.msg("保存失败");
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
    <select  id="tagsTwo"  class="form-control" multiple = "multiple" title="请选择分类" >
        <c:forEach items="${tagsExtList}" var="tagsExtList" varStatus="status">
            <optgroup label="${tagsExtList.name}">
                <c:forEach items="${tagsExtList.childrenTagsList}" var="childrenTagsList" varStatus="status">
                    <option value="${childrenTagsList.id}">${childrenTagsList.name}</option>
                </c:forEach>
            </optgroup>
        </c:forEach>

    </select>
</div>
<div>
    <label id = "addTagsTwo" >确认</label>
    <label id = "cancel" >取消</label>
</div>
</body>
</html>
