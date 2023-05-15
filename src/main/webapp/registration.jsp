<%--
  Created by IntelliJ IDEA.
  User: gleb
  Date: 12.05.2023
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="/servlet/process" method="post">
          <input type="email" name="username">
          <input type="password" name="password">
          <input type="submit" value=Submit>
        </form>
    </body>
</html>