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

@WebServlet(name = "AddVrServlet")
public class AddVrServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try{
            int pymachine_id = Integer.parseInt(request.getParameter("py_id"));
            PymachineService pymachineService = new PymachineService();
            Pymachine pymachine = pymachineService.getPymachineById(pymachine_id);


            String name  = request.getParameter("name");
            int cpu = Integer.parseInt(request.getParameter("cpu"));
            int ram = Integer.parseInt(request.getParameter("ram"));

            VrmachineService vrmachineService = new VrmachineService();
            Vrmachine sumVrs = vrmachineService.getSumVermachine(pymachine_id);

            System.out.println(sumVrs.getRam());

            if(pymachine.getCpu() < cpu+sumVrs.getCpu()){
                response.getWriter().write(VenderUtils.returnToJson(0,"添加失败，物理机CPU核数超出", null));
                return;
            }else if (pymachine.getRam() < ram + sumVrs.getRam()){
                response.getWriter().write(VenderUtils.returnToJson(0,"添加失败，物理机RAM容量超出", null));
                return;
            }


            Vrmachine vrmachine = new Vrmachine();
            vrmachine.setName(name);
            vrmachine.setCpu(cpu);
            vrmachine.setRam(ram);
            vrmachine.setPymachine_id(pymachine_id);
            vrmachine.setStatus(1);
            vrmachine.setCreated_at(System.currentTimeMillis());



            vrmachineService.create(vrmachine);

            response.getWriter().write(VenderUtils.returnToJson(1,"添加成功", null));
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write(VenderUtils.returnToJson(0,"添加失败", null));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
