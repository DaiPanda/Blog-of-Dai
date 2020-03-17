<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加博文</title>
<style type="text/css">
#box {
	width: 1000px;
	margin-left: auto;
	margin-right: auto;
	background-image: url(../css/image/18.gif);
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
		<form class="layui-form" action="" enctype="multipart/form-data"
			id="myform">
		<!-- 	<h3 style="text-align:center;margin-bottom:20px;text-shadow:1px 1px 2px red;">增加博文</h3> -->
			<div class="layui-form-item">
				<label class="layui-form-label">文章标题</label>
				<div class="layui-input-block" style="width: 420px;">
					<input type="text" name="title" required lay-verify="required"
						placeholder="请输入标题" autocomplete="off" class="layui-input"
						id="article_title">
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
					<input type="radio" name="isTop" value="是" title="是"> <input
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
					<img src="" id="article_image" class="article_image"
						style="display: none; margin-top: 20px;"></img>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章内容</label>
				<div class="layui-input-block">
					<div id="editor" name="article_content"
						style="width: 900px; height: 400px;"></div>
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
						id="article_desc"></textarea>
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
		//alert("1-进入了getFirstCategory的方法");
		$.post("${ctx}/getFirstCategory", {}, function(result) {
			with (result) {
				for (var i = 0; i < data.length; i++) {
					var option = "<option id="+data[i].category_id+">"
							+ data[i].category_name + "</option>";
					$("#first_category").append(option);
					layui.form.render('select');
				}
			}

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
	layui.use('form', function() {
		//1.初始化form表单
		var form = layui.form;
		//2.初始化富文本编辑器
		var ue = window.UE.getEditor('editor');
		//3.获得一级分类
		getFirstCategory();
		//补充之获得一级标签（最少选择一个，最多选择三个）
		getFirstTag();

		//4.获得二级分类
		form.on('select(myFirstSelect)', function(data2) {
			var category_id = $("#first_category :selected").attr("id");
			$.post("${ctx}/getSecondCatgegory", {
				parent_id : category_id
			}, function(result) {
				with (result) {
					$("#second_category").html("");
					for (var i = 0; i < data.length; i++) {
						//alert("2-测试进入了吗")
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
			$.post("${ctx}/addArticle",//后台地址
			{
				article_title : article_title,
				article_desc : article_desc,
				article_content : article_content,
				article_image : article_image,
				article_isTop : article_isTop,
				tag_id1 : tag_id1,
				tag_id2 : tag_id2,
				tag_id3 : tag_id3,
				category_id : category_id
			},//需要提交到后台的数据
			function(result) {
				if (result) {
					//添加成功
					alert("添加成功");
					layer.msg("添加成功", {
						icon : 1
					});
					document.getElementById("myform").reset();
				} else {
					layer.msg("添加失败", {
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
					//alert(returnPath);
					/* $("#article_image").attr("src", "../upload/" + returnPath); */
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