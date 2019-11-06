package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.integration.ActorManagerLocal;
import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.model.Actor;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        Actor actor = actorManager.findActorByID(Long.getLong(username));

        if(actor != null && actor) {
        }


    }
}


/*
        if (usr != null) {
            if (usr.getPassword().equals(password)) {
                // Save the user id in the session
                req.getSession().setAttribute("user", usr);
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            } else {
                // The password for the given email is wrong
                req.setAttribute("error", "Wrong password !");
            }
        } else {
            // The email doesn't exist
            req.setAttribute("error", "Wrong user email !");
        }

        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}
 */