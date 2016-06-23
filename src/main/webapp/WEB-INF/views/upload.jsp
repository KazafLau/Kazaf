<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>读取Excel from WEB-INF/views</title>
  </head>
  
  <body>
    <form action="/uploadservlet"  enctype="multipart/form-data" method="post">
        读取文件：<input type="file" name="file1"><br/>
        <select name="type">
            <option value="bill">Bill</option>
            <option value="gym">Gym</option>
        </select>

        <input type="submit" value="读取">
    </form>


  </body>
</html>