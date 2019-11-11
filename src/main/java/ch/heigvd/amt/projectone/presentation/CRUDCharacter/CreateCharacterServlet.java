package ch.heigvd.amt.projectone.presentation.CRUDCharacter;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;
import ch.heigvd.amt.projectone.model.Movie;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class representing a servlet used to create a character
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "CreateCharacterServlet", urlPatterns = "/createCharacter")
public class CreateCharacterServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private CharacterManagerLocal characterManager;

    @EJB
    private ClipManagerLocal clipManager;

    /**
     * Method called on a GET request on the servlet
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/createCharacter.jsp").forward(req, resp);
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
        String idMovie = req.getParameter("idMovie");
        long idMovieLong = Long.parseLong(idMovie);
        Movie clip = clipManager.findClipById(idMovieLong);
        String errorMessage;
        String character;
        if(clip != null) {
            character = req.getParameter("character");
            long idActor = Long.parseLong(req.getSession().getAttribute("login").toString());

            Movie movie = clipManager.findClipById(idMovieLong);
            characterManager.createCharacter(character, idActor, movie.getIdMovie());

            resp.sendRedirect(req.getContextPath() + "/characters");
        } else {
            errorMessage = "Ce film n'existe pas.";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/createCharacter.jsp");
            rd.forward(req, resp);
        }
    }
}
