package ch.heigvd.amt.projectone.presentation.CRUDCharacter;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCharacterServlet", urlPatterns = "/deleteCharacter")
public class DeleteCharacterServlet extends javax.servlet.http.HttpServlet {

    @EJB
    CharacterManagerLocal characterManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/deleteCharacter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idActor = Long.parseLong(req.getParameter("idActorToDelete"));
        long idMovie = Long.parseLong(req.getParameter("idMovieToDelete"));

        characterManager.deleteCharacter(idActor, idMovie);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}