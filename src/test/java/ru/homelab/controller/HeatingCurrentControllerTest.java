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
import ru.homelab.entity.MeterTypeName;
import ru.homelab.exception.NoValueException;
import ru.homelab.service.MeterValueService;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeatingCurrentControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private MeterValueService service;
    @InjectMocks
    private HeatingCurrentController controller;

    @Test
    void doGet_SC_OK() throws ServletException, IOException, NoValueException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        int expected = 22;
        doReturn(expected).when(service).currentValue(MeterTypeName.HEATING);
        StringWriter writer = new StringWriter();
        doReturn(new PrintWriter(writer)).when(response).getWriter();

        controller.doGet(request, response);

        Integer value = objectMapper.readValue(writer.toString(), Integer.class);
        assertEquals(expected, value);
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doGet_SC_NOT_FOUND() throws NoValueException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        doThrow(NoValueException.class).when(service).currentValue(MeterTypeName.HEATING);
        StringWriter writer = new StringWriter();
        doReturn(new PrintWriter(writer)).when(response).getWriter();

        controller.doGet(request, response);

        assertEquals(
                "Not Found",
                objectMapper.readValue(writer.toString(), String.class));
        verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}