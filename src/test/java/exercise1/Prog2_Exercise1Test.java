package exercise1;

// Link to git repository: https://github.com/FuhrmannMartin/prog2-ss21-exercise1/tree/87ba06868355c844eb327f6b908c92a9b530ac05

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Prog2_Exercise1Test {

    final String validPassword = "Mayonnaise7%";

    // Valides Passwort muss akzeptiert werden
    @Test
    void checkValidPassword() {
        assertTrue(Prog2_Exercise1.checkPassword(validPassword));
    }

    // Kennwort muss zwischen 8 und 25 Zeichen lang sein - Passwort zu kurz?
    @Test
    void checkPasswordLengthTooShort() {
        String password = validPassword.substring(0,7);
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    // Kennwort muss zwischen 8 und 25 Zeichen lang sein - Passwort zu lang?
    @Test
    void checkPasswordLengthTooLong() {
        String password = validPassword + "abcdefghijklmn";
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    // Das Kennwort enthält keine Kleinbuchstaben
    @Test
    void checkPasswordNoLowerCase() {
        String password = validPassword.toLowerCase();
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    // Das Kennwort enthält keine Großbuchstaben
    @Test
    void checkPasswordNoUpperCase() {
        String password = validPassword.toUpperCase();
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    // Das Kennwort enthält keine Zahlen
    @Test
    void checkPasswordNoNumber() {
        // Replace all numbers from given String
        String password = validPassword.replaceAll("[0123456789]", "");
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    // Das Kennwort enthält keines der angegebenen Sonderzeichen
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

    // Das Kennwort enthält nicht erlaubtes Sonderzeichen
    @Test
    void checkPasswordInvalidSpecialCharacter() {
        String password = validPassword + "*";
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    // Es dürfen nicht mehr als zwei Zahlen fortlaufend sein
    @Test
    void checkPasswordAscendingNumbers() {
        String password = validPassword + "123";
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

    // Es darf nicht eine Zahl öfters als 3-mal hintereinander kommen z.B. 1111 ist nicht erlaubt
    @Test
    void checkPasswordRepeatingNumbers() {
        String password = validPassword + "1111";
        assertFalse(Prog2_Exercise1.checkPassword(password));
    }

}