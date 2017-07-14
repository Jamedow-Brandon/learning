//************************  ajax **********************
/**
 * get请求
 * @param geturl  发送请求地址
 * @param getdata  待发送 Key/value 参数
 * @param functionSuccess  发送成功时回调函数
 * @param functionError	 发送失败时回调函数
 * @param showData  发送成功时 是否显示返回的消息  true：显示， false：不显示
 */
function ajaxGet(geturl, getdata, functionSuccess, functionError, showData){
    $.ajax({
        type : "get",
        url : geturl,
        data : getdata,
        cache : false,
        success : function(data) {
            if(showData==false){

            }else{
                dialogShow(data);
            }
            if(functionSuccess!=null){
                functionSuccess(data);
            }
        },
        error : function() {
            dialogShow("操作失败,请刷新页面后重试");
            if(functionError!=null){
                functionError();
            }
        }
    });
}

/**
 * post请求
 * @param postUrl	发送请求地址
 * @param postData	待发送 Key/value 参数
 * @param functionSuccess	发送成功时回调函数
 * @param functionError	发送失败时回调函数
 * @param showData	发送成功时 是否显示返回的消息  true：显示， false：不显示
 */
function ajaxPost(postUrl, postData, functionSuccess, functionError, showData){
    $.ajax({
        type : "post",
        url : postUrl,
        data : postData,
        cache : false,
        success : function(data) {
            if(showData==false){

            }else{
                layer.msg(data);
            }
            if(functionSuccess!=null){
                functionSuccess(data);
            }
        },
        error : function() {
            layer.msg("操作失败,请刷新页面后重试");
            if(functionError!=null){
                functionError();
            }
        }
    });
}

/**
 *  GET请求设置所匹配元素的HTML内容
 * @param id  选择器匹配一个的元素的id
 * @param geturl	发送请求地址
 * @param showLoading   是否显示正在加载  true：显示加载（用户必须等待请求的完全响应才能继续操作）， false：不显示加载
 */
function ajaxGetHtml(id, geturl, showLoading){
    $.ajax({
        type : "get",
        url : geturl,
        cache : false,
        beforeSend : function(){
            if(showLoading){
                loadingOpen();
            }
        },
        success : function(data) {
            $("#"+id).html(data);
        },
        error : function() {
        },
        complete : function(){
            if(showLoading){
                loadingClose();
            }
        }
    });
}

/**
 * post请求设置所匹配元素的HTML内容。
 * @param id	选择器匹配一个的元素的id
 * @param geturl	发送请求地址
 * @param data	待发送 Key/value 参数
 * @param showLoading	是否显示正在加载  true：显示加载（用户必须等待请求的完全响应才能继续操作）， false：不显示加载
 */
function ajaxPostHtml(id, geturl, data, showLoading){
    $.ajax({
        type : "post",
        url : geturl,
        data:data,
        cache : false,
        beforeSend : function(){
            if(showLoading){
                loadingOpen();
            }
        },
        success : function(data) {
            $("#"+id).html(data);
        },
        error : function() {
        },
        complete : function(){
            loadingClose();
        }
    });
}


/**
 * post发送form
 * @param jqForm	form的id
 * @param postUrl	发送请求地址
 * @param functionSuccess	发送成功时回调函数
 * @param functionError	发送失败时回调函数
 * @param showData	发送成功时 是否显示返回的消息  true：显示， false：不显示
 */
function ajaxFormSubmit(jqForm, postUrl, functionSuccess, functionError, showData){
    var options = {
        url : postUrl,
        type : "post",
        cache : false,
        success : function(data) {
            if(showData==false){

            }else{
                dialogShow(data);
            }
            if(functionSuccess!=null){
                functionSuccess(data);
            }
        },
        error : function() {
            dialogShow("操作失败,请刷新页面后重试");
            if(functionError!=null){
                functionError();
            }
        },
        iframe : true
    };
    jqForm.ajaxSubmit(options);
}

/**
 * 发送JSON
 * @param postUrl	发送请求地址
 * @param postData
 * @param functionSuccess	发送成功时回调函数
 * @param functionError	发送失败时回调函数
 * @param showData	发送成功时 是否显示返回的消息  true：显示， false：不显示
 */
function ajaxPostJson(postUrl, postData, functionSuccess, functionError, showData){
    $.ajax({
        type : "post",
        url : postUrl,
        //dataType:"json",
        contentType:"application/json",
        data : postData,
        cache : false,
        success : function(data) {
            if(showData==false){

            }else{
                layer.show(data);

            }
            if(functionSuccess!=null){
                functionSuccess(data);
            }
        },
        error : function() {
            layer.show("操作失败,请刷新页面后重试");
            if(functionError!=null){
                functionError();
            }
        }
    });
}

//****************** dialog对话框 ******************************


/**
 * 打开对话框窗口（弹出层）
 * @param url	发送请求地址
 * @param title		对话框的标题
 * @param width		宽度（以像素为单位）
 * @param height	高度（以像素为单位）
 * @param showCancel	是否显示窗口的右上角的关闭按钮   true：显示， false：不显示
 */
function openDialog(url, title, width, height, showCancel){
    top.dialog({
        id:'openDialog',
        fixed: true,
        padding: 5,
        title: title,
        url: url,
        width:width,
        height:height,
        cancel: showCancel,
        cancelValue: '关闭',
        backdropOpacity:0.1
    }).showModal();
    //不显示右下角关闭按钮
    $("button[i-id='cancel']").hide();
}

/**
 * 关闭对话框窗口（弹出层）
 */
function closeDialog(){
    var openDialog = top.dialog.get('openDialog');
    if(openDialog!=null&&openDialog.open){
        openDialog.close().remove();
    }
}

/**
 * 打开正在加载（用户不能继续操作）
 */
function loadingOpen(){
    dialog({
        id:'loadingDialog',
        width: 40,
        height: 40,
        backdropOpacity:0.1
    }).showModal();
}

/**
 * 关闭正在加载
 */
function loadingClose(){
    dialogClose('loadingDialog');
}


/**
 * 提示对话框（确认删除使用！！！！）
 * （用户点“确定”继续操作，点“取消”关闭对话框）
 * @param msg   对话框的标题（如：确认删除？）
 * @param funcOk	点“确定”后的回调函数
 * @param funcCancel  点“取消”后的回调函数（可省略）
 */
function dialogConfirm(msg,functionOk,functionCancel){
    dialogConfirmBasic(msg,functionOk,functionCancel, 300);
}

/**
 * 提示对话框（2秒后自动关闭对话框）
 * @param msg  提示的消息
 */
function dialogShow(msg){
    var d = dialog({
        padding:20,
        content:msg
    });
    d.show();
    setTimeout(function(){
        d.close().remove();
    },2000);
}


/**
 * 提示对话框（用户点“确定”关闭对话框）
 * @param msg
 */
function dialogAlert(msg){
    var d = dialog({
        fixed: true,
        width: 400,
        title: '提示',
        cancelValue:'关闭',
        content: msg,
        button:[
            {
                value: '确定',
                callback:function(){
                    d.close().remove();
                },
                autofocus: true
            }
        ]
    });
    d.show();
}

function dialogCenter(){
    var uiDialog = $(window.document).find(".ui-dialog");
    $.each(uiDialog, function(i, ud){
        $(ud).parent().position({
            my: "center",
            at: "center",
            of: $("body",window.document),
            within: $("body",window.document)
        });
    });
}
//***********  华丽的分割线    （不使用）*****************************

function dialogClose(dialogId){
    var myDialog = dialog.get(dialogId);
    if(myDialog!=null&&myDialog.open){
        myDialog.close().remove();
    }
}


function dialogConfirmBasic(msg,funcOk,funcCancel,width){
    dialog({
        fixed: true,
        width:width,
        title: '提示',
        cancelValue:'关闭',
        content: msg,
        backdropOpacity:0.1,
        button:[
            {
                value: '确定',
                callback:funcOk,
                autofocus: true
            },
            {
                value: '取消',
                callback:funcCancel
            }
        ]
    }).showModal();
}

