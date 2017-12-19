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
    <link rel="stylesheet" type="text/css" href="${ctx}/static/webuploader/css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/webuploader/examples/image-upload/style.css">
    <link rel="stylesheet" href="${ctx}/static/common/css/recipe-edit.css"/>
    <title>老豆坊</title>
</head>
<body>
<%@include file="../common/site-nav.jsp" %>

<div class="container content">
    <form action="${ctx}/recipe/add" method="post">
        <div id="wrapper">
            <div id="container">
                <!--头部，相册选择和格式选择-->

                <div id="uploader">
                    <div class="queueList">
                        <div id="dndArea" class="placeholder">
                            <div id="filePicker"></div>
                            <p>或将照片拖到这里</p>
                        </div>
                    </div>
                    <div class="statusBar" style="display:none;">
                        <div class="progress">
                            <span class="text">0%</span>
                            <span class="percentage"></span>
                        </div>
                        <div class="info"></div>
                        <div class="btns">
                            <div id="filePicker2"></div>
                            <div class="uploadBtn">开始上传</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input id="imgUrl" name="imgUrl" type="hidden"/>
        <div class="form-group">
            <label for="title">菜名：</label>
            <input id="title" type="text" class="form-control" placeholder="请输入菜名" maxlength="20"/>
        </div>
        <div class="form-group">
            <label for="intro">简介：</label>
            <textarea id="intro" class="form-control" rows="5" cols="20" placeholder="请输入简介"></textarea>
        </div>
        <div class="form-group">
            <label>标签：</label>
            <select id="tags" class="js-example-basic-multiple form-control" multiple="multiple" placeholder="请选择标签">
                <c:forEach items="${tags}" var="tag">
                    <option value="${tag.id}">${tag.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>主料：</label>
            <select id="ingredients" class="js-example-basic-multiple form-control" multiple="multiple"
                    placeholder="请选择主料">
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
            <select id="burdenings" class="js-example-basic-multiple form-control" multiple="multiple"
                    placeholder="请选择辅料">
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
        <label id="submit">提交</label>
    </form>
</div>

<%@include file="../common/copy-right.jsp" %>
<%@include file="../common/footer.jsp" %>
<script type="text/html" id="paramTemplate">
    <div>
        <span class="col-sm-1 control-label ">{{name}}：</span><input type="text" class="form-control cm-xl-1 weight"
                                                                     tagId="{{value}}"/>
    </div>
</script>
<script type="application/javascript" src="${ctx}/static/script/template.js"></script>
<script type="application/javascript" src="${ctx}/static/ckeditor/ckeditor.js"></script>
<script type="application/javascript" src="${ctx}/static/select2/dist/js/select2.js"></script>
<script type="application/javascript" src="${ctx}/static/webuploader/dist/webuploader.js"></script>
<script type="application/javascript" src="${ctx}/static/webuploader/examples/image-upload/upload.js"></script>
<script type="application/javascript" src="${ctx}/static/layer/layer.js"></script>
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
    });

    $("#submit").click(function () {

        var title = $("#title").val();
        if (title == "") {
            layer.msg("请输入菜名");
            return;
        }

        var intro = $("#intro").val();
        if (intro == "") {
            layer.msg("请输入简介");
            return;
        }

        var tags = "[]";//标签
        var selectedTags = $("#tags").find("option:selected");
        if (selectedTags.length == 0) {
            layer.msg("请选择至少一个标签");
            return;
        }
        var jsonArray3 = eval('(' + tags + ')');
        var m = 0;
        selectedTags.each(function (index, param) {
            var arr =
                {
                    "id": selectedTags[m].value,
                    "name": param.text,
                }
            jsonArray3.push(arr);
            m++;
        })
        tags = JSON.stringify(jsonArray3);


        var selectedIngredient = $("#ingredients").find("option:selected");//主料
        var weight = $(".weight");//分量
        if (selectedIngredient.length == 0) {
            layer.msg("请选择至少一个主料");
            return;
        }

        var ingredients = "[]";//json存储主料
        var jsonArray = eval('(' + ingredients + ')');
        var i = 0;
        selectedIngredient.each(function (index, param) {
            var arr =
                {
                    "id": selectedIngredient[i].value,
                    "name": param.text,
                    "weight": weight[i].value
                }
            jsonArray.push(arr);
            i++;
        })
        ingredients = JSON.stringify(jsonArray);

        var selectedBurdening = $("#burdenings").find("option:selected");//选择的辅料
        var burdenings = "[]";//json存储辅料
        var imgUrl = $("#imgUrl").val();//json存储辅料
        var jsonArray2 = eval('(' + burdenings + ')');
        var j = 0;//选择的辅料下标
        selectedBurdening.each(function (index, param) {
            var arr =
                {
                    "id": selectedBurdening[j].value,
                    "name": param.text,
                    "weight": weight[i].value
                }
            jsonArray2.push(arr);
            i++;
        })
        burdenings = JSON.stringify(jsonArray2);

        $.ajax({
            url: "${ctx}/recipe/saveMenu",
            data: {
                name: title,
                intro: intro,
                tags: tags,
                ingredient: ingredients,
                burdening: burdenings,
                imgUrl: imgUrl
            },
            success: function (value) {
                if (1 == value) {
                    layer.msg("保存成功");
                    //成功跳转
                } else {
                    layer.msg("保存失败");
                }
            }
        });
    });

</script>
</body>
</html>
