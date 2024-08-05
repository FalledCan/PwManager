package com.github.falledcan.pwmanager;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryption {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256; // AES-256
    public static String key;

    public static void loadKey() throws Exception {
        key = FileManager.jsonData_key();
    }

    //指定されたデータをAESで暗号化
    public static String encrypt(String data) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // 指定された暗号化データをAESで復号化
    public static String decrypt(String encryptedData) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedData);
    }

    //ランダムなAES鍵を生成
    public static String generateRandomAESKey() throws Exception {

            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(KEY_SIZE); // AES-256ビットの鍵を生成
            SecretKey secretKey = keyGen.generateKey();
            return key = Base64.getEncoder().encodeToString(secretKey.getEncoded());

    }

    //データベースチェック
    public  static boolean isCheckDataBase() throws Exception {
        String checkKey = "ajS932FdkmL49De5vkG";
        String key = DatabaseManager.dataKey();
        return decrypt(key).equals(checkKey);
    }

}

