<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
</head>
<body>

<table class="table table-bordered">
      <thead>
        <tr>
          <th>#</th>
          <th>名称</th>
          <th>数量</th>
          <th>备注</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">1</th>
          <td>总数据次数</td>
          <td>${count} </td>
          <td>所有采集到的人员进出板的数据次数</td>
        </tr>
        <tr>
          <th scope="row">2</th>
          <td>正常的情况数据量</td>
          <td>${count00xxxxxx}</td>
          <td>在返回结果中以00报文开头的数据量</td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td>错误的数据量</td>
          <td>${mistakecount}</td>
          <td>采集到的在标准之外的错误数据</td>
        </tr>
        
        <tr>
          <th scope="row">4</th>
          <td>进入的次数</td>
          <td>${incount}</td>
          <td>从abc顺利通过的记录</td>
        </tr>
        
        <tr>
          <th scope="row">5</th>
          <td>出去的次数</td>
          <td>${outcount}</td>
          <td>从cba顺利通过的记录</td>
        </tr>
          <tr>
          <th scope="row">6</th>
          <td>进入超时的次数</td>
          <td>${abovertimecount}</td>
          <td>在ab之间超时的记录</td>
        </tr>
          <tr>
          <th scope="row">7</th>
          <td>出去超时的次数</td>
          <td>${bcovertimecount}</td>
          <td>在bc之间超时的记录</td>
        </tr>
      </tbody>
    </table>
<!--
消息：${msg} <br>
---------------------------
总数：${count} <br>
正常情况的数据量：${count00xxxxxx} <br>
超时的数据量：${overtimecount} <br>
错误的数据量：${mistakecount} <br>
--------------------------------<br>
从abc顺利通过的记录：${incount} <br>
从cba顺利通过的记录：${outcount} <br>

在ab超时的记录：${abovertimecount} <br>
在bc超时的记录：${bcovertimecount} <br>
<br><br><br><br>
----------------------------------
  -->

</body>
</html>