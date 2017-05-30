/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import api.AccountService;
import api.ObjectService;
import database.DBService;
import objects.User;
import org.apache.logging.log4j.LogManager;
import scripts.Crypt;
import scripts.EMailSender;
import scripts.TokenGeneration;

/**
 * Handles requests related with the user account.
 *
 * @author OlesiaPC
 */
public class AccountServiceImplementation implements AccountService {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AccountService.class.getName());

    @Override
    public User signIn(String login, String password) {
        User user = DBService.getByLoginAndPassword(login, Crypt.encryptMD5(password));
        return user;
    }
    

    @Override
    public boolean saveAccountChanges(User user, String login, String eMail, String password) {
        if (DBService.isLoginOrEMailExists(login, eMail, user.getID())) { //Проверка на существование в БД такого логина и/или почты. 
            return false;
        } else {
            user.setPassword(Crypt.encryptMD5(password));
            user.setLogin(login);
            user.seteMail(eMail);
            if (!user.getToken().equals("USED")) {
                user.setToken("USED");
            }
            DBService.saveObject(user);
            return true;
        }
    }

    @Override
    public String createUser(User user) {
        ObjectService objectService = new ObjectServiceImplementation();
        String token = TokenGeneration.create();
        user.setToken(token);
        user.setID(objectService.getObjectID('U'));
        objectService.saveObject(user);
        return token;
    }

    @Override
    public User getByToken(String token) { //can be null
        User user = DBService.getByToken(token);
        return user;
    }

    @Override
    public boolean resetPassword(String eMail) {
        String token = TokenGeneration.create();
        User user = DBService.getByEMail(eMail);
        if (user != null && EMailSender.send(user, token)) {
            user.setToken(token);
            DBService.saveObject(user);
            return true;
        } else {
            return false;
        }
    }

}
