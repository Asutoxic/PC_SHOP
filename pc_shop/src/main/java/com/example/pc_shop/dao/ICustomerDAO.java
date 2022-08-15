package com.example.pc_shop.dao;

import com.example.pc_shop.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    List<Customer> selectAllCustomer() throws SQLException, ClassNotFoundException;

    Customer selectCustomer(int id) throws SQLException, ClassNotFoundException;

    void insertCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(int id) throws ClassNotFoundException, SQLException;

    boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException;

    List<Customer> getNumberPage(int offset, int noOfRecords, String q) throws ClassNotFoundException, SQLException;

    List<Customer> searchByName(String search) throws ClassNotFoundException, SQLException;

    int getNoOfRecords();

    Customer selectCustomerByEmail(String email) throws ClassNotFoundException, SQLException;

    Customer selectCustomerByphone(String phone) throws ClassNotFoundException, SQLException;

    Customer login(String email, String password) throws ClassNotFoundException, SQLException;
}
