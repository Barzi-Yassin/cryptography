package vigenerecipher;

import java.util.Scanner;

/**
 *
 * @author barzy
 */
public class Vigenere {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String plaintext = "SECURITYISESSENTIAL".toLowerCase();
        String key = "true";

        while (true) {
            System.out.println("plain text: " + plaintext);
            
            String encryptedText = encryptVigenere(plaintext, key);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decryptVigenere(encryptedText, key);
            System.out.println("Decrypted Text: " + decryptedText);
        }
    }

    private static String encryptVigenere(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char keyChar = key.charAt(j % key.length());
                char encryptedChar = encryptChar(currentChar, keyChar);
                ciphertext.append(encryptedChar);
                j++;
            } else {
                ciphertext.append(currentChar);
            }
        }

        return ciphertext.toString();
    }

    private static String decryptVigenere(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0, j = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);

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
}
