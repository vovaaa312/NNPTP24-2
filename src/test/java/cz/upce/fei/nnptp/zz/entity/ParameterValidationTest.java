package cz.upce.fei.nnptp.zz.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class ParameterValidationTest {

    @Test
    void testTextParameterValid() {
        Parameter.TextParameter param = new Parameter.TextParameter("Hello");
        assertDoesNotThrow(param::validate,
                "Valid text should not throw exception.");
    }

    @Test
    void testTextParameterNull() {
        Parameter.TextParameter param = new Parameter.TextParameter(null);
        assertThrows(IllegalArgumentException.class, param::validate,
                "Null text should throw an exception.");
    }

    @Test
    void testTextParameterTooLong() {
        String longString = "A".repeat(6000);
        Parameter.TextParameter param = new Parameter.TextParameter(longString);
        assertThrows(IllegalArgumentException.class, param::validate,
                "Text over 5000 chars should throw an exception.");
    }

    @Test
    void testDateTimeParameterValid() {
        Parameter.DateTimeParameter param =
                new Parameter.DateTimeParameter(LocalDateTime.now());
        assertDoesNotThrow(param::validate,
                "Valid LocalDateTime should not throw exception.");
    }

    @Test
    void testDateTimeParameterNull() {
        Parameter.DateTimeParameter param =
                new Parameter.DateTimeParameter(null);
        assertThrows(IllegalArgumentException.class, param::validate,
                "Null DateTime should throw an exception.");
    }

    @Test
    void testDateTimeParameterMinValue() {
        Parameter.DateTimeParameter param =
                new Parameter.DateTimeParameter(LocalDateTime.MIN);
        assertThrows(IllegalArgumentException.class, param::validate,
                "LocalDateTime.MIN should throw an exception.");
    }

    @Test
    void testPasswordParameterValid() {
        Parameter.PasswordParameter param =
                new Parameter.PasswordParameter("Abc123!");
        assertDoesNotThrow(param::validate,
                "Complex password should not throw exception.");
    }

    @Test
    void testPasswordParameterNull() {
        Parameter.PasswordParameter param =
                new Parameter.PasswordParameter(null);
        assertThrows(IllegalArgumentException.class, param::validate,
                "Null password should throw an exception.");
    }

    @Test
    void testPasswordParameterEmpty() {
        Parameter.PasswordParameter param =
                new Parameter.PasswordParameter("");
        assertThrows(IllegalArgumentException.class, param::validate,
                "Empty password should throw an exception.");
    }

    @Test
    void testPasswordParameterNoSpecialChar() {
        Parameter.PasswordParameter param =
                new Parameter.PasswordParameter("Abc123");
        assertThrows(IllegalArgumentException.class, param::validate,
                "Password missing a special char should throw exception.");
    }

    @Test
    void testPasswordParameterNoDigit() {
        Parameter.PasswordParameter param =
                new Parameter.PasswordParameter("Abc!");
        assertThrows(IllegalArgumentException.class, param::validate,
                "Password missing a digit should throw exception.");
    }

    @Test
    void testPasswordParameterNoUppercase() {
        Parameter.PasswordParameter param =
                new Parameter.PasswordParameter("abc123!");
        assertThrows(IllegalArgumentException.class, param::validate,
                "Password missing an uppercase letter should throw exception.");
    }

    @Test
    void testPasswordParameterNoLowercase() {
        Parameter.PasswordParameter param =
                new Parameter.PasswordParameter("ABC123!");
        assertThrows(IllegalArgumentException.class, param::validate,
                "Password missing a lowercase letter should throw exception.");
    }
}
