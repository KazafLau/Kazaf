<%@ page import="com.kazaf.plugins.ExecuteMySql" %>
<%@ page import="com.kazaf.domain.Bill" %>
<%--
  Created by IntelliJ IDEA.
  User: Kazaf
  Date: 16/4/2
  Time: ����1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>Hello First!</title>
</head>
<body>
<form action="GymServlet" method="post">
 <label>�û���</label> <input type="text" name="username">
  <label>����</label> <input type="password" name="password">
  <input type="submit" value="ȷ��">
<%
    for(Bill temp:ExecuteMySql.getBillList()){
        %><%=temp.getBill_comments()%><br><%
    }
%>
</form>
</body>
</html>
