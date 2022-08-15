package com.example.pc_shop.dao;

import com.example.pc_shop.model.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDAO {
    List<Role> selectAllRole() throws ClassNotFoundException, SQLException;

    void insertRole(Role role) throws SQLException, ClassNotFoundException;

    Role selectRole(int id) throws SQLException, ClassNotFoundException;

    boolean deleteRole(int id);

    boolean updateRole(Role role);
}
