/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains regular expressions for checking input
 *
 * @author OlesiaPC
 */
public class RegEx {

    /**
    * Cheks login and password
    * 
    * @param string input login or password string
    * @return <code>true</code> if login or password meet requirements, otherwise <code>false</code>.
    */
    public static synchronized boolean checkLoginAndPassword(String string) { //Хотя не особо правильно ограничивать человека в выборе пароля
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[A-Za-z0-9\\.]{4,20}$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }
    
    /**
    * Cheks dose in ration
    * 
    * @param string input dose string
    * @return <code>true</code> if dose meet requirements, otherwise <code>false</code>.
    */
    public static synchronized boolean checkDigits(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\d]+$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }

    /**
    * Cheks user's eMail
    * 
    * @param string input user's eMail
    * @return <code>true</code> if eMail meet requirements, otherwise <code>false</code>.
    */
    public static synchronized boolean checkEMail(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\w\\.]+[@]{1}[a-z]+\\.(com|ua|ru)$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }
    
    /**
    * Cheks user's token when registering
    * 
    * @param string input user's token
    * @return <code>true</code> if token meet requirements, otherwise <code>false</code>.
    */
    public static synchronized boolean checkToken(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\w\\d]{12}$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }
    
    /**
    * Cheks user's firs, second and middle name's
    * 
    * @param string input user's firs, second and middle name's
    * @return <code>true</code> if name meet requirements, otherwise <code>false</code>.
    */
    public static synchronized boolean checkName(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\p{L}]+$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }

    /**
    * Cheks animal breed, hangar's, paddock's or vaccination's name
    * 
    * @param string input animal breed, hangar's, paddock's or vaccination's name
    * @return <code>true</code> if name meet requirements, otherwise <code>false</code>.
    */
    public static synchronized boolean checkSpecialName(String string) {
        if (string == null) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[\\p{L}_\\d\\.]+$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
    }
}
