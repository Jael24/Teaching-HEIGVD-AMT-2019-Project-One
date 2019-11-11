package ch.heigvd.amt.projectone.presentation.CRUDCharacter;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class used to represent a servlet used to update a character
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "UpdateCharacterServlet", urlPatterns = "/updateCharacter")
public class UpdateCharacterServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private CharacterManagerLocal CharacterManager;

    /**
     * Method called on a GET request on the servlet
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/updateCharacter.jsp").forward(req, resp);
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
        String newCharacter = req.getParameter("newCharacter");
        long idCharacterToUpdate = Long.parseLong(req.getParameter("idCharacterToUpdate"));

        long actorId = CharacterManager.getActorIdByCharacter(idCharacterToUpdate);
        String errorMessage;

        if(actorId != Long.parseLong(req.getSession().getAttribute("login").toString())){
            errorMessage = "ID incorrecte";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/updateCharacter.jsp");
            rd.forward(req, resp);
        } else {
            CharacterManager.updateCharacter(idCharacterToUpdate, newCharacter);

            resp.sendRedirect(req.getContextPath() + "/characters");
        }
    }
}
