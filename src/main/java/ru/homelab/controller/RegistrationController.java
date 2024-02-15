package ru.homelab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(UrlPath.REGISTRATION)
public class RegistrationController extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final DBConnectionProvider dbConnectionProvider = new DBConnectionProviderImpl();
    private final UserRepository userRepository = new UserRepositoryImpl(dbConnectionProvider);
    private final UserService userService = new UserServiceImpl(userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        try (var printWriter = resp.getWriter()) {
            UserDto userDto = objectMapper.readValue(readJson(req), UserDto.class);
            String login = userDto.getLogin();
            String password = userDto.getPassword();

            if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
                printWriter.println(objectMapper
                        .writeValueAsString("логин и пароль не должны быть пустыми"));
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                userService.create(userDto);
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
        } catch (ValidationException | SQLException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private String readJson(HttpServletRequest req) {
        StringBuilder jb = new StringBuilder();
        try {
            String line;
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            System.out.println("sql exception, read json: " + e.getMessage());
        }

        return jb.toString();
    }
}