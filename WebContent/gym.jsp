<%--
  Created by IntelliJ IDEA.
  User: Kazaf
  Date: 16/4/4
  Time: 上午8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.kazaf.plugins.ExecuteMySql" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kazaf.domain.Bill" %>
<html>
<head>
    <title>The Gym Page</title>
</head>
<body>
<label>Hello this is gym page</label><br>
<%
 // ExecuteMySql em=ExecuteMySql.getInstance();
  List<Bill> list=ExecuteMySql.getBillList();
  for(Bill tempbill:list){
%>
<%=tempbill.getBill_date()%> &nbsp&nbsp <%=tempbill.getBill_comments()%> &nbsp&nbsp <%=tempbill.getBill_cost()%>  <br>
<%
  }
%>

<form action="GymServlet" method="post">
  <label>用户名</label> <input type="text" name="username">
  <label>密码</label> <input type="password" name="password">
  <input type="submit" value="确认">
</form>



</body>
</html>
