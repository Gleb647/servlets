<%@ page import="com.example.demo.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gleb
  Date: 13.05.2023
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <% List<User> users = (ArrayList<User>)request.getAttribute("users");
            for(User user : users) {
                out.print("Email: " + user.getEmail());
                out.print("<br/>");
                out.print("Password: " + user.getPassword());
                out.print("<br/>");
                out.print("<br/>");
            }
        %>
    </body>
</html>
