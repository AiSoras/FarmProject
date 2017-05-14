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
import java.util.logging.Level;
import java.util.logging.Logger;

import scripts.UUIDGeneration;

/**
 *
 * @author OlesiaPC
 */
public class ObjectServiceImplementation implements ObjectService {

    @Override
    public void addObject(Object object, char symbol) {
        try {
            Method method = object.getClass().getMethod("setID", String.class);
            method.invoke(object, UUIDGeneration.create(symbol));
            DBService.saveObject(object);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ObjectServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

}
