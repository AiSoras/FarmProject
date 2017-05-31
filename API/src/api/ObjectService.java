/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.util.List;
import objects.Paddock;
import objects.User;

/**
 *
 * @author OlesiaPC
 */
public interface ObjectService {
    
    public void saveObject(Object object);
    
    public String getObjectID(Object object);
    
    public List<Object> getListOfObjects(Class objectClass);
    
    public void deleteObject(Object object);
    
    public List<User> getListOfUsersLike(String query);
    
    public List<Paddock> getListOfPaddocksLike(String query);
    
    public String getObjectID(char symbol);
    
}
