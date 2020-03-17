<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/base.css" rel="stylesheet" media="screen" />
<title>标签列表</title>
</head>
<body>
<div id="box">
	<table id="demo" lay-filter="test"></table>
</div>
</body>
<script>
	layui.use('table', function() {
		var table = layui.table;
		//开始渲染表格
		table.render({
			elem : '#demo',//要渲染的表格id
			height : 470,//表格的高度
			width : 463,
			url : '${ctx}/listTag', //数据接口
			page : true, //开启分页
			cols : [ [ //表头
			{
				field : 'tag_id',
				title : 'ID',
				width : 100,
				sort : true,
				fixed : 'left'
			},
			{
				field : 'tag_name',
				title : '类名',
				width : 180
			}, {
				title : '操作',
				toolbar : '#barDemo',
				width : 180
			} ] ]
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
				//do somehing
			} else if (layEvent === 'del') { //删除
				layer.confirm('真的删除该标签么？？', function(index) {
					obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
					layer.close(index);
					//向服务端发送删除指令
					$.post("${ctx}/delTag", {
						tag_id : data.tag_id
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
					title : ["编辑标签","text-align:center"],
					type : 2,
					offset : [ "80px" ],//位置
					area : [ "500px", "220px" ],//大小
					content : ['${ctx}/selectTag?tag_id=' + data.tag_id,"no"], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
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