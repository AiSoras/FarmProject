/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import api.ObjectService;
import database.DBService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.User;

import scripts.UUIDGeneration;

/**
 * Handles requests related to objects.
 *
 * @author OlesiaPC
 */
public class ObjectServiceImplementation implements ObjectService {

    /**
     *
     * @param object
     * @return
     */
    @Override
    public String getObjectID(Object object) {
        try {
            Method method = object.getClass().getMethod("getID", null);
            return (String) method.invoke(object, null);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ObjectServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param objectClass
     * @return
     */
    @Override
    public List<Object> getListOfObjects(Class objectClass) {
        return DBService.loadObjects(objectClass);
    }

    /**
     *
     * @param object
     */
    @Override
    public void deleteObject(Object object) {
        DBService.deleteObject(object, getObjectID(object));
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public List<User> getListOfUsersLike(String query) {
        List<User> usersList = DBService.getUserLikeList(query);
        return usersList == null ? new ArrayList<User>() : usersList;
    }

    /**
     *
     * @param object
     */
    @Override
    public void saveObject(Object object) {
        DBService.saveObject(object);
    }

    @Override
    public String getObjectID(char symbol) {
        return UUIDGeneration.create(symbol);
    }

    
}
