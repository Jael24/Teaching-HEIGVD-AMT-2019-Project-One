package ch.heigvd.amt.projectone.presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class representing a servlet used to logout
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends javax.servlet.http.HttpServlet {

    /**
     * Method called on a GET request on the servlet
     * @param req the http request
     * @param resp the http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
