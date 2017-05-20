/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author OlesiaPC
 */
@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute<User, String> ID;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> middleName;
    public static volatile SingularAttribute<User, String> secondName;
    public static volatile SingularAttribute<User, String> eMail;
    public static volatile SingularAttribute<User, Positions> levelOfAccess;
    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> token;
}