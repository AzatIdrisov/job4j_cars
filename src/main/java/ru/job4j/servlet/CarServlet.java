package ru.job4j.servlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.model.CarBody;
import ru.job4j.model.CarMark;
import ru.job4j.store.AdRepostiroty;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JSONObject obj = new JSONObject();
        JSONArray carMarks = new JSONArray();
        for (CarMark carMark : AdRepostiroty.instOf().getAllCarMarks()) {
            JSONObject markJSON = new JSONObject();
            markJSON.put("carMark", carMark.getName());
            carMarks.add(markJSON);
        }
        JSONArray carBodies = new JSONArray();
        for (CarBody carBody : AdRepostiroty.instOf().getAllCarBodies()) {
            JSONObject bodyJSON = new JSONObject();
            bodyJSON.put("carBody", carBody.getName());
            carBodies.add(bodyJSON);
        }
        obj.put("carMarks", carMarks);
        obj.put("carBodies", carBodies);
        writer.print(obj.toJSONString());
        writer.flush();
    }
}
