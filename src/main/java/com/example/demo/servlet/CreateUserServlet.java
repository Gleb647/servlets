package com.example.demo.servlet;

import com.example.demo.utils.Encrypt;
import com.example.demo.utils.db_params;
import com.example.demo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/process")
public class CreateUserServlet extends HttpServlet implements db_params {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _username = request.getParameter("username");
        String _pas;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String path;
        if (username == null || !username.equals(_username)){
            try {
                _pas = Encrypt.doEncrypt(request.getParameter("password"));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
            try {
                Class.forName(db_driver);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                session.setAttribute("username", _username);
                Connection connection = DriverManager.getConnection(db_url, db_admin_name, db_admin_pass);
                PreparedStatement statement = connection.prepareStatement("insert into ee_user(email, password) values (?, ?)");
                statement.setString(1, _username);
                statement.setString(2, _pas);
                statement.addBatch();
                statement.executeBatch();
                path = "users";
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            path = "authorized";
        }
        response.sendRedirect(path);
    }
}
