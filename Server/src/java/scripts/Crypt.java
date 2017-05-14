/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author OlesiaPC
 */
public class Crypt { 

    public static String encryptMD5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digester = MessageDigest.getInstance("MD5");
        digester.update(string.getBytes("UTF-8"));
        byte[] hash = digester.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            stringBuffer.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1)); //Перевод в 16-тиричную с.с.
        }
        return stringBuffer.toString();
    }
}
