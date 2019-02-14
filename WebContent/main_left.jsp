<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />

<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/index.css">
<!-- 修改自Bootstrap官方Demon，你可以按自己的喜好制定CSS样式 -->
<link rel="stylesheet" href="css/font-change.css">
<!-- 将默认字体从宋体换成微软雅黑（个人比较喜欢微软雅黑，移动端和桌面端显示效果比较接近） -->
<style>
li{float：left;list-style:none}
</style>
</head>

<body  style="background-color:#EAEAEA;">
	
	<ul>
		<li>
		<a href="/xinhaiopendieinterface/getdata?method=shiyujie"
			class="nav-header menu-first collapsed" data-toggle="collapse" target="main_right">
			 <i class="fa fa-user"></i>&nbsp; 人员进出数据看板
			 <span class="sr-only">(current)</span>
         </a>
		</li>
		<li>
		<a href="/xinhaiopendieinterface/getdata?method=shujuchengxian" 
		class="nav-header menu-first collapsed" data-toggle="collapse" target="main_right">
		 <i class="fa fa-user"></i>&nbsp; 进出板数据呈现 <span
			class="sr-only">(current)</span></a>
	 </li>
		
		</ul>

	
</body>
</html>