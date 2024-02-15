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
import ru.homelab.dto.DataDto;
import ru.homelab.service.MeterValueService;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeatingForMonthControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private MeterValueService service;
    @InjectMocks
    private HeatingForMonthController controller;

    @Test
    void doPost_SC_SC_BAD_REQUEST() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String json = objectMapper.writeValueAsString(new DataDto());
        doReturn(new BufferedReader(new StringReader(json))).when(request).getReader();
        StringWriter writer = new StringWriter();
        doReturn(new PrintWriter(writer)).when(response).getWriter();

        controller.doPost(request, response);

        String message = objectMapper.readValue(writer.toString(), String.class);
        assertEquals("Bad Request", message);
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}