<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container">
    <div class="col-sm-12 col-md-12 oper-panel" style="padding-top: 40px;margin-right: 20px;width: 100%;">
        <form id="editForm" method="post" class="form-horizontal" >

            <div class="form-group">
                <label class="col-xs-4 control-label text-center" for="contactsName">用户名：</label>
                <div class="col-xs-7">
                    <input type="text" class="form-control validate[required,maxSize[15]]" id="contactsName" name="contactsName" value="${contacts.userName}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-4 control-label text-center" for="mobie">手机号：</label>
                <div class="col-xs-7">
                    <input type="text" class="form-control validate[required,custom[mobile]]" id="mobie" name="mobie" value="${contacts.mobie}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-4 control-label text-center" for="eMail">E-Mail：</label>
                <div class="col-xs-7">
                    <input type="text" class="form-control validate[maxSize[50],custom[email]]" id="eMail" name="eMail" value="${contacts.eMail}"/>
                </div>
            </div>
            <div class="text-center" style="margin-top: 25px;">
                <button type="button" class="btn btn-primary" id="saveBtn" onclick="saveContacts()">保存</button>
                <button type="button" class="btn btn-default" onclick="cancelEdit()">返回</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>