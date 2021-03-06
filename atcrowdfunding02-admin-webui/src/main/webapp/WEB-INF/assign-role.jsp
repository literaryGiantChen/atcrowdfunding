<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>

<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/get/page.html">首页</a></li>
                <li>
                    <a href="admin/get/page.html?adminId=${param.adminId}&pageNum=${param.pageNum}&keyword=${param.keyword}">数据列表</a>
                </li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/role/assign.html" method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select class="form-control" multiple="multiple" size="10"
                                    style="width:120px; overflow-y:auto;">
                                <c:forEach var="assigned" items="${requestScope.unAssignedRoleList}">
                                    <option value="${assigned.id}">${assigned.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select name="roleIdList" class="form-control" multiple="multiple" size="10"
                                    style="width:120px; overflow-y:auto;">
                                <c:forEach var="assigned" items="${requestScope.assignedRoleList}">
                                    <option value="${assigned.id}">${assigned.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="submitBtn" style="width: 120px; margin-left: 248px; margin-top: 10px;" type="submit"
                                class="btn btn-lg btn-success btn-block">保存
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#toRightBtn").click(function () {
            // select 是标签选择器
            // :eq(0)表示选择页面上的第一个
            // :eq(1)表示选择页面上的第二个
            // “>”表示选择子元素
            // :selected 表示选择“被选中的”option
            // appendTo()能够将 jQuery 对象追加到指定的位置
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
        });

        $("#toLeftBtn").click(function () {
            // select 是标签选择器
            // :eq(0)表示选择页面上的第一个
            // :eq(1)表示选择页面上的第二个
            // “>”表示选择子元素
            // :selected 表示选择“被选中的”option
            // appendTo()能够将 jQuery 对象追加到指定的位置
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");
        });

        $("#submitBtn").click(function () {
            // 在提交表单前把“已分配”部分的 option 全部选中
            $("select:eq(1)>option").prop("selected", "selected");
        });
    });
</script>
</body>
</html>
