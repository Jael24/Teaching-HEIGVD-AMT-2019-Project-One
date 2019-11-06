package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;
import ch.heigvd.amt.projectone.model.Actor;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private ActorManagerLocal actorManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String passwordRetyped = req.getParameter("passwordRetyped");

        if(password.equals(passwordRetyped)) {
            // TODO Problème de concurrence ?
            long newId = actorManager.findMaxId()+1;
            Actor actor = new Actor(newId, fullName, password);

            actorManager.createActor(fullName, password);

            req.getSession().setAttribute("login", actor.getIdActor());
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("login", -1);
            req.setAttribute("errorMsg", "Mots de passe différents.");
            resp.sendRedirect(req.getContextPath() + "/register");
        }

    }
}
