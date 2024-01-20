package cryptography;

import static caesarcipher.CaesarCipher.*;
import static vigenerecipher.VigenereCipher.*;

/**
 *
 * @author barzy
 */
public class Root {

    public static void main(String[] args) {
        System.out.println("Cryptography");
        runCaesarCipher();
        runVigenereCipher();
    }

    private static void runCaesarCipher() {
        System.out.println("<Programmed by: Barzi Yassin Karim>\n\n");
        System.out.println("• • • Caesar Cipher Program • • •\n");

        fillDictionary();

        System.out.println("\nPress:\n  E: to Encrypt\n  D: to Decrypt\n  K: to Set Key\n  X: to Exit the program\n");
        while (true) {
            System.out.print("  ");
            switch (input().toLowerCase()) {
                case "e" -> {

                    System.out.print("\n• Enter plaintext to encrypt: ");
                    String input = input();
                    System.out.println(" + plaintext:   " + input);
                    System.out.println(" - ciphertext:  " + encrypt(input));

                    System.out.println("----------------------------\n");
                    break;
                }

                case "d" -> {
                    System.out.print("\n• Enter ciphertext to decrypt: ");
                    String input = input();
                    System.out.println(" - ciphertext:  " + input);
                    System.out.println(" + plaintext:   " + decrypt(input));

                    System.out.println("----------------------------\n");
                    break;
                }

                case "k" -> {
                    try {
                        System.out.print("\n• Enter new key: ");
                        int newKey = Integer.parseInt(input());
                        setKey(newKey);
                    } catch (NumberFormatException e) {
                        System.out.println(" - Invalid key!");
                    }
                    System.out.println("----------------------------\n");
                    break;
                }

                case "x" -> {
//                    System.out.println("\nGoodBye...\n");
                    System.out.println("\n</GoodBye>\n");

                    return;
                }

                default -> {
                    System.out.println("\nwrong input pressed, try another one ↓");
                }
            }
        }

    }

    private static void runVigenereCipher() {
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
                    setPlainText(inputString());
                    printDetails();
                    break;
                case 2:
                    System.out.print("Enter new key: ");
                    String oldKey = getKey();
                    setKey(inputString());
                    System.out.println("key changed from \"" + oldKey + "\" to \"" + getKey() + "\"");
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
    }
}
