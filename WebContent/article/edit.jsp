<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑博文</title>
<%
	int article_id = (int)request.getAttribute("article_id");
	String article_content = (String)request.getAttribute("article_content");
	String article_desc = (String)request.getAttribute("article_desc");
	String article_image = (String)request.getAttribute("article_image");
	int article_isTop = (int)request.getAttribute("article_isTop");
	String article_title = (String)request.getAttribute("article_title");
	String category_name = (String)request.getAttribute("category_name");
	int tag1 = (int)request.getAttribute("tag1");
	int tag2 = (int)request.getAttribute("tag2");
	int tag3 = (int)request.getAttribute("tag3");	
	String parent_name = (String)request.getAttribute("parent_name");
%>
<style type="text/css">
#box {
	width: 1000px;
	margin-left: auto;
	margin-right: auto;
	/*background-image: url(../css/image/18.gif);  */
	padding: 20px;
	opacity: 0.8;
}

.layui-form-select dl {
	z-index: 1999;
}
</style>
</head>
<body>
	<div id="box">
	<input value = "<%=article_id %>" hidden = "hidden" id = "tid">
	<input value = "<%=tag1 %>" hidden = "hidden" id = "tag_1">
	<input value = "<%=tag2 %>" hidden = "hidden" id = "tag_2">
	<input value = "<%=tag3 %>" hidden = "hidden" id = "tag_3">
	<input value = "<%=article_isTop %>" hidden = "hidden" id = "article_isTop">
	
		<form class="layui-form" action="" enctype="multipart/form-data"
			id="myform">
			<h3 style="text-align:center;margin-bottom:20px;text-shadow:1px 1px 2px red;">编辑博文</h3>
			<div class="layui-form-item">
				<label class="layui-form-label">文章标题</label>
				<div class="layui-input-block" style="width: 420px;">
					<input type="text" name="title" required lay-verify="required"
						placeholder="请输入标题" autocomplete="off" class="layui-input"
						id="article_title" value=<%=article_title%>>
				</div>
			</div>

			<div class="layui-form-item" style="z-index: 1999px;">
				<label class="layui-form-label">文章分类</label>
				<div class="layui-input-inline" style="width: 180px;">
					<select name="first_category" lay-verify="required"
						id="first_category" lay-filter="myFirstSelect">
						<option value=""></option>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 180px;">
					<select name="second_category" lay-verify="required"
						id="second_category" lay-filter="mySecondSelect">

					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">置顶</label>
				<div class="layui-input-block">
					<input type="radio" name="isTop" value="是" title="是" id="isT"> <input
						type="radio" name="isTop" value="否" title="否" checked>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<!-- 在这里放一个img标签 默认不显示 -->
					<button type="button" class="layui-btn" id="test1">
						<i class="layui-icon">&#xe67c;</i>上传封面图
					</button>
					<!--  style="display:none;" -->
					<div style="height: 20px; width: 100%;"></div>
					<img id="article_image" class="article_image" src="<%=article_image %>"
						style=" margin-top: 20px;width:135px;height:180px;"></img>
				</div>
			</div>
			<div  style="display:none;">
				<input type="hidden" name="article_title" id="content" 
						value="<%=article_content %>"/>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章内容</label>
				<div class="layui-input-block">			
					<div id="editor" name="article_content"
						style="width: 900px; height: 400px;">
						
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">标签</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="tag1" id="tag1" lay-verify="" lay-search>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="tag2" id="tag2" lay-verify="" lay-search>
						<option id="0">可不选</option>
					</select>
				</div>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="tag3" id="tag3" lay-verify="" lay-search>
						<option id="0">可不选</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">博文简介</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" class="layui-textarea"
						id="article_desc" ></textarea>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top: 50px;">
				<div class="layui-input-block">
					<button class="layui-btn" type="button" lay-submit
						lay-filter="formDemo">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary"
						style="margin-left: 12%;">重置</button>
				</div>
			</div>
		</form>


	</div>
</body>
<script>
	function getFirstCategory() {
		$("#article_desc").val("<%=article_desc%>");
		$.post("${ctx}/getFirstCategory", {}, function(result) {
			with (result) {
				for (var i = 0; i < data.length; i++) {
					var option = "<option id="+data[i].category_id+">"
							+ data[i].category_name + "</option>";
					$("#first_category").append(option);
					layui.form.render('select');
				}
			}
			//当数据加载完毕后，调用trigger方法触发选中事件
			

		}, "json");
		$.ajaxSetup({
			cache : false
		});
	}
	function getFirstTag() {
		$.post("${ctx}/getFirstTag", {}, function(result) {
			with (result) {
				for (var i = 0; i < data.length; i++) {
					var option = "<option id="+data[i].tag_id+">"
							+ data[i].tag_name + "</option>";
					$("#tag1").append(option);
					$("#tag2").append(option);
					$("#tag3").append(option);
					layui.form.render('select');
				}
			}
		}, "json");
		$.ajaxSetup({
			cache : false
		});
	}
 	function getSelectedCategory(){		
 		setTimeout(function () {
 			  $('select[name="first_category"]').next().find('.layui-anim').children('dd[lay-value="<%=parent_name%>"]').click();
 			  $('select[name="second_category"]').next().find('.layui-anim').children('dd[lay-value="<%=category_name%>"]').click();
 	           
		},500);
		 layui.form.render('select');
	} 
	function getSelectedTag(){
		//遍历 将某个置为选中状态
		$('#tag1 option').each(function(){
			 if($(this).attr('id')==<%=tag1%>){
				$(this).attr("selected",true);
			}
		});
		$('#tag2 option').each(function(){
			if($(this).attr('id')==<%=tag2%>){
				$(this).attr("selected",true);
			}
		});
		$('#tag3 option').each(function(){
			if($(this).attr('id')==<%=tag3%>){
				$(this).attr("selected",true);
			}
		});
		layui.form.render('select');
	}
	//获取默认的状态
	function getSelectedState(){
		//alert(1);
		var article_isTop =$("#article_isTop").val();
		if(article_isTop!=0){
			//alert(111111);
			$("#isT").attr("checked",true);
			layui.form.render();
		}
	}
	layui.use('form', function() {
		//就是这行代码  搞定了tag默认选中非要选择的
		 $.ajaxSettings.async = false; 
		//1.初始化form表单
		var form = layui.form;
		//2.初始化富文本编辑器
		var ue = window.UE.getEditor('editor');
		ue.ready(function(){
			//alert($("#content").val());
			ue.setContent('<%=article_content%>');
		});
		//3.获得一级分类
		getFirstCategory();
		//补充之获得一级标签（最少选择一个，最多选择三个）
		getFirstTag();
		getSelectedTag();
		getSelectedCategory(); 
		getSelectedState();
		//4.获得二级分类
		form.on('select(myFirstSelect)', function(data2) {
			data2 = $("#first_category :selected").attr("id");
			$.post("${ctx}/getSecondCatgegory", {
				parent_id : data2
			}, function(result) {
				with (result) {
					$("#second_category").html("");
					for (var i = 0; i < data.length; i++) {
						var option = "<option id="+data[i].category_id+">"
								+ data[i].category_name + "</option>";
						$("#second_category").append(option);
						layui.form.render('select');
					}
				}
				
			}, "json");
			$.ajaxSetup({
				cache : false
			});
			layui.form.render('select');
		});

		//监听提交
		form.on('submit(formDemo)', function(data) {
			var tag_id1 = $("#tag1 :selected").attr("id");
			var tag_id2 = $("#tag2 :selected").attr("id");
			var tag_id3 = $("#tag3 :selected").attr("id");
			var category_id = $("#second_category :selected").attr("id");
			var article_title = $("#article_title").val();
			var article_desc = $("#article_desc").val();
			var article_content = UE.getEditor('editor').getContent();
			var article_image = $("#article_image").attr("src");
			var article_isTop_name = $('input[name="isTop"]:checked').val();
			var article_id = $('#tid').val();
			var tag_1 =$('#tag_1').val();
			var tag_2 = $('#tag_2').val();
			var tag_3 = $('#tag_3').val();
			if (article_isTop_name == '是') {
				var article_isTop = 1;
			} else {
				var article_isTop = 0;
			}
			
			if(tag_id2==tag_id3&&tag_id2!=0){
				layer.msg("不能多次选择同一个标签哦！", {
					icon : 2
			});
			return;
		}
			
			if (tag_id1 === tag_id2 || tag_id1 === tag_id3) {
					layer.msg("不能多次选择同一个标签哦！", {
					icon : 2
				});
				return;
			}
			if (article_image == "") {
				layer.msg("一定要上传封面图哦！", {
					icon : 2
				});
				return;
			}
			if(article_desc==""){
				layer.msg("博文简介不能为空哦！", {
					icon : 2
				});
				return;
			}
			// 1.检查是否提交了必填项
			$.post("${ctx}/editArticle",//后台地址
			{
				article_id: article_id,
				article_title : article_title,
				article_desc : article_desc,
				article_content : article_content,
				article_image : article_image,
				article_isTop : article_isTop,
				tag_id1 : tag_id1,
				tag_id2 : tag_id2,
				tag_id3 : tag_id3,
				category_id : category_id,
				tag_1:tag_1,
				tag_2:tag_2,
				tag_3:tag_3
			},//需要提交到后台的数据
			function(result) {
				if (result) {
					alert("修改成功");
					//添加成功
					layer.msg("修改成功", {
						icon : 1
					});
					//返回到上一个界面
					/* window.parent.location.reload(); */
					/* document.getElementById("myform").reset(); */
				} else {
					layer.msg("修改失败", {
						icon : 2
					});
				}
			},//回调函数
			"json");
		});
	});
	let UPLOAD_FILES;

	layui.use('upload', function() {
		var upload = layui.upload;
		//提示用户上传图片不可以太大了
		//执行实例
		var uploadInst = upload.render({
			elem : '#test1' //绑定元素
			,
			url : '${ctx}/upload' //上传接口
			,
			exts : 'jpg|png|jpeg' //可传输文件的后缀
			,
			accept : 'file' //video audio images
			,
			done : function(res) {
				//上传完毕回调
				if (res.code == 0) {
					var returnPath = res.data.returnPath;
					$("#article_image")
							.attr(
									"src",
									"http://localhost:8080/MyBlog/upload/"
											+ returnPath);
					$("#article_image").attr("style",
							"display:block;width:135px;height:180px;");

				}
			},
			error : function() {
				//请求异常回调
				//比如生成一个“重新上传”的按钮
			}
		});
	});
</script>
<script type="text/javascript" src="${ctx}/js/umedit/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/umedit/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/umedit/lang/zh-cn/zh-cn.js"></script>
</html>