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




    <button id="login" class="btn btn-default" data-toggle="modal" data-target="#loginDialog" >登录/注册</button>



<script >
    $(document).ready(function() {



        $("#login").click(function () {

            $(".errorInfo").hide();

            //$("#loginDialog").modal();

            $("#signin").click();
            getRememberInfo();//加载页面时获取cookie中的值

        });

        $("#signin").click(function () {

            $("#signin").addClass("action-title");
            $("#signup").removeClass("action-title");
            $("#signupBody,#signupSubmit").remove();
            $("#loginBody").addClass("dialog-body");
            $("#loginBody").removeClass("signup-dialog-body");
            $("#signinBody,#signinSubmit").show();
        });

        $("#signup").click(function () {

            $("#signup").addClass("action-title");
            $("#signin").removeClass("action-title");
            $("#signinBody,#signinSubmit").hide();
            $("#signupBody,#signupSubmit").remove();
            $("#loginBody").addClass("signup-dialog-body");
            $("#loginBody").removeClass("dialog-body");
            $("#loginBody").append("<div class='modal-body'' id='signupBody'><div class='form-group'>" +
                    "<label for='newUserName'>用户名</label> " +
                    "<input type='text' name='newUserName' class='form-control' id='newUserName' " +
                    "placeholder='6-15位，允许输入英文、数字、中文、_'> "+
                    " <p id = 'newNameError' class ='errorInfo pFont'>( ¯ □ ¯ )6-15位，允许输入英文、数字、中文、_</p>"+
                    "</div>" +
                    " <div class='form-group' > <label for='newPassword'>密码</label>" +
                    " <input type='password'  name='newPassword' class='form-control' id='newPassword' " +
                    "placeholder='6-16位密码，区分大小写，不能用空格'> </div>" +
                     " <p id = 'newPwdError' class ='errorInfo pFont'>请输入6-16位密码，不能使用空格(‘▽′)Ψ </p>"+
                    " <div class='form-group'> <label for='passwordConfirm'>确认密码</label> " +
                    "<input type='password'  name='passwordConfirm' class='form-control' id='passwordConfirm' " +
                    "placeholder='请再次输入密码'> </div> " +
                    " <p id = 'pwdAgainError' class ='errorInfo pFont'>密码不一致(‘▽′)Ψ </p>"+
                    "</div><button type='button' id='signupSubmit' onclick='signupSubmit()' class='btn btn-primary loginButton' " +
                    "data-dismiss='modal'><span  aria-hidden='true'></span>注册</button>");
            $(".errorInfo").hide();

        });

        $("#signInSubmit").click(function(){

            $(".errorInfo").hide();
            var url="${ctx}/login/accessLogin";

            var userName = $("#userName").val();
            var password = $("#password").val();
            var remember=document.all.remember.checked;

            setCookie("userName",userName,24*10,"/");//保留24小时
            if(remember){
                setCookie("userPassword",password,24*10,"/");	//保留24小时
                setCookie("remember",remember,24*10,"/");
            }else {
                setCookie("remember",remember,24*10,"/");
                deleteCookie("userPassword","/");
            }

            var regex=/^[0-9A-Za-z_\u4e00-\u9fa5]{6,15}$/;
            if(regex.exec(userName)==null){

                $("#userError").show();
                return false;
            }
            var regStr = /^[0-9A-Za-z]{6,16}$/;
            if(regStr.exec(password)==null){

                $("#pwdError").show();
                return false;
            }

            ajaxPost(url,{"userName":userName,"password":password},

                function(value){

                    if("登录成功"==value ){

                        dialogShow(userName);
                        $("#loginDialog").hide();
                    }else {

                        $("#signInError").show();
                        $("#loginDialog").modal("show");
                    }
            },null,false );
        });


    });

    function  signupSubmit(){

        $(".errorInfo").hide();
        var newUserName = $("#newUserName").val();
        var regex=/^[0-9A-Za-z_\u4e00-\u9fa5]{6,15}$/;

        if(regex.exec(newUserName)==null){

            $("#newNameError").show();
            return false;
        }
        var newPassword = $("#newPassword").val();
        var regStr = /^[0-9A-Za-z]{6,16}$/;
        if(regStr.exec(newPassword)==null){

            $("#newPwdError").show();
            return false;
        }
        var passwordConfirm = $("#passwordConfirm").val();
        if(newPassword == passwordConfirm){

                var url="${ctx}/login/signup";
                ajaxPost(url,{"userName":newUserName,"password":newPassword},

                        function(value){

                            if("注册成功"==value ){
                                dialogShow("注册成功");
                                closeDialog();
                            }else {
                                $("#loginDialog").modal("show");
                            }
                        },null,false );
        }else{
            $("#pwdAgainError").show();
        }



    }
    //新建cookie。
    //hours为空字符串时,cookie的生存期至浏览器会话结束。hours为数字0时,建立的是一个失效的cookie,这个cookie会覆盖已经建立过的同名、同path的cookie（如果这个cookie存在）。
    function setCookie(name,value,hours,path){
        var name = escape(name);
        var value = escape(value);
        var expires = new Date();
        expires.setTime(expires.getTime() + hours*3600000);
        path = path == "" ? "" : ";path=" + path;
        expires = (typeof hours) == "string" ? "" : ";expires=" + expires.toUTCString();
        document.cookie = name + "=" + value + expires + path;
    }

    //获取cookie值
    function getCookieValue(name){
        var name = escape(name);
        //读cookie属性，这将返回文档的所有cookie
        var allCookies = document.cookie;
        //查找名为name的cookie的开始位置
        name += "=";
        var pos = allCookies.indexOf(name);
        //如果找到了具有该名字的cookie，那么提取并使用它的值
        if (pos != -1){ //如果pos值为-1则说明搜索"version="失败
            var start = pos + name.length; //cookie值开始的位置
            var end = allCookies.indexOf(";",start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
            if (end == -1) end = allCookies.length; //如果end值为-1说明cookie列表里只有一个cookie
            var value = allCookies.substring(start,end); //提取cookie的值
            return unescape(value); //对它解码
        }
        else return ""; //搜索失败，返回空字符串
    }

    //删除cookie
    function deleteCookie(name,path){

        var name = escape(name);
        var expires = new Date(0);
        path = path == "" ? "" : ";path=" + path;
        document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;
    }

    //获取cookie信息
    function getRememberInfo(){

        var userName = getCookieValue("userName");
        var userPassword = getCookieValue("userPassword");
        var remember = getCookieValue("remember");

        document.getElementById("userName").value=userName;
        document.getElementById("password").value=userPassword;
        if(remember=="true"){
            $("#remember").attr('checked','checked');
        }else{
            $("#remember").removeAttr('checked');
        }
    }
</script>


<style  >
    .action-title
    {
        border-bottom:1px solid #f0310b;
        color: #f02312;
    }
    .dialog-body
    {
        height: 382px;
        width: 326px;
    }
    .signup-dialog-body
    {
        height: 382px;
        width: 326px;
    }
    .loginButton{
        position: absolute;
        right:10%;
        width:80%;
    }
    .titleFont{
        font-family:Microsoft YaHei
    }
    .dialogPosition{
        position:fixed;
        left: 20%;
    }
    .pFont{
        font-size: small;
        color: red;
    }
    .checkMargin{
        margin-bottom: 0px;
        margin-top: 0px;;
    }
</style>

    <div class="modal fade" id="loginDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog"  role="document">
            <div class="modal-content dialog-body dialogPosition" id="loginBody" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class = "header_title">
                        <span id = "signin" class = "action-title titleFont" style="cursor:pointer">登录</span>
                        <span id = "signup" class = "titleFont" style="cursor:pointer">注册</span>
                    </h4>
                </div>
                <div class="modal-body" id = "signinBody">

                    <div style="height:24px">
                        <p id = "signInError" class ="errorInfo pFont">用户名或密码错误</p>
                    </div>
                    <div style="height:84px">
                        <label for="userName">用户名</label>
                        <input type="text" name="userName" class="form-control" id="userName" placeholder="请输入用户名/账号">
                        <p id = "userError" class ="errorInfo pFont">请输入正确的用户名或者账号( ¯ □ ¯ )</p>
                    </div>
                    <div style="height:84px">
                        <label for="password">密码</label>
                        <input type="password"  name="password" class="form-control" id="password" placeholder="请输入密码">
                        <p id = "pwdError" class ="errorInfo pFont">请输入6-16位密码，不能使用空格(‘▽′)Ψ </p>
                    </div>
                    <div class="checkbox checkMargin">
                        <label>
                            <input id ="remember" name="remember" type="checkbox"> 记住密码
                        </label>
                    </div>


                </div>


                    <button type="button" id="signInSubmit" class="btn btn-primary loginButton" data-dismiss="modal"><span  aria-hidden="true"></span>登录</button>

            </div>





        </div>
    </div>
</body>
</html>
