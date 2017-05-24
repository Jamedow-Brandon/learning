<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript" src="${ctx}/static/ajax-dialog.js"></script>
    <script src="${ctx}/static/jquery/jquery-3.1.1.min.js"></script>
    <script src="${ctx}/static/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/dialog/dialog-min.js"></script>

</head>
<body>




    <button id="login" class="btn btn-default" >登录/注册</button>



<script >
    $(document).ready(function() {



        $("#login").click(function () {

            $(".errorinfo").hide();

            $("#loginDialog").modal("show");



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
            $("#loginBody").addClass("signup-dialog-body");
            $("#loginBody").removeClass("dialog-body");
            $("#loginBody").append("<div class='modal-body'' id='signupBody'><div class='form-group'>" +
                    "<label for='newUserName'>用户名</label> " +
                    "<input type='text' name='newUserName' class='form-control' id='newUserName' " +
                    "placeholder='请输入用户名/账号'> "+
                    " <p id = 'newNameError' class ='errorinfo pFont'>请输入正确的用户名或者账号( ¯ □ ¯ )</p>"+
                    "</div>" +
                    " <div class='form-group'> <label for='newPassword'>密码</label>" +
                    " <input type='newPassword'  name='newPassword' class='form-control' id='newPassword' " +
                    "placeholder='请输入密码'> </div>" +
                     " <p id = 'newPwdError' class ='errorinfo pFont'>请输入6-16位密码，不能使用空格(‘▽′)Ψ </p>"+
                    " <div class='form-group'> <label for='passwordConfirm'>确认密码</label> " +
                    "<input type='passwordConfirm'  name='passwordConfirm' class='form-control' id='passwordConfirm' " +
                    "placeholder='请再次输入密码'> </div> " +
                    "</div><button type='button' id='signupSubmit' class='btn btn-primary loginButton' " +
                    "data-dismiss='modal'><span  aria-hidden='true'></span>注册</button>");


        });

        $("#signinSubmit").click(function(){

            $(".errorinfo").hide();
            var url="${ctx}/login/accessLogin";

            var userName = $("#userName").val();
            var password = $("#password").val();


            var regex=/^[0-9A-Za-z_]{6,15}$/;
            if(regex.exec(userName)==null){

                $("#userError").show();
                return false;
            }
            var regStr = /^[0-9A-Za-z]{1,16}$/;
            if(regStr.exec(password)==null){

                $("#pwdError").show();
                return false;
            }

            ajaxPost(url,{"userName":userName,"password":password},

                function(value){

                    if("登录成功"==value ){

                        dialogShow("111登录成功");
                    $("#loginDialog").modal("hide");
                    }else {

                        dialogShow(value);

                        $("#loginDialog").modal("show");
                    }
            },null,true );
        });

        $("#signupSubmit").click(function(){

            $(".errorinfo").hide();
            var newUserName = $("#newUserName").val();

            var regex=/^[0-9A-Za-z_]{6,15}$/;
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
            if(newUserName==null&&newUserName == ""){
                return;
            }
            if((newPassword!=null)&&(newPassword!="")){
                if(newPassword == passwordConfirm){

                    var url="${ctx}/login/signup";
                    ajaxPost(url,{"userName":newUserName,"password":newPassword},

                            function(value){

                                if("注册成功"==value ){
                                    dialogShow("注册成功");
                                    closeDialog();
                                }else {

                                }
                            },null,false );
                }


            }
        });

    });
</script>


<style  >
    .action-title
    {
        border-bottom:1px solid #f0310b;
        color: #f02312;
    }
    .dialog-body
    {
        height: 320px;
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

                    <div style="height:84px">
                        <label for="userName">用户名</label>
                        <input type="text" name="userName" class="form-control" id="userName" placeholder="请输入用户名/账号">
                        <p id = "userError" class ="errorinfo pFont">请输入正确的用户名或者账号( ¯ □ ¯ )</p>
                    </div>
                    <div style="height:84px">
                        <label for="password">密码</label>
                        <input type="password"  name="password" class="form-control" id="password" placeholder="6-16位密码，区分大小写，不能用空格">
                        <p id = "pwdError" class ="errorinfo pFont">请输入6-16位密码，不能使用空格(‘▽′)Ψ </p>
                    </div>

                </div>


                    <button type="button" id="signinSubmit" class="btn btn-primary loginButton" data-dismiss="modal"><span  aria-hidden="true"></span>登录</button>

            </div>





        </div>
    </div>
</body>
</html>
