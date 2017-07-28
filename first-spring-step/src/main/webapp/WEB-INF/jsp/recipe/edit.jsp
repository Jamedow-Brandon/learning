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
    <link rel="stylesheet" href="${ctx}/static/select2/dist/css/select2.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/recipe-add.css"/>
    <title>Title</title>
</head>
<body>
<%@include file="../common/site-nav.jsp" %>

<div class="container content">
    <form action="${ctx}/recipe/add" method="post">
        <div class="form-group">
            <label for="title">菜名：</label>
            <input id="title" type="text" class="form-control" placeholder="请输入菜名"/>
        </div>
        <div class="form-group">
            <label for="intro">简介：</label>
            <input id="intro" type="text" class="form-control" placeholder="请输入简介"/>
        </div>
        <div class="form-group">
            <label>标签：</label>
            <select class="js-example-basic-multiple form-control" multiple="multiple" placeholder="请选择标签">
                <c:forEach items="${tags}" var="tag">
                    <option value="${tag.id}">${tag.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>主料：</label>
            <select class="js-example-basic-multiple form-control" multiple="multiple" placeholder="请选择主料">
                <c:forEach items="${fodders}" var="fodder">
                    <optgroup label="${fodder.name}">
                        <c:forEach items="${fodder.tags}" var="tag">
                            <option value="${tag.id}">${tag.name}</option>
                        </c:forEach>
                    </optgroup>
                </c:forEach>
            </select>
            <div class="params form-inline"></div>
        </div>
        <div class="form-group">
            <label>辅料：</label>
            <select class="js-example-basic-multiple form-control" multiple="multiple" placeholder="请选择辅料">
                <c:forEach items="${fodders}" var="fodder">
                    <optgroup label="${fodder.name}">
                        <c:forEach items="${fodder.tags}" var="tag">
                            <option value="${tag.id}">${tag.name}</option>
                        </c:forEach>
                    </optgroup>
                </c:forEach>
            </select>
            <div class="params form-inline"></div>
        </div>
        <div class="form-group">
            <label>步骤：</label>
            <textarea id="detail" class="form-control" name="detail" rows="10" cols="30">
            This is my textarea to be replaced with CKEditor.
        </textarea>
        </div>
    </form>
</div>

<%@include file="../common/copy-right.jsp" %>
<%@include file="../common/footer.jsp" %>
<script type="text/html" id="paramTemplate">
    <div>
        <span class="col-sm-1 control-label">{{name}}：</span><input type="text" class="form-control cm-xl-1"
                                                                    tagId="{{value}}"/>
    </div>
</script>
<script type="application/javascript" src="${ctx}/static/ckeditor/ckeditor.js"></script>
<script type="application/javascript" src="${ctx}/static/select2/dist/js/select2.js"></script>
<script type="application/javascript" src="${ctx}/static/script/template.js"></script>
<script type="application/javascript">
    CKEDITOR.replace('detail');

    $(".js-example-basic-multiple").select2({
        allowClear: true
    });
    $(".js-example-basic-multiple:gt(0)").on("change", function () {
        var $select = $(this);
        var selectedParams = $select.find("option:selected");
        var paramHtml = "";
        selectedParams.each(function (index, param) {
            paramHtml += template("paramTemplate", {name: param.text, value: param.value});
        });
        $select.parent().find(".params").html(paramHtml);
    })
</script>
</body>
</html>
