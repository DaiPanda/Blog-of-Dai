<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑友链</title>
</head>
<body>
<!-- session由服务器产生，保存在服务器的内存中，sessionid会返回给客户端 -->
<%
	int friend_id = (int)request.getAttribute("friend_id");
	String friend_url = (String)request.getAttribute("friend_url");
	String friend_name = (String)request.getAttribute("friend_name");
%>
<form class="layui-form" action="">
<!-- hidden = "hidden"  -->
		<input value = "<%=friend_id %>" hidden = "hidden" id = "tid">
		<div class="layui-form-item">
			<label class="layui-form-label">友链名称：</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" name="title" lay-verify="title"
					autocomplete="off" placeholder="请输入友链名称" class="layui-input"
					id="friend_name" name="friend_name"  value = "<%=friend_name%>">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">友链地址:</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" name="title" lay-verify="title"
					autocomplete="off" placeholder="请输入友链地址" class="layui-input"
					id="friend_url" name="friend_url"  value = "<%=friend_url%>">
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
	$("#sub").click(function(){
		//alert("进了click吗！！！")
		if($("#friend_name").val() == ""|| $("#friend_url").val()==""){
			layer.msg("不能输入为空哦！", {
				icon : 2
			});
			return;
		}else{
			$.post("${ctx}/editFriend",//后台地址
					{
						friend_url : $("#friend_url").val(),
						friend_name : $("#friend_name").val(),
						friend_id : $("#tid").val()
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