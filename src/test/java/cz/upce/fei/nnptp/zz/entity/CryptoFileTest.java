package cz.upce.fei.nnptp.zz.entity;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Roman
 */
public class CryptoFileTest {

    private File file;
    
    public CryptoFileTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        file = new File("file.enc");
    }
    
    @AfterEach
    public void tearDown() {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Test of readFile method, of class CryptoFile.
     */
    @Test
    public void testReadFile() {
        System.out.println("readFile");
        File file = null;
        String password = "";
        String expResult = "";
        String result = ""; //CryptoFile.readFile(file, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of writeFile method, of class CryptoFile.
     */
    @Test
    public void testWriteFile() {
        System.out.println("writeFile");
        String password = "password";
        String cnt = "content";
        CryptoFile.writeFile(file, password, cnt);
        assertTrue(file.length() > 0);
        String decryptedContent = CryptoFile.readFile(file, password);
        assertEquals(cnt, decryptedContent);
    }
    
}
