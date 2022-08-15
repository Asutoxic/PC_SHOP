package com.example.pc_shop.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Customer {
    private int idCustomer;
    private String fullName;
    private String password;
    private String address;
    private String phone;
    private String email;
    private int idRole;

    public Customer() {
    }

    public Customer(int idCustomer, String fullName, String password, String address, String phone, String email, int idRole) {
        this.idCustomer = idCustomer;
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idRole = idRole;
    }

    public Customer(String fullName, String password, String address, String phone, String email, int idRole) {
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idRole = idRole;
    }

    public Customer(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @NotEmpty(message = "Không được bỏ trống !!!")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @NotEmpty
    @Length(min = 6, max = 10)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotEmpty
    @Pattern(regexp = "((84|0[1|2|3|4|5|6|7|8|9])+([0-9]{8})\\b)", message = "Format phone not right")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotEmpty
    @Email(message = "Email format not right")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$", message = "Format mail not right")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", idRole=" + idRole +
                '}';
    }
}
