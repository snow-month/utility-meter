package ru.homelab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.homelab.entity.MeterTypeName;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.repository.impl.MeterValueRepositoryImpl;
import ru.homelab.service.DBConnectionProvider;
import ru.homelab.service.MeterValueService;
import ru.homelab.service.impl.DBConnectionProviderImpl;
import ru.homelab.service.impl.MeterValueServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/heating")
public class HeatingController extends HttpServlet {
    private MeterValueService meterValueService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        DBConnectionProvider dbConnectionProvider = new DBConnectionProviderImpl();
        MeterValueRepository meterValueRepository = new MeterValueRepositoryImpl(dbConnectionProvider);
        this.meterValueService = new MeterValueServiceImpl(meterValueRepository);
    }

    //    // user, admin
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            var meterValueDtos = meterValueService.allValuesUser(MeterTypeName.HEATING);
            printWriter.println(objectMapper.writeValueAsString(meterValueDtos));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    // todo addValue
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // todo
            meterValueService.addValue(22, MeterTypeName.HEATING);
        } catch (ValueAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    // todo читаем json post
//    StringBuffer jb = new StringBuffer();
//    String line = null;
//  try {
//        BufferedReader reader = request.getReader();
//        while ((line = reader.readLine()) != null)
//            jb.append(line);
//    } catch (Exception e) { /*report an error*/ }

//  try {
//        JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
//    } catch (JSONException e) {
//         crash and burn
//        throw new IOException("Error parsing JSON request string");
//    }

    // Work with the data using methods like...
    // int someInt = jsonObject.getInt("intParamName");
    // String someString = jsonObject.getString("stringParamName");
    // JSONObject nestedObj = jsonObject.getJSONObject("nestedObjName");
    // JSONArray arr = jsonObject.getJSONArray("arrayParamName");
    // etc...
}