/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.company;

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
public class CompanyDAO {

    public Company insertCompany(Company company) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "INSERT INTO COMPANY (DATA_CADASTRO,NOME_FANTASIA,CNPJ,TIPO_LOGRADOURO,LOGRADOURO,BAIRRO,CIDADE,UF,CEP)"
                    + " VALUES"
                    + " (SYSDATETIME(),?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, company.getNomeFantasia());
            ps.setString(2, company.getCnpj());
            ps.setString(3, company.getTipoLogradouro());
            ps.setString(4, company.getLogradouro());
            ps.setString(5, company.getBairro());
            ps.setString(6, company.getCidade());
            ps.setString(7, company.getUf());
            ps.setString(8, company.getCep());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return company;
    }

    public Company updateCompany(Company company) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "UPDATE COMPANY SET NOME_FANTASIA=?,CNPJ=?,TIPO_LOGRADOURO=?,LOGRADOURO=?,BAIRRO=?,CIDADE=?,UF=?,CEP=? WHERE ID_COMPANY=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, company.getNomeFantasia());
            ps.setString(2, company.getCnpj());
            ps.setString(3, company.getTipoLogradouro());
            ps.setString(4, company.getLogradouro());
            ps.setString(5, company.getBairro());
            ps.setString(6, company.getCidade());
            ps.setString(7, company.getUf());
            ps.setString(8, company.getCep());
            ps.setLong(9, company.getIdCompany());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return company;
    }

    public List<Company> listCompany(ClausulaWhere sClausula) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "SELECT * FROM COMPANY " + sClausula.montarsClausula();

            List<Company> listCompany = new ArrayList<Company>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Company company = new Company();
                company.setIdCompany(rs.getLong("ID_COMPANY"));
                company.setNomeFantasia(rs.getString("NOME_FANTASIA"));
                company.setCnpj(rs.getString("CNPJ"));
                company.setTipoLogradouro(rs.getString("TIPO_LOGRADOURO"));
                company.setLogradouro(rs.getString("LOGRADOURO"));
                company.setBairro(rs.getString("BAIRRO"));
                company.setCidade(rs.getString("CIDADE"));
                company.setUf(rs.getString("UF"));
                company.setCep(rs.getString("CEP"));
                company.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                listCompany.add(company);
            }

            ps.execute();
            ps.close();

            return listCompany;
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean deleteCompany(Company company) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "DELETE FROM COMPANY WHERE ID_COMPANY=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, company.getIdCompany());

            ps.execute();
            ps.close();

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
