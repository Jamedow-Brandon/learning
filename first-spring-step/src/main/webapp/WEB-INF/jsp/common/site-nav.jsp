<%@page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="site-header">
        <div class="container">
            <nav>
                <div class="logo-area">
                    <h1>
                        <a href="//www.laodou.site">老豆坊<img class="icon" src="${ctx}/static/img/logo.png"
                                                            alt="logo"/><img class="logo" src="${ctx}/static/img/H1.png"
                                                                             alt="店招"/></a>
                    </h1>
                    <h2>
                        <a href="//www.laodou.site">老豆坊</a>
                    </h2>
                </div>
                <div class="search-field">
                    <div class="search-box-input">
                        <input type="text"/>
                    </div>
                    <button class="btn-search">搜索</button>
                </div>

                <div class="user">
                    <%--<span><a href="#2">登录</a>|<a href="#2">注册</a></span>--%>
                    <img class="photo"
                         src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWQlEJLSCnHKoJZJhoiciw4YRoazrkKrh-IpRLwo2L4bOcUnoEUg"
                         alt="头像">
                    <span>个人中心</span>
                </div>
            </nav>
        </div>
    </div>
</header>