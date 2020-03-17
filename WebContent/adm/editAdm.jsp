<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑管理员</title>
</head>
<body>
<!-- session由服务器产生，保存在服务器的内存中，sessionid会返回给客户端 -->
<%
	int adm_id = (int)request.getAttribute("adm_id");
	String adm_name = (String)request.getAttribute("adm_name");
	String adm_password = (String)request.getAttribute("adm_password");
%>
<form class="layui-form" action="">
<!-- hidden = "hidden"  -->
		<input value = "<%=adm_id %>" hidden = "hidden" id = "tid">
		<div class="layui-form-item">
			<label class="layui-form-label">管理员名:</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" name="title" lay-verify="title"
					autocomplete="off" placeholder="请输入管理员名称" class="layui-input"
					id="adm_name" name="adm_name"  value = "<%=adm_name%>">
					  <span id="tip" style="color:red;margin-top:20px;"></span>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">管理员密码:</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" name="title" lay-verify="title"
					autocomplete="off" placeholder="请输入管理员密码" class="layui-input"
					id="adm_password" name="adm_password"  value = "<%=adm_password%>">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<div class="layui-btn"  lay-submit="" lay-filter="demo1" id="sub">立即提交</div>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	layui.use([ 'layer' ], function() {
	var isOk = false;
	$("#adm_name").blur(function(){
		if ($("#adm_name").val() == "") {
			layer.msg("不能输入为空哦！", {
				icon : 2
			});
			return;
		}else {
				$.post('isExist', 
				{
					username : $('#adm_name').val()
				},
				function(result) { 
					//alert(typeof result);
					if(result=="true"){
						//alert(1);
						isOk = false;
						//利用jquery对元素设置样式
						$('#username').css("border", "1px solid red");
						$('#tip').html('该用户已存在');
						$('#tip').css("color", "red");
						return;
					}else{
						//alert(2);
						isOk = true;
						//利用jquery对元素设置样式
						$('#username').css("border", "1px solid gray");
						//html() 设置标签之间的 内容
						$('#tip').html('该用户名可用');
						$('#tip').css("color", "green");
					}
					
			}, "text");
		}
	});	
	$("#sub").click(function(){
		if (!isOk) {
			layer.msg("管理员名是唯一的哦！", {
				icon : 2
			});
			return;
		}else{
			$.post("${ctx}/editAdm",//后台地址
					{
						adm_password : $("#adm_password").val(),
						adm_name : $("#adm_name").val(),
						adm_id : $("#tid").val()
					},//需要提交到后台的数据
					function(result) {
						if (result) {
							//修改成功
							layer.msg("修改成功", {
								icon : 1
							});
							//假设这是iframe页
							var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
							parent.layer.close(index); //再执行关闭        
						}
					},//回调函数
					"json");
		}
		});
	});
	</script>
</body>
</html>