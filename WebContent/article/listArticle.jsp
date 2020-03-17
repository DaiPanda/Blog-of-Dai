<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/base.css" rel="stylesheet" media="screen" />
<title>博文列表</title>
</head>
<body>
	<div class="demoTable" style="margin-top:20px;margin-left:30px; ">
		搜索标题：
		<div class="layui-inline">
			<input class="layui-input" name="id" id="demoReload"
				autocomplete="off">
		</div>
		<button class="layui-btn" data-type="reload" id="searchReload">搜索</button>
	</div>
	<div id="box">
		<table id="demo" lay-filter="test"></table>
	</div>
</body>
<script>
	layui.use('table', function() {
		var table = layui.table;
		//开始渲染表格
		table.render({
			elem : '#demo',//要渲染的表格id
			height : 470,//表格的高度
			/* width : 1007, 注释掉这行代码就可以自适应表格宽度了*/
			/* toolbar : '#searchToolBar',不开启ToolBar */
			url : '${ctx}/listArticle', //数据接口
			page : true, //开启分页
			cols : [ [ //表头
			{
				field : 'article_id',
				title : 'ID',
				width : 70,
				sort : true,
				fixed : 'left'
			}, {
				field : 'article_title',
				title : '标题',
				width : 200,
			}, {
				field : 'article_createtime',
				title : '创建时间',
				width : 175,
				/*加一个template处理一下创建时间  */
				sort : true,
				templet:'<div>{{Format(d.article_createtime,"yyyy-MM-dd h:m:s")}}</div>'
			}, {
				field : 'article_views',
				title : '浏览量',
				width : 100,
				sort : true,
			}, {
				field : 'article_likenum',
				title : '点赞量',
				width : 100,
				sort : true,
			}, {
				field : 'category_id',
				title : '分类',
				width : 100,
				templet:"#manageCategoryId"
			}, {
				field : 'article_isTop',
				title : '是否置顶',
				width : 110,
				sort : true,
			}, {
				title : '操作',
				toolbar : '#barDemo',
				width : 190
			} ] ],
			id:'seaReload'
		});
		//监听工具条
		table.on('tool(test)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的DOM对象

			if (layEvent === 'detail') { //查看评论
				 //弹出层
				layer.open({
					title : ["查看评论","text-align:center;text-shadow:1px 1px 2px red;"],
					type : 2,
					offset : [ "60px" ],//位置
			    	area : [ "990px", "500px" ],//大小 
					content : ['../commentArticle/listArticleComment.jsp?article_id=' + data.article_id,"no"], //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
					anim:1,
					//当窗口关闭时执行
					end : function() {
						table.reload('demo');
					}
				}); 
			} else if (layEvent === 'del') { //删除
				layer.confirm('真的删除该博文么？？', function(index) {
					obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
					layer.close(index);
					//向服务端发送删除指令
					$.post("${ctx}/delArticle", {
						article_id : data.article_id
					}, function(result) {
						if (result) {
							layer.msg("删除成功！！", {
								icon : 1
							});
							table.reload('demo');
						}
					}, "json");
				});
				layer.close(index);
			} else if (layEvent === 'edit') { //编辑
				
				window.location.href= '${ctx}/selectArticle?article_id=' + data.article_id;
			}
		});

		//搜索重载
		$("#searchReload").click(function() {
			var article_title = $("#demoReload").val();
			//alert(article_title);
			//执行重载
			table.reload('seaReload', {
				url : '${ctx}/getSearchArticle',
				methods : 'post',
				page : {
					curr : 1
				//重新从第 1 页开始
				},
				where : {
					article_title : article_title
				}
			}, 'data');
		});

	});
	/* 时间格式处理 */
	function Format(datetime,fmt) {
		  if (parseInt(datetime)==datetime) {
		    if (datetime.length==10) {
		      datetime=parseInt(datetime)*1000;
		    } else if(datetime.length==13) {
		      datetime=parseInt(datetime);
		    }
		  }
		  datetime=new Date(datetime);
		  var o = {
		  "M+" : datetime.getMonth()+1,                 //月份   
		  "d+" : datetime.getDate(),                    //日   
		  "h+" : datetime.getHours(),                   //小时   
		  "m+" : datetime.getMinutes(),                 //分   
		  "s+" : datetime.getSeconds(),                 //秒   
		  "q+" : Math.floor((datetime.getMonth()+3)/3), //季度  
		  "S"  : datetime.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		  fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		  if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;
		}
</script>
<!--工具条模板  -->
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="detail">查看评论</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>  
</script>
<!-- 处理时间格式 -->
<script type="text/html" id="formateTime">


</script>
<!-- 处理分类 -->
<script type="text/html" id="manageCategoryId">
	  <div>
            {{# var cate = function(){
                            var eType = d.category_id;
							switch(eType){
								case 3:return "杂谈";break;
								case 4:return "技术笔记";break;
								case 5:return "生活";break;
								case 6:return "学习";break;
								case 7:return "情感";break;
								case 8:return "美妆";break;
								case 9:return "美食";break;
								case 10:return "HTML5";break;
								case 11:return "CSS3";break;
								case 12:return "JavaScript";break;
								case 13:return "Python";break;
								case 14:return "Java";break;
								case 15:return "Php";break;
								case 16:return "Android";break;
								case 25:return "小说";break;
								case 26:return "MYSQL";break;
							default:return "其他";break;
							}	
                       
                        }
            }}
            {{cate()}}
            </div>
</script>
<!-- 供以后参考，注意类型！！！
<script type="text/html" id="searchToolBar">
	   <input  class="searchInput" name="" id=""
                      placeholder="请输入标题内容">
		 <button id="search" lay-filter="">
			<i class="layui-icon layui-icon-search" style="color:#fff;font-size: 26px;"></i>
		  </button>
</script>
-->
</html>