<%@ page import="java.util.List" %>
<%@ page import="com.kazaf.pojos.Bill" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: Kazaf
  Date: 16/4/14
  Time: 下午9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message from WEB-INF/views</title>
</head>
<body>
${requestScope.days}
${requestScope.firstday}
${requestScope.lastday}
${requestScope.cosumedays}


<form action="getbillbymonth" method="post">
    <label>月份</label> <input type="text" name="month">
    <input type="submit" value="确认">
</form>

<%
    List<Bill> monthBill=null;
    monthBill=(List<Bill>)request.getAttribute("getBillbyMonth");
    if(monthBill!=null){
       Bill billtemp;
%>
<table >
    <tr>
        <th>BillDate</th>
        <th>BillCost</th>
        <th>BillComments</th>
    </tr>
    <tr>
        <%
            Iterator a=monthBill.iterator();
            while (a.hasNext()){
            billtemp=(Bill)a.next();
        %>
        <td><%billtemp.getBill_date();%></td>
        <td><%billtemp.getBill_cost();%></td>
        <td><%billtemp.getBill_comments();%></td>
        <% } %>
    </tr>
</table>
<%}%>
</body>
</html>
