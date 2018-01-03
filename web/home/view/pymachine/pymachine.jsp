<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<rapid:override name="css">
</rapid:override>

<rapid:override name="title">
    -设备管理
</rapid:override>

<rapid:override name="content">
    <div class="layui-container">

        <div class="layui-row">
            <div class="layui-col-md3">

                <div class="layui-form-item">
                    <label class="layui-form-label">物理机名称</label>
                    <div class="layui-input-block">
                        <input type="text" placeholder="请输入物理机名称" autocomplete="off" class="layui-input" value="${ pymachine.name }">
                    </div>
                </div>


            </div>
            <div class="layui-col-md3">
                <div class="layui-form-item">
                    <label class="layui-form-label">物理机内核数</label>
                    <div class="layui-input-block">
                        <input type="number" placeholder="请输入物理机CPU内核数" autocomplete="off" class="layui-input" value="${ pymachine.cpu }">
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-col-md3">

                <div class="layui-form-item">
                    <label class="layui-form-label">物理机RAM</label>
                    <div class="layui-input-block">
                        <input type="number" placeholder="请输入物理机内存" autocomplete="off" class="layui-input" value="${ pymachine.ram }">
                    </div>
                </div>


            </div>
            <div class="layui-col-md3">
                <div class="layui-form-item">
                    <label class="layui-form-label">物理机电量</label>
                    <div class="layui-input-block">
                        <input type="number" placeholder="请输入物理机电量" autocomplete="off" class="layui-input" value="${ pymachine.power }">
                    </div>
                </div>
            </div>

            <div class="layui-col-md3">
                <button class="layui-btn layui-btn-normal" data-id="${ pymachine.id }" style="margin-left: 20px;">保存修改</button>
            </div>
        </div>


        <div class="layui-row">
            <div class="layui-col-md9">
                <h1>虚拟机列表</h1>
            </div>
            <div class="layui-col-md3">
                <button style="float: right" class="layui-btn" data-toggle="modal" data-target="#addVrmachineModal">添加</button>
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
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="vrmachine" items="${ vrmachines }">
                    <tr>
                        <td>${ vrmachine.id }</td>
                        <td>${ vrmachine.name }</td>
                        <td>${ vrmachine.cpu }</td>
                        <td>${ vrmachine.ram }</td>
                        <td>${ vrmachine.power }</td>
                        <td>${ vrmachine.created_at }</td>
                        <td>

                            <select>
                                <option value="0" <c:if test="${ vrmachine.status == 0 }">selected = "selected"</c:if>>关闭</option>
                                <option value="1" <c:if test="${ vrmachine.status == 1 }">selected = "selected"</c:if>>休眠</option>
                                <option value="2" <c:if test="${ vrmachine.status == 2 }">selected = "selected"</c:if>>激活</option>
                            </select>
                        </td>
                        <td>
                            <button class="layui-btn layui-btn-normal layui-btn-xs">编辑</button>
                            <button class="layui-btn layui-btn-danger layui-btn-xs"><a href="${pageContext.request.contextPath }/DeleteServlet?id=1">删除</a></button>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>

        <div class="layui-row">
            <div id="cpuChart" style="width: 800px;height:400px;"></div>
        </div>

        <div class="layui-row">
            <div id="ramChart" style="width: 800px;height:400px;"></div>
        </div>

        <div class="layui-row">
            <div id="powerChart" style="width: 800px;height:400px;"></div>
        </div>

    </div>


</rapid:override>

<rapid:override name="js">
    <script src="${ pageContext.request.contextPath }/home/vender/layui/layui/lay/modules/layer.js"></script>
    <script src="${ pageContext.request.contextPath }/home/vender/echarts/echarts.min.js"></script>
    <script>
        window.onload = function () {
            var  vrmachineNames = [];
            var cpuChartDate = [],ramChartDate = [],powerChartDate = [];
            var restCpu = ${ pymachine.cpu }, restRam = ${ pymachine.ram },restPower = ${ pymachine.power } ;

            <c:forEach var="vrmachine" items="${ vrmachines }">
                vrmachineNames.push("${ vrmachine.name }");
                cpuChartDate.push({value: ${ vrmachine.cpu }, name: "${ vrmachine.name }"});
                ramChartDate.push({value: ${ vrmachine.ram }, name: "${ vrmachine.name }"});
                powerChartDate.push({value: ${ vrmachine.power }, name: "${ vrmachine.name }"});

                restCpu -= ${ vrmachine.cpu };
                restRam -= ${ vrmachine.ram };
                restPower -= ${ vrmachine.power };

            </c:forEach>

            vrmachineNames.push("剩余");
            cpuChartDate.push({value: restCpu, name: "剩余"});
            ramChartDate.push({value: restRam, name: "剩余"});
            powerChartDate.push({value: restPower, name: "剩余"});

            var cpuOption = {
                    title : {
                        text: 'CPU占用比例图'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        top: 40,
                        orient: 'vertical',
                        left: 'right',
                        data: vrmachineNames
                    },
                    series : [
                        {
                            name: '机器名称',
                            type: 'pie',
                            data: cpuChartDate
                        }
                    ]
                },
                ramOption = {
                    title : {
                        text: 'RAM占用比例图'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        top: 40,
                        orient: 'vertical',
                        left: 'right',
                        data: vrmachineNames
                    },
                    series : [
                        {
                            name: '机器名称',
                            type: 'pie',
                            data:ramChartDate
                        }
                    ]
                },
                powerOption = {
                    title : {
                        text: '电量占用比例'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        top: 40,
                        orient: 'vertical',
                        left: 'right',
                        data: vrmachineNames
                    },
                    series : [
                        {
                            name: '机器名称',
                            type: 'pie',
                            data: powerChartDate
                        }
                    ]
                };

            var cpuChart = echarts.init(document.getElementById('cpuChart')),
                ramChart = echarts.init(document.getElementById('ramChart')),
                powerChart = echarts.init(document.getElementById('powerChart'));

            cpuChart.setOption(cpuOption);
            ramChart.setOption(ramOption);
            powerChart.setOption(powerOption);


        };
    </script>
</rapid:override>

<%@include file="../layout/layout.jsp"%>
