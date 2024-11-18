package cz.upce.fei.nnptp.zz.entity;

import java.util.HashMap;

/**
 * @author Roman
 */
public record Password(int id, String password, HashMap<String, Parameter> parameters) {


    public Password(int id, String password) {
        this(id, password, new HashMap<>());
    }

    public boolean hasParameter(String title) {
        return parameters.containsKey(title);
    }

    public Parameter getParameter(String title) {
        return parameters.get(title);
    }


}
