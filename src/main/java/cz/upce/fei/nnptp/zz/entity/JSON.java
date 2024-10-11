/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import java.util.List;
import java.util.HashMap;
/**
 *
 * @author Roman
 */
public class JSON {

    public String toJson(List<Password> passwords)  {
        String output = "[";
        for (Password password : passwords) {
            if (!output.isEmpty() && !output.equals("["))
                output += ",";
            output += "{";
            output += "\"id\":" + password.getId() + ",";
            output += "\"password\":\"" + password.getPassword()+"\"";
            HashMap<String, Parameter> parameters = password.getParameters();
            if (parameters != null && !parameters.isEmpty()) {
                output += ",\"parameters\":{";
                for (String key : parameters.keySet()) {
                    Parameter parameter = parameters.get(key);
                    output += "\"" + key + "\":" + parameter.toJson() + ",";
                }
                output = output.substring(0, output.length() - 1);
                output += "}";
            }
            output += "}";
        }
        output += "]";
        
        return output;
    }
    
    public List<Password> fromJson(String json) {
        throw new RuntimeException("NYI");
    }
}
