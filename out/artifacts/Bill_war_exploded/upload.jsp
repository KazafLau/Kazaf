<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>读取Excel</title>
  </head>
  
  <body>
    <form action="ReadExcelServlet"  enctype="multipart/form-data" method="post">
        读取文件：<input type="file" name="file1"><br/>
        <input type="text" name="month"/>
        <select name="type">
            <option value="bill">Bill</option>
            <option value="gym">Gym</option>
        </select>

        <input type="submit" value="读取">
    </form>


  </body>
</html>