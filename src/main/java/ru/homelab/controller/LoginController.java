package ru.homelab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.homelab.repository.UserRepository;
import ru.homelab.repository.impl.UserRepositoryImpl;
import ru.homelab.service.DBConnectionProvider;
import ru.homelab.service.UserService;
import ru.homelab.service.impl.DBConnectionProviderImpl;
import ru.homelab.service.impl.UserServiceImpl;
import ru.homelab.util.UrlPath;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(UrlPath.LOGIN)
public class LoginController extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final DBConnectionProvider dbConnectionProvider = new DBConnectionProviderImpl();
    private final UserRepository userRepository = new UserRepositoryImpl(dbConnectionProvider);
    private final UserService userService = new UserServiceImpl(userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        try (var printWriter = resp.getWriter()) {
            // todo читаем json
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if (login.isEmpty() || password.isEmpty()) {
                printWriter.println(objectMapper
                        .writeValueAsString("логин и пароль не должны быть пустыми"));
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                try {
                    userService.login(login, password)
                            .ifPresentOrElse(
                                    user -> {
                                        req.getSession().setAttribute("user", user);
                                    },
                                    () -> {
                                        try {
                                            invalidLoginOrPassword(printWriter, resp);
                                        } catch (JsonProcessingException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void invalidLoginOrPassword(PrintWriter printWriter, HttpServletResponse resp) throws JsonProcessingException {
        printWriter.println(objectMapper
                .writeValueAsString("некорректный логин или пароль"));
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
