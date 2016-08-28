/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.officer;

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
public class OfficerDAO {

    public Officer insertOfficer(Officer officer) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "INSERT INTO OFFICER (DATA_CADASTRO,ID_COMPANY,ID_DEPARTMENT,NOME,CPF,PIS,DATA_NASCIMENTO,TIPO_LOGRADOURO,LOGRADOURO,BAIRRO,CIDADE,UF,SITUACAO,CEP)"
                    + " VALUES"
                    + " (SYSDATETIME(),?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, officer.getIdCompany());
            ps.setLong(2, officer.getIdDepartment());
            ps.setString(3, officer.getNome());
            ps.setString(4, officer.getCpf());
            ps.setString(5, officer.getPis());
            try {
                ps.setDate(6, new java.sql.Date(officer.getDataNascimento().getTime()));
            } catch (NullPointerException e) {
                ps.setDate(6, null);
            }
            ps.setString(7, officer.getTipoLogradouro());
            ps.setString(8, officer.getLogradouro());
            ps.setString(9, officer.getBairro());
            ps.setString(10, officer.getCidade());
            ps.setString(11, officer.getUf());
            ps.setString(12, officer.getSituacao());
            ps.setString(13, officer.getCep());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(OfficerDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return officer;
    }

    public Officer updateOfficer(Officer officer) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "UPDATE OFFICER SET ID_COMPANY=?,ID_DEPARTMENT=?,NOME=?,CPF=?,PIS=?,DATA_NASCIMENTO=?,TIPO_LOGRADOURO=?,LOGRADOURO=?,BAIRRO=?,CIDADE=?,UF=?,SITUACAO=?,CEP=? WHERE ID_OFFICER=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, officer.getIdCompany());
            ps.setLong(2, officer.getIdDepartment());
            ps.setString(3, officer.getNome());
            ps.setString(4, officer.getCpf());
            ps.setString(5, officer.getPis());
            try {
                ps.setDate(6, new java.sql.Date(officer.getDataNascimento().getTime()));
            } catch (NullPointerException e) {
                ps.setDate(6, null);
            }
            ps.setString(7, officer.getTipoLogradouro());
            ps.setString(8, officer.getLogradouro());
            ps.setString(9, officer.getBairro());
            ps.setString(10, officer.getCidade());
            ps.setString(11, officer.getUf());
            ps.setString(12, officer.getSituacao());
            ps.setString(13, officer.getCep());
            ps.setLong(14, officer.getIdOfficer());

            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(OfficerDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return officer;
    }

    public List<Officer> listOfficer(ClausulaWhere sClausula) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "SELECT * FROM OFFICER " + sClausula.montarsClausula();

            List<Officer> listOfficer = new ArrayList<Officer>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Officer officer = new Officer();
                officer.setIdOfficer(rs.getLong("ID_OFFICER"));
                officer.setIdCompany(rs.getLong("ID_COMPANY"));
                officer.setIdDepartment(rs.getLong("ID_DEPARTMENT"));
                officer.setNome(rs.getString("NOME"));
                officer.setCpf(rs.getString("CPF"));
                officer.setPis(rs.getString("PIS"));
                officer.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
                officer.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                officer.setTipoLogradouro(rs.getString("TIPO_LOGRADOURO"));
                officer.setLogradouro(rs.getString("LOGRADOURO"));
                officer.setBairro(rs.getString("BAIRRO"));
                officer.setCidade(rs.getString("CIDADE"));
                officer.setUf(rs.getString("UF"));
                officer.setSituacao(rs.getString("SITUACAO"));
                officer.setCep(rs.getString("CEP"));
                officer.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                listOfficer.add(officer);
            }

            ps.execute();
            ps.close();

            return listOfficer;
        } catch (SQLException ex) {
            Logger.getLogger(OfficerDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean deleteOfficer(Officer officer) {
        try {
            Connect connect = new Connect();
            Connection conn = connect.getConnection();

            String sql = "DELETE FROM OFFICER WHERE ID_OFFICER=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, officer.getIdOfficer());

            ps.execute();
            ps.close();

            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
