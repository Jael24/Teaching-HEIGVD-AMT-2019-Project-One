package ch.heigvd.amt.projectone.presentation.CRUDActor;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class representing the servlet used to delete an actor
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "DeleteActorServlet", urlPatterns = "/deleteActor")
public class DeleteActorServlet extends javax.servlet.http.HttpServlet {

    @EJB
    ActorManagerLocal ActorManager;

    /**
     * Method called on a GET request
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/deleteActor.jsp").forward(req, resp);
    }

    /**
     * Method called on a POST request
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idActor = Long.parseLong(req.getSession().getAttribute("login").toString());
        ActorManager.deleteActor(idActor);
        resp.sendRedirect(req.getContextPath() + "/logout");
    }
}