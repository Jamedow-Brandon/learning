//插件化写法
(function (history) {
    history.goToUrl = function (url) {
        var currentUrl = window.location.pathname + "?" + window.location.search;
        
    }
})(window.HISTORY = window.HISTORY || {});