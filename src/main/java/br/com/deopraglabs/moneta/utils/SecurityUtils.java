package br.com.deopraglabs.moneta.utils;

import br.com.deopraglabs.moneta.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SecurityUtils {

    @Value("${encrypt.algorithm}")
    private static final String ALGORITHM = "";

    @Value("${encrypt.key}")
    private static final byte[] KEY = "".getBytes();

    public static String encryptPassword(String password) {
        return SecurityConfig.passwordEncoder().encode(password);
    }

    public String encrypt(String data) throws Exception {
        final SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        final byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptedData) throws Exception {
        final SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        final byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(original);
    }
}
