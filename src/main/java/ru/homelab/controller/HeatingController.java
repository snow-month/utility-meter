package ru.homelab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.homelab.aspect.logging.Logging;
import ru.homelab.entity.MeterTypeName;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.repository.impl.MeterValueRepositoryImpl;
import ru.homelab.service.DBConnectionProvider;
import ru.homelab.service.MeterValueService;
import ru.homelab.service.impl.DBConnectionProviderImpl;
import ru.homelab.service.impl.MeterValueServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

import static ru.homelab.util.UrlPath.HEATING;

@WebServlet(HEATING)
public class HeatingController extends HttpServlet {
    private MeterValueService meterValueService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        DBConnectionProvider dbConnectionProvider = new DBConnectionProviderImpl();
        MeterValueRepository meterValueRepository = new MeterValueRepositoryImpl(dbConnectionProvider);
        this.meterValueService = new MeterValueServiceImpl(meterValueRepository);
    }

    @Logging(message = "получение всех значений отопления")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try (var printWriter = resp.getWriter()) {
            var meterValueDtos = meterValueService.allValuesUser(MeterTypeName.HEATING);
            printWriter.println(objectMapper.writeValueAsString(meterValueDtos));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Logging(message = "добавление значения отопления")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int value = Integer.parseInt(readJson(req));
            meterValueService.addValue(value, MeterTypeName.HEATING);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (ValueAlreadyExistsException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
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