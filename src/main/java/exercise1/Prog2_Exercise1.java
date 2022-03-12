package exercise1;

import java.util.Arrays;

public class Prog2_Exercise1 {

    // Valide Sonderzeichen: ()#$?!%/@#
    final static int[] validASCII = new int[] {40, 41, 35, 36, 63, 33, 37, 47, 64};

    public static void main(String[] args) {
        String s = "aaasaaaa$aCa569133*33";
        checkPassword(s);
    }

    public static boolean checkPassword(String pw) {
        /*
        Passwort muss zwischen 8 und 25 Zeichen lang sein
        Das Kennwort muss aus Klein- und Großbuchstaben bestehen
        Das Kennwort muss Zahlen enthalten
        Das Kennwort muss mindestens einen der folgenden Sonderzeichen enthalten: ()#$?!%/@#
        Andere Sonderzeichen sind nicht erlaubt
        Wenn zwei Zahlen enthalten sind dürfen nicht mehr als zwei Zahlen fortlaufend sein z.B. 123 oder 456 sind nicht erlaubt
        Es darf nicht eine Zahl öfters als 3-mal hintereinander kommen z.B. 1111 ist nicht erlaubt
         */

        int[] asciiArray = getIntArray(pw);
        int[] asciiValueDiff = new int[3];
        boolean upperCaseContained = false, lowerCaseContained = false, numberContained = false, specialCharacterContained = false;

        // Passwort muss zwischen 8 und 25 Zeichen lang sein
        if (pw.length() < 8 || pw.length() > 25) {
            System.out.println("Das Kennwort ist nicht zwischen 8 und 25 Zeichen lang");
            return false;
        }

        for (int i = 0; i < asciiArray.length; i++) {
            int asciiValue = asciiArray[i];

            if (asciiValue >= 48 && asciiValue <= 57) {
                numberContained = true;
                Arrays.fill(asciiValueDiff, -1);

                for (int j = 0; j < asciiValueDiff.length; j++) {
                    if (i+j < asciiArray.length-1) {
                        asciiValueDiff[j] = asciiArray[i+j+1] - asciiArray[i+j];
                    } else {
                        asciiValueDiff[j] = -1;
                    }
                }

                if (asciiValueDiff[0] == 1 && asciiValueDiff[1] == 1) {
                    System.out.println("Fortlaufende Zahl");
                    return false;
                }

                if (asciiValueDiff[0] == 0 && asciiValueDiff[1] == 0 && asciiValueDiff[2] == 0) {
                    System.out.println("3-mal selbe Zahl hintereinander");
                    return false;
                }

            } else if (asciiValue >= 97 && asciiValue <= 122) {
                lowerCaseContained = true;
            } else if (asciiValue >= 65 && asciiValue <= 90) {
                upperCaseContained = true;
            } else if (Arrays.stream(validASCII).anyMatch(validValue -> validValue == asciiValue)) {
                specialCharacterContained = true;
            } else {
                System.out.println((char) asciiValue + " ist keine gültige Eingabe");
                return false;
            }
        }

        // Alle Tests bestanden
        if (upperCaseContained && lowerCaseContained && numberContained && specialCharacterContained) {
            System.out.println("Passwort is gültig");
            return true;
        } else {
            System.out.println("Passwort is ungültig");
            return false;
        }

    }

    private static int[] getIntArray(String s){
        int[] arr = new int[s.length()];

        for (int i=0; i<s.length(); i++) arr[i] = s.charAt(i);

        return arr;
    }

}
