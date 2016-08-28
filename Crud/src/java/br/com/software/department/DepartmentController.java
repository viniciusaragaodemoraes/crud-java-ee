/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.department;

import br.com.software.company.CompanyController;
import br.com.software.util.Message;
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
@ManagedBean(name = "departmentController")
@ViewScoped
public class DepartmentController {

    Department department = new Department();
    DepartmentService departmentService = new DepartmentService();
    List<Department> listDepartment = new ArrayList<Department>();
    String search = "";
    Integer screen = 0;

    public void goToIndex() {
        try {
            Url.changeURL("/index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DepartmentController() {
        listDepartmentByDesc();
    }

    public void newDepartment() {
        department = new Department();
        screen = 1;
    }

    public void listDepartmentByDesc() {
        listDepartment = departmentService.listDepartmentByDesc(search);
    }

    public void SaveOrUpdateDepartment(int pScreen) {
        department = departmentService.SaveOrUpdateDepartment(department);
        listDepartmentByDesc();
        screen = pScreen;
        if (department.getIdDepartment() != 0) {
            Message.sendMessage("Registro alterado com sucesso.", "Success");
        } else {
            Message.sendMessage("Registro armazenado com sucesso.", "Success");
        }
    }

    public void deleteDepartment() {
        if (departmentService.deleteDepartment(department)) {
            department = new Department();
            screen = 0;
            Message.sendMessage("Registro eliminado com sucesso.", "Success");
            listDepartmentByDesc();
        } else {
            Message.sendMessage("Falha ao excluir registro.", "Fail");
        }
    }

    public void selectObjectDepartment(Department pDepartment) {
        department = pDepartment;
        screen = 1;
    }

    public void changeScreen(Integer pScreen) {
        screen = pScreen;
    }

// GET's e SET's
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
