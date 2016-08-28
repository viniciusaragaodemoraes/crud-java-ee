/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.officer;

import br.com.software.company.Company;
import br.com.software.company.CompanyController;
import br.com.software.company.CompanyService;
import br.com.software.department.Department;
import br.com.software.department.DepartmentService;
import br.com.software.util.CheckCPF;
import br.com.software.util.CheckPIS;
import br.com.software.util.Message;
import br.com.software.util.SearchCEP;
import br.com.software.util.Status;
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
@ManagedBean(name = "officerController")
@ViewScoped
public class OfficerController {

    Officer officer = new Officer();
    OfficerService officerService = new OfficerService();
    List<Officer> listOfficer = new ArrayList<Officer>();

    DepartmentService departmentService = new DepartmentService();
    List<Department> listDepartment = new ArrayList<Department>();

    CompanyService companyService = new CompanyService();
    List<Company> listCompany = new ArrayList<Company>();
    String search = "";
    Integer screen = 0;

    public void goToIndex() {
        try {
            Url.changeURL("/index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OfficerController() {
        listOfficerByName();
        listDepartment = departmentService.listDepartmentByStatus(Status.ACTIVE);
        listCompany = companyService.listCompanyByName("");
    }

    public void newOfficer() {
        officer = new Officer();
        screen = 1;
    }

    public void listOfficerByName() {
        listOfficer = officerService.listOfficerByName(search);
    }

    public void SaveOrUpdateOfficer(int pScreen) {
        String cpf = officer.getCpf().replace(".", "").replace("-", "").replace("/", "");
        String pis = officer.getPis().replace(".", "").replace("-", "").replace("/", "");
        boolean validCPF = CheckCPF.isValidCPF(cpf);
        boolean validPis = CheckPIS.isValidPIS(pis);

        if (!validCPF) {
            Message.sendMessage("CPF Invalido!", "Fail");
            return;
        } else if (!validPis) {
            Message.sendMessage("PIS Invalido!", "Fail");
            return;
        }

        officer = officerService.SaveOrUpdateOfficer(officer);
        listOfficerByName();
        screen = pScreen;

        if (officer.getIdOfficer() != 0) {
            Message.sendMessage("Registro alterado com sucesso.", "Success");
        } else {
            Message.sendMessage("Registro armazenado com sucesso.", "Success");
        }

    }

    public void deleteOfficer() {
        if (officerService.deleteOfficer(officer)) {
            officer = new Officer();
            screen = 0;
            Message.sendMessage("Registro eliminado com sucesso.", "Success");
            listOfficerByName();
        } else {
            Message.sendMessage("Falha ao excluir registro.", "Fail");
        }
    }

    public void selectObjectOfficer(Officer pOfficer) {
        officer = pOfficer;
        screen = 1;
    }

    public void changeScreen(Integer pScreen) {
        screen = pScreen;
    }

    public void searchCEP() {
        SearchCEP cep = SearchCEP.searchCep(officer.getCep());
        if (cep.wasSuccessful()) {
            officer.setTipoLogradouro(cep.getLogradouroType());
            officer.setLogradouro(cep.getLogradouro());
            officer.setBairro(cep.getBairro());
            officer.setCidade(cep.getCidade());
            officer.setUf(cep.getUf());
        } else {
            Message.sendMessage("CEP Invalido!", "Fail");
        }
    }

// GET's e SET's
    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public OfficerService getOfficerService() {
        return officerService;
    }

    public void setOfficerService(OfficerService officerService) {
        this.officerService = officerService;
    }

    public List<Officer> getListOfficer() {
        return listOfficer;
    }

    public void setListOfficer(List<Officer> listOfficer) {
        this.listOfficer = listOfficer;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public List<Department> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(List<Department> listDepartment) {
        this.listDepartment = listDepartment;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
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
