/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import api.AccountService;
import api.ObjectService;
import com.caucho.hessian.server.HessianServlet;
import database.DBService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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
public class AccountServiceImplementation implements AccountService { //extends HessianServlet

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AccountService.class.getName());

//    @Override
//    public void service(ServletRequest request, ServletResponse response) throws IOException, ServletException {
//        String seanceID = ((HttpServletRequest) request).getHeader("seanceID");
//        System.out.println(seanceID);
//        super.service(request, response);
//    }

    /**
     *
     * @param login
     * @param password
     * @return
     */
    @Override
    public User signIn(String login, String password) {
        User user = DBService.getByLoginAndPassword(login, Crypt.encryptMD5(password));
        return user;
    }
    
//    public User getCurrentUser(){
//        
//        return null;
//    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public boolean saveAccountChanges(User user) {
        if (DBService.isLoginOrEMailExists(user.getLogin(), user.geteMail())) { //Проверка на существование в БД такого логина и/или почты. 
            return false;
        } else {
            user.setPassword(Crypt.encryptMD5(user.getPassword()));
            if (!user.getToken().equals("USED")) {
                user.setToken("USED");
            }
            DBService.saveObject(user);
            return true;
        }
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public String createUser(User user) {
        ObjectService objectService = new ObjectServiceImplementation();
        String token = TokenGeneration.create();
        user.setToken(token);
        user.setID(objectService.getObjectID('U'));
        objectService.saveObject(user);
        return token;
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public User getByToken(String token) { //can be null
        User user = DBService.getByToken(token);
        return user;
    }

    /**
     *
     * @param eMail
     * @return
     */
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
