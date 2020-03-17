<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/base.css" rel="stylesheet" media="screen" />
<!-- 这里没有写 因为注册就是这个 实质是一样的 -->
<title>增加标签</title>
</head>
<body>
	<div id="box">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">标签名称:</label>
				<div class="layui-input-inline">
					<input type="text" name="title" lay-verify="title"
						autocomplete="off" placeholder="请输入类别名称" class="layui-input"
						id="tag_name" name="tag_name">
					<p id="tip"></p>
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
	<script>
		 layui.use([ 'layer' ], function() {
			layui.use([ 'form' ], function() {
				var form = layui.form;
			});
			var flag = false;
			//点击提交，表单验证
			$('#sub').click(function() {
				//alert("1-进入了判断是否输入了标签名的阶段");
				//1.判断是否输入了标签名
				if ($("#tag_name").val() == "") {
					layer.msg("不能输入为空哦！", {
						icon : 2
					});
					return;
				} //end 判断是否输入了标签名
				
				
				//2.判断标签名是否存在
					//alert("2-进入了判断标签名是否存在的阶段");	
					$.post('${ctx}/tagIsExist', {
						tag_name : $('#tag_name').val()
					}, function(result) {
						//alert("3-判断标签名是否存在的方法已经返回了值，返回的值为：");
						if (result) {
							//利用jquery对元素设置样式
							$('#tip').html('该标签已存在');
							$('#tip').css("color", "red");
							flag = true;
							return;
						} else {
							//利用jquery对元素设置样式
							//html() 设置标签之间的 内容
							$('#tip').html('该标签名可用');
							$('#tip').css("color", "green");
							//alert("4-进入了向后台传递数据的阶段");
							$.post("${ctx}/addTag",//后台地址
									{
										tag_name : $("#tag_name").val(),
									},//需要提交到后台的数据
									function(result) {
										if (result) {
											//alert("5-后台返回了结果，即添加成功");
											//添加成功
											layer.msg("添加成功", {
												icon : 1
											});					
										}else {
											layer.msg("添加失败", {
												icon : 2
											});
											// 这里使用JQuery实现会有问题 
											//清空表单
											document.getElementById("myform").reset();
											$('#tip').html("");
										}
									}, "json");//end 第二个$.post */						
						}
					}, "json");// end 第一个$.post
			});// end click方法
		});// end layui的form组件 
	</script>

</body>
</html>