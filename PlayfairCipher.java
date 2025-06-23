import java.util.Scanner;

public class PlayfairCipher {

    private static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    // Generate Playfair square
    public static char[][] generatePlayfairSquare(String key) {
        String adjustedKey = key.toUpperCase().replace("J", "I");
        StringBuilder keySquareBuilder = new StringBuilder();

        for (char c : adjustedKey.toCharArray()) {
            if (ALPHABET.indexOf(c) != -1 && keySquareBuilder.indexOf(String.valueOf(c)) == -1) {
                keySquareBuilder.append(c);
            }
        }

        for (char c : ALPHABET.toCharArray()) {
            if (keySquareBuilder.indexOf(String.valueOf(c)) == -1) {
                keySquareBuilder.append(c);
            }
        }

        char[][] keySquare = new char[5][5];
        for (int i = 0; i < 25; i++) {
            keySquare[i / 5][i % 5] = keySquareBuilder.charAt(i);
        }
        return keySquare;
    }

    // Print the Playfair square
    public static void printPlayfairSquare(char[][] keySquare) {
        System.out.println("Generated Playfair Square:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(keySquare[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Encrypt using Playfair cipher
    public static String encrypt(String plaintext, char[][] keySquare) {
        plaintext = plaintext.toUpperCase().replace("J", "I").replaceAll("\\s", "");
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char first = plaintext.charAt(i);
            char second = (i + 1 < plaintext.length() && plaintext.charAt(i) != plaintext.charAt(i + 1))
                    ? plaintext.charAt(i + 1)
                    : 'X';
            int[] pos1 = findPosition(first, keySquare);
            int[] pos2 = findPosition(second, keySquare);

            if (pos1[0] == pos2[0]) {
                ciphertext.append(keySquare[pos1[0]][(pos1[1] + 1) % 5]);
                ciphertext.append(keySquare[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) {
                ciphertext.append(keySquare[(pos1[0] + 1) % 5][pos1[1]]);
                ciphertext.append(keySquare[(pos2[0] + 1) % 5][pos2[1]]);
            } else {
                ciphertext.append(keySquare[pos1[0]][pos2[1]]);
                ciphertext.append(keySquare[pos2[0]][pos1[1]]);
            }
        }
        return ciphertext.toString();
    }

    private static int[] findPosition(char c, char[][] keySquare) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keySquare[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key for the Playfair Cipher: ");
        String key = scanner.nextLine();

        System.out.print("Enter the plaintext to encrypt: ");
        String plaintext = scanner.nextLine();

        char[][] keySquare = generatePlayfairSquare(key);

        // Print the generated Playfair square
        printPlayfairSquare(keySquare);

        String encrypted = encrypt(plaintext, keySquare);

        System.out.println("Playfair Encrypted: " + encrypted);
        scanner.close();
    }
}
