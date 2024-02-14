package ru.homelab.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.homelab.dto.UserDto;
import ru.homelab.exception.ValidationException;
import ru.homelab.repository.UserRepository;
import ru.homelab.repository.impl.UserRepositoryImpl;
import ru.homelab.service.DBConnectionProvider;
import ru.homelab.service.UserService;
import ru.homelab.service.impl.DBConnectionProviderImpl;
import ru.homelab.service.impl.UserServiceImpl;
import ru.homelab.util.UrlPath;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet(UrlPath.REGISTRATION)
public class RegistrationController extends HttpServlet {
    private final DBConnectionProvider dbConnectionProvider = new DBConnectionProviderImpl();
    private final UserRepository userRepository = new UserRepositoryImpl(dbConnectionProvider);
    private final UserService userService = new UserServiceImpl(userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        UserDto userDto = new UserDto(
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("role")
        );

        try {
            userService.create(userDto);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ValidationException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            throw new RuntimeException(e);
        }
    }
}
