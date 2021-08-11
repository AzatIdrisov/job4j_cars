package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.UserStore;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
            Optional<User> user = UserStore.instOf().findUserByEmailAndPassword(email, password);
            if(user.isPresent()){
                req.getSession().setAttribute("user", user.get());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Не верный email или пароль");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
    }
}
