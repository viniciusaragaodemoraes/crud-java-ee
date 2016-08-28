/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date 08-28-2016
 * @author Vinicius A. de Moraes
 */
public class CheckEmail {

    public static boolean isValidEmail(String pEmail) {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(pEmail);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }
}
