<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>
        SDN水下传感器节点虚拟化管理系统
        <rapid:block name="title">

        </rapid:block>
    </title>
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/home/vender/layui/layui/css/layui.css">
    <rapid:block name="css">

    </rapid:block>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">SDN管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    admin
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">退出登录</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item"><a href="${ pageContext.request.contextPath }/home/view/pymachine/list.jsp">设备管理</a></li>

                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">test</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">1111</a></dd>
                    </dl>
                </li>

            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <rapid:block name="content">
                主要内容
            </rapid:block>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © <a href="http://www.marchsoft.cn">三月软件</a>
    </div>
</div>
<script src="${ pageContext.request.contextPath }/home/vender/jquery/jquery-3.2.1.min.js"></script>
<script src="${ pageContext.request.contextPath }/home/vender/layui/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });
</script>

<rapid:block name="js">

</rapid:block>
</body>

<rapid:block name="msgmodal">

</rapid:block>
</html>