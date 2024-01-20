package cryptography;

/**
 *
 * @author barzy
 */
import java.util.Scanner;

public class PlayfairCipher {

    private static final int SIZE = 5;
    private static char[][] matrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();
        generateMatrix(key);

        System.out.print("Enter the message: ");
        String message = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        System.out.print("Encrypt or Decrypt? (E/D): ");
        char choice = scanner.next().toUpperCase().charAt(0);

        if (choice == 'E') {
            String encryptedMessage = encrypt(message);
            System.out.println("Encrypted Message: " + encryptedMessage);
        } else if (choice == 'D') {
            String decryptedMessage = decrypt(message);
            System.out.println("Decrypted Message: " + decryptedMessage);
        } else {
            System.out.println("Invalid choice. Please enter 'E' for encryption or 'D' for decryption.");
        }

        scanner.close();
    }

    private static void generateMatrix(String key) {
        matrix = new char[SIZE][SIZE];
        String keyWithoutDuplicates = removeDuplicateCharacters(key + "ABCDEFGHIKLMNOPQRSTUVWXYZ");

        int k = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = keyWithoutDuplicates.charAt(k++);
            }
        }
    }

    private static String removeDuplicateCharacters(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (result.indexOf(String.valueOf(currentChar)) == -1) {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    private static String encrypt(String message) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i += 2) {
            char firstChar = message.charAt(i);
            char secondChar = (i + 1 < message.length()) ? message.charAt(i + 1) : 'X';
            result.append(encryptPair(firstChar, secondChar));
        }
        return result.toString();
    }

    private static String decrypt(String message) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i += 2) {
            char firstChar = message.charAt(i);
            char secondChar = (i + 1 < message.length()) ? message.charAt(i + 1) : 'X';
            result.append(decryptPair(firstChar, secondChar));
        }
        return result.toString();
    }

    private static String encryptPair(char a, char b) {
        int[] posA = findPosition(a);
        int[] posB = findPosition(b);

        if (posA[0] == posB[0]) {
            return String.valueOf(matrix[posA[0]][(posA[1] + 1) % SIZE])
                    + matrix[posB[0]][(posB[1] + 1) % SIZE];
        } else if (posA[1] == posB[1]) {
            return String.valueOf(matrix[(posA[0] + 1) % SIZE][posA[1]])
                    + matrix[(posB[0] + 1) % SIZE][posB[1]];
        } else {
            return String.valueOf(matrix[posA[0]][posB[1]]) + matrix[posB[0]][posA[1]];
        }
    }

    private static String decryptPair(char a, char b) {
        int[] posA = findPosition(a);
        int[] posB = findPosition(b);

        if (posA[0] == posB[0]) {
            return String.valueOf(matrix[posA[0]][(posA[1] - 1 + SIZE) % SIZE])
                    + matrix[posB[0]][(posB[1] - 1 + SIZE) % SIZE];
        } else if (posA[1] == posB[1]) {
            return String.valueOf(matrix[(posA[0] - 1 + SIZE) % SIZE][posA[1]])
                    + matrix[(posB[0] - 1 + SIZE) % SIZE][posB[1]];
        } else {
            return String.valueOf(matrix[posA[0]][posB[1]]) + matrix[posB[0]][posA[1]];
        }
    }

    private static int[] findPosition(char c) {
        int[] result = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix[i][j] == c) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
