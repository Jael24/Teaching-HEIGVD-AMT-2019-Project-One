package ch.heigvd.amt.projectone.presentation.CRUDActor;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateActorServlet", urlPatterns = "/updateActor")
public class UpdateActorServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private ActorManagerLocal ActorManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/updateActor.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newActor = req.getParameter("newName");
        long idActor = Long.parseLong(req.getSession().getAttribute("login").toString());

        ActorManager.updateActor(idActor, newActor);

        resp.sendRedirect(req.getContextPath() + "/actor");
    }
}
