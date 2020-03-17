<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/base.css" rel="stylesheet" media="screen" />
<title>管理员列表</title>
</head>
<body>
<div id="box">
	<table id="demo" lay-filter="test"></table>
</div>
</body>
<!-- 这里实现了分页效果 -->
<script>
	layui.use('table', function() {
		var table = layui.table;
		//开始渲染表格
		table.render({
			elem : '#demo',//要渲染的表格id
			height : 480,//表格的高度
			width : 635,
			url : '${ctx}/listAdm', //数据接口
			page : true, //开启分页
			cols : [ [ //表头
			{
				field : 'adm_id',
				title : 'ID',
				width : 80,
				sort : true,
				fixed : 'left'
			},
			 {
				field : 'adm_name',
				title : '管理员昵称',
				width : 160,
			},
			{
				field : 'adm_password',
				title : '管理员密码',
				width : 210
			}, {
				title : '操作',
				toolbar : '#barDemo',
				width : 180
			} ] ],
			done:function(res){
				//如果是异步请求数据方式，res即为你接口返回的信息
				console.log(res);
			}
		});
		//监听工具条
		table.on('tool(test)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象

			if (layEvent === 'detail') { //查看
				/* 
				后来可以查看父id的值
				*/
				layer.msg("你今天真好看！！", {
					icon : 1
				});
				//alert(data.adm_id);
				//alert(data.adm_password);
				//do somehing
			} else if (layEvent === 'del') { //删除
				layer.confirm('真的删除该分类么？？', function(index) {
					obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
					layer.close(index);
					//向服务端发送删除指令
					$.post("${ctx}/delAdm", {
						adm_id : data.adm_id
					}, function(result) {
						if (result) {
							layer.msg("删除成功！！", {
								icon : 1
							});
							table.reload('demo');
						}
					}, "json");
				});
				layer.close(index);
			} else if (layEvent === 'edit') { //编辑
				//弹出层
				layer.open({
					title : ["编辑管理员","text-align:center"],
					type : 2,
					offset : [ "80px" ],//位置
					area : [ "500px", "220px" ],//大小
					content : ['${ctx}/selectAdm?adm_id=' + data.adm_id,"no"], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
					anim:1,
					//当窗口关闭时执行
					end : function() {
						table.reload('demo');
					}
				});

			}
		});
	});
	
	
</script>
<!--工具条模板  -->
	<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  
</script>
</html>