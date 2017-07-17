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
    <link rel="stylesheet" href="${ctx}/static/css/login.css"/>
</head>

<body>
<%@include file="common/site-nav.jsp" %>
<div class="content">
    <div class="login-bg"
         style="background-image: url(https://img.alicdn.com/tfs/TB1bvEOSXXXXXb0aXXXXXXXXXXX-2500-600.jpg);height:600px;">

    </div>
    <div class="content-layout">
        <div class="bd">
            <!--标准登录框-->
            <div class="static-form " id="J_StaticForm">
                <div class="login-title">
                    密码登录
                </div>

                <form action="/member/login.jhtml?redirectURL=http%3A%2F%2Fwww.taobao.com%2F" method="post" id="J_Form">
                    <div id="J_Message" style="display:none;" class="login-msg error">
                        <i class="iconfont"></i>

                        <p class="error"></p>

                    </div>
                    <!-- 手机号登录 -->
                    <div class="field ph-hide username-field ">
                        <label for="TPL_username"> <i class="iconfont" title="会员名"></i></label>
                        <input type="text" name="TPL_username" id="TPL_username" class="login-text J_UserName"
                               value="" maxlength="32" tabindex="1" placeholder="会员名/邮箱/手机号"/>

                        <div class="field pwd-field">
                            <label for="TPL_password"> <i class="iconfont" title="登录密码"></i></label>
                            <input type="password" name="TPL_password" id="TPL_password" class="login-text"
                                   maxlength="40" tabindex="2" autocomplete="off" aria-placeholder="登录密码"/>
                        </div>
                        <div class="submit">
                            <button type="submit" class="J_Submit" tabindex="3" id="J_SubmitStatic" data-ing="正在登录...">登
                                录
                            </button>
                        </div>
                    </div>

                    <div class="login-links">
                        <a href="https://passport.taobao.com/ac/password_find.htm?from_site=0&amp;lang=zh_CN&amp;login_id=&amp;app_name=tbTop"
                           tabindex="6" class="forget-pwd" target="_blank">
                            忘记密码</a>

                        <a href="https://passport.taobao.com/ac/nick_find.htm?from_site=0&amp;lang=zh_CN&amp;login_id=&amp;app_name=tbTop"
                           target="_blank">忘记会员名</a>

                        <a href="http://reg.taobao.com/member/newbie.htm?from=login" class="register"
                           target="_blank"
                           tabindex="7">免费注册</a>

                    </div>
                    <input type="hidden" name="um_token" value="HV01PAAZ0b870f7eda5e7383596c74060078722a"><input
                        type="hidden" name="ua" id="UA_InputId"
                        value="095#6ULo7EoEoqTocERZoooooPL7/8uQJPF9/xNezPCle/6VgpYdRza5wPOUoUt5DfOSi8u/sPIewzuwJjaFLUNhDfCwTecVV6eRoobsYQz2WwLoRSDcO+foV4TDoIoTce2a1uD+ceIetX+73ILoRj7xxh/DoIoTceAx1HD+ceI7slv73ILoRj7xxh/DoIoTceAN1Hb+ceI7qhP7nwLoAPWAO+fGIkTOGU/Xy8LhfMaKCISLnwLoAPWLO+fA/XTOGU/Xy8LhfMaKCISLWwLoApF+O+a+RITItwETE2xUbMVfupKqWUeRoRQ5IGp74vi9bPDRY+OF8XYUmxHhUlE5anRG6h/RoRH5IZ2eRvi9IP/NPuM5/7IJRbH3WPXSaC/RooF9IIg78vi9bdTroIoLgfN2xUHRooI9bA2wqILoR0EngxwsoIojceap1m1+JwLoqnZ2OBCSNOTebv5iknfkO07tKxGOsnRfuUF3r6xfsaCVaMLyknfkO07tKnXDNgagGeIer/xJjoYXfjVXUb/KRfQADj2cjyFKDyFEYsztTaIyavC7wqThO3tIHvfp3pWYcRLkcVjSNAC3IULRzteSISRvYfvoX6iMDE6kADoYXIO0IPTFkSXJf0tfu9kfs8OMDE6kAte5NOH9PfsiE8qljpRfuSjOsytOu7V80wLouPDLO+a+ZSDLcebOx91+cTL9bAb7Z9iSZPDLava+ZScOcebODm1+T7IvoIoC6softxVOJ9oWoIoj60I0ofa9/ILoPPYauJf0jot7R9a+E5eRoobsYQz2xwLo0pDR2JagZSDvvC/RooF9IM97Dmi9bej2oIo6ceL/1Cc+ceZt3ILoRj7xxhzroIoONOap1YvnYUb4jdKj/pyt6aQyPTMDoIoLgfu2xhiRoRI5bar7nd5nAv/g6QKUNAFWfxLTUCiRoRI5bAr7n7VnAv/g6QKUNAFWfxLTUCiRoRI5bAr73WAnAv/g6QKUNAFWfxLTUo==">
                </form>
            </div>
        </div>
    </div>


    <%@include file="common/copy-right.jsp" %>
    <%@include file="common/footer.jsp" %>
</body>
</html>
