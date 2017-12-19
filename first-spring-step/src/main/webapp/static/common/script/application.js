//插件化写法
(function (history) {
    history.goToUrl = function (url) {
        var currentUrl = window.location.pathname + window.location.search;
        var histories = JSON.parse(sessionStorage.getItem("histories")) || [];
        //在浏览历史中将当前链接推入栈中
        histories.push(currentUrl);
        //更新缓存
        sessionStorage.setItem("histories", JSON.stringify(histories));

        if (url.indexOf("random") > 0) {//如果已有随机数的，替换随机数，保证刷新
            url = url.substring(0, url.indexOf("random"));
            url = url + "random=" + Math.random();
            window.location.href = url;
        } else {//如果没有随机数的，在后面拼接随机数，保证刷新
            if (url.indexOf("?") > 0) {
                window.location.href = url + "&random=" + Math.random();
            } else {
                window.location.href = url + "?random=" + Math.random();
            }
        }
    }

    history.back = function () {
        var histories = JSON.parse(sessionStorage.getItem("histories")) || [];
        //去除栈顶最后入栈的历史记录
        var backUrl = histories.pop();
        //更新缓存
        sessionStorage.setItem("histories", JSON.stringify(histories));

        if (backUrl.indexOf("random") > 0) {//如果已有随机数的，替换随机数，保证刷新
            backUrl = backUrl.substring(0, backUrl.indexOf("random"));
            backUrl = backUrl + "random=" + Math.random();
            window.location.href = backUrl;
        } else {//如果没有随机数的，在后面拼接随机数，保证刷新
            if (backUrl.indexOf("?") > 0) {
                window.location.href = backUrl + "&random=" + Math.random();
            } else {
                window.location.href = backUrl + "?random=" + Math.random();
            }
        }
    }
})(window.HISTORY = window.HISTORY || {});