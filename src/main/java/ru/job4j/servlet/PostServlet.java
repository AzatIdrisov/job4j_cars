package ru.job4j.servlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.model.Car;
import ru.job4j.model.CarBody;
import ru.job4j.model.CarMark;
import ru.job4j.model.User;
import ru.job4j.store.AdRepostiroty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        JSONObject obj = new JSONObject();
        List<Car> allCars = AdRepostiroty.instOf().getAllCars();
        JSONArray cars = new JSONArray();
        for (Car car : allCars) {
            JSONObject carObj = new JSONObject();
            carObj.put("id", car.getId());
            carObj.put("desc", car.getDescription());
            carObj.put("body", car.getBody().getName());
            carObj.put("mark", car.getMark().getName());
            carObj.put("author", car.getUser().getName());
            carObj.put("photo", car.getPhotoStatus());
            carObj.put("status", car.isStatus());
            cars.add(carObj);
        }
        obj.put("cars", cars);
        obj.put("curUser", currentUser.getName());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter writer = new PrintWriter(resp.getWriter());
        writer.write(obj.toJSONString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("utf-8");
        User user = (User) req.getSession().getAttribute("user");
        CarMark carMark = AdRepostiroty.instOf().findMarkByName(req.getParameter("car_mark"));
        CarBody carBody = AdRepostiroty.instOf().findBodyByName(req.getParameter("car_body"));
        AdRepostiroty.instOf().saveCarAd(
                new Car(req.getParameter("desc"),
                        user,
                        carMark,
                        carBody)
        );
        resp.sendRedirect("index.jsp");
    }
}
