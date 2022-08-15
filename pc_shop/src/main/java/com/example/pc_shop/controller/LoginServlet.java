package com.example.pc_shop.controller;

import com.example.pc_shop.dao.CustomerDAO;
import com.example.pc_shop.model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/login/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            Customer account = customerDAO.login(email,password);
            if (account == null){
                req.setAttribute("mess","Wrong user or password !!!");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/login/login.jsp");
                requestDispatcher.forward(req,resp);
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("acc",account);
                resp.sendRedirect("/home");

            }
        } catch (Exception e) {
        }

    }
}
