/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import cz.upce.fei.nnptp.zz.entity.Parameter.DateTimeParameter;
import cz.upce.fei.nnptp.zz.entity.Parameter.PasswordParameter;
import cz.upce.fei.nnptp.zz.entity.Parameter.TextParameter;
import java.time.LocalDateTime;
import java.util.HashMap;
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
public class PasswordTest {

    public PasswordTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testPasswordValue() {
        Password ppwd = new Password(0, "pass");
        assertEquals("pass", ppwd.password(), "Password should match 'pass'");
    }

    @Test
    public void testHasParameter() {
        HashMap<String, Parameter> parameters = new HashMap<>();
        PasswordParameter pwdParam = new PasswordParameter("secret123");
        parameters.put("key", pwdParam);

        Password ppwd = new Password(0, "pass", parameters);

        assertTrue(ppwd.hasParameter("key"), "Parameter 'key' should exist");
        assertFalse(ppwd.hasParameter("nonexistentKey"), "Parameter 'nonexistentKey' should not exist");
    }

    @Test
    public void testGetPasswordParameterValue() {
        HashMap<String, Parameter> parameters = new HashMap<>();
        PasswordParameter pwdParam = new PasswordParameter("secret123");
        parameters.put("key", pwdParam);

        Password ppwd = new Password(0, "pass", parameters);

        PasswordParameter retrievedParam = (PasswordParameter) ppwd.getParameter("key");
        assertEquals("secret123", retrievedParam.getPassword(), "Parameter 'key' should return 'secret123' as its value");
    }

    @Test
    public void testGetTextParameterValue() {
        HashMap<String, Parameter> parameters = new HashMap<>();
        TextParameter textParam = new TextParameter("textParamTest");
        parameters.put("textKey", textParam);

        Password ppwd = new Password(0, "mainPass", parameters);

        TextParameter retrievedParam = (TextParameter) ppwd.getParameter("textKey");
        assertEquals("textParamTest", retrievedParam.getValue(), "Parameter 'textKey' should return 'exampleText' as its value");
    }

    @Test
    public void testGetDateTimeParameterValue() {
        HashMap<String, Parameter> parameters = new HashMap<>();
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeParameter dateTimeParam = new DateTimeParameter(currentTime);
        parameters.put("dateKey", dateTimeParam);

        Password ppwd = new Password(0, "mainPass", parameters);

        DateTimeParameter retrievedParam = (DateTimeParameter) ppwd.getParameter("dateKey");
        assertEquals(currentTime, retrievedParam.getValue(), "Parameter 'dateKey' should return the correct LocalDateTime value");
    }

    @Test
    public void testGetMultipleParametersValue() {
        HashMap<String, Parameter> parameters = new HashMap<>();

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeParameter dateTimeParam = new DateTimeParameter(currentTime);
        parameters.put("dateKey", dateTimeParam);

        TextParameter textParam = new TextParameter("textParamTest");
        parameters.put("textKey", textParam);

        PasswordParameter pwdParam = new PasswordParameter("secret123");
        parameters.put("key", pwdParam);

        Password ppwd = new Password(0, "mainPass", parameters);

        DateTimeParameter retrievedParamDate = (DateTimeParameter) ppwd.getParameter("dateKey");
        assertEquals(currentTime, retrievedParamDate.getValue(), "Parameter 'dateKey' should return the correct LocalDateTime value");

        TextParameter retrievedParamText = (TextParameter) ppwd.getParameter("textKey");
        assertEquals("textParamTest", retrievedParamText.getValue(), "Parameter 'textKey' should return 'exampleText' as its value");

        PasswordParameter retrievedParamPassword = (PasswordParameter) ppwd.getParameter("key");
        assertEquals("secret123", retrievedParamPassword.getPassword(), "Parameter 'key' should return 'secret123' as its value");
    }

}
