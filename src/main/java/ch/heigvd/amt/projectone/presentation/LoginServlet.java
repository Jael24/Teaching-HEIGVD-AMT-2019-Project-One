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

/**
 * Class representing a servlet used to login
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {
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
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    /**
     * Method called on a POST request on the servlet
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Retrieving every login parameters
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        long userID = -1;
        try {
            userID = Long.parseLong(username);
        } catch (NumberFormatException e){
        }

        //Retrieving the actor corresponding to the user's credentials
        Actor actor = actorManager.findActorByID(userID);

        if(actor != null && actor.getPassword().equals(password)) {
            req.getSession().setAttribute("login", actor.getIdActor());
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            //In case of error, display the error message
            String errorMessage = "Nom d'utilisateur ou mot de passe incorrect!";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/login.jsp");
            rd.forward(req, resp);
        }
    }
}
