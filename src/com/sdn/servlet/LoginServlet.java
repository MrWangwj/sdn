package com.sdn.servlet;

import java.io.IOException;
import com.sdn.model.User;
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username = request.getParameter("username");//获取用户phone
        String password = request.getParameter("password");//获取用户密码
        User user = new User();
        user.setUsername(username);//设置电话号码
        user.setPassword(password);//设置密码
        UserService userService= new UserService();
        User u;
    }
}
