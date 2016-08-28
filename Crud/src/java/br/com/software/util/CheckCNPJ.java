/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.software.util;

/**
 * @date 08-28-2016
 * @author Vinicius A. de Moraes
 */
public class CheckCNPJ {

    private static final int[] CNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcDigit(String str, int[] peso) {
        int add = 0;
        for (int indice = str.length() - 1, digit; indice >= 0; indice--) {
            digit = Integer.parseInt(str.substring(indice, indice + 1));
            add += digit * peso[peso.length - str.length() + indice];
        }
        add = 11 - add % 11;
        return add > 9 ? 0 : add;
    }

    public static boolean isValidCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }

        Integer digit1 = calcDigit(cnpj.substring(0, 12), CNPJ);
        Integer digit2 = calcDigit(cnpj.substring(0, 12) + digit1, CNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digit1.toString() + digit2.toString());
    }
}
