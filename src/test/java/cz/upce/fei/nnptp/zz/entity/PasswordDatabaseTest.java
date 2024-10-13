package cz.upce.fei.nnptp.zz.entity;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PasswordDatabaseTest {

    private File temporaryDirectory;

    @BeforeEach
    void setUp() {
        temporaryDirectory = new File(System.getProperty("java.io.tmpdir", "./"), "NNPTP24-2");
        temporaryDirectory.mkdirs();
        assertTrue(temporaryDirectory.exists(), "Temporary directory was not created");
    }

    @AfterEach
    void tearDown() throws Exception {
        FileUtils.deleteDirectory(temporaryDirectory);
    }

    @Test
    void testSaveEmpty() throws Exception {
        File testFile = new File(temporaryDirectory, "testEmpty.txt");

        PasswordDatabase passwordDatabase = new PasswordDatabase(testFile, "password");
        passwordDatabase.save();

        assertTrue(testFile.exists(), "DB file was not created");

        URL resource = PasswordDatabaseTest.class.getResource("testEmpty.txt");
        assertEquals(-1, Files.mismatch(testFile.toPath(), Paths.get(resource.toURI())), "Generated file content does not match the expected output");
    }

    @Test
    void testSaveFilled() throws Exception {
        File testFile = new File(temporaryDirectory, "testFilled.txt");

        PasswordDatabase passwordDatabase = new PasswordDatabase(testFile, "password");
        passwordDatabase.add(new Password(0, "pwd1"));
        passwordDatabase.add(new Password(1, "pwd2"));
        passwordDatabase.save();

        assertTrue(testFile.exists(), "DB file was not created");

        URL resource = PasswordDatabaseTest.class.getResource("testFilled.txt");
        assertEquals(-1, Files.mismatch(testFile.toPath(), Paths.get(resource.toURI())), "Generated file content does not match the expected output");
    }

    @Test
    void testSaveNullFile() {
        assertDoesNotThrow(() -> new PasswordDatabase(null, "password"));
    }

    @Test
    void testSaveNullPasswd() {
        File testFile = new File(temporaryDirectory, "test.txt");
        assertDoesNotThrow(() -> new PasswordDatabase(testFile, null));
        assertFalse(testFile.exists(), "DB file was created");
    }
}