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

    public static void main(String[] args) {
        System.out.println("<Programmed by: Barzi Yassin Karim>\n\n");
        System.out.println("• • • Vigenere Cipher Program • • •\n");
        printOptions();
        printDetails();
        int option = -1;
        do {
            switch (option) {
                case -1:
                    break;
                case 0:
                    return;
                case 1:
                    System.out.print("Enter new plain text: ");
                    plainText = inputString();
                    printDetails();
                    break;
                case 2:
                    System.out.print("Enter new key: ");
                    String oldKey = key;
                    key = inputString();
                    System.out.println("key changed from \"" + oldKey + "\" to \"" + key + "\"");
                    break;
                case 3:
                    printDetails();
                    break;
                default:
                    System.out.println("Wrong option entered!");

            }

            System.out.println("\n•\n");
            System.out.print("input an option number: ");
            option = inputInt();
        } while (option != 0);

//        while (true) {
//
//            System.out.println("plain text: " + plaintext);
//
//            String encryptedText = encryptVigenere(plaintext, key);
//            System.out.println("Encrypted Text: " + encryptedText);
//
//            String decryptedText = decryptVigenere(encryptedText, key);
//            System.out.println("Decrypted Text: " + decryptedText);
//        }
    }

    private static String encryptVigenere() {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0, j = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);

            if (Character.isLetter(currentChar)) {
                char keyChar = key.charAt(j % key.length());
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
                char keyChar = key.charAt(j % key.length());
                char decryptedChar = decryptChar(currentChar, keyChar);
                decryptedText.append(decryptedChar);
                j++;
            } else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }

    private static char encryptChar(char plaintextChar, char keyChar) {
        return (char) (((plaintextChar - 'a' + keyChar - 'a') % 26) + 'a');
    }

    private static char decryptChar(char ciphertextChar, char keyChar) {
        int result = (ciphertextChar - 'a' - (keyChar - 'a')) % 26;
        // Handling negative result
        if (result < 0) {
            result += 26;
        }
        return (char) (result + 'a');
    }

    private static String extendKey() {
        int length = plainText.length();
        // Repeat the word until it reaches the length characters
        StringBuilder duplicatedWord = new StringBuilder();
        while (duplicatedWord.length() < length) {
            duplicatedWord.append(key);
        }

        // Trim the excess characters if more than length
        String result = duplicatedWord.substring(0, length);

        return result;
    }

    // printing methods
    private static void printDetails() {
        System.out.println("");
        System.out.println("Plain text: " + plainText + " | length: " + plainText.length());
        System.out.println("Key: " + key);
        System.out.println("exteded Key:    " + extendKey());

        System.out.println("Encrypted Text: " + encryptVigenere());
        System.out.println("Decrypted Text: " + decryptVigenere());
    }

    private static void printOptions() {
        System.out.println("menu options:");
        System.out.println("\t1: changing plain text");
        System.out.println("\t2: changing key");
        System.out.println("\t3: print details");
        System.out.println("\t0: exit");
        System.out.println("");
    }

    // helper methods
    private static String inputString() {
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

    private static int inputInt() {
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
}
