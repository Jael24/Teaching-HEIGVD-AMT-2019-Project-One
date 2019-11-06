package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="ActorServlet", urlPatterns = "/actor")
public class ActorServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private ActorManagerLocal actorManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setAttribute("actors", actorManager.findAllActors());
        req.getRequestDispatcher("/WEB-INF/pages/Movie.jsp").forward(req, resp);
    }
}
