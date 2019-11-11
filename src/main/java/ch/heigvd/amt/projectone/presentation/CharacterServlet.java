package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;
import ch.heigvd.amt.projectone.model.Character;
import ch.heigvd.amt.projectone.model.Movie;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class used to represent a servlet used to display the characters played by the user
 * @author Guillaume Vetter & Jael Dubey
 */
@WebServlet(name = "CharacterServlet", urlPatterns = "/characters")
public class CharacterServlet extends javax.servlet.http.HttpServlet {

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
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/WEB-INF/pages/characters.jsp").forward(req, resp);
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
        int start = Integer.parseInt(req.getParameter("start"));
        int length = Integer.parseInt(req.getParameter("length"));
        long actorId = Long.parseLong(req.getSession().getAttribute("login").toString());

        List<Character> characters = characterManager.findCharWhereActorHasPlayed(actorId, start, length);
        long nbChars = characterManager.countCharacters(actorId);
        long nbAllChars = characterManager.countAllCharacters();

        // Create the JSON to send to the datatable
        Gson gson = new Gson();
        HashMap response = new HashMap<>();
        response.put("recordsTotal", nbAllChars);
        response.put("recordsFiltered", nbChars);
        response.put("data", characters);

        // Send data as JSON to the datatable
        resp.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = resp.getOutputStream();
        out.write(gson.toJson(response).getBytes());
        out.flush();
    }
}
