/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import api.AccountService;
import database.DBService;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Positions;
import objects.User;
import scripts.EMailSender;
import scripts.TokenGeneration;
import scripts.UUIDGeneration;

/**
 *
 * @author OlesiaPC
 */
public class AccountServiceImplementation implements AccountService {

    @Override
    public Positions signIn(String login, String password) { //return null if login doesn't exist or password is wrong
        Positions userLevel = null;
        try {
            userLevel = DBService.chechByLoginAndPassword(login, password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(AccountServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userLevel;
    }

    @Override
    public boolean signUp(User user) {
        if (DBService.existenceOfLoginAndEMail(user.getLogin(),user.geteMail())) { //Проверка на существование в БД такого логина и/или почты.
            return false;
        } else {
            DBService.saveObject(user);
            return true;
        }
    }

    @Override
    public String createUser(User user) {
        String token = TokenGeneration.create();
        user.setToken(token);
        user.setID(UUIDGeneration.create('U'));
        DBService.saveObject(user);
        return token;
    }

    @Override
    public User getByToken(String token) { //can be null
        User user = DBService.checkByToken(token);
        if (user != null){
            user.setToken("USED");
        }
        return user;
    }

    @Override
    public boolean resetPassword(String eMail) {
        String token = TokenGeneration.create();
        User user = DBService.getByEMail(eMail);
        if (EMailSender.send(user, token)) {
            user.setToken(token);
            DBService.saveObject(user);
            return true;
        } else {
            return false;
        }
    }

}
