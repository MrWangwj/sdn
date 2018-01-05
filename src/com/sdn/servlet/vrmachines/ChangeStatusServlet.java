package com.sdn.servlet.vrmachines;

import com.sdn.model.Pymachine;
import com.sdn.model.Vrmachine;
import com.sdn.service.PymachineService;
import com.sdn.service.VrmachineService;
import com.sdn.utils.VenderUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangeStatusServlet")
public class ChangeStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        int pymachine_id = Integer.parseInt(request.getParameter("py_id"));
        System.out.println(id+" -- "+status+" " +pymachine_id);

        Vrmachine v = new Vrmachine();
        PymachineService py = new PymachineService();
        try {
            VrmachineService vs = new VrmachineService();
            Pymachine pymachine = py.getPymachineById(pymachine_id);
            Vrmachine sumVrs = vs.getSumVermachine(pymachine_id,id);
            if(status == 3){
                if(pymachine.getPower() <  3+ sumVrs.getPower()){
                    response.getWriter().write(VenderUtils.returnToJson(0,"保存失败，电量容量超出", null));
                    return;
                }
            }else if(status == 2){
                if(pymachine.getPower() <  5+ sumVrs.getPower()){
                    response.getWriter().write(VenderUtils.returnToJson(0,"保存失败，电量容量超出", null));
                    return;
                }
            }
            v.setId(id);
            v.setStatus(status);


            vs.changeStatus(v);
            response.getWriter().write(VenderUtils.returnToJson(1,"保存成功", null));
        } catch (SQLException e) {
            response.getWriter().write(VenderUtils.returnToJson(1,"保存成功", null));
        }
    }
}
