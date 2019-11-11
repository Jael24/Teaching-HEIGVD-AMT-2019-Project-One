package ch.heigvd.amt.projectone.presentation.CRUDCharacter;

import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;
import ch.heigvd.amt.projectone.integration.CharacterManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateCharacterServlet", urlPatterns = "/updateCharacter")
public class UpdateCharacterServlet extends javax.servlet.http.HttpServlet {

    @EJB
    private CharacterManagerLocal CharacterManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/updateCharacter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newCharacter = req.getParameter("newCharacter");
        long idCharacterToUpdate = Long.parseLong(req.getParameter("idCharacterToUpdate"));

        CharacterManager.updateCharacter(idCharacterToUpdate, newCharacter);

        resp.sendRedirect(req.getContextPath() + "/characters");
    }
}
