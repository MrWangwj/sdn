package com.sdn.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import com.sdn.model.User;
import com.sdn.service.UserService;


public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String account = request.getParameter("account");//获取用户account
        String password = request.getParameter("password");//获取用户密码
        User user = new User();
        user.setAccount(account);//设置电话号码
        user.setPassword(password);//设置密码
        UserService userService= new UserService();
        User u;
        try {
            u = userService.login(user);
            //页面跳转
            if(u!=null){

                request.getSession().setAttribute("user", u);//将查询到的user对象放入session域中
                request.getRequestDispatcher("/back/index.jsp").forward(request, response);
            }else{

                request.setAttribute("msg", "您输入的用户名或密码有误");
                request.getRequestDispatcher("/back/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
