<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/b_register.css">
<%@include file="base.jsp"%>
<title>注册</title>
</head>
<body>
<%-- <%String  errorMsg = (String)request.getAttribute("errMsg");
						if(errorMsg == null){
							errorMsg = "";}
	%> --%>
<div id="box">
<form id="myform"  method="post">
  <div class="form-group">
    <label for="username">用户名</label>
    <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名：">
    <span id="tip" style="color:red;margin-top:20px;"></span>
  </div>
  
  <div class="form-group">
    <label for="pwd">密码</label>
    <input type="password" class="form-control" id="pwd" name="pwd" placeholder="请输入密码：">
  </div>
   <div class="form-group">
    <label for="conFirmPwd">确认密码</label>
    <input type="password" class="form-control" id="conFirmPwd" name="conFirmPwd" placeholder="请再次输入密码：">
  </div>
  <button type="button" class="btn btn-default btn_" id = "btn">提交</button>
  <span style="margin-left:250px;"></span>
  <a href="login.jsp"><button type="button" class="btn btn-default btn_">返回</button></a>
</form>
</div>
</body>
<script>
layui.use([ 'layer' ], function() {
	/* alert(0); */
	layui.use([ 'form' ], function() {
		var form = layui.form;
	});
	var isOk = false;
	//	点击提交执行的内容
	$('#username').blur(function() {
		//或者取长度
		if ($("#username").val() == "") {
			layer.msg("不能输入为空哦！", {
				icon : 2
			});
			return;
		}else {
				$.post('isExist', 
				{
					username : $('#username').val()
				},
				function(result) { 
					//alert(typeof result);
					if(result=="true"){
						//alert(1);
						isOk = false;
						//利用jquery对元素设置样式
						$('#username').css("border", "1px solid red");
						//html() 设置标签之间的 内容
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
	//点击提交，表单验证
	$('#btn').click(function() {
		if (!isOk) {
			return;
		}	
	  if($("#pwd").val() !== $("#conFirmPwd").val()){
			layer.msg("两次密码输入不同！",{
				icon : 2
			});
			return;
		}
		 //提交表单
   	   $.post("register",//后台地址
			{
				username: $("#username").val(),
					 pwd:$("#pwd").val(),
			},//需要提交到后台的数据
			function(result){
				//alert(typeof result);
				//alert(result);
				if (result) {
					//添加成功
					layer.msg("注册成功", {
						icon : 1
					});
					window.location.href = "login.jsp";
				}else{
					//alert("鬼知道为啥子出错");
					layer.msg("注册失败",{
						icon : 2
					});
					// 这里使用JQuery实现会有问题 
					document.getElementById("myform").reset();
					$('#tip').html("");
				}
			},
			"json"); 
		//清空表单
	});
});
</script>
</html>