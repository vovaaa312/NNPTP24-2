package cz.upce.fei.nnptp.zz.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {

    @Test
    public void testToJsonEmptyPasswordList() {
        JsonConverter json = new JsonConverter();
        List<Password> passwords = new ArrayList<>();
        String result = json.toJson(passwords);
        assertEquals("[]", result, "Expected empty JSON array for empty password list.");
    }
    @Test
    public void testToJsonSinglePasswordNoParameters() {
        JsonConverter json = new JsonConverter();
        List<Password> passwords = new ArrayList<>();
        passwords.add(new Password(1, "password123"));
        String result = json.toJson(passwords);
        assertEquals("[{\"id\":1,\"password\":\"password123\"}]", result, "Expected JSON for a single password without parameters.");
    }
    @Test
    public void testToJsonMultiplePasswordsWithParameters() {
        JsonConverter json = new JsonConverter();
        HashMap<String, Parameter> parameters1 = new HashMap<>();
        parameters1.put(Parameter.StandardizedParameters.TITLE, new Parameter.TextParameter("Title"));
        LinkedHashMap<String, Parameter> parameters2 = new LinkedHashMap<>();
        parameters2.put(Parameter.StandardizedParameters.EXPIRATION_DATETIME, new Parameter.DateTimeParameter(LocalDateTime.of(2024, 11, 10, 23, 50)));
        parameters2.put(Parameter.StandardizedParameters.WEBSITE, new Parameter.TextParameter("https://www.upce.cz/"));

        List<Password> passwords = new ArrayList<>();
        passwords.add(new Password(1, "password123", parameters1));
        passwords.add(new Password(2, "password456", parameters2));

        String result = json.toJson(passwords);
        assertEquals("[{\"id\":1,\"password\":\"password123\",\"parameters\":{\"title\":\"Title\"}},"
                        + "{\"id\":2,\"password\":\"password456\",\"parameters\":{\"expiration-datetime\":\"2024-11-10T23:50\",\"website\":\"https://www.upce.cz/\"}}]",
                result, "Expected JSON for multiple passwords with parameters.");
    }
}