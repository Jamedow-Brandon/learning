<%@page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="site-header">
        <div class="container">
            <nav>
                <div class="logo-area">
                    <h1>
                        <a href="//www.laodou.site/">老豆坊<img class="icon" src="${ctx}/static/img/logo.png"
                                                             alt="logo"/><img class="logo" src="${ctx}/static/img/H1.png"
                                                                             alt="店招"/></a>
                    </h1>
                    <h2>
                        <a href="//www.laodou.site/">老豆坊</a>
                    </h2>
                </div>
                <div class="search-field">
                    <form action="${ctx}/product/list" method="get">
                        <div class="search-box-input">
                            <input type="text" name="searchKeyWord" placeholder="请输入你想做的菜"/>
                        </div>
                        <button class="btn-search" type="submit">搜索</button>
                    </form>
                </div>

                <div class="user">
                    <c:if test="${empty user}">
                        <span><a class="login-button" href="${ctx}/user/login">登录</a></span>
                    </c:if>

                    <c:if test="${not empty user}">
                        <c:if test="${empty user.photo}">
                            <img class="photo"
                                 src="${ctx}/static/img/default-photo.png"
                                 alt="头像"/>
                        </c:if>
                        <c:if test="${not empty user.photo}">
                            <img class="photo"
                                 src="${user.photo}"
                                 alt="头像"/>
                        </c:if>
                        <span>个人中心</span> |
                        <span><a class="logout-button" href="${ctx}/user/logout">退出</a></span>
                    </c:if>
                </div>
            </nav>
        </div>
    </div>
</header>