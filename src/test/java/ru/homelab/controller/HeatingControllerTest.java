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
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.service.MeterValueService;

import java.io.*;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeatingControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private MeterValueService service;
    @InjectMocks
    private HeatingController controller;

    @Test
    void doGet_SC_OK() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        doReturn(List.of()).when(service).allValuesUser(MeterTypeName.HEATING);
        StringWriter writer = new StringWriter();
        doReturn(new PrintWriter(writer)).when(response).getWriter();

        controller.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doPost_SC_OK() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String json = objectMapper.writeValueAsString(22);
        doReturn(new BufferedReader(new StringReader(json))).when(request).getReader();

        controller.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void doPost_SC_CONFLICT() throws IOException, ServletException, ValueAlreadyExistsException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String json = objectMapper.writeValueAsString(22);
        doReturn(new BufferedReader(new StringReader(json))).when(request).getReader();
        doThrow(ValueAlreadyExistsException.class).when(service).addValue(22, MeterTypeName.HEATING);

        controller.doPost(request, response);

        verify(response).setStatus(HttpServletResponse.SC_CONFLICT);
    }
}