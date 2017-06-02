<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>learning</title>

    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript" src="${ctx}/static/ajax-dialog.js"></script>
    <script src="${ctx}/static/jquery/jquery-3.1.1.min.js"></script>
    <script src="${ctx}/static/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/dialog/dialog-min.js"></script>

</head>
<body>
<script>

    $(document).ready(function(){


        $("#login ,#userExit").hover(function(){
            $("#userExit").show();
        },function(){
            $("#userExit").hide();
        });

        var userName  = getCookieValue("userName");
        var loginStatus  = getCookieValue("loginStatus");
        if(userName!=""&&loginStatus=="Y"){

            $("#login").hide();
            var userInfo = "<li><a  id='userInfo' href='#' >"+userName+"</a></li>";
            $("#navbarTop").append(userInfo);
        }
        $("#userExit").click(function(){

            setCookie("loginStatus","N",24*10,"/");//登录状态：Y：已登录，N：未登录
            $("#userInfo").remove();
            $("#login").show();
        });
    });

    function getCookieValue(name){
        var start = pos + name.length; //cookie值开始的位置
        if (pos != -1){ //如果pos值为-1则说明搜索"version="失败
            var end = allCookies.indexOf(";",start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
            if (end == -1) end = allCookies.length; //如果end值为-1说明cookie列表里只有一个cookie
        else return ""; //搜索失败，返回空字符串
            var value = allCookies.substring(start,end); //提取cookie的值
            return unescape(value); //对它解码
        }
        var name = escape(name);
        //读cookie属性，这将返回文档的所有cookie
        var allCookies = document.cookie;
        //查找名为name的cookie的开始位置
        name += "=";
        var pos = allCookies.indexOf(name);
        //如果找到了具有该名字的cookie，那么提取并使用它的值
    }

</script>
<style  >
    body{
        padding-top: 50px;
    }
    .carousel{
        height: 500px;
        background-color: #000;
    }
    .carousel .item{
        height: 500px;
        background-color: #000;
    }
    .carousel img{
        width: 100%;
    }
    .user-exit{
        height: 83%;
        background-color: #fff;
        margin-top: 51px;
        position: absolute;
        width: 6%;
        right: 0;
        text-align: center;
        padding-top: 1%;
        cursor: pointer;
    }
</style>
    <jsp:include page="/WEB-INF/jsp/login.jsp"></jsp:include>


<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
<div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
         <ul id = "navbarTop" class="nav navbar-nav navbar-right">
            <li><a  id="login" href="#" data-toggle="modal" data-target="#loginDialog">登录</a></li>
        </ul>
        <div id = "userExit" class = "user-exit">注销</div>
    </div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>

    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            <li data-target="#carousel-example-generic" data-slide-to="4"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="${ctx}/static/img/zhengdan.jpg" alt="">
                <div class="carousel-caption">
                    <h1>蒸蛋</h1>
                </div>
            </div>
            <div class="item">
                <img src="${ctx}/static/img/jianzhupai.jpg" alt="">
                <div class="carousel-caption">
                    <h1>煎猪排</h1>
                </div>
            </div>
            <div class="item">
                <img src="${ctx}/static/img/xiawucha.jpg" alt="">
                <div class="carousel-caption">
                    <h1>下午茶</h1>
                </div>
            </div>
            <div class="item">
                <img src="${ctx}/static/img/pisa.jpg" alt="">
                <div class="carousel-caption">
                    <h1>披萨</h1>
                </div>
            </div>
            <div class="item">
                <img src="${ctx}/static/img/xiaopai.jpg" alt="">
                <div class="carousel-caption">
                    <h1>小排</h1>
                </div>
            </div>

        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">上一页</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">下一页</span>
        </a>
    </div>

</body>
</html>
