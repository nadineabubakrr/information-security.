import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AESExample {

    // Method to encrypt plaintext using AES
    public static String encrypt(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt ciphertext using AES
    public static String decrypt(String ciphertext, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
            return new String(decryptedBytes);
        } catch (Exception e) {
            // Return null if decryption fails
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Step 1: Generate AES Secret Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // AES key size: 128 bits
            SecretKey secretKey = keyGenerator.generateKey();

            // Display the AES Key (for demonstration purposes)
            String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("Generated AES Key (Base64): " + base64Key);

            // Step 2: Encrypt a plaintext message
            System.out.print("Enter the plaintext to encrypt: ");
            String plaintext = scanner.nextLine();
            String encryptedText = encrypt(plaintext, secretKey);
            System.out.println("Encrypted Text (Base64): " + encryptedText);

            // Step 3: Decrypt the encrypted message
            System.out.print("Enter the ciphertext to decrypt: ");
            String ciphertext = scanner.nextLine();
            String decryptedText = decrypt(ciphertext, secretKey);

            if (decryptedText == null) {
                System.out.println("Wrong input: Unable to decrypt the given ciphertext.");
            } else {
                System.out.println("Decrypted Text: " + decryptedText);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
