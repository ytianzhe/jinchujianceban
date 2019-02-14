<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
</head>
<!--  
<script>
$(function()
{
	$("#btn_search").on("click",function()
	{
			//alert("1");
			var page=${page};
			var row=${row};
			var allpage=${allpage};
			var state =document.getElementById("fStates").value;
			var time =document.getElementById("fSeartime").value;			
		//	alert(state+"  "+time);
		//	indow.location.reload();
		
			$.ajax({
	            type: "post",//数据发送的方式（post 或者 get）
	            url: "Search",//要发送的后台地址
	            data: {'state':state,'time':time,'page':page,'row':row,"allpage":allpage,'method':"search"},//要发送的数据（参数）格式为{'val1':"1","val2":"2"}
	          
	          
	             dataType: "json",//后台处理后返回的数据格式
	           success: function (data) {//ajax请求成功后触发的方法
	        	   
	        	   window.location.href = "shujumsg.jsp";
	            }
	          //  error: function (msg) {//ajax请求失败后触发的方法
	          //      alert(msg);//弹出错误信息
	           // }
	        });
	});															
});
		
</script>
-->
<script>
function select(){
	var state=null;
	state=document.getElementById("state").value;	
	if(state!=""||state!=null){
		//alert(state);
	if (state == "00"){
		$("#fStates").val("00");	
	}
	else if (state == "01"){
		//alert("是01");
		$("#fStates").val("01");
	}
	else if (state == "10"){
		//alert("是10");
		$("#fStates").val("10");
	}
	else if(state == "-1"){
		$("#fStates").val("-1");
	 }
	}
}

</script>
<body onload="select()">
 state: ${state}  start:${Starttime}  end:${Endtime}
 <c:if test="${ empty list}">没有返回结果
 <div>



<form action="time" method="post" class="myform"  id="myform">
	<table>
	<tr>
	<th>状态：</th>
	<th><select id="fStates" class="form-control width-150" name="state">
						<option value="-1"></option>
						<option value="00">正常</option>
						<option value="01">超时</option>
						<option value="10">超时后下一条</option>
				</select></th>
	
   <th> 查询开始于：</th>
   <th> <input  id="startTime" type="date" value="${Starttime}" name="Starttime"/>
	</th>
  <th> 查询结束于：  </th>
 <!--   <th>   <input id="EndTime" type="date" value="<%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10)%>" name="Endtime"/> -->
  <th>   <input id="EndTime" type="date" value="${Endtime}" name="Endtime"/>
   </th>
   <th>  <input class="btn btn-default purple" type="submit" name="button" value="查询"/></th>
  <!--  <th> <input  type="button" name="button" id="qingkong" value="清空" onClick="reset()"/></th> -->
    <th> <input  class="btn btn-default purple" type="button" onclick="reset1()"   value="清空"/></th>
<tr>
</table>
</form>
</div>
 
 
 </c:if>
<c:if test="${not empty list}">有返回结果
<!--  
<div>
			<div class="line bm-bordered">
				<div class="form-inline ">
					<label class="padding-left-20 control-label">状态:  </label> 
					<select
						id="fStates" class="form-control width-150">
						<option value=""></option>
						<option value="00">正常</option>
						<option value="01">超时</option>
						<option value="10">超时后下一条</option>
					</select> 
					<label class="padding-left-20 control-label">  时间 : </label> 
						<select
						id="fSeartime" class="form-control width-150">
						<option value="0"></option>
						<option value="1">今天</option>
						<option value="2">本周</option>
						<option value="3">本月</option>
						<option value="4">本季度</option>
						<option value="5">本年</option>
						<option value="6"  disabled=true>----</option>
						<option value="7">昨天</option>
						<option value="8">上周</option>
						<option value="9">上个月</option>
						<option value="10">上季度</option>
						<option value="11">去年</option>
						</select> 
					
					<a class="btn btn-default purple" id="btn_search"><i
						class="fa fa-search" style="float:left;" ></i>查询</a>
				</div>
			</div>
		</div>
-->
<div>



<form action="time" method="post" class="myform"  id="myform">
	<table>
	<tr>
	<th>状态：</th>
	<th><select id="fStates" class="form-control width-150" name="state">
						<option value="-1"></option>
						<option value="00">正常</option>
						<option value="01">超时</option>
						<option value="10">超时后下一条</option>
				</select></th>
	
   <th> 查询开始于：</th>
   <th> <input  id="startTime" type="date" value="${Starttime}" name="Starttime"/>
	</th>
  <th> 查询结束于：  </th>
 <!--   <th>   <input id="EndTime" type="date" value="<%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10)%>" name="Endtime"/> -->
  <th>   <input id="EndTime" type="date" value="${Endtime}" name="Endtime"/>
   </th>
   <th>  <input class="btn btn-default purple" type="submit" name="button" value="查询"/></th>
  <!--  <th> <input  type="button" name="button" id="qingkong" value="清空" onClick="reset()"/></th> -->
    <th> <input  class="btn btn-default purple" type="button" onclick="reset1()"   value="清空"/></th>
<tr>
</table>
</form>
</div>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="row">#</th>

					<th>状态</th>
					<th>装置触发顺序</th>
					<th>时间</th>
					<th>备注</th>
					<th></th>

				</tr>
				<c:forEach items="${list}" var="l" varStatus="vs" begin="0">

					<c:if
						test="${(vs.index + 1)<=(row*page) && (vs.index + 1)>=(row*page-9)}">
						<tr>
							<th><c:out value="${vs.index + 1}" /></th>

							<th><c:out value="${l.state}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.info}" escapeXml="false" /><br /></th>
							<th><c:out value="${l.time}" escapeXml="false" /><br /></th>
							<th></th>
							<th></th>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<th>
						<form action="fenye" method="post">
						<input  id="state" type="hidden" name="state" value="${state}" />
						<input type="hidden" name="StartTime" value="${Starttime}" />
						<input type="hidden" name="EndTime" value="${Endtime}" />
							<input type="hidden" name="page" value="${page}" /> <input
								type="hidden" name="row" value="${row}" /> <input type="hidden"
								name="allpage" value="${allpage}" /> <input type="hidden"
								name="list" value="${list}" /> <input type="hidden"
								name="method" value="first" /> <input class="btn btn-default"
								type="submit" name="button" value="首页" />
						</form>
					</th>
					<th>
						<form action="fenye" method="post">
						<input type="hidden" name="state" value="${state}" />
						<input type="hidden" name="StartTime" value="${Starttime}" />
						<input type="hidden" name="EndTime" value="${Endtime}" />
							<input type="hidden" name="page" value="${page}" /> <input
								type="hidden" name="row" value="${row}" /> <input type="hidden"
								name="allpage" value="${allpage}" /> <input type="hidden"
								name="list" value="${list}" /> <input type="hidden"
								name="method" value="pgup" /> <input class="btn btn-default"
								type="submit" name="button" value="上一页" />
						</form>
					</th>

					<th>当前页数(${page}/${allpage})</th>
					<th>
						<form action="fenye" method="post">
						<input type="hidden" name="state" value="${state}" />
						  <input type="hidden" name="StartTime" value="${Starttime}" />
						   <input type="hidden" name="EndTime" value="${Endtime}" />
							<input type="hidden" name="page" value="${page}" /> <input
								type="hidden" name="row" value="${row}" /> <input type="hidden"
								name="allpage" value="${allpage}" /> <input type="hidden"
								name="list" value="${list}" /> <input type="hidden"
								name="method" value="pgdn" /> <input class="btn btn-default"
								type="submit" name="button" value="下一页" />
						</form>
					</th>
					<th>
						<form action="fenye" method="post">
						<input type="hidden" name="state" value="${state}" />
						<input type="hidden" name="StartTime" value="${Starttime}" />
						<input type="hidden" name="EndTime" value="${Endtime}" />
							<input type="hidden" name="page" value="${page}" /> <input
								type="hidden" name="row" value="${row}" /> <input type="hidden"
								name="allpage" value="${allpage}" /> <input type="hidden"
								name="list" value="${list}" /> <input type="hidden"
								name="method" value="final" /> <input class="btn btn-default"
								type="submit" name="button" value="末页" />
						</form>
					</th>


					<th><a
						href="/xinhaiopendieinterface/getdata?method=shujuchengxian"
						class="nav-header menu-first collapsed" data-toggle="collapse"
						target="main_right"> <i class="fa fa-user"></i>&nbsp; 刷新数据 <span
							class="sr-only">(current)</span></a></th>
				</tr>





			</thead>
		</table>

	</c:if>



	<script>
function pgup(){
	alert("1");	
}

function reset1(){
	//alert(document.getElementById("EndTime").value);
	//$('#myform').reset();	
	document.getElementById("fStates").value = "-1";
	document.getElementById("startTime").value = "";
	document.getElementById("EndTime").value = "";
}
</script>

</body>
</html>