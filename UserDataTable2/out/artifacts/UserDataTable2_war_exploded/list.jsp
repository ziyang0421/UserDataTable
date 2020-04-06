<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">

    <%--表格--%>
    <h3 style="text-align: center">用户信息列表</h3>
    <%--内联表单--%>
    <div style="float: left; margin: 5px;">
        <form class="form-inline" action="${pageContext.request.contextPath}/limit" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" id="exampleInputName2" name="name" value="${condition.name[0]}" placeholder="姓名">
            </div>
            <div class="form-group">
                <label for="exampleInputAddr2">籍贯</label>
                <input type="text" id="exampleInputAddr2" name="address" value="${condition.address[0]}" placeholder="籍贯">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">Email</label>
                <input type="text" id="exampleInputEmail2" name="email" value="${condition.email[0]}" placeholder="zs123456@example.com">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <%--添加、删除按钮--%>
    <div style="float: right; margin: 5px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a id="delSelected" class="btn btn-primary" href="javascript:void(0)">删除联系人</a>
    </div>
    <form id="idForm" action="${pageContext.request.contextPath}/deleted" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <td><input type="checkbox" id="firstCb"></td>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <%--通过El和JSTL动态添加user信息到表格--%>
            <c:forEach items="${pb.list}" var="element" varStatus="s">
                <tr class="success">
                    <td><input type="checkbox" name="uid" value="${element.id}"></td>
                    <td>${s.count}</td>
                    <td>${element.name}</td>
                    <td>${element.gender}</td>
                    <td>${element.age}</td>
                    <td>${element.address}</td>
                    <td>${element.qq}</td>
                    <td>${element.email}</td>
                    <td>
                            <%--?id=${element.id}将与键对应的id传给request--%>
                        <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/find?id=${element.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/remove?id=${element.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>


    <%--分页--%>
    <div style="text-align: center">
        <nav aria-label="Page navigation">
            <ul class="pagination">

                <c:if test="${pb.currentPage == 1}">
                    <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage != 1}">
                    <li>
                </c:if>

                    <a aria-label="Previous"
                       href="${pageContext.request.contextPath}/limit?rows=5&currentPage=${pb.currentPage-1}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <li>

                    <c:if test="${i == pb.currentPage}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/limit?rows=5&currentPage=${i}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${i != pb.currentPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/limit?rows=5&currentPage=${i}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a>
                        </li>
                    </c:if>

                    </li>
                </c:forEach>

                <c:if test="${pb.currentPage == pb.totalPage}">
                    <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage != pb.totalPage}">
                    <li>
                </c:if>

                    <a aria-label="Next"
                       href="${pageContext.request.contextPath}/limit?rows=5&currentPage=${pb.currentPage+1}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>
            </ul>
        </nav>
    </div>
</div>
<script>
    window.onload = function () {
        document.getElementById("delSelected").onclick = function () {  // 获取删除button的点击事件
            if (confirm("您确定要删除所选内容吗？")) {  // 删除之前询问
                var cbs = document.getElementsByName("uid");
                // 判断是否有选中条目
                var flag = false;
                for (var i = 0; i < cbs.length; i++) {
                    if (cbs[i].checked) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {     // 提交表单
                    document.getElementById("idForm").submit();
                }
            }
        }
        // 设置全选复选框
        document.getElementById("firstCb").onclick = function () {
            var cbs = document.getElementsByName("uid");
            for (var i = 0; i < cbs.length; i++) {
                cbs[i].checked = this.checked;
            }
        }
    }
</script>
</body>
</html>
