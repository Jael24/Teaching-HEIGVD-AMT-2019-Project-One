package ch.heigvd.amt.projectone.presentation.CRUDActor;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteActorServlet", urlPatterns = "/deleteActor")
public class DeleteActorServlet extends javax.servlet.http.HttpServlet {

    @EJB
    ActorManagerLocal ActorManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/deleteActor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idActor = Long.parseLong(req.getSession().getAttribute("login").toString());
        ActorManager.deleteActor(idActor);
        resp.sendRedirect(req.getContextPath() + "/logout");
    }
}