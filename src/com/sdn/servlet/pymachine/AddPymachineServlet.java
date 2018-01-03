package com.sdn.servlet.pymachine;

import com.sdn.model.Pymachine;
import com.sdn.service.PymachineService;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name="AddPymachineServlet")
public class AddPymachineServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String name=request.getParameter("name");
        String cpu=request.getParameter("cpu");
        String ram=request.getParameter("ram");
        String power=request.getParameter("power");
        Pymachine pymachine=new Pymachine();
        pymachine.setName(name);
        pymachine.setCpu(Integer.parseInt(cpu));
        pymachine.setRam(Integer.parseInt(ram));
        pymachine.setPower(Integer.parseInt(power));
        PymachineService ps= new PymachineService();
        try {
            int res=ps.addPymachine(pymachine);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            if (res==1){
                response.getWriter().write("添加成功！");
            }else{
                response.getWriter().write("添加失败。请重试！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
