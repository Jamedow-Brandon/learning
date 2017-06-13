<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>
<html>
<head>
    <title>礼物唔</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript" src="${ctx}/static/ajax-dialog.js"></script>
    <script src="${ctx}/static/jquery/jquery-3.1.1.min.js"></script>
    <script src="${ctx}/static/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/dialog/dialog-min.js"></script>
</head>
<style>

    body{
        margin: 0;
    }
    #blackTop{
        background-color: #000;
        height: 70%;
        width: 100%;
    }
    #whiteBottom{
        background-color: #fff;
        height: 30%;
        width: 100%;
    }
    #homePageTitle{
        color: #fff;
        padding-left: 34%;
        padding-top: 2%;
        font-size: 45px;
    }
    #center{
        cursor: pointer;
        margin-left: 40%;
        width: 10%;
        height: 177px;
    }
    #questionMark{

        margin-left: 33%;
        width: 25%;
        height: 324.5px;
    }

</style>
<script>

    $(document).ready(function () {

        $("#center").click(function(){

        });
    });
</script>
<body>

<div id = "blackTop" >
    <div id ="homePageTitle">纠结过节送什么礼</div>
    <img id = "questionMark" src="${ctx}/static/img/questionMark.png" alt="">
</div>
<div id = "whiteBottom">
    <img id = "center" src="${ctx}/static/img/center.jpg" alt="">
</div>
</body>
</html>
