/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import objects.User;

/**
 * Handles requests related with the user account.
 * 
 * @author OlesiaPC
 */
public interface AccountService {

    /**
     * Returns the user, taken from the database by login and password
     *
     * @param login String containing the user's login
     * @param password String containing the user's password
     * @return the user, taken from the database by login and password
     */
    public User signIn(String login, String password);

    /**
     * Registers a user in the system
     *
     * @param user User information entered by the administrator
     * @param login String containing the user's login
     * @param eMail String containing the user's eMail
     * @param password String containing the user's password
     * @return <code>true</code> if the user with <code>login</code> or
     * <code>eMail</code>, and different <code>ID</code> is already in the database, otherwise
     * <code>false</code>.
     */
    public boolean saveAccountChanges(User user, String login, String eMail, String password);
    
    /**
     * Creates a token for the user, saves the user
     * 
     * @param user Contains entered information about the user
     * @return Token of the saved user
     */
    public String createUser(User user);
    
    /**
     * Returns the user found by the token
     * 
     * @param token String containing a token for searching
     * @return the user found by the token
     */
    public User getByToken(String token);
    
    /**
     * Sends a token to restore the password to the user's mail
     * 
     * @param eMail String containing the user's eMail
     * @return <code>true</code> if the user exists and the message was sent successfully, otherwise
     * <code>false</code>.
     */
    public boolean resetPassword(String eMail);
       
}
