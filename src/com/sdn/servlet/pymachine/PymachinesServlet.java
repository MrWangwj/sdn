package com.sdn.servlet.pymachine;

import com.sdn.model.Pymachine;
import com.sdn.service.PymachineService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import net.sf.json.JSONArray;

@WebServlet(name = "PymachinesServlet")
public class PymachinesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PymachineService pymachinesServlet=new PymachineService();
        try {
            List<Pymachine> list=pymachinesServlet.getPymachines();
            request.setAttribute("pymachineList", list);
            request.getRequestDispatcher("/home/view/pymachine/list.jsp").forward(request, response);
            JSONArray jsonArray=JSONArray.fromObject(list);
            String result=jsonArray.toString();
            response.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
