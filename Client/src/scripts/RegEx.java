/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author OlesiaPC
 */
public class RegEx {

    public static boolean checkLoginAndPassword(String string) { //Хотя не особо правильно ограничивать человека в выборе пароля
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[A-Za-z0-9\\.]{4,20}$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }

    public static boolean checkEMail(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\w\\.]+[@]{1}[a-z]+\\.(com|ua|ru)$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }

    public static boolean checkToken(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\w\\d]{12}$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }

    public static boolean checkName(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\p{L}]+$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }

    public static boolean checkSpecialName(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\p{L}_\\d\\.]+$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }
    
    public static boolean checkDigits(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\d]+$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }
}
