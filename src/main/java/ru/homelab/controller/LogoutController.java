package ru.homelab.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.homelab.aspect.logging.Logging;

import java.io.IOException;

import static ru.homelab.util.UrlPath.LOGOUT;

@WebServlet(LOGOUT)
public class LogoutController extends HttpServlet {
    @Logging(message = "logout")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}


