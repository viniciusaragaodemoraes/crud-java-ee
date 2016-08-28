/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.company;

import br.com.software.util.CheckCNPJ;
import br.com.software.util.Message;
import br.com.software.util.SearchCEP;
import br.com.software.util.Url;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @date 08-28-2016
 * @author Vinicius A. de Moraes
 */
@ManagedBean(name = "companyController")
@ViewScoped
public class CompanyController {

    Company company = new Company();
    CompanyService companyService = new CompanyService();
    List<Company> listCompany = new ArrayList<Company>();
    String search = "";
    Integer screen = 0;

    public CompanyController() {
        listCompanyByName();
    }

    public void goToIndex() {
        try {
            Url.changeURL("/index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newCompany() {
        company = new Company();
        screen = 1;
    }

    public void listCompanyByName() {
        listCompany = companyService.listCompanyByName(search);
    }

    public void SaveOrUpdateCompany(int pScreen) {
        String cnpj = company.getCnpj().replace(".", "").replace("-", "").replace("/", "");
        boolean validCNPJ = CheckCNPJ.isValidCNPJ(cnpj);
        if (validCNPJ) {
            company = companyService.SaveOrUpdateCompany(company);
            listCompanyByName();
            screen = pScreen;
            if (company.getIdCompany() != 0) {
                Message.sendMessage("Registro alterado com sucesso.", "Success");
            } else {
                Message.sendMessage("Registro armazenado com sucesso.", "Success");
            }
        } else {
            Message.sendMessage("CNPJ Invalido!", "Fail");
        }
    }

    public void deleteCompany() {
        if (companyService.deleteCompany(company)) {
            company = new Company();
            screen = 0;
            Message.sendMessage("Registro eliminado com sucesso.", "Success");
            listCompanyByName();
        } else {
            Message.sendMessage("Falha ao excluir registro.", "Fail");
        }
    }

    public void selectObjectCompany(Company pCompany) {
        company = pCompany;
        screen = 1;
    }

    public void changeScreen(Integer pScreen) {
        screen = pScreen;
    }

    public void searchCEP() {
        SearchCEP cep = SearchCEP.searchCep(company.getCep());
        if (cep.wasSuccessful()) {
            company.setTipoLogradouro(cep.getLogradouroType());
            company.setLogradouro(cep.getLogradouro());
            company.setBairro(cep.getBairro());
            company.setCidade(cep.getCidade());
            company.setUf(cep.getUf());
        } else {
            Message.sendMessage("CEP Invalido!", "Fail");
        }
    }

// GET's e SET's
    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Company> getListCompany() {
        return listCompany;
    }

    public void setListCompany(List<Company> listCompany) {
        this.listCompany = listCompany;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getScreen() {
        return screen;
    }

    public void setScreen(Integer screen) {
        this.screen = screen;
    }

}
