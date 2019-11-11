package ch.heigvd.amt.projectone.presentation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class used to represent a security filter, called on every servlet listed in the urlPatterns field.
 * @author Guillaume Vetter & Jael Dubey
 */
@WebFilter(urlPatterns = {"", "/characters", "/actor", "/logout", "/createMovie", "/createMovie", "/updateMovie", "/deleteMovie", "/createCharacter", "/updateCharacter", "/deleteCharacter", "/updateActor", "/deleteActor"})
public class SecurityFilter implements Filter {

    /**
     * Method called when the filter is applied on a request
     * @param servletRequest the http request
     * @param servletResponse the http response
     * @param filterChain the rest of the filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //If the user is not connected, redirect on the login page
        if (req.getSession().getAttribute("login") == null || req.getSession().getAttribute("login").toString().equals("-1")) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
