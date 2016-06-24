<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>读取Excel from WEB-INF/views</title>
  </head>
  
  <body>
    <form action="/uploadservlet"  enctype="multipart/form-data" method="post">
        读取文件：<input type="file" name="file1"><br/>
        <select name="month">
            <option value="1">Jan</option>
            <option value="2">Feb</option>
            <option value="3">Mar</option>
            <option value="4">Apr</option>
            <option value="5">May</option>
            <option value="6">Jun</option>
            <option value="7">Jul</option>
            <option value="8">Aug</option>
            <option value="9">Sep</option>
            <option value="10">Nov</option>
            <option value="11">Dec</option>
            <option value="12">Oct</option>
        </select>

        <input type="submit" value="读取">
    </form>


  </body>
</html>