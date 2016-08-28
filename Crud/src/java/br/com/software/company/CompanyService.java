/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.company;

import br.com.orm.sql.ClausulaWhere;
import br.com.orm.sql.GeneroCondicaoWhere;
import br.com.orm.sql.OperacaoCondicaoWhere;
import br.com.orm.sql.TipoCondicaoWhere;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 08-28-2016
 * @author Vinicius A. de Moraes
 */
public class CompanyService {

    public Company SaveOrUpdateCompany(Company company) {
        CompanyDAO dao = new CompanyDAO();
        if (company.getIdCompany() == 0) {
            dao.insertCompany(company);
            return company;
        } else {
            dao.updateCompany(company);
            return company;
        }
    }

    public List<Company> listCompanyByName(String pName) {
        CompanyDAO dao = new CompanyDAO();
        List<Company> listCompany = new ArrayList<Company>();
        ClausulaWhere where = new ClausulaWhere();
        where.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "company.nome_fantasia", GeneroCondicaoWhere.contem, pName, TipoCondicaoWhere.Texto);
        listCompany = dao.listCompany(where);
        return listCompany;
    }

    public Company searchCompanyById(long pId) {
        CompanyDAO dao = new CompanyDAO();
        ClausulaWhere where = new ClausulaWhere();
        where.AdicionarCondicaoManual("where id_company = " + String.valueOf(pId));
        return dao.listCompany(where).get(0);
    }

    public boolean deleteCompany(Company company) {
        CompanyDAO dao = new CompanyDAO();
        return dao.deleteCompany(company);
    }

}
