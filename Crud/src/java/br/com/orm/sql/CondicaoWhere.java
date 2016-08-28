/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.orm.sql;

/**
 *
 * @author Cross
 */
public class CondicaoWhere {

    String campo;
    String valor;
    GeneroCondicaoWhere genero;
    TipoCondicaoWhere tipo;
    OperacaoCondicaoWhere operacao;

    public void AdicionarCondicao(String campo, GeneroCondicaoWhere genero, String valor, TipoCondicaoWhere tipo, OperacaoCondicaoWhere operacao) {
        this.campo = campo;
        this.valor = valor;
        this.genero = genero;
        this.tipo = tipo;
        this.operacao = operacao;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public GeneroCondicaoWhere getGenero() {
        return genero;
    }

    public void setGenero(GeneroCondicaoWhere genero) {
        this.genero = genero;
    }

    public TipoCondicaoWhere getTipo() {
        return tipo;
    }

    public void setTipo(TipoCondicaoWhere tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public OperacaoCondicaoWhere getOperacao() {
        return operacao;
    }

    public void setOperacao(OperacaoCondicaoWhere operacao) {
        this.operacao = operacao;
    }
    
    
}
