/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @date 08-28-2016
 * @author Vinicius A. de Moraes
 */
public class Message {

    public static void sendMessage(String pMsg, String pType) {
        if (pType.equals("Success")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, pMsg, ""));
        } else if (pType.equals("Fail")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, pMsg, ""));
        }

    }
}
