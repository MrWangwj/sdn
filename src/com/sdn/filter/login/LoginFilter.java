package com.sdn.filter.login;

import com.sdn.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {



    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        HttpSession session = httpServletRequest.getSession();



        // 获得用户请求的URI
        String path = httpServletRequest.getRequestURI();

        // 从session取得已经登录验证的凭证 我这里的demo用的是password来作为登录凭证
        User user = (User) session.getAttribute("user");
        // login.jsp页面无需过滤(根据自己项目的要求来)
        System.out.println(path);

//        if(path.indexOf("/login.jsp") == -1){
//            if (user == null){
//                httpServletResponse.sendRedirect("/home/view/login.jsp");
//                return;
//            }
//
//        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
