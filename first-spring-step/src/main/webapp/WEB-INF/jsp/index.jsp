<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/4 0004
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>

Hello <a href="javascript:HISTORY.goToUrl('aaa')">${name}</a>!
<td><img id="uploadPreview" style="width: 100px; height: 100px;" src="" alt="Image preview"/></td>

<input type="file" id="uploadImage" onchange=""/>
</body>

<script src="${ctx}/static/jquery/jquery-3.1.1.js"></script>
<script src="${ctx}/static/application.js"></script>
<script src="${ctx}/static/exif-js-master/exif.js"></script>
<script type="text/javascript">
    oFReader = new FileReader(), rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;

    oFReader.onload = function (oFREvent) {
        document.getElementById("uploadPreview").src = oFREvent.target.result;
    };

    $(function () {

        $("#uploadImage").on("change", function () {
            loadImageFile();
        })
    })

    function loadImageFile() {
        if (document.getElementById("uploadImage").files.length === 0) {
            return;
        }
        var oFile = document.getElementById("uploadImage").files[0];
        if (!rFilter.test(oFile.type)) {
            alert("You must select a valid image file!");
            return;
        }
        oFReader.readAsDataURL(oFile);

        EXIF.getData(this, function () {
            console.log(EXIF.pretty(this));
        });
    }
</script>
</html>
