<%@page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${ctx}/static/jquery/jquery-3.1.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script type="application/javascript" src="${ctx}/static/script/application.js"></script>
<script>
    document.body.addEventListener('touchend', function () {
    });

    (function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                if (1300>=clientWidth&&clientWidth >= 640) {
                    docEl.style.fontSize = '13px';
                } else if(clientWidth > 1300){
                    docEl.style.fontSize = '15px';
                } else{
                    docEl.style.fontSize = 24 * (clientWidth / 640) + 'px';

                }
            };

        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);
</script>