import java.util.Scanner;

public class AtbashCipher {

    // Method to encrypt or decrypt text using Atbash Cipher
    public static String atbashCipher(String input) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                // For uppercase letters, reverse the alphabet
                result.append((char) ('Z' - (c - 'A')));
            } else if (Character.isLowerCase(c)) {
                // For lowercase letters, reverse the alphabet
                result.append((char) ('z' - (c - 'a')));
            } else {
                // Non-alphabetic characters remain unchanged
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter the text to encrypt or decrypt: ");
        String input = scanner.nextLine();

        // Apply Atbash Cipher
        String result = atbashCipher(input);
        System.out.println("Result: " + result);

        scanner.close();
    }
}
