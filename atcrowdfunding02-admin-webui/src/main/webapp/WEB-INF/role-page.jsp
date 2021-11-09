<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="glyphicon glyphicon-th"></i> 数据列表
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float: left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger"
                            style="float: right; margin-left: 10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button
                            type="button"
                            id="showAddModalBtn" class="btn btn-primary"
                            style="float: right;">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody">

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/modal-role-add.jsp" %>
<%@include file="/WEB-INF/modal-role-edit.jsp" %>
<%@include file="/WEB-INF/modal-role-confirm.jsp" %>
<%@include file="/WEB-INF/modal-role-assign-auth.jsp" %>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js" charset="utf-8"></script>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-role.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function () {
        // 1.为分页操作准备初始化数据
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";

        // 2.调用执行分页的函数，显示分页效果
        generatePage();

        // 3.给查询按钮绑定单击响应函数
        $("#searchBtn").click(function () {
            window.keyword = $("#keywordInput").val();
            generatePage();
        });

        // 4.点击新增按钮打开模态框
        $("#showAddModalBtn").click(function () {
            // 清理模态框
            $("#addModal [name=roleName]").val("");
            $("#addModal").modal("show");
        });

        // 5.给新增模态框中的保存按钮绑定单击响应函数
        $("#saveRoleBtn").click(function () {
            // 获取表单中的角色名称
            let roleName = $.trim($("#addModal [name='roleName']").val());

            // 通过ajax增加角色
            $.ajax({
                "url": "role/save.json",
                "type": "post",
                "data": {
                    "name": roleName
                },
                // 前端请求传Json对象 则后端使用@RequestParam
                // 前端请求传Json对象的字符串 则后端使用@RequestBody
                // JSON.stringify(data)的方式就能将对象变成json字符串
                "dataType": "json",
                "success": function (resultData) {
                    var result = resultData.operationResult;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功！");

                        // 关闭模态框
                        $("#addModal").modal("hide");

                        // 将页码定位到最后一页
                        window.pageNum = 99999999;
                        // 重新加载分页数据
                        generatePage();
                    }
                    if (result == "FAILED") {
                        layer.msg("操作失败！" + resultData.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }
            });
        });

        // 6.给页面上的“铅笔”按钮绑定单击响应函数，目的是打开模态框
        $("#rolePageBody").on("click", ".pencilBtn", function () {
            // rolePageBody 是当前的表格
            $("#editModal").modal("show");

            // 当前行的角色名称 在最后元素的上一级元素中
            let roleName = $(this).parent().prev().text();

            // 获取当前表格的ID 方便修改角色名称数据
            window.roleId = this.id;

            // 将角色名称 传到要修改的表单中
            $(".modal-open [name='roleName']").val(roleName);
        });

        // 7.给更新模态框中的更新按钮绑定单击响应函数
        $("#updateRoleBtn").click(function () {
            let roleName = $("#editModal [name='roleName']").val();
            // 通过ajax修改角色
            $.ajax({
                "url": "role/update.json",
                "type": "post",
                "data": {
                    "id": window.roleId,
                    "name": roleName
                },
                // 前端请求传Json对象 则后端使用@RequestParam
                // 前端请求传Json对象的字符串 则后端使用@RequestBody
                // JSON.stringify(data)的方式就能将对象变成json字符串
                "dataType": "json",
                "success": function (resultData) {
                    var result = resultData.operationResult;
                    if (result != "SUCCESS") {
                    } else {
                        layer.msg("操作成功！");

                        // 关闭模态框
                        $("#editModal").modal("hide");

                        // 重新加载分页数据
                        generatePage();
                    }
                    if (result == "FAILED") {
                        layer.msg("操作失败！" + resultData.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }
            });
        });

        // 8.给单条删除按钮绑定单击响应函数
        $("#rolePageBody").on("click", ".removeBtn", function () {

            // 从当前按钮出发获取角色名称
            var roleName = $(this).parent().prev().text();

            // 创建role对象存入数组
            var roleArray = [{
                roleId: this.id,
                roleName: roleName
            }];

            // 调用专门的函数打开模态框
            showConfirmModal(roleArray);
        });

        // 9.点击确认模态框中的确认删除按钮执行删除
        $("#removeRoleBtn").click(function () {
            // 从全局变量范围获取roleIdArray，转换为JSON字符串
            var requestBody = JSON.stringify(window.roleIdArray);
            console.log(requestBody);

            $.ajax({
                "url": "role/remove/by/role/id/array.json",
                "type": "post",
                "data": requestBody,
                "contentType": "application/json;charset=UTF-8",
                "dataType": "json",
                "success": function (response) {
                    var result = response.operationResult;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功！");

                        // 重新加载分页数据
                        generatePage();
                    }
                    if (result == "FAILED") {
                        layer.msg("操作失败！" + response.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }
            });

            // 关闭模态框
            $("#confirmModal").modal("hide");

            // false = 取消勾选 true = 勾选
            $("#summaryBox").attr("checked", false);
        });

        // 10.给总的checkbox绑定单击响应函数
        $("#summaryBox").click(function () {
            // ①获取当前多选框自身的状态
            var currentStatus = this.checked;

            // ②用当前多选框的状态设置其他多选框
            $(".itemBox").prop("checked", currentStatus);

        });

        // 11.全选、全不选的反向操作
        $("#rolePageBody").on("click", ".itemBox", function () {
            // 获取当前已经选中的.itemBox的数量
            var checkedBoxCount = $(".itemBox:checked").length;

            // 获取全部.itemBox的数量
            var totalBoxCount = $(".itemBox").length;

            // 使用二者的比较结果设置总的checkbox
            $("#summaryBox").prop("checked", checkedBoxCount == totalBoxCount);
        });

        // 12.给批量删除的按钮绑定单击响应函数
        $("#batchRemoveBtn").click(function () {
            // 创建一个数组对象用来存放后面获取到的角色对象
            var roleArray = [];
            // 遍历当前选中的多选框
            $(".itemBox:checked").each(function () {
                // 使用this引用当前遍历得到的多选框
                var roleId = this.id;
                // 通过DOM操作获取角色名称
                var roleName = $(this).parent().next().text();
                roleArray.push({
                    "roleId": roleId,
                    "roleName": roleName
                });
            });
            // 检查roleArray的长度是否为0
            if (roleArray.length == 0) {
                layer.msg("请至少选择一个执行删除");
                return;
            }
            // 调用专门的函数打开模态框
            showConfirmModal(roleArray);
        });


        // 13.给分配权限按钮绑定单击响应函数
        $("#rolePageBody").on("click", ".checkBtn", function () {
            // 打开动态框
            $("#assignModal").modal("show");

            window.roleId = this.id;

            // 在模态框中装载树 Auth 的形结构数据
            fillAuthTree();
        });

        // 14.给分配权限模态框中的“分配”按钮绑定单击响应函数
        $("#assignBtn").click(function () {
            // ①收集树形结构的各个节点中被勾选的节点
            // [1]声明一个专门的数组存放 id
            var authIdArray = [];

            // [2]获取 zTreeObj 对象
            var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

            // [3]获取全部被勾选的节点
            var checkedNodes = zTreeObj.getCheckedNodes();

            // [4]遍历 checkedNodes
            for (var i = 0; i < checkedNodes.length; i++) {
                var authId = checkedNodes[i].id;
                authIdArray.push(authId);
            }

            // ②发送请求执行分配
            var requestBody = {
                "authIdArray": authIdArray,
                // 为了服务器端 handler 方法能够统一使用 List<Integer>方式接收数据，roleId 也存 入数组
                "roleId": [window.roleId]
            };
            requestBody = JSON.stringify(requestBody);
            console.log(requestBody);

            $.ajax({
                "url": "assign/do/role/assign/auth.json",
                "type": "post",
                "data": requestBody,
                "contentType": "application/json;charset=UTF-8",
                "dataType": "json",
                "success": function (response) {
                    var result = response.operationResult;
                    if (result == "SUCCESS") {
                        layer.msg("操作成功！");

                        // 关闭动态框
                        $("#assignModal").modal("hide");
                    }
                    if (result == "FAILED") {
                        layer.msg("操作失败！" + response.message);
                    }
                },
                "error": function (response) {
                    layer.msg(response.status + " " + response.statusText);
                }
            });
        });
    });
</script>
</body>
</html>