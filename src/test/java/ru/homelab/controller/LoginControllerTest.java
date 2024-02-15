package ru.homelab.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.homelab.dto.UserDto;
import ru.homelab.service.UserService;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService service;
    @InjectMocks
    private LoginController controller;

    @Test
    void doPost_SC_BAD_REQUEST() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String json = objectMapper.writeValueAsString(new UserDto());
        doReturn(new BufferedReader(new StringReader(json))).when(request).getReader();
        StringWriter writer = new StringWriter();
        doReturn(new PrintWriter(writer)).when(response).getWriter();

        controller.doPost(request, response);

        String message = objectMapper.readValue(writer.toString(), String.class);
        assertEquals("логин и пароль не должны быть пустыми", message);
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}