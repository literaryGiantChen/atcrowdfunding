<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"
         errorPage="system-error.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="/WEB-INF/include-head.jsp" %>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("button").click(function () {
                // 相当于浏览器的后退按钮
                window.history.back();
            });
        });
    </script>
    <style>
    </style>
</head>
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

    <h2 class="form-signin-heading" style="text-align: center;">
        <i class="glyphicon glyphicon-log-in"></i> 尚筹网系统消息
    </h2>
    <!--
        requestScope对应的是存放request域数据的Map
        requestScope.exception相当于request.getAttribute("exception")
        requestScope.exception.message相当于exception.getMessage()
     -->
    <h3 style="text-align: center;">${requestScope.exception.message }</h3>
    <button style="width: 150px;margin: 50px auto 0px auto;" class="btn btn-lg btn-success btn-block">点我返回上一步</button>
</div>
</body>
</html>