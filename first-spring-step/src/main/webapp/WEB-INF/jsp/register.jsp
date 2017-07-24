<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/4 0004
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <%@include file="common/header.jsp" %>
    <title>登录</title>
    <link rel="stylesheet" href="${ctx}/static/css/register.css"/>

</head>

<body>
<%@include file="common/site-nav.jsp" %>
<div class="content">
    <div class="login-bg"></div>
    <div class="content-layout">
        <div class="static-form " id="J_StaticForm">
            <%--<form action="/member/login.jhtml?redirectURL=http%3A%2F%2Fwww.taobao.com%2F" method="post" id="J_Form">--%>
            <div class="field">
                <label for="newUserName">用户名</label>
                <input type="text" name="newUserName"
                       id="newUserName" placeholder="6-15位，英文、数字、中文、_"/>
                <label class="testName" id="testUserName">检测</label>
                <div id="newNameError" class="alertText errorInfo alert alert-danger " role="alert">
                    6-15位，允许输入英文、数字、中文、_
                </div>
                <div id="newNameSuccess" class="alertText errorInfo alert alert-success " role="alert">用户名可使用</div>
                <div id="newNameExist" class="alertText errorInfo alert alert-warning " role="alert">( ¯ □ ¯
                    )用户名已存在
                </div>
            </div>
            <div class="form-group field">
                <label for="newPassword">密码</label>
                <input type="password" name="newPassword" id="newPassword"
                       placeholder="6-16位密码，区分大小写，不能用空格"/>
            </div>
            <div id="newPwdError" class="alertText errorInfo alert alert-danger " role="alert">
                请输入6-16位密码，不能使用空格(‘▽′)Ψ
            </div>
            <div class="form-group field">
                <label for="passwordConfirm">确认密码</label>
                <input type="password" name="passwordConfirm" id="passwordConfirm"
                       placeholder="请再次输入密码"/>
            </div>
            <div id="pwdAgainError" class="alertText errorInfo alert alert-warning " role="alert">密码不一致(‘▽′)Ψ</div>
            <div class="submit">
                <button type="submit" class="J_Submit" tabindex="3" id="J_SubmitStatic" data-ing="正在注册...">注册
                </button>
            </div>
            <%--</form>--%>
        </div>
    </div>
    <%@include file="common/copy-right.jsp" %>
    <%@include file="common/footer.jsp" %>
    <script src = "${ctx}/static/layer/layer.js"></script>
    <script type="application/javascript">
        $(function () {
            $("#testUserName").on("click", function () {
                testUserName();
            });
            $("#J_SubmitStatic").on("click", function () {
                signUpSubmit();
            });
        });

        function testUserName() {

            $(".errorInfo").hide();
            var userName = $("#newUserName").val();
            var regex = /^[0-9A-Za-z_\u4e00-\u9fa5]{6,15}$/;

            if (regex.exec(userName) != null) {

                $.ajax({
                    url: "${ctx}/user/testName",
                    data: {"userName": userName},
                    success: function (value) {

                        if ("用户存在" == value) {

                            $("#newNameExist").show();
                        } else {
                            $("#newNameSuccess").show();
                        }
                    }
                });

            } else {
                $("#newNameError").show();
            }
        }

        function signUpSubmit() {
            $(".errorInfo").hide();
            var newUserName = $("#newUserName").val();
            var regex = /^[0-9A-Za-z_\u4e00-\u9fa5]{6,15}$/;
            if (regex.exec(newUserName) == null) {
                $("#newNameError").show();
                return false;
            }
            var newPassword = $("#newPassword").val();
            var regStr = /^[0-9A-Za-z]{6,16}$/;
            if (regStr.exec(newPassword) == null) {
                $("#newPwdError").show();
                return false;
            }
            var passwordConfirm = $("#passwordConfirm").val();
            if ((newPassword == passwordConfirm)) {
                $.ajax({
                    url: "${ctx}/user/signup",
                    data: {"userName": newUserName, "password": newPassword},
                    success: function (value) {
                        console.log(value);
                        if ("注册成功" == value) {
                            layer.msg("注册成功");
                        } else {
                            $("#signupError").show();
                        }
                    }
                });
            } else {
                $("#pwdAgainError").show();
                return false;
            }
        }
    </script>
</body>
</html>
