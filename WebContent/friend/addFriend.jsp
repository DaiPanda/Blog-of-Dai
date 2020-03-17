<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/base.css" rel="stylesheet" media="screen" />
<title>增加友链</title>
</head>
<body>
	<div id="box">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">友链名称:</label>
				<div class="layui-input-inline">
					<input type="text" name="title" lay-verify="title"
						autocomplete="off" placeholder="请输入友链名称" class="layui-input"
						id="friend_name" name="friend_name">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">友链地址:</label>
				<div class="layui-input-inline">
					<input type="text" name="title" lay-verify="title"
						autocomplete="off" placeholder="请输入友链名称" class="layui-input"
						id="friend_url" name="friend_url">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<div class="layui-btn" lay-submit="" lay-filter="demo1" id="sub">立即提交</div>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		layui.use([ 'layer' ], function() {
			//	点击提交执行的内容
			$('#sub').click(function() {
				//或者取长度
				if ($("#friend_name").val() == ""|| $("#friend_url").val()=="") {
					layer.msg("不能输入为空哦！", {
						icon : 2
					});
					return;
				} else {
					$.post("${ctx}/addFriend",//后台地址
					{
						friend_url:$("#friend_url").val(),
						friend_name:$("#friend_name").val()
					},//需要提交到后台的数据
					function(result) {
						//alert(typeof result);
						if (result) {
							//添加成功
							layer.msg("添加成功", {
								icon : 1
							});							
						}else{
							alert("难道我是大笨蛋？")
						}
					},//回调函数
					"json");
				}
				//清空表单
				$("#friend_name").val("");
				$("#friend_url").val("");
			});
		});
	</script>
	<script type="text/javascript">
		layui.use('form', function() {
			var form = layui.form;
			form.render();
		});
	</script>

</body>
</html>