package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;
import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.model.Actor;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    @EJB
    private ActorManagerLocal actorManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        long userID = -1;
        try {
            userID = Long.parseLong(username);
        } catch (NumberFormatException e){
        }

        Actor actor = actorManager.findActorByID(userID);

        if(actor != null && actor.getPassword().equals(password)) {
            req.getSession().setAttribute("login", actor.getIdActor());
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            String errorMessage = "Nom d'utilisateur ou mot de passe incorrect!";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/login.jsp");
            rd.forward(req, resp);
        }
    }
}
