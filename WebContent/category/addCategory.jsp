<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/base.css" rel="stylesheet" media="screen" />
<title>增加分类</title>
</head>
<body>
	<div id="box">
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">父类别名称:</label>
				<div class="layui-input-block">
					<select name="city" lay-verify="" id="pid">
						<option id="0">请选择父类别:可不选</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">类别名称:</label>
				<div class="layui-input-inline">
					<input type="text" name="title" lay-verify="title"
						autocomplete="off" placeholder="请输入类别名称" class="layui-input"
						id="category_name" name="category_name">
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<div class="layui-btn"  lay-submit="" lay-filter="demo1" id="sub">立即提交</div>
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
				if ($("#category_name").val() == "") {
					layer.msg("类别不能输入为空哦！", {
						icon : 2
					});
					return;
				} else {
					var str = $("#pid :selected").attr("id");
					$.post("${ctx}/addServlet",//后台地址
					{
						parent_id:str,
						category_name:$("#category_name").val()
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
				$("#category_name").val("");
			});
		});
	</script>
	<script type="text/javascript">
		function getDataList() {
			$.post("${ctx}/listCategory", {}, function(result) {
				with (result) {
					for (var i = 0; i < data.length; i++) {
						var option = "<option id="+data[i].category_id+">"
								+ data[i].category_name + "</option>";
						$("#pid").append(option);
						layui.form.render('select');
					}
				}

			}, "json");
			$.ajaxSetup({cache:false});
		}
		layui.use('form', function() {
			var form = layui.form;
			getDataList();
			form.render();
		});
	</script>

</body>
</html>