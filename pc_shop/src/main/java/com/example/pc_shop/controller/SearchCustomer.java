package com.example.pc_shop.controller;

import com.example.pc_shop.dao.CustomerDAO;
import com.example.pc_shop.model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet(urlPatterns = "/search")
public class SearchCustomer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        try {
            CustomerDAO customerDAO = new CustomerDAO();
            String search = req.getParameter("search");
            List<Customer> customerList = customerDAO.searchByName(search);

            req.setAttribute("listCustomer",customerList);
            req.setAttribute("searchValue",search);


            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {

        }

    }
}
