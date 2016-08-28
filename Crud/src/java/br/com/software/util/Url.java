/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.util;

import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Develiper
 */
public class Url {

    public static void changeURL(String pDirectory) throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesContext.getCurrentInstance().getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + pDirectory);
    }
}
