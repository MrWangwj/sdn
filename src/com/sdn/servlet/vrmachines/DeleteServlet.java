package com.sdn.servlet.vrmachines;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.sdn.model.Vrmachine;
import com.sdn.service.VrmachineService;
@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        Vrmachine vrmachine = new Vrmachine();
        vrmachine.setId(Integer.parseInt(id));
        VrmachineService vrmachineService = new VrmachineService();
        System.out.println("vid="+vrmachine.getId());
        try {
            vrmachineService.delete(vrmachine);

            response.getWriter().write("ok");

        } catch (SQLException e) {

            response.getWriter().write("error");
        }

    }
}
