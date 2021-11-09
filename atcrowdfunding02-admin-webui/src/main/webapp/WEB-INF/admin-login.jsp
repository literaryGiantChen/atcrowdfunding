<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div>
                <a class="navbar-brand" href="" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
            </div>
        </div>
    </div>
</nav>

<div class="container">

    <form action="admin/do/admin-login.html" method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading">
            <i class="glyphicon glyphicon-log-in"></i> 管理员登录
        </h2>
        <p>${requestScope.exception.message}</p>
        <div class="form-group has-success has-feedback">
            <input type="text" name="loginAcct" class="form-control" id="inputSuccess4"
                   placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" name="userPswd" class="form-control" id="inputSuccess4"
                   placeholder="请输入登录密码" style="margin-top: 10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <button type="submit" class="btn btn-lg btn-success btn-block">登录</button>
    </form>
</div>
<div>
    <button>
        <a href="ssm/all/not_exception.html">点击一下,没有异常</a>
    </button>
    <button>
        <a href="ssm/all/arithmetic_exception.html">点击一下,触发算数异常</a>
    </button>
</div>
</body>
</html>
