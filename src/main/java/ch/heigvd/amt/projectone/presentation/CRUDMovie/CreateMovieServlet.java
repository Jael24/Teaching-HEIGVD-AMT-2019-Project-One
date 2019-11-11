package ch.heigvd.amt.projectone.presentation.CRUDMovie;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateMovieServlet", urlPatterns = "/createMovie")
public class CreateMovieServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private ClipManagerLocal clipManager;

    @EJB
    private CharacterManagerLocal characterManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/createMovie.jsp").forward(req, resp);
    }

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
