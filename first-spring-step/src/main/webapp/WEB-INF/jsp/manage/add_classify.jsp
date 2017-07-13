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
<script>
    $(document).ready(function(){

        $("#addClassify").click(function(){

            var name = $("#name").val();
            if(name == ""){
                layer.msg("请填写名称");
                return ;
            }
            var url = "${ctx}/tags/addClassify?name="+name;
            ajaxPost(url,null,
                    function(classify){

                        if(classify != null){

                            var newClassify = "<label class = 'classify-items' id='"+classify.id+"' onclick='chooseClassify(\'"+classify.id+"\')'>"+classify.name+"</label>"
                            $(".classify-content").append(newClassify);
                            window.parent.location="javascript:closeDialog()";

                        }else{
                            layer.msg("添加失败");
                        }
                    },null,null);

        });

        $("#cancel").click(function(){
            window.parent.location="javascript:closeDialog()";
        });

    })
</script>

<body>
<label>名称</label>
<input id = "name" type = "text"/>
<input id = "addClassify" type = "button" value="确认"/>
<input id = "cancel" type = "button" value="取消"/>
</body>
</html>
