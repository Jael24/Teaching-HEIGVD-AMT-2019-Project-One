package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;
import ch.heigvd.amt.projectone.model.Actor;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class used to represent a servlet used to display the current user
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name="ActorServlet", urlPatterns = "/actor")
public class ActorServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private ActorManagerLocal actorManager;

    /**
     * Method called on a GET request on the servlet
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        long actorId = Long.parseLong(req.getSession().getAttribute("login").toString());

        Actor actor = actorManager.findActorByID(actorId);

        req.getSession().setAttribute("actor", actor);
        req.getRequestDispatcher("/WEB-INF/pages/actor.jsp").forward(req, resp);
    }
}
