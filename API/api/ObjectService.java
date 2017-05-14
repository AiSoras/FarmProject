/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

/**
 *
 * @author OlesiaPC
 */
public interface ObjectService {
    
    public void addObject(Object object, char symbol);
    
    public String getObjectID(Object object);
}
