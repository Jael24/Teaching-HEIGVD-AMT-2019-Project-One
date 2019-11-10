package ch.heigvd.amt.projectone.presentation.CRUDCharacter;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;
import ch.heigvd.amt.projectone.model.Movie;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateCharacterServlet", urlPatterns = "/createCharacter")
public class CreateCharacterServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private CharacterManagerLocal characterManager;

    @EJB
    private ClipManagerLocal clipManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/createCharacter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String character = req.getParameter("character");
        long idMovie = Long.parseLong(req.getParameter("idMovie"));

        long idActor = Long.parseLong(req.getSession().getAttribute("login").toString());

        Movie movie = clipManager.findClipById(idMovie);
        characterManager.createCharacter(character, idActor, movie.getIdMovie());

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
