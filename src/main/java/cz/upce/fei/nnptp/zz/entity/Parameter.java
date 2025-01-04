package cz.upce.fei.nnptp.zz.entity;

import java.time.LocalDateTime;

/**
 * @author Roman
 */
public class Parameter {

    public static class StandardizedParameters {
        public static final String TITLE = "title";
        public static final String EXPIRATION_DATETIME = "expiration-datetime";
        public static final String WEBSITE = "website";
        public static final String DESCRIPTION = "description";

    }

    public String toJson() {
        return "{}";
    }

    public void validate() throws IllegalArgumentException {
    }

    public static class TextParameter extends Parameter {
        private String value;

        public TextParameter(String value) {
            this.value = value;
        }

        public TextParameter() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toJson() {
            return "\"" + value + "\"";
        }

        @Override
        public void validate() throws IllegalArgumentException {
            if (value == null) {
                throw new IllegalArgumentException("Text parameter cannot be null.");
            }
            if (value.length() > 5000) {
                throw new IllegalArgumentException("Text parameter exceeds maximum length (5000).");
            }
        }
    }

    public static class DateTimeParameter extends Parameter {
        private LocalDateTime value;

        public DateTimeParameter() {
        }

        public DateTimeParameter(LocalDateTime value) {
            this.value = value;
        }

        public LocalDateTime getValue() {
            return value;
        }

        public void setValue(LocalDateTime value) {
            this.value = value;
        }

        @Override
        public String toJson() {
            return "\"" + (value != null ? value.toString() : "null") + "\"";
        }

        @Override
        public void validate() throws IllegalArgumentException {
            if (value == null) {
                throw new IllegalArgumentException("DateTime parameter cannot be null.");
            }
            if (value.equals(LocalDateTime.MIN)) {
                throw new IllegalArgumentException(
                        "DateTime parameter cannot be LocalDateTime.MIN."
                );
            }
        }
    }

    public static class PasswordParameter extends Parameter {
        private String password;

        public PasswordParameter() {
        }

        public PasswordParameter(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toJson() {
            return "\"" + password + "\"";
        }

        @Override
        public void validate() throws IllegalArgumentException {
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty.");
            }
            // Password complexity check: min. 1 lowercase letter, 1 uppercase letter, 1 digit, 1 special character
            String complexityRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).+$";
            if (!password.matches(complexityRegex)) {
                throw new IllegalArgumentException(
                        "Password must contain at least one lowercase letter, "
                                + "one uppercase letter, one digit, and one special character."
                );
            }
        }
    }
}
