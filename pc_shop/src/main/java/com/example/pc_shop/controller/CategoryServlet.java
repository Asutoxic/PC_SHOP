package com.example.pc_shop.controller;

import com.example.pc_shop.dao.ProductDAO;
import com.example.pc_shop.model.Category;
import com.example.pc_shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        ProductDAO productDAO = new ProductDAO();
        List<Product> list = productDAO.getProductById(idCategory);
        List<Category> listCategory = productDAO.getAllCategory();
        Product lastProduct = productDAO.getLast();

        req.setAttribute("lastP",lastProduct);
        req.setAttribute("listC",listCategory);
        req.setAttribute("listP",list);


        req.getRequestDispatcher("/WEB-INF/view/products/products.jsp").forward(req,resp);

    }
}
