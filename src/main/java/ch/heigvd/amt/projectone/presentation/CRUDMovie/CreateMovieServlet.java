package ch.heigvd.amt.projectone.presentation.CRUDMovie;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class representing a servlet used to create movies
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "CreateMovieServlet", urlPatterns = "/createMovie")
public class CreateMovieServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private ClipManagerLocal clipManager;

    @EJB
    private CharacterManagerLocal characterManager;

    /**
     * Method called on a GET request on the servlet
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/createMovie.jsp").forward(req, resp);
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
        String title = req.getParameter("title");
        String character = req.getParameter("character");

        long idActor = Long.parseLong(req.getSession().getAttribute("login").toString());


        long idNewMovie = clipManager.createClip(title).getIdMovie();
        characterManager.createCharacter(character, idActor, idNewMovie);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
