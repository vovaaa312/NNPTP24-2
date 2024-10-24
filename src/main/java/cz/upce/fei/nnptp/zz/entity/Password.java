/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import java.util.HashMap;

/**
 * @author Roman
 */
public record Password(int id, String password, HashMap<String, Parameter> parameters) {


    public Password(int id, String password) {
        this(id, password, new HashMap<>());
    }

    boolean hasParameter(String title) {
        return parameters.containsKey(title);
    }

    public Parameter getParameter(String title) {
        return parameters.get(title);
    }


}
