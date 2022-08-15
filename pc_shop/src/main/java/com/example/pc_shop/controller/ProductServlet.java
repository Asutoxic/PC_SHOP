package com.example.pc_shop.controller;

import com.example.pc_shop.dao.ProductDAO;
import com.example.pc_shop.model.Category;
import com.example.pc_shop.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    ProductDAO productDAO;
    String errors = "";

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showCreateProduct(req, resp);
                    break;
                case "edit":
                    showEditProduct(req, resp);
                    break;
                case "delete":
                    showDeleteProduct(req, resp);
                    break;
                default:
                    listProduct(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> listCategory = productDAO.getAllCategory();
        Product product = new Product();
        req.setAttribute("listP", product);
        req.setAttribute("listC", listCategory);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/products/createProduct.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showEditProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int idProduct = Integer.parseInt(req.getParameter("idProduct"));
        Product product = productDAO.selectProductById(idProduct);
        List<Category> listCategory = productDAO.getAllCategory();


        req.setAttribute("listEdit", product);
        req.setAttribute("listC", listCategory);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/products/editProduct.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int idProduct = Integer.parseInt(req.getParameter("idProduct"));
        productDAO.deleteProduct(idProduct);

        int page = 1;
        int recordsPerPage = 6;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        Product lastProduct = productDAO.getLast();
        List<Category> listCategory = productDAO.getAllCategory();
        List<Product> listP = productDAO.selecAllProduct((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("listP", listP);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("lastP", lastProduct);
        req.setAttribute("listC", listCategory);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/products/products.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int page = 1;
        int recordsPerPage = 6;
        String q = "";
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (req.getParameter("q") != null) {
            q = req.getParameter("q");
        }
        Product lastProduct = productDAO.getLast();
        List<Category> listCategory = productDAO.getAllCategory();
        List<Product> listP = productDAO.selecAllProduct((page - 1) * recordsPerPage, recordsPerPage, q);
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("listP", listP);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("lastP", lastProduct);
        req.setAttribute("listC", listCategory);
        req.setAttribute("q", q);
        System.out.println(q);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/view/products/products.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProduct(req, resp);
                    break;
                case "edit":
                    updateProduct(req, resp);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<String, String>();  // Luu lai truong nao bi loi va loi gi
        try {
            product.setIdProduct(Integer.parseInt(req.getParameter("idProduct")));
            Product checkProduct = productDAO.selectProductById(Integer.parseInt(req.getParameter("idProduct")));
            product.setTitle(req.getParameter("title"));
            product.setPrice(Double.parseDouble(req.getParameter("price")));
            product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            product.setImage(req.getParameter("image"));
            product.setIdCategory(Integer.parseInt(req.getParameter("idCategory")));
            product.setDescription(req.getParameter("description"));


            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

            if (!constraintViolations.isEmpty()) {

                errors = "<ul>";
                // constraintViolations is has error
                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                            + "</li>";
                }
                errors += "</ul>";


                req.setAttribute("listEdit", product);
                req.setAttribute("errors", errors);

                List<Category> listCategory = productDAO.getAllCategory();
                req.setAttribute("listC", listCategory);

                req.getRequestDispatcher("/WEB-INF/view/products/editProduct.jsp").forward(req, resp);
            } else {

                if (flag) {
                    productDAO.updateProduct(product);
                    Product c = new Product();
                    req.setAttribute("listEdit", c);
                    req.setAttribute("message", "Sửa thành công !!!");
                    req.getRequestDispatcher("/WEB-INF/view/products/editProduct.jsp").forward(req, resp);
                } else {
                    errors = "<ul>";
                    hashMap.forEach(new BiConsumer<String, String>() {
                        @Override
                        public void accept(String keyError, String valueError) {
                            errors += "<li>" + valueError
                                    + "</li>";
                        }
                    });
                    errors += "</ul>";
                    req.setAttribute("listEdit", product);
                    req.setAttribute("errors", errors);
                    req.getRequestDispatcher("/WEB-INF/view/products/editProduct.jsp").forward(req, resp);
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println(this.getClass() + " NumberFormatException: User info from request: " + product);
            errors = "<ul>";
            errors += "<li>" + "Lỗi định dạng số  !!!"
                    + "</li>";

            errors += "</ul>";


            req.setAttribute("listEdit", product);
            req.setAttribute("errors", errors);

            req.getRequestDispatcher("/WEB-INF/view/products/editProduct.jsp").forward(req, resp);
        } catch (Exception ex) {
            System.out.println("Không Tìm Thấy");
        }
    }

    private void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<String, String>();  // Lưu lại trường nào bị lỗi và là lỗi gì
        try {
            product.setIdProduct(Integer.parseInt(req.getParameter("idProduct")));
            product.setTitle(req.getParameter("title"));
            product.setPrice(Double.parseDouble(req.getParameter("price")));
            product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            product.setImage(req.getParameter("image"));
            product.setIdCategory(Integer.parseInt(req.getParameter("idCategory")));
            product.setDescription(req.getParameter("description"));


            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

            if (!constraintViolations.isEmpty()) {

                errors = "<ul>";
                // constraintViolations is has error
                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
                            + "</li>";
                }
                errors += "</ul>";


                req.setAttribute("listP", product);
                req.setAttribute("errors", errors);

                List<Category> listCategory = productDAO.getAllCategory();
                req.setAttribute("listC", listCategory);

                req.getRequestDispatcher("/WEB-INF/view/products/createProduct.jsp").forward(req, resp);
            } else {

                if (flag) {
                    productDAO.insertProduct(product);
                    Product c = new Product();
                    req.setAttribute("listP", c);
                    req.setAttribute("message", "Insert success...........");
//                    req.getRequestDispatcher("/WEB-INF/view/products/createProduct.jsp").forward(req, resp);
                    resp.sendRedirect("/product?action=create");
                } else {
                    errors = "<ul>";
                    hashMap.forEach(new BiConsumer<String, String>() {
                        @Override
                        public void accept(String keyError, String valueError) {
                            errors += "<li>" + valueError
                                    + "</li>";
                        }
                    });
                    errors += "</ul>";
                    req.setAttribute("listP", product);
                    req.setAttribute("errors", errors);
                    req.getRequestDispatcher("/WEB-INF/view/products/createProduct.jsp").forward(req, resp);
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println(this.getClass() + " NumberFormatException: User info from request: " + product);
            errors = "<ul>";
            errors += "<li>" + "Không được bỏ trống !!!"
                    + "</li>";

            errors += "</ul>";


            req.setAttribute("listP", product);
            req.setAttribute("errors", errors);

            req.getRequestDispatcher("/WEB-INF/view/products/createProduct.jsp").forward(req, resp);
        } catch (Exception ex) {
            System.out.println("Không Tìm Thấy");
        }
    }
}
