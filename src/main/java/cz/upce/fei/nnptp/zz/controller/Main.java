package cz.upce.fei.nnptp.zz.controller;

import cz.upce.fei.nnptp.zz.entity.CryptoFile;
import cz.upce.fei.nnptp.zz.entity.JsonConverter;
import cz.upce.fei.nnptp.zz.entity.Password;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Enterprise password manager - in console
 * - uses strong industry-based encryption algorithm
 * - stores your passwords and relevent information (website, login, notes, ...)
 * - allows you to simply manage all stored informations
 * 
 * 
 * 
 */
public class Main {
    public static void main(String[] args) {
        // This is only temporary demo for existing API
        // main should not be primarily updated
        // main is currently not in focus for development
        // most development should focus on application APIs
        List<Password> pwds = new ArrayList<>();
        pwds.add(new Password(0, "sdfghjkl"));
        pwds.add(new Password(1, "ASDSAFafasdasdasdas"));
        pwds.add(new Password(2, "aaa-aaaa-"));
        String contents = new JsonConverter().toJson(pwds);
        
        CryptoFile.writeFile(new File("test.txt"), "password",  contents);
        
        String read = CryptoFile.readFile(new File("test.txt"), "password");
        System.out.println(read);
        
    }
   
}
