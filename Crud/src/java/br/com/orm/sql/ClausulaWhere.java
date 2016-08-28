/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.orm.sql;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cross
 */
public class ClausulaWhere {

    List<CondicaoWhere> condicoes = new ArrayList<CondicaoWhere>();
    List<String> cs = new ArrayList<String>();

    public void AdicionarCondicao(CondicaoWhere condicao) {
        condicoes.add(condicao);
    }

    public String montarsClausula() {
        String cd = " ";
        for (int i = 0; i < cs.size(); i++) {
            cd = cd + cs.get(i);
        }
        return cd;

    }

    public void AdicionarCondicaoManual(String condicao) {
        cs.add(condicao);
    }

    public void AdicionarCondicao(OperacaoCondicaoWhere operacao, String campo, GeneroCondicaoWhere genero, String valor, TipoCondicaoWhere tipo) {
        CondicaoWhere c = new CondicaoWhere();
        c.setCampo(campo);
        c.setValor(valor);
        c.setGenero(genero);
        c.setTipo(tipo);
        c.setOperacao(operacao);
        condicoes.add(c);

        String cdc = " ";
        if (operacao.equals(operacao.and)) {
            cdc = cdc + " and ";
        } else if (operacao.equals(operacao.or)) {
            cdc = cdc + " or ";
        } else if (operacao.equals(operacao.vazio)) {
            cdc = cdc + " where ";
        }

        String vValor = " ";
        if (tipo.equals(TipoCondicaoWhere.Texto)) {
            //cdc = cdc + "upper(" + campo + ")";
            cdc = cdc + "upper(" + campo + ")";

            vValor = "'" + valor.toUpperCase() + "'";
        } else if (tipo.equals(TipoCondicaoWhere.Numero)) {
            cdc = cdc + campo;
            vValor = valor;
        } else if (tipo.equals(TipoCondicaoWhere.Data)) {
            cdc = cdc + "trunc(" + campo + ")";
            vValor = "trunc(to_date('" + valor + "','dd/mm/yyyy)'))";
        }

        if (genero.equals(genero.igual)) {
                cdc = cdc + " = " + vValor;
        } else if (genero.equals(genero.maior)) {
            cdc = cdc + " > " + vValor;
        } else if (genero.equals(genero.menor)) {
            cdc = cdc + " < " + vValor;
        } else if (genero.equals(genero.contem)) {
            cdc = cdc + " like ('%" + vValor.replaceAll("'", "").replaceAll(" ", "%") + "%')";
        } else if (genero.equals(genero.diferente)) {
            cdc = cdc + " <> " + vValor;
        } else if (genero.equals(genero.isNull)) {
            cdc = cdc + " is null ";
        } else if (genero.equals(genero.isNotNull)) {
            cdc = cdc + " is not null ";
        } else if (genero.equals(genero.MaiorIgual)) {
            cdc = cdc + " >= " + vValor;
        } else if (genero.equals(genero.MenorIgual)) {
            cdc = cdc + " <= " + vValor;
        }
        cs.add(cdc);
    }

    public List<CondicaoWhere> condicoes() {
        return condicoes;
    }

    public List<CondicaoWhere> getCondicoes() {
        return condicoes;
    }

    public void setCondicoes(List<CondicaoWhere> condicoes) {
        this.condicoes = condicoes;
    }

    public List<String> getCs() {
        return cs;
    }

    public void setCs(List<String> cs) {
        this.cs = cs;
    }
}
