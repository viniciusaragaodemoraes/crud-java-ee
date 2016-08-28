/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.department;

import br.com.orm.sql.ClausulaWhere;
import br.com.orm.sql.GeneroCondicaoWhere;
import br.com.orm.sql.OperacaoCondicaoWhere;
import br.com.orm.sql.TipoCondicaoWhere;
import br.com.software.util.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 08-28-2016
 * @author Vinicius A. de Moraes
 */
public class DepartmentService {

    public Department SaveOrUpdateDepartment(Department department) {
        DepartmentDAO dao = new DepartmentDAO();
        if (department.getIdDepartment() == 0) {
            dao.insertDepartment(department);
            return department;
        } else {
            dao.updateDepartment(department);
            return department;
        }
    }

    public List<Department> listDepartmentByStatus(Status pStatus) {
        DepartmentDAO dao = new DepartmentDAO();
        List<Department> listDepartment = new ArrayList<Department>();
        ClausulaWhere where = new ClausulaWhere();
        if (pStatus == Status.ACTIVE) {
            where.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "department.situacao", GeneroCondicaoWhere.igual, "Ativo", TipoCondicaoWhere.Texto);
        } else if (pStatus == Status.INATIVE) {
            where.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "department.situacao", GeneroCondicaoWhere.igual, "Inativo", TipoCondicaoWhere.Texto);
        } else if (pStatus == Status.ALL) {
            where.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "department.situacao", GeneroCondicaoWhere.contem, "", TipoCondicaoWhere.Texto);
        }
        listDepartment = dao.listDepartment(where);
        return listDepartment;
    }

    public List<Department> listDepartmentByDesc(String pName) {
        DepartmentDAO dao = new DepartmentDAO();
        List<Department> listDepartment = new ArrayList<Department>();
        ClausulaWhere where = new ClausulaWhere();
        where.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "department.descricao", GeneroCondicaoWhere.contem, pName, TipoCondicaoWhere.Texto);
        listDepartment = dao.listDepartment(where);
        return listDepartment;
    }

    public Department searchDepartmentById(long pId) {
        DepartmentDAO dao = new DepartmentDAO();
        ClausulaWhere where = new ClausulaWhere();
        where.AdicionarCondicaoManual("where id_department = " + String.valueOf(pId));
        return dao.listDepartment(where).get(0);
    }

    public boolean deleteDepartment(Department department) {
        DepartmentDAO dao = new DepartmentDAO();
        return dao.deleteDepartment(department);
    }
}
