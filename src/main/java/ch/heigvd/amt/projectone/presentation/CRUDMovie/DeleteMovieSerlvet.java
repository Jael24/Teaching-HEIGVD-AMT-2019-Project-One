package ch.heigvd.amt.projectone.presentation.CRUDMovie;

import ch.heigvd.amt.projectone.integration.ClipManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteMovieServlet", urlPatterns = "/deleteMovie")
public class DeleteMovieSerlvet extends javax.servlet.http.HttpServlet {

    @EJB
    ClipManagerLocal clipManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/deleteMovie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idMovieToDelete = Long.parseLong(req.getParameter("idMovieToDelete"));

        clipManager.deleteClip(idMovieToDelete);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}