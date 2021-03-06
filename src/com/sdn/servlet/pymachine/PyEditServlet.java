package com.sdn.servlet.pymachine;

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

@WebServlet(name = "EditServlet")
public class PyEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try{



            int id = Integer.parseInt(request.getParameter("id"));
            String name  = request.getParameter("name");
            int cpu = Integer.parseInt(request.getParameter("cpu"));
            int ram = Integer.parseInt(request.getParameter("ram"));
            int power = Integer.parseInt(request.getParameter("power"));


            Pymachine pymachine = new Pymachine();
            pymachine.setId(id);
            pymachine.setName(name);
            pymachine.setCpu(cpu);
            pymachine.setRam(ram);
            pymachine.setPower(power);




            VrmachineService vrmachineService = new VrmachineService();
            Vrmachine vrmachine = vrmachineService.getSumVermachine(id, 0);

            if(vrmachine.getCpu() > cpu){
                response.getWriter().write(VenderUtils.returnToJson(0,"CPU核数小于虚拟机总数，请修改虚拟机后再操作", null));
                return;
            }else if (vrmachine.getRam() > ram){
                response.getWriter().write(VenderUtils.returnToJson(0,"RAM容量小于虚拟机总数，请修改虚拟机后再操作", null));
                return;
            }


            PymachineService pymachineService = new PymachineService();
            pymachineService.update(pymachine);

            response.getWriter().write(VenderUtils.returnToJson(1,"修改成功", null));
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write(VenderUtils.returnToJson(0,"发生错误", null));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
