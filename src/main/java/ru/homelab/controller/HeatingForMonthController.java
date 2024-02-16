package ru.homelab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.homelab.aspect.logging.Logging;
import ru.homelab.dto.DataDto;
import ru.homelab.entity.MeterTypeName;
import ru.homelab.exception.NoValueException;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.repository.impl.MeterValueRepositoryImpl;
import ru.homelab.service.DBConnectionProvider;
import ru.homelab.service.MeterValueService;
import ru.homelab.service.impl.DBConnectionProviderImpl;
import ru.homelab.service.impl.MeterValueServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

import static ru.homelab.util.UrlPath.HEATING_MONTH;

@WebServlet(HEATING_MONTH)
public class HeatingForMonthController extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private MeterValueService service;

    @Override
    public void init() throws ServletException {
        DBConnectionProvider dbConnectionProvider = new DBConnectionProviderImpl();
        MeterValueRepository meterValueRepository = new MeterValueRepositoryImpl(dbConnectionProvider);
        this.service = new MeterValueServiceImpl(meterValueRepository);
    }

    @Logging(message = "получение значения отопления за месяц")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String json = readJson(req);
        DataDto dataDto = objectMapper.readValue(json, DataDto.class);
        try (var printWriter = resp.getWriter()) {
            try {
                String year = dataDto.getYear();
                String month = dataDto.getMonth();
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