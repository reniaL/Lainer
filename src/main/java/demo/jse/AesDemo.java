package demo.jse;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * AES 加、解密
 */
public class AesDemo {
    
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String ALGORITHM = "AES";
    
    public static void main(String[] args) {
        String content = "test";
        String password = "12345678";
        
        // 加密
        System.out.println("加密前：" + content);
        byte[] encryptResult = encrypt(content, password);
        String encryptedStr = Hex.encodeHexString(encryptResult);
        System.out.println("加密后16进制字符串：" + encryptedStr);
        
        // base64
        String base64Encoded = Base64.encodeBase64String(encryptResult);
        System.out.println("base64 编码后字符串：" + base64Encoded);
        
        // 解密
        try {
            byte[] base64Decoded = Base64.decodeBase64(base64Encoded);
            byte[] decryptResult = decrypt(base64Decoded, password);
            System.out.println("解密后：" + new String(decryptResult, DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 加密
     * @param content 需要加密的内容
     * @param password 加密密码
     */
    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
            byte[] byteContent = content.getBytes(DEFAULT_CHARSET);
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            return cipher.doFinal(byteContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 解密
     * @param content 待解密内容
     * @param password 解密密钥
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 加密
     * @param content 需要加密的内容
     * @param password 加密密码。注意，必须为16位
     */
    public static byte[] encrypt1(String content, String password) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(Hex.decodeHex(password.toCharArray()), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);// 初始化
            return cipher.doFinal(content.getBytes(DEFAULT_CHARSET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 解密
     * @param content 待解密内容
     * @param password 解密密钥。注意，必须为16位
     */
    public static byte[] decrypt1(byte[] content, String password) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(Hex.decodeHex(password.toCharArray()), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);// 初始化
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
