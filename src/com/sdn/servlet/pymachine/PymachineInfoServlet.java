package com.sdn.servlet.pymachine;

import com.sdn.model.Pymachine;
import com.sdn.model.Vrmachine;
import com.sdn.service.PymachineService;
import com.sdn.service.VrmachineService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PymachineInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int pymachineId = Integer.parseInt(request.getParameter("id"));

            PymachineService pymachineService = new PymachineService();
            Pymachine pymachine = pymachineService.getPymachineById(pymachineId);

            VrmachineService vrmachineService = new VrmachineService();
            List<Vrmachine> vrmachines = vrmachineService.getVrmachinesByPymachineId(pymachineId);

            request.setAttribute("pymachine", pymachine);
            request.setAttribute("vrmachines", vrmachines);

            request.getRequestDispatcher("/home/view/pymachine/pymachine.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
