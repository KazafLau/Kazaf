<%@ page import="com.kazaf.service.BMRcalculator" %>
<%@ page import="java.math.BigDecimal" %>
<%--
  Created by IntelliJ IDEA.
  User: Kazaf
  Date: 16/4/2
  Time: 下午1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>Hello First!</title>
</head>
<body>
<form action="GymServlet" method="post">
 <label>用户名</label> <input type="text" name="username">
  <label>密码</label> <input type="password" name="password">
  <input type="submit" value="确认">

    <%
        BigDecimal weight=new BigDecimal(85);
        BigDecimal height=new BigDecimal(185);
        int age=27;
        BMRcalculator bmr=new BMRcalculator(weight,height,age);

    %><br>

    <%=bmr.getBMR()%>
</form>
</body>
</html>
