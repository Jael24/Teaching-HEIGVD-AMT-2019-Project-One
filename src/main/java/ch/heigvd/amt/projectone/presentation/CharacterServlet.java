package ch.heigvd.amt.projectone.presentation;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.ClipManagerLocal;
import ch.heigvd.amt.projectone.model.Character;
import ch.heigvd.amt.projectone.model.Movie;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CharacterServlet", urlPatterns = "/characters")
public class CharacterServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private CharacterManagerLocal characterManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        long actorId = Long.parseLong(req.getSession().getAttribute("login").toString());

        List<Character> characters = characterManager.findCharWhereActorHasPlayed(actorId);


        req.getSession().setAttribute("characters", characters);
        req.getRequestDispatcher("/WEB-INF/pages/characters.jsp").forward(req, resp);

    }
}
