package com.example.pc_shop.dao;

import com.example.pc_shop.model.Category;
import com.example.pc_shop.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {
    List<Product> getAllProduct();

    List<Category> getAllCategory();

    List<Product> getProductById(int idCategory);

    void insertProduct(Product product) throws ClassNotFoundException, SQLException;

    List<Product> selecAllProduct(int offset, int noOfRecords) throws ClassNotFoundException, SQLException;

    List<Product> selecAllProduct(int offset, int noOfRecords, String q) throws ClassNotFoundException, SQLException;

    int getNoOfRecords();

    boolean updateProduct(Product product) throws ClassNotFoundException, SQLException;

    Product selectProductById(int id) throws ClassNotFoundException, SQLException;

    boolean deleteProduct(int id) throws ClassNotFoundException, SQLException;

    Product getLast();
}
