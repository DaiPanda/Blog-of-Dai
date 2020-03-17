<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑标签</title>
</head>
<body>
<!-- session由服务器产生，保存在服务器的内存中，sessionid会返回给客户端 -->
<%
	int tag_id = (int)request.getAttribute("tag_id");
	String tag_name = (String)request.getAttribute("tag_name");
%>
<form class="layui-form" action="">
<!-- hidden = "hidden"  -->
		<input value = "<%=tag_id %>" hidden = "hidden" id = "tid">
		<div class="layui-form-item">
			<label class="layui-form-label">标签名:</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" name="title" lay-verify="title"
					autocomplete="off" placeholder="请输入标签名称" class="layui-input"
					id="tag_name" name="tag_name"  value = "<%=tag_name%>">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<div class="layui-btn" lay-submit="" lay-filter="demo1" id="sub">立即提交</div>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	layui.use([ 'layer' ], function() {
	var isOk = false;
	$("#sub").click(function(){
		if ($("#tag_name").val() == "") {
			layer.msg("不能输入为空哦！", {
				icon : 2
			});
			return;
		}else{
			$.post("${ctx}/EditTag",//后台地址
					{
						tag_name : $("#tag_name").val(),
						tag_id : $("#tid").val()
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