package ch.heigvd.amt.projectone.presentation.CRUDMovie;

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
import java.util.List;

@WebServlet(name = "UpdateMovieServlet", urlPatterns = "/updateMovie")
public class UpdateMovieServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private ClipManagerLocal clipManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/updateMovie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newTitle = req.getParameter("newTitle");
        long idToUpdate = Long.parseLong(req.getParameter("idMovieToUpdate"));
        String errorMessage;
        if(clipManager.containsMovieId(Long.parseLong(req.getSession().getAttribute("login").toString()), idToUpdate)) {
            clipManager.updateClip(idToUpdate, newTitle);

            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            errorMessage = "ID incorrecte";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/updateMovie.jsp");
            rd.forward(req, resp);
        }
    }
}
