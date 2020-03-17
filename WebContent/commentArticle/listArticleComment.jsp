<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/base.css" rel="stylesheet" media="screen" />
<title>评论列表</title>
<%
	int article_id = Integer.parseInt(request.getParameter("article_id"));
    HttpSession se = request.getSession();
    se.setAttribute("article_id", article_id);
%>
</head>
<body>
	<div class="demoTable" style="margin-top: 20px; margin-left: 50px;">
		<div class="layui-inline" style="width: 200px;">
			<input class="layui-input" name="id" id="demoReload"
				autocomplete="off">
		</div>
		<button class="layui-btn" data-type="reload" id="searchReload">搜索</button>
	</div>
	<div id="box" style="height:390px;">
		<table id="demo" lay-filter="test"></table>
	</div>
</body>
 <script>
	layui
			.use(
					'table',
					function() {
						var table = layui.table;
						//开始渲染表格
						table
								.render({
									elem : '#demo',//要渲染的表格id
									height : 320,//表格的高度
									/*  width : 886, */
									url : '${ctx}/listComment2', //数据接口
									page : true, //开启分页
									limit:5,
									limits:[5],
									cols : [ [ //表头
											{
												field : 'comment_id',
												title : 'ID',
												width : 80,
												sort : true,
												fixed : 'left'
											},
											{
												field : 'comment_name',
												title : '用户昵称',
												width : 110
											},
											{
												field : 'parent_id',
												title : '父评论id',
												width : 90
											},
											{
												field : 'comment_content',
												title : '评论内容',
												width : 190
											},
											/*
											数据库中有这个字段，但我暂时不显示出来，也不做处理
											{
												field : 'comment_email',
												title : '评论邮箱',
												width : 120
											}, */
											{
												field : 'comment_likenum',
												title : '点赞数',
												width : 100,
												sort : true
											},
											{
												field : 'comment_createtime',
												title : '评论日期',
												width : 170,
												templet : '<div>{{Format(d.comment_createtime,"yyyy-MM-dd h:m:s")}}</div>'
											}, {
												field : 'article_id',
												title : '博文id',
												width : 80
											}, {
												title : '操作',
												toolbar : '#barDemo',
												width : 120
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
							} else if (layEvent === 'del') { //删除
								layer.confirm('真的删除该评论么？？', function(index) {
									obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
									layer.close(index);
									//向服务端发送删除指令
									$.post("${ctx}/delComment2", {
										comment_id : data.comment_id
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
							} /* 
													按照常理而言，用户的评论应该不可以编辑，所以这里的编辑功能暂时不需要
													else if (layEvent === 'edit') { //编辑
																//弹出层
																layer.open({
																	title : ["编辑分类","text-align:center"],
																	type : 2,
																	offset : [ "80px" ],//位置
																	area : [ "500px", "220px" ],//大小
																	content : ['${ctx}/selectServlet?category_id=' + data.category_id,"no"], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
																	anim:1,
																	//当窗口关闭时执行
																	end : function() {
																		table.reload('demo');
																	}
																});

															} */
						});
						//搜索重载
						$("#searchReload").click(
								function() {
									var comment_content = $("#demoReload")
											.val();
									//执行重载
									table.reload('demo', {
										url : '${ctx}/getArticleComment',
										methods : 'post',
										page : {
											curr : 1
										//重新从第 1 页开始
										},
										where : {
											comment_content : comment_content
										}
									}, 'data');
								});
					});

	/* 时间格式处理 */
	function Format(datetime, fmt) {
		if (parseInt(datetime) == datetime) {
			if (datetime.length == 10) {
				datetime = parseInt(datetime) * 1000;
			} else if (datetime.length == 13) {
				datetime = parseInt(datetime);
			}
		}
		datetime = new Date(datetime);
		var o = {
			"M+" : datetime.getMonth() + 1, //月份   
			"d+" : datetime.getDate(), //日   
			"h+" : datetime.getHours(), //小时   
			"m+" : datetime.getMinutes(), //分   
			"s+" : datetime.getSeconds(), //秒   
			"q+" : Math.floor((datetime.getMonth() + 3) / 3),
			"S" : datetime.getMilliseconds()
		//毫秒   
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
</script>
<!--  
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a> -->
<!--工具条模板  -->
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>  
</script>
</html>