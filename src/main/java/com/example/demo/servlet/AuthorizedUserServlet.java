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
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/authorized")
public class AuthorizedUserServlet extends HttpServlet implements db_params{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName(db_driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(db_url, db_admin_name, db_admin_pass);
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ee_user where email=?");
            preparedStatement.setString(1, username);
            ResultSet res = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            if (res.next()) {
                User user = new User(res.getString("email"), res.getString("password"));
                users.add(user);
                request.setAttribute("users", users);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
