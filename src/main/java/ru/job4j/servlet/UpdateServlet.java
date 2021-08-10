package ru.job4j.servlet;

import ru.job4j.model.Car;
import ru.job4j.model.CarBody;
import ru.job4j.model.CarMark;
import ru.job4j.model.User;
import ru.job4j.store.AdRepostiroty;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("utf-8");
        String carIdStr = req.getParameter("carId");
        if (carIdStr != null) {
            AdRepostiroty.instOf().changeStatusToSold(Integer.parseInt(carIdStr));
            resp.sendRedirect("index.jsp");
        }
    }
}
