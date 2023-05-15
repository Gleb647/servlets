package com.example.demo.servlet;

import com.example.demo.model.User;
import com.example.demo.utils.db_params;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/users")
public class ShowUsersServlet extends HttpServlet implements db_params {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Class.forName(db_driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(db_url, db_admin_name, db_admin_pass);
            List<User> users = new ArrayList<>();
            ResultSet res;
            Statement statement = connection.createStatement();
            res = statement.executeQuery("select * from ee_user");
            while (res.next()) {
                users.add(new User(res.getString("email"), res.getString("password")));
            }
            request.setAttribute("users", users);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}