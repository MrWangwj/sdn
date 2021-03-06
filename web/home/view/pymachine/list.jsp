<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<rapid:override name="title">
    -设备管理
</rapid:override>

<rapid:override name="content">
    <div class="layui-container" id="pymachine" url="${ pageContext.request.contextPath }/home/pymachines">

        <div class="layui-row">
            <div class="layui-col-md9">
                <h1>物理机列表</h1>
            </div>
            <div class="layui-col-md3">
                <button style="float: right" class="layui-btn" id="add_pumachine" url="${ pageContext.request.contextPath }/pymachine/AddPymachineServlet">添加</button>
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
                    <c:choose>
                        <c:when test="${ !empty  pymachineList }">
                            <c:forEach var="pymachine" items="${pymachineList }" varStatus="status">
                                <tr>
                                    <td>${pymachine.id}</td>
                                    <td>${pymachine.name}</td>
                                    <td>${pymachine.cpu}</td>
                                    <td>${pymachine.ram}</td>
                                    <td>${pymachine.power}</td>
                                    <td>
                                        <jsp:useBean id="dateValue" class="java.util.Date"/>
                                        <jsp:setProperty name="dateValue" property="time" value="${pymachine.created_at}"/>
                                        <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <a href="${ pageContext.request.contextPath }/home/pymachine/get?id=${pymachine.id}"><button class="layui-btn layui-btn-warm layui-btn-xs">查看</button></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when >
                        <c:otherwise>
                            <tr>
                                <td colspan="7"  style="text-align: center">暂无物理机，请添加！</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
            </table>
        </div>
    </div>
</rapid:override>

<rapid:override name="js">
    <script>
        function redirect(){
            var newurl=$("#pymachine").attr("url");
            window.location=newurl;
        }
        $("#power").keydown(function (e) {
            var code = parseInt(e.keyCode);
            if (code >= 96 && code <= 105 || code >= 48 && code <= 57 || code == 8) {
                return true;
            } else {
                return false;
            }
        })
        function onlyNonNegative() {
            var str=$("#power").val();

            //替换除数字与逗号以外的所有字符。
            str=str.replace(/[^0-9,]*/g,"");
            //去掉第一个逗号
            if (str.substr(0,1)==',') str=str.substr(1);
            //去掉第二个逗号
            var reg=/,$/gi;
            str=str.replace(reg,"");

            if(str>9999999){
                str=9999999;
            }
            if(str==""){
                str=0;
            }
            $("#power").val(str);
        }
        $("#add_pumachine").on("click",function () {

            var max = 0;

            <c:forEach var="pymachine" items="${ pymachineList }">
                var num = ("${pymachine.name}").replace(/[^0-9]/ig,"");
                if(num > max) max = num;
            </c:forEach>

            var name = "物理机"+(parseInt(max)+1)+"号";

            layui.use('layer', function() {
                //示范一个公告层
                layer.open({
                    type: 1,
                    title: false ,//不显示标题栏
                    closeBtn: false,
                    area: '500px;',
                    shade: 0.8,
                    id: 'LAY_layuipro', //设定一个id，防止重复弹出
                    btn: ['添加', '取消'],
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: '' +
                    '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
                    '<form class="layui-form" action="">\n' +
                    '  <div class="layui-form-item">\n' +
                    '    <label class="layui-form-label">名称</label>\n' +
                    '    <div class="layui-input-block">\n' +
                    '      <input type="text" id="name" lay-verify="title" autocomplete="off" placeholder="请输入物理机名称" value="'+name+'" class="layui-input">\n' +
                    '    </div>\n' +
                    '  </div>' +
                    '<div class="layui-form-item">' +
                    '<label class="layui-form-label">CPU(核)</label>' +
                        '<div class="layui-input-block">' +
                            '<select name="interest" lay-filter="aihao" class="layui-input" style="display:block;" id="cpu">' +
                            '<option value="2">2</option>' +
                            '<option value="4" >4</option>' +
                            '<option value="8">8</option>' +
                            '<option value="16">16</option>' +
                            '<option value="32">32</option>' +
                            '<option value="64">64</option>' +
                            '</select>' +
                        '</div>' +
                    '</div>' +
                    '<div class="layui-form-item">' +
                    '<label class="layui-form-label">内存(GB)</label>' +
                    '<div class="layui-input-block">' +
                    '<select name="interest" lay-filter="aihao" class="layui-input" style="display:block;" id="ram">' +
                    '<option value="2">2</option>' +
                    '<option value="4" >4</option>' +
                    '<option value="8">8</option>' +
                    '<option value="16">16</option>' +
                    '<option value="32">32</option>' +
                    '<option value="64">64</option>' +
                    '<option value="128">128</option>' +
                    '<option value="256">256</option>' +
                    '<option value="512">512</option>' +
                    '</select>' +
                    '</div>' +
                    '</div>' +
                    '  <div class="layui-form-item">\n' +
                    '    <label class="layui-form-label">电量(MA)</label>\n' +
                    '    <div class="layui-input-block">\n' +
                    '      <input type="number"  value="0" id="power" lay-verify="title" min="0" max="7" autocomplete="off" placeholder="请输入物理机电量" class="layui-input" onkeyup="onlyNonNegative();">\n' +
                    '    </div>\n' +
                    '  </div>' +
                    '</form>'+
                    '</div>' ,
                    success: function (layero) {
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').on('click',function () {
                            var url=$("#add_pumachine").attr("url");
                            var name=$("#name").val();
                            var cpu=$("#cpu").val();
                            var ram=$("#ram").val();
                            var power=$("#power").val();
                            $.ajax({
                                url: url,
                                data: {
                                    'name': name,
                                    'cpu' : cpu,
                                    'ram' : ram,
                                    'power' : power
                                },
                                type: 'post',
                                success: function (data, status) {
                                    if(data=="添加成功！"){
                                        layer.msg(data);
                                        setTimeout("redirect()",500);
                                    }else{
                                        layer.msg(data);
                                    }
                                },
                                fail: function (err, status) {
                                    console.log(err)
                                }
                            })
                        })
                    }
                });
            });
        });
    </script>
</rapid:override>

<%@include file="../layout/layout.jsp"%>
