/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.department;

import br.com.orm.sql.ClausulaWhere;
import br.com.software.util.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date 08-28-2016
 * @author Vinicius A. de Moraes
 */
public class DepartmentDAO {

    public Department insertDepartment(Department department) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "INSERT INTO DEPARTMENT (DATA_CADASTRO,DESCRICAO,SITUACAO)"
                    + " VALUES"
                    + " (SYSDATETIME(),?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, department.getDescricao());
            ps.setString(2, department.getSituacao());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return department;
    }

    public Department updateDepartment(Department department) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "UPDATE DEPARTMENT SET DESCRICAO=?,SITUACAO=? WHERE ID_DEPARTMENT=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, department.getDescricao());
            ps.setString(2, department.getSituacao());
            ps.setLong(3, department.getIdDepartment());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return department;
    }

    public List<Department> listDepartment(ClausulaWhere sClausula) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "SELECT * FROM DEPARTMENT " + sClausula.montarsClausula();

            List<Department> listDepartment = new ArrayList<Department>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Department department = new Department();
                department.setIdDepartment(rs.getLong("ID_DEPARTMENT"));
                department.setDescricao(rs.getString("DESCRICAO"));
                department.setSituacao(rs.getString("SITUACAO"));
                department.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                listDepartment.add(department);
            }

            ps.execute();
            ps.close();

            return listDepartment;
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean deleteDepartment(Department department) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "DELETE FROM DEPARTMENT WHERE ID_DEPARTMENT=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, department.getIdDepartment());

            ps.execute();
            ps.close();

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
