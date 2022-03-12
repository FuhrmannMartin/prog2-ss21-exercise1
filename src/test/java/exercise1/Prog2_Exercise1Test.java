package exercise1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Prog2_Exercise1Test {

    final String validPassword = "Mayonnaise7%";

    @Test
    void checkValidPassword() {
        assertTrue(Prog2_Exercise1.checkPassword(validPassword));
    }

    @Test
    void checkPasswordLengthTooShort() {
        String password = validPassword.substring(0,7);
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    @Test
    void checkPasswordLengthTooLong() {
        String password = validPassword + "abcdefghijklmn";
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    @Test
    void checkPasswordNoLowerCase() {
        String password = validPassword.toLowerCase();
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    @Test
    void checkPasswordNoUpperCase() {
        String password = validPassword.toUpperCase();
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    @Test
    void checkPasswordNoNumber() {
        // Replace all numbers from given String
        String password = validPassword.replaceAll("[0123456789]", "");
        System.out.println(password);
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    @Test
    void checkPasswordNoSpecialCharacters() {
        StringBuilder replace = new StringBuilder();
        replace.append("[");
        for (int validValue : Prog2_Exercise1.validASCII) {
            String y = Character.toString((char) validValue);
            replace.append(y);
        }
        replace.append("]");
        String password = validPassword.replaceAll(replace.toString(), "");
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    @Test
    void checkPasswordInvalidSpecialCharacter() {
        String password = validPassword + "*";
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    @Test
    void checkPasswordAscendingNumbers() {
        String password = validPassword + "123";
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    @Test
    void checkPasswordRepeatingNumbers() {
        String password = validPassword + "1111";
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

}