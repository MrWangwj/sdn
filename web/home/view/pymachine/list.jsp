<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<rapid:override name="title">
    -设备管理
</rapid:override>

<rapid:override name="content">
    <div class="layui-container">

        <div class="layui-row">
            <div class="layui-col-md9">
                <h1>物理机列表</h1>
            </div>
            <div class="layui-col-md3">
                <button style="float: right" class="layui-btn">添加</button>
            </div>
        </div>

        <div class="layui-row">
            <table class="layui-table">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>CPU(核)</th>
                    <th>RAM(GB)</th>
                    <th>电量(MA)</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>物理机1号</td>
                    <td>64</td>
                    <td>100</td>
                    <td>1000</td>
                    <td>2017-12-11</td>
                    <td>
                        <button class="layui-btn layui-btn-warm layui-btn-xs">查看</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</rapid:override>

<%@include file="../layout/layout.jsp"%>
