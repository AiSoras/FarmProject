/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;

/**
 * Encrypts user's password.
 * @author OlesiaPC
 */
public class Crypt {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Crypt.class.getName());

    /**
     * Encrypts the <code>password</code>.
     *
     * @param string The string contains user's password.
     * @return The encrypted <code>password</code> or empty string if exceptions are catched.
     */
    public static String encryptMD5(String string) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            digester.update(string.getBytes("UTF-8"));
            byte[] hash = digester.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                stringBuffer.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1)); //Перевод в 16-тиричную с.с.
            }
            logger.info("The password was encrypted successfully");
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            logger.error("Exception: ", ex);
            return "";
        }
    }
}
