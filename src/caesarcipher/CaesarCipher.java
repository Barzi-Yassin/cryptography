package caesarcipher;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author barzy
 */
public class CaesarCipher {

    // caesar cipher - monoalphanumeric program.
    private static char[] dictionary;
    private static int key = 5;

    public static void fillDictionary() {
        // filling the array with ASCII codes dynamically at runtime;
        int[] alphaNumerics = {97, 122, (47 - (122 - 97)), (56 - (122 - 97)),};
        // index(0&1) are for lowercase alphabets 
        // index(2&3) are for decimal numbers

        //finding the length of the dictionary
        int lengthToBe = 0;
        dictionary = new char[lengthToBe];
        for (int i = 0; i < alphaNumerics.length; i += 2) {
            lengthToBe += alphaNumerics[i + 1] - alphaNumerics[i] + 1;
        }
        // System.out.println("lengthToBe: " + lengthToBe);
        dictionary = new char[lengthToBe];

        // assigning data to the dictionary
        for (int i = 0; i < dictionary.length; i++) {
            if (i + alphaNumerics[0] <= alphaNumerics[1]) {
                dictionary[i] = (char) (i + alphaNumerics[0]);
            } else {
                dictionary[i] = (char) (i + alphaNumerics[2]);
            }
        }
        System.out.println("Dictionary: " + Arrays.toString(dictionary));
    }

    public static String input() {
        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter a plain text: ");
        String plainText = "!!! empty plain text !!!";
        try {
            plainText = sc.next();
            return plainText;
        } catch (Exception e) {
            input();
            return plainText; // i guess this line never executes
//        } finally {
//            sc.close();
        }
    }

    private static int getCharPositionInTheDictionary(char character) {
        for (int i = 0; i < dictionary.length; i++) {
            if (character == dictionary[i]) {
                return i;
            }
        }
        return -1;
    }

    public static String encrypt(String plainText) {
        String result = "";
        plainText = plainText.toLowerCase();
        System.out.println("    •");
        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);
            final int plainCharIndex = getCharPositionInTheDictionary(currentChar);
            final int cipherCharIndex = (plainCharIndex + key) % dictionary.length;
            System.out.println("     E(" + currentChar + ", " + key + ") = (" + plainCharIndex + " + " + key + ") % " + dictionary.length + " = " + cipherCharIndex + " => '" + dictionary[cipherCharIndex] + "'");
            result += dictionary[cipherCharIndex];
        }
        System.out.println("    •");
        return result;
    }

    public static String decrypt(String cipherText) {
        String result = "";
        System.out.println("    •");
        for (int i = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);
            final int cipherCharIndex = getCharPositionInTheDictionary(currentChar);
            final int plainCharIndex = ((cipherCharIndex - key) + dictionary.length) % dictionary.length;
            System.out.println("     D(" + currentChar + ", " + key + ") = ((" + cipherCharIndex + " - " + key + ") + " + dictionary.length + ") % " + dictionary.length + " = " + plainCharIndex + " => '" + dictionary[plainCharIndex] + "'");
            result += dictionary[plainCharIndex];
        }
        System.out.println("    •");
        return result;
    }

    public static void setKey(int newKey) {
        key = newKey;
        System.out.println("  + key is set to: " + key);
    }
}
