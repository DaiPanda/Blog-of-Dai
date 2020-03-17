<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/b_register.css">
<%@include file="base.jsp"%>
<title>登录</title>
</head>
<body>
	<%String  errorMsg = (String)request.getAttribute("errMsg");
						if(errorMsg == null){
							errorMsg = "";}
	%>
	<div id="box">
	<!--  action="${ctx}/login" -->
		<form id="myform" method="post" action="${ctx}/login">
			<div class="form-group">
				<label for="username">用户名</label> <input type="text"
					class="form-control" id="username" name="username"
					placeholder="请输入用户名："><span id="tip" style="color:red;margin-top:20px;"><%=errorMsg%></span>
				
			</div>

			<div class="form-group">
				<label for="pwd">密码</label> <input type="password"
					class="form-control" id="pwd" name="pwd" placeholder="请输入密码：">
			</div>

			<button type="submit" class="btn btn-default btn_" id="btn">确定</button>
			<span style="margin-left:250px;"></span>
			<a href="register.jsp"><button type="button" class="btn btn-default btn_">注册</button></a>
		</form>
	</div>
	<script type="text/javascript">
		
		layui.use([ 'layer' ], function() {
			layui.use([ 'form' ], function() {
				var form = layui.form;
			});
			//	点击提交执行的内容
			$('#username').blur(function() {
				//或者取长度
				if ($("#username").val() == "") {
					layer.msg("用户名不能输入为空哦！", {
						icon : 2
					});
					return;
				} 
			});
			//点击提交，表单验证
			$('#btn').click(function() {
			 if ($("#pwd").val() === "") {
					layer.msg("密码不能输入为空哦！", {
						icon : 2
					});
					return;
			}
			}); 
			/* if($("#tip")!=null){
				$("#myform")[0].reset();
				layer.msg("您的用户名或密码有误哦！", {
					icon : 2
				});
			}	 */
		});
	</script>
</body>
</html>