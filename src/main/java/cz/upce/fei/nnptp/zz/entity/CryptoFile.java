package cz.upce.fei.nnptp.zz.entity;

import java.io.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Roman
 */
public class CryptoFile {

    private static Cipher initializeCipher(String password, int mode) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), "DES");
        cipher.init(mode, secretKey);
        return cipher;
    }

    public static String readFile(File file, String password) {
        try (FileInputStream fis = new FileInputStream(file);
             CipherInputStream cis = new CipherInputStream(fis, initializeCipher(password, Cipher.DECRYPT_MODE));
             DataInputStream dis = new DataInputStream(cis)) {

            return dis.readUTF();
        } catch (Exception ex) {
            Logger.getLogger(CryptoFile.class.getName()).log(Level.SEVERE, "Failed to read file", ex);
        }
        return null;
    }

    public static void writeFile(File file, String password, String content) {
        try (FileOutputStream fos = new FileOutputStream(file);
             CipherOutputStream cos = new CipherOutputStream(fos, initializeCipher(password, Cipher.ENCRYPT_MODE));
             DataOutputStream dos = new DataOutputStream(cos)) {

            dos.writeUTF(content);
        } catch (Exception ex) {
            Logger.getLogger(CryptoFile.class.getName()).log(Level.SEVERE, "Failed to write file", ex);
        }
    }
}
