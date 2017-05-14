/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import objects.Positions;
import objects.User;

/**
 *
 * @author OlesiaPC
 */
public interface AccountService {

    public Positions signIn(String login, String password);

    public boolean signUp(User user);
    
    public String createUser(User user);
    
    public User getByToken(String token);
    
    public boolean resetPassword(String eMail);
    
    
}
