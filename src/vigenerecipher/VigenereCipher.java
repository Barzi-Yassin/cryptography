package vigenerecipher;

import java.util.Scanner;

/**
 *
 * @author barzy
 */
public class VigenereCipher {

    private static Scanner sc;

    private static String plainText = "technicalcollegeofinformatic".toLowerCase();
    private static String cipherText;
    private static String key = "networking";

    public static String encryptVigenere() {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0, j = 0; i < getPlainText().length(); i++) {
            char currentChar = getPlainText().charAt(i);

            if (Character.isLetter(currentChar)) {
                char keyChar = getKey().charAt(j % getKey().length());
                char encryptedChar = encryptChar(currentChar, keyChar);
                ciphertext.append(encryptedChar);
                j++;
            } else {
                ciphertext.append(currentChar);
            }
        }

        cipherText = ciphertext.toString();

        return ciphertext.toString();
    }

    private static String decryptVigenere() {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0, j = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);

            if (Character.isLetter(currentChar)) {
                char keyChar = getKey().charAt(j % getKey().length());
                char decryptedChar = decryptChar(currentChar, keyChar);
                decryptedText.append(decryptedChar);
                j++;
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    public static char encryptChar(char plaintextChar, char keyChar) {
        return (char) (((plaintextChar - 'a' + keyChar - 'a') % 26) + 'a');
    }

    public static char decryptChar(char ciphertextChar, char keyChar) {
        int result = (ciphertextChar - 'a' - (keyChar - 'a')) % 26;
        // Handling negative result
        if (result < 0) {
            result += 26;
        }
        return (char) (result + 'a');
    }

    public static String extendKey() {
        int length = getPlainText().length();
        // Repeat the word until it reaches the length characters
        StringBuilder duplicatedWord = new StringBuilder();
        while (duplicatedWord.length() < length) {
            duplicatedWord.append(getKey());
        }

        // Trim the excess characters if more than length
        String result = duplicatedWord.substring(0, length);

        return result;
    }

    // printing methods
    public static void printDetails() {
        System.out.println("");
        System.out.println("Plain text: " + getPlainText() + " | length: " + getPlainText().length());
        System.out.println("Key: " + getKey());
        System.out.println("exteded Key:    " + extendKey());

        System.out.println("Encrypted Text: " + encryptVigenere());
        System.out.println("Decrypted Text: " + decryptVigenere());
    }

    public static void printOptions() {
        System.out.println("menu options:");
        System.out.println("\t1: changing plain text");
        System.out.println("\t2: changing key");
        System.out.println("\t3: print details");
        System.out.println("\t0: exit");
        System.out.println("");
    }

    // helper methods
    public static String inputString() {
        sc = new Scanner(System.in);
        String input = "";
        try {
            input = sc.next();
            return input;
        } catch (Exception e) {
            System.out.println("try again, please enter a string:\n");
            input = inputString();
            return input;
        }
    }

    public static int inputInt() {
        sc = new Scanner(System.in);
        int input = 0;
        do {
            try {
//                System.out.print("num: ");
                input = sc.nextInt();
                return input;
            } catch (Exception e) {
                System.out.print("try again, please enter a number: ");
                input = inputInt();
                return input;
            }
        } while (input == 0);
//        return input;
    }

// starting getter and setter methods
    public static String getPlainText() {
        return plainText;
    }

    public static String getKey() {
        return key;
    }

    public static void setPlainText(String aPlainText) {
        plainText = aPlainText;
    }

    public static void setKey(String aKey) {
        key = aKey;
    }
}
