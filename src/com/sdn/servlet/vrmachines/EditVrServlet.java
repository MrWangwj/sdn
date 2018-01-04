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

@WebServlet(name = "EditVrServlet")
public class EditVrServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try{
            int pymachine_id = Integer.parseInt(request.getParameter("py_id"));
            int vrmachine_id = Integer.parseInt(request.getParameter("id"));
            String name  = request.getParameter("name");
            int cpu = Integer.parseInt(request.getParameter("cpu"));
            int ram = Integer.parseInt(request.getParameter("ram"));

            PymachineService pymachineService = new PymachineService();
            VrmachineService vrmachineService = new VrmachineService();

            Pymachine pymachine = pymachineService.getPymachineById(pymachine_id);
            Vrmachine sumVrs = vrmachineService.getSumVermachine(pymachine_id, vrmachine_id);


            if(pymachine.getCpu() < cpu + sumVrs.getCpu()){
                response.getWriter().write(VenderUtils.returnToJson(0,"保存失败，物理机CPU核数超出", null));
                return;
            }else if(pymachine.getRam() < ram + sumVrs.getRam()){
                response.getWriter().write(VenderUtils.returnToJson(0,"保存失败，物理机RAM容量超出", null));
                return;
            }

            Vrmachine vrmachine = new Vrmachine();
            vrmachine.setId(vrmachine_id);
            vrmachine.setName(name);
            vrmachine.setCpu(cpu);
            vrmachine.setRam(ram);

            vrmachineService.update(vrmachine);

            response.getWriter().write(VenderUtils.returnToJson(1,"保存成功", null));
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write(VenderUtils.returnToJson(0,"保存失败", null));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
