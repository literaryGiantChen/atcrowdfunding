<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-menu.js"></script>
<script type="text/javascript">
    $(function () {
        generateTree();
    });
</script>
<body>

<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/modal-menu-add.jsp" %>
<%@include file="/WEB-INF/modal-menu-confirm.jsp" %>
<%@include file="/WEB-INF/modal-menu-edit.jsp" %>
<script>
    $(function () {

        // 给添加子节点按钮绑定单击响应函数 出现增加的对话框
        $("#treeDemo").on("click", ".addBtn", function () {
            // 将当前节点的 id，作为新节点的 pid 保存到全局变量
            window.pid = this.id;
            // 打开模态框
            $("#menuAddModal").modal("show");
            return false;
        });

        // 执行增加子元素
        $("#menuSaveBtn").click(function () {
            let name = $("#menuAddModal [name='name']").val();
            let url = $("#menuAddModal [name='url']").val();
            let icon = $("#menuAddModal [name='icon']:checked").val();

            $.ajax({
                "url": "menu/save.json",
                "type": "post",
                "data": {
                    "pid": window.pid,
                    "name": name,
                    "url": url,
                    "icon": icon
                },
                "dataType": "json",
                "success": function (response) {
                    var resultData = response.operationResult;
                    if (resultData === "SUCCESS") {
                        layer.msg("增加元素成功！！！");

                        // 打开模态框
                        $("#menuAddModal").modal("hide");

                        // 删除完元素后，将该页面再次初始化一下
                        generateTree();
                    }
                    if (resultData === "FAILED") {
                        layer.msg(response.message);
                    }
                }
            });
        });

        // 给修改子节点按钮绑定单击响应函数 出现增加的对话框
        $("#treeDemo").on("click", ".editBtn", function () {
            // 将当前节点的 id，作为新节点的 pid 保存到全局变量
            window.id = this.id;

            // 获取 zTreeObj 对象
            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

            // 根据 id 属性查询节点对象
            // 用来搜索节点的属性名
            var key = "id";

            // 用来搜索节点的属性值
            var value = this.id;
            var currentNode = zTreeObj.getNodeByParam(key, value);

            // 打开模态框
            $("#menuEditModal").modal("show");

            // 回显表单数据
            $("#menuEditModal [name='name']").val(currentNode.name);
            $("#menuEditModal [name='url']").val(currentNode.url);

            // 回显 radio 可以这样理解：被选中的 radio 的 value 属性可以组成一个数组，
            // 然后再用这个数组设置回 radio，就能够把对应的值选中
            $("#menuEditModal [name=icon]").val([currentNode.icon]);

            return false;
        });

        // 执行修改子元素
        $("#menuEditBtn").click(function () {
            $.ajax({
                "url": "menu/update.json",
                "type": "post",
                "data": {
                    "id": window.id,
                    "name": $("#menuEditModal [name='name']").val(),
                    "url": $("#menuEditModal [name='url']").val(),
                    "icon": $("#menuEditModal [name='icon']").val()
                },
                "dataType": "json",
                "success": function (response) {
                    var resultData = response.operationResult;
                    if (resultData === "SUCCESS") {
                        layer.msg("修改元素成功！！！");

                        // 打开模态框
                        $("#menuEditModal").modal("hide");

                        // 删除完元素后，将该页面再次初始化一下
                        generateTree();
                    }
                    if (resultData === "FAILED") {
                        layer.msg(response.message);
                    }
                }
            });
        });

        // 删除功能，没有对话框，直接执行
        $("#treeDemo").on("click", ".removeBtn", function () {
            $.ajax({
                "url": "menu/remove.json",
                "type": "post",
                "data": this.id,
                "contentType": "application/json;charset=UTF-8",
                "dataType": "json",
                "success": function (response) {
                    var resultData = response.operationResult;
                    if (resultData === "SUCCESS") {
                        layer.msg("删除元素成功！！！");

                        // 删除完元素后，将该页面再次初始化一下
                        generateTree();
                    }
                    if (resultData === "FAILED") {
                        layer.msg(response.message);
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>