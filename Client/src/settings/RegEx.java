/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author OlesiaPC
 */
public class RegEx {

    public static boolean stringCheck(String string) {
        Pattern pattern = Pattern.compile("^[a-z0-9_A-Z]{4,20}$"); 
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    
    public static boolean eMailCheck(String string) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+[@]{1}[a-z]+\\.(com|ua|ru)$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    
    public static boolean tokenCheck(String string) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{12}$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    
     public static boolean nameCheck(String string) { //Чтоб не было цифр и специальных знаков
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
     }
}
