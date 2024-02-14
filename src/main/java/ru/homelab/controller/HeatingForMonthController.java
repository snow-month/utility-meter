package ru.homelab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.homelab.entity.MeterTypeName;
import ru.homelab.exception.NoValueException;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.repository.impl.MeterValueRepositoryImpl;
import ru.homelab.service.DBConnectionProvider;
import ru.homelab.service.MeterValueService;
import ru.homelab.service.impl.DBConnectionProviderImpl;
import ru.homelab.service.impl.MeterValueServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/heating/month/*")
public class HeatingForMonthController extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MeterValueService service;

    @Override
    public void init() throws ServletException {
        DBConnectionProvider dbConnectionProvider = new DBConnectionProviderImpl();
        MeterValueRepository meterValueRepository = new MeterValueRepositoryImpl(dbConnectionProvider);
        this.service = new MeterValueServiceImpl(meterValueRepository);
    }

    // todo dopost не возвращает данные read json???
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try (var printWriter = resp.getWriter()) {
            try {
                String year = req.getParameter("year");
                String month = req.getParameter("month");
                if (year == null || month == null) {
                    printWriter.println(objectMapper.writeValueAsString("Bad Request"));
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    Integer value = service.valueForMonth(
                            Integer.parseInt(year),
                            Integer.parseInt(month),
                            MeterTypeName.HEATING);

                    printWriter.println(objectMapper.writeValueAsString(value));
                    resp.setStatus(HttpServletResponse.SC_OK);
                }
            } catch (NoValueException e) {
                printWriter.println(objectMapper.writeValueAsString("Not Found"));
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}