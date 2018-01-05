package com.sdn.servlet.pymachine;

import com.sdn.model.Pymachine;
import com.sdn.service.PymachineService;
import com.sdn.utils.VenderUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PyListServlet")
public class PyListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try{
            PymachineService pymachineService = new PymachineService();
            List<Pymachine> list = pymachineService.getPymachines();

            response.getWriter().write(VenderUtils.returnToJson(1, "查询成功", list));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
