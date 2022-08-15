package com.example.pc_shop.dao;

import com.example.pc_shop.connect_MySQL.ConnectMySQL;
import com.example.pc_shop.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    ConnectMySQL connectionSQL = new ConnectMySQL();
    PreparedStatement ps;
    ResultSet rs;
    private int noOfRecords;
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer(fullName,password,address,phone,email,idRole) values (?,?,?,?,?,?)";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT idCustomer,fullName,password,address,phone,email,idRole FROM customer where idCustomer = ?";
    private static final String SELECT_CUSTOMER_ALL = "SELECT * FROM customer";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customer WHERE idCustomer = ?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer " +
            "SET fullName = ? , password = ? , address = ? , phone = ? , email = ? , idRole = ? " +
            "where idCustomer = ?";
    private static final String SELECT_CUSTOMER_BY_EMAIL = "select c.idCustomer,c.fullName,c.password,c.address,c.phone,c.email,c.idRole \n" +
            "from customer as c inner join role as r where c.email = ? and c.idRole = r.idRole;";
    private static final String SELECT_CUSTORMER_BY_PHONE = "select c.idCustomer,c.fullName,c.password,c.address,c.phone,c.email,c.idRole \n" +
            "from customer as c inner join role as r where c.phone = ? and c.idRole = r.idRole;";

    @Override
    public List<Customer> selectAllCustomer() throws SQLException, ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();
        Connection connection = connectionSQL.getConnection();
        ps = connection.prepareStatement(SELECT_CUSTOMER_ALL);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("idCustomer");
            String name = rs.getString("fullName");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            int idRole = rs.getInt("idRole");
            customers.add(new Customer(id, name, password, address, phone, email, idRole));
        }
        connection.close();
        return customers;
    }

    @Override
    public Customer selectCustomer(int id) throws SQLException, ClassNotFoundException {
        Customer customer = null;
        Connection connection = connectionSQL.getConnection();

        ps = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("fullName");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            int idRole = rs.getInt("idRole");
            customer = new Customer(id, name, password, address, phone, email, idRole);
        }
        connection.close();
        return customer;
    }

    @Override
    public void insertCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = connectionSQL.getConnection();
        ps = connection.prepareStatement(INSERT_CUSTOMER_SQL);
        ps.setString(1, customer.getFullName());
        ps.setString(2, customer.getPassword());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getPhone());
        ps.setString(5, customer.getEmail());
        ps.setInt(6, customer.getIdRole());
        ps.executeUpdate();
        connection.close();
    }

    @Override
    public boolean deleteCustomer(int id) throws ClassNotFoundException, SQLException {
        boolean flag;
        Connection connection = connectionSQL.getConnection();

        ps = connection.prepareStatement(DELETE_CUSTOMER_SQL);
        ps.setInt(1, id);
        flag = ps.executeUpdate() > 0;
        connection.close();
        return flag;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        boolean flag;
        Connection connection = connectionSQL.getConnection();

        ps = connection.prepareStatement(UPDATE_CUSTOMER_SQL);
        ps.setString(1, customer.getFullName());
        ps.setString(2, customer.getPassword());
        ps.setString(3, customer.getAddress());
        ps.setString(4, customer.getPhone());
        ps.setString(5, customer.getEmail());
        ps.setInt(6, customer.getIdRole());
        ps.setInt(7, customer.getIdCustomer());

        flag = ps.executeUpdate() > 0;
        connection.close();
        return flag;
    }

    @Override
    public List<Customer> getNumberPage(int offset, int noOfRecords, String q) throws ClassNotFoundException, SQLException {
        Connection connection = connectionSQL.getConnection();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM customer where fullname like ? limit " + offset + "," + noOfRecords;
        List<Customer> list = new ArrayList<>();
        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + q + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setIdCustomer(rs.getInt("idCustomer"));
            customer.setFullName(rs.getString("fullName"));
            customer.setPassword(rs.getString("password"));
            customer.setAddress(rs.getString("address"));
            customer.setPhone(rs.getString("phone"));
            customer.setEmail(rs.getString("email"));
            customer.setIdRole(rs.getInt("idRole"));
            list.add(customer);
        }
        rs = ps.executeQuery("SELECT FOUND_ROWS()");
        if (rs.next()) {
            this.noOfRecords = rs.getInt(1);
        }
        connection.close();
        return list;
    }

    @Override
    public List<Customer> searchByName(String search) throws ClassNotFoundException, SQLException {
        String query = "select * from customer where fullname like ?";
        List<Customer> customerList = new ArrayList<>();
        Connection connection = connectionSQL.getConnection();
        ps = connection.prepareStatement(query);
        ps.setString(1, "%" + search + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("idCustomer");
            String name = rs.getString("fullName");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            int idRole = rs.getInt("idRole");
            customerList.add(new Customer(id, name, password, address, phone, email, idRole));
        }
        return customerList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public Customer selectCustomerByEmail(String email) throws ClassNotFoundException, SQLException {
        Customer customer = null;
        Connection connection = connectionSQL.getConnection();
        ps = connection.prepareStatement(SELECT_CUSTOMER_BY_EMAIL);
        ps.setString(1, email);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("idCustomer");
            String address = rs.getString("address");
            String name = rs.getString("fullName");
            String password = rs.getString("password");


            String phone = rs.getString("phone");
            int idRole = rs.getInt("idRole");
            customer = new Customer(id, name, password, address, phone, email, idRole);
            return customer;
        }
        connection.close();
        return null;
    }

    @Override
    public Customer selectCustomerByphone(String phone) throws ClassNotFoundException, SQLException {
        Customer customer = null;
        Connection connection = connectionSQL.getConnection();
        ps = connection.prepareStatement(SELECT_CUSTORMER_BY_PHONE);
        ps.setString(1, phone);
        rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("idCustomer");
            String address = rs.getString("address");
            String name = rs.getString("fullName");
            String password = rs.getString("password");
            String email = rs.getString("email");
            int idRole = rs.getInt("idRole");
            customer = new Customer(id, name, password, address, phone, email, idRole);
            return customer;
        }
        connection.close();
        return null;
    }

    @Override
    public Customer login(String email, String password) throws ClassNotFoundException, SQLException {
        Connection connection = connectionSQL.getConnection();
        String query = "SELECT * FROM customer where email = ? and password = ?";
        ps = connection.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("idCustomer");
            String address = rs.getString("address");
            String name = rs.getString("fullName");
            String phone = rs.getString("phone");
            int idRole = rs.getInt("idRole");
            return new Customer(id, name, password, address, phone, email, idRole);
        }
        return null;
    }
}
