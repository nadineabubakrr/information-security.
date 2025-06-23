import java.util.Scanner;

public class VigenereCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase().replaceAll("\\s", ""); 
        key = key.toUpperCase();
        StringBuilder ciphertext = new StringBuilder();

        int keyIndex = 0;
        for (char c : plaintext.toCharArray()) {
            if (ALPHABET.indexOf(c) != -1) {
                int charIndex = (ALPHABET.indexOf(c) + ALPHABET.indexOf(key.charAt(keyIndex % key.length()))) % 26;
                ciphertext.append(ALPHABET.charAt(charIndex));
                keyIndex++;
            } else {
                ciphertext.append(c); 
            }
        }

        return ciphertext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key for the Vigenère Cipher: ");
        String key = scanner.nextLine();

        System.out.print("Enter the plaintext to encrypt: ");
        String plaintext = scanner.nextLine();

        String encrypted = encrypt(plaintext, key);

        System.out.println("Vigenère Encrypted: " + encrypted);

        scanner.close();
    }
}
