/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.officer;

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
public class OfficerService {

    public Officer SaveOrUpdateOfficer(Officer officer) {
        OfficerDAO dao = new OfficerDAO();
        if (officer.getIdOfficer() == 0) {
            dao.insertOfficer(officer);
            return officer;
        } else {
            dao.updateOfficer(officer);
            return officer;
        }
    }

    public List<Officer> listOfficerByStatus(Status pStatus) {
        OfficerDAO dao = new OfficerDAO();
        List<Officer> listOfficer = new ArrayList<Officer>();
        ClausulaWhere where = new ClausulaWhere();
        if (pStatus == Status.ACTIVE) {
            where.AdicionarCondicao(OperacaoCondicaoWhere.and, "officer.situacao", GeneroCondicaoWhere.igual, "ATIVO", TipoCondicaoWhere.Texto);
        } else if (pStatus == Status.INATIVE) {
            where.AdicionarCondicao(OperacaoCondicaoWhere.and, "officer.situacao", GeneroCondicaoWhere.igual, "INATIVO", TipoCondicaoWhere.Texto);
        } else if (pStatus == Status.ALL) {
            where.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "officer.situacao", GeneroCondicaoWhere.contem, "", TipoCondicaoWhere.Texto);
        }
        listOfficer = dao.listOfficer(where);
        return listOfficer;
    }

    public List<Officer> listOfficerByName(String pName) {
        OfficerDAO dao = new OfficerDAO();
        List<Officer> listOfficer = new ArrayList<Officer>();
        ClausulaWhere where = new ClausulaWhere();
        where.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "officer.nome", GeneroCondicaoWhere.contem, pName, TipoCondicaoWhere.Texto);
        listOfficer = dao.listOfficer(where);
        return listOfficer;
    }

    public Officer searchOfficerById(long pId) {
        OfficerDAO dao = new OfficerDAO();
        ClausulaWhere where = new ClausulaWhere();
        where.AdicionarCondicaoManual("where id_officer = " + String.valueOf(pId));
        return dao.listOfficer(where).get(0);
    }

    public boolean deleteOfficer(Officer officer) {
        OfficerDAO dao = new OfficerDAO();
        return dao.deleteOfficer(officer);
    }
}
