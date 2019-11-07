package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="HomeServlet", urlPatterns = "")
public class HomeServlet  extends javax.servlet.http.HttpServlet {

    @EJB
    private ClipManagerLocal clipManager;

    @EJB
    private ActorManagerLocal actorManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        long actorId = Long.parseLong(req.getSession().getAttribute("login").toString());

        req.setAttribute("movies", clipManager.findClipsWhereActorHasPlayed(actorId));
        req.getSession().setAttribute("fullName", actorManager.findActorByID(actorId).getFullName());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}



