package ch.heigvd.amt.projectone.controller;

import ch.heigvd.amt.projectone.model.Actor;
import ch.heigvd.amt.projectone.services.dao.ActorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Actor actor = ActorManager.
    }
}
