package ch.heigvd.amt.projectone.presentation.CRUDCharacter;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class used to represent a servlet used to delete a character
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "DeleteCharacterServlet", urlPatterns = "/deleteCharacter")
public class DeleteCharacterServlet extends javax.servlet.http.HttpServlet {

    @EJB
    CharacterManagerLocal characterManager;

    /**
     * Method called on a GET request on the servlet
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/deleteCharacter.jsp").forward(req, resp);
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
        long idChar = Long.parseLong(req.getParameter("idCharToDelete"));
        long idActor = Long.parseLong(req.getSession().getAttribute("login").toString());
        String errorMessage;
        if(characterManager.getActorIdByCharacter(idChar) == idActor){
            characterManager.deleteCharacter(idChar);
            resp.sendRedirect(req.getContextPath() + "/characters");
        } else {
            errorMessage = "ID incorrecte";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/deleteCharacter.jsp");
            rd.forward(req, resp);
        }

    }
}