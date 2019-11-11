package ch.heigvd.amt.projectone.presentation.CRUDMovie;

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

/**
 * Class used to represent a servlet used to delete movies
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "DeleteMovieServlet", urlPatterns = "/deleteMovie")
public class DeleteMovieSerlvet extends javax.servlet.http.HttpServlet {

    @EJB
    ClipManagerLocal clipManager;

    /**
     * Method called on a GET request on the servlet
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/deleteMovie.jsp").forward(req, resp);
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
        long idMovieToDelete = Long.parseLong(req.getParameter("idMovieToDelete"));
        String errorMessage;
        if(clipManager.containsMovieId(Long.parseLong(req.getSession().getAttribute("login").toString()), idMovieToDelete)){
            clipManager.deleteClip(idMovieToDelete);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            errorMessage = "ID incorrecte";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/deleteMovie.jsp");
            rd.forward(req, resp);
        }


    }
}